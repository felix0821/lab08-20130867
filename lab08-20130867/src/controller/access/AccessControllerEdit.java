package controller.access;

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
	public class AccessControllerEdit extends HttpServlet{
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
									final Query s = pm.newQuery(Resource.class);
									try{
									List<Role> roles = (List<Role>) r.execute();
									List<Resource> resources = (List<Resource>) s.execute();
				                    if(req.getParameter("modify")!=null){
				                    	resp.setContentType("text/plain");
				                    	//Adquiriendo datos del formulario
				                    	String qSelect= "select from "+Access.class.getName()+" where id=="+Long.parseLong(req.getParameter("id"));
				            			List<Access>results=(List<Access>)pm.newQuery(qSelect).execute();
			    						String rol = req.getParameter("rol");
			    						String resourc = req.getParameter("resourc");
			    						Long ad=null;
			    				    	for( Role p : roles ) {if(rol.equals(p.getName())){ad=Long.parseLong(p.getId());}}
			    				    	Long role = ad;
			    				    	ad=null;
			    				    	for( Resource p : resources ) {if(resourc.equals(p.getName())){ad=Long.parseLong(p.getId());}}
			    				    	Long resource = ad;
			    						boolean status=false;
			    						if(req.getParameter("status")!=null){status=true;}
			    						for(Access p: results){
			                    			p.setResource(resource);
			                    			p.setRole(role);
			                    			p.setStatus(status);
			                    		}
			    						resp.sendRedirect("/access");
			                    		//resp.getWriter().println(i);
				                    }else{
										resp.setContentType("text/plain");
										List<Access> results = (List<Access>) req.getAttribute("access");
			                        	req.setAttribute("access", results);
			                        	req.setAttribute("roles", roles);
			                        	req.setAttribute("resources", resources);
			                        	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Access/edit.jsp");
			                        	rd.forward(req, resp);}
				                    
								}catch(Exception e){
									resp.setContentType("text/plain");
									RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Error/errorEdit.jsp");
									rd.forward(req, resp);
							    	System.out.println(e);
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

	