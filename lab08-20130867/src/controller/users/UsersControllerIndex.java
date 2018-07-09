package controller.users;

import java.io.IOException;
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
	public class UsersControllerIndex extends HttpServlet{
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
				                    if(req.getParameter("edit")!=null||req.getParameter("del")!=null){
				                    	if(req.getParameter("edit")!=null){
				                    		resp.setContentType("text/plain");
				                    		int i=(Integer.parseInt((String) req.getParameter("edit")));
				                    		Long ad=null;
				                    		int o=0;
				                    		for( User p : users ) {
					                    		if(i==o){ad=Long.parseLong(p.getId());}
					                    		o++;}
				                    		q.setFilter("id == ad");
				                        	q.declareParameters("Long ad");
				                        	List<User> results = (List<User>) q.execute(ad);
				                        	req.setAttribute("users", results);
				                        	RequestDispatcher rd = req.getRequestDispatcher("/users/edit");
				                        	rd.forward(req, resp);
				                    		//resp.getWriter().println(i);
				                    	}else if(req.getParameter("del")!=null){
				                    	resp.setContentType("text/plain");
				                    	int e=(Integer.parseInt((String) req.getParameter("del")));
				                    	req.setAttribute("e", e);
			                        	RequestDispatcher rd = req.getRequestDispatcher("/users/delete");
			                        	rd.forward(req, resp);
				                    	}
									}else{
				                    req.setAttribute("users", users);
									RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Users/index.jsp");
									rd.forward(req, resp);}
				                    
								}catch(Exception e){
									resp.setContentType("text/plain");
									resp.getWriter().println("holaaaa");
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

	