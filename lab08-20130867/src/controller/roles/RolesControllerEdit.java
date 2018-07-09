package controller.roles;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Resource;
import model.entity.Role;
import model.entity.User;

	@SuppressWarnings("serial")
	public class RolesControllerEdit extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
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
									try{
									List<Role> roles = (List<Role>) r.execute();
				                    if(req.getParameter("modify")!=null){
				                    	resp.setContentType("text/plain");
				                    	boolean noRepetido=true;
				                    	for( Role p : roles  ) {
								    		if(p.getName().equals(req.getParameter("name"))){
								    			noRepetido=false;}}
										if(noRepetido){	
				                    	//Adquiriendo datos del formulario
				                    	String qSelect= "select from "+Role.class.getName()+" where id=="+Long.parseLong(req.getParameter("id"));
				            			List<Role>results=(List<Role>)pm.newQuery(qSelect).execute();
			                    		String name = req.getParameter("name");
			    						boolean status=false;
			    						if(req.getParameter("status")!=null){status=true;}
			    						for(Role p: results){
			                    			p.setName(name);
			                    			p.setStatus(status);
			                    		}
			                        	resp.sendRedirect("/roles");
			                    		//resp.getWriter().println(i);
										}else{
											resp.setContentType("text/plain");
											resp.getWriter().println("Ya existe esta rol");
											RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Error/errorExistRol.jsp");
											rd.forward(req, resp);
										}
				                    }else{
										resp.setContentType("text/plain");
										List<Role> results = (List<Role>) req.getAttribute("role");
			                        	req.setAttribute("roles", results);
			                        	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Roles/edit.jsp");
			                        	rd.forward(req, resp);}
				                    
								}catch(Exception e){
									resp.setContentType("text/plain");
									RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Error/errorEdit.jsp");
									rd.forward(req, resp);
							    	System.out.println(e);
								}finally{
									r.closeAll();
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

	