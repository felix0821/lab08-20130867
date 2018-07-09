package controller.users;

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
	public class UsersControllerDelete extends HttpServlet{
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
									final Query q = pm.newQuery(User.class);
									try{
									List<User> users = (List<User>) q.execute();
		                        	Long ad=null;
			                    	int e=(int) req.getAttribute("e");
			                    	int o=0;
			                    	for( User p : users ) {
			                    		if(e==o){
			                    			ad=Long.parseLong(p.getId());
			                    		}
			                    		o++;
			                    	}
			                    	q.setFilter("id == ad");
			                    	q.declareParameters("Long ad");
			                    	q.deletePersistentAll(ad);
			                    	resp.sendRedirect("/users");
				                    
								}catch(Exception e){
									resp.setContentType("text/plain");
									RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Error/errorEdit.jsp");
									rd.forward(req, resp);
							    	System.out.println(e);
								}finally{
									q.closeAll();
								}
				                //fin de la ejecucion
				}}}}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					pm.close();}
						
		}
	}

	