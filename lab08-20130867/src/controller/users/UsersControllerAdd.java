package controller.users;
import model.*;
import model.entity.Access;
import model.entity.Resource;
import model.entity.Role;
import model.entity.User;

import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.*;

import controller.PMF;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User count = us.getCurrentUser();
		if(count==null){
			RequestDispatcher dp= req.getRequestDispatcher("/WEB-INF/Views/Error/error1.jsp");
			dp.forward(req, resp);
		}else{
			String query= "select from "+User.class.getName()+" where email=='"+count.getEmail()
			+"'"+" && status==true";
			List<User>cSearch=(List<User>)pm.newQuery(query).execute();
			if(cSearch.isEmpty()){
				RequestDispatcher dp= req.getRequestDispatcher("/WEB-INF/Views/Error/error2.jsp");
				dp.forward(req, resp);
			}else{
				System.out.println(req.getServletPath());
				String query2="select from "+Resource.class.getName()+" where name=='"+req.getServletPath()+
				"'"+" && status==true";
				List<Resource>rSearch=(List<Resource>)pm.newQuery(query2).execute();
				if(rSearch.isEmpty()){
					RequestDispatcher dp= req.getRequestDispatcher("/WEB-INF/Views/Error/error3.jsp");
					dp.forward(req, resp);
				}else{
					String query3="select from "+Access.class.getName()+
					" where role=="+cSearch.get(0).getRole()+
					" && resource=="+Long.parseLong(rSearch.get(0).getId())+
				    " && status==true";
					List<Access>aSearch=(List<Access>)pm.newQuery(query3).execute();
					if(aSearch.isEmpty()){
						RequestDispatcher dp= req.getRequestDispatcher("/WEB-INF/Views/Error/error4.jsp");
						dp.forward(req, resp);
					}else{
						//Ejecucion de la pagina
						final Query r = pm.newQuery(Role.class);
						final Query u = pm.newQuery(User.class);
						try{
						List<Role> roles = (List<Role>) r.execute();
						resp.setContentType("text/plain");
						resp.setCharacterEncoding("UTF-8");
						//verificamos si el boton esta apretado
						 if(req.getParameter("submit")!=null&&req.getParameter("email")!=null){
					    //Verificamos que el correo sea unico
						boolean noRepetido=true;
						List<User> users = (List<User>) u.execute();
						for( User p : users ) {
				    		if(p.getEmail().equals(req.getParameter("email"))){
				    			noRepetido=false;}}
						if(noRepetido){
					    String email = req.getParameter("email");
						String rol = req.getParameter("rol");
						Long ad=null;
				    	for( Role p : roles ) {
				    		if(rol.equals(p.getName())){
				    			ad=Long.parseLong(p.getId());}}
				    	r.setFilter("id == ad");
				    	r.declareParameters("Long ad");
				    	Long role = ad;
				    	int birthD = Integer.parseInt(req.getParameter("selectDay"));
				    	String birthM = req.getParameter("selectMonth");
						int birthY = Integer.parseInt(req.getParameter("selectYear"));
						String date = fecha();
						boolean gender=false;
						if(req.getParameter("gender")!=null){gender=true;}
						final User p = new User(email, role, birthD, birthM, birthY, gender, date);
						try{
							pm.makePersistent(p);
							resp.getWriter().println("Persona grabada correctamente.");
							resp.sendRedirect("/users");}
						catch(Exception e){
							System.out.println(e);
							resp.getWriter().println("Ocurrió un error, vuelva a intentarlo.");
							resp.sendRedirect("/user/add");}}
						else{
							resp.setContentType("text/plain");
							resp.getWriter().println("Ya existe esta cuenta");
							RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Error/errorExist.jsp");
							rd.forward(req, resp);
						}
						
						}else{
							if(req.getParameter("email")!=null){
								String email = req.getParameter("email");
								req.setAttribute("email", email);}
							
							req.setAttribute("roles", roles);
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/add.jsp");
						    dispatcher.forward(req, resp);}
						 
					    }catch(Exception e){
					    	System.out.println(e);
						}finally{
							r.closeAll();
							u.closeAll();
						}
						//fin de la ejecucion
		}}}}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			pm.close();}
		
	}
	
	public String fecha(){
		String date;
		Calendar calendario = Calendar.getInstance();
		calendario = new GregorianCalendar();
		int hora, minutos, segundos, mes, dia, año;
	    hora =calendario.get(Calendar.HOUR_OF_DAY);
	    minutos = calendario.get(Calendar.MINUTE);
	    segundos = calendario.get(Calendar.SECOND);
	    mes=calendario.get(Calendar.MONTH)+1;
	    año=calendario.get(Calendar.YEAR);
	    dia=calendario.get(Calendar.DAY_OF_MONTH);
	    date = hora + ":" + minutos + ":" + segundos+"-"+dia+"/"+mes+"/"+año;
	    return date;
	}
}