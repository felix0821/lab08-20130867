package controller.access;
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

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;

@SuppressWarnings("serial")
public class AccessControllerAdd extends HttpServlet {
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
						final Query s = pm.newQuery(Resource.class);
						try{
						Long role=null;
						Long resource=null;
						String date;
						resp.setContentType("text/plain");
						resp.setCharacterEncoding("UTF-8");
						//verificamos si el boton esta apretado
						 if(req.getParameter("submit")!=null){
					    String roles = req.getParameter("role");
					    String resources = req.getParameter("resource");
					    //buscamos el rol seleccionado
					    r.setFilter("name == roles");
				        r.declareParameters("String roles");
				        List<Role> results = (List<Role>) r.execute(roles);
				        for( Role p : results ){
				        	role=Long.parseLong(p.getId());
				        }
				        //buscamos el recurso seleccionado
				        s.setFilter("name == resources");
				        s.declareParameters("String resources");
				        List<Resource> results2 = (List<Resource>) s.execute(resources);
				        for( Resource n : results2 ){
				        	resource=Long.parseLong(n.getId());
				        }
						date = fecha();
						final Access a = new Access(role, resource, date);
						try{
							pm.makePersistent(a);
							resp.getWriter().println("Persona grabada correctamente.");
							resp.sendRedirect("/access");
						}catch(Exception e){
							System.out.println(e);
							resp.getWriter().println("Ocurrió un error, vuelva a intentarlo.");
							resp.sendRedirect("/access/add");}
						}else{
							List<Role> roles = (List<Role>) r.execute();
							req.setAttribute("roles", roles);
							List<Resource> resources= (List<Resource>) s.execute();
							req.setAttribute("resources", resources);
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/add.jsp");
						    dispatcher.forward(req, resp);}
						 
						}catch(Exception e){
							e.printStackTrace();
						}finally{
							r.closeAll();
							s.closeAll();
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