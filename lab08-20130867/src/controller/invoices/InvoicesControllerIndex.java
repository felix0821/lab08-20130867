package controller.invoices;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.Access;
import model.entity.Resource;
import model.entity.Role;
import model.entity.User;

	@SuppressWarnings("serial")
	public class InvoicesControllerIndex extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			final PersistenceManager pm = PMF.get().getPersistenceManager();
			final Query q = pm.newQuery(Access.class);
				//q.setOrdering("id ascending");
				//q.setOrdering("id descending");
				//q.setRange(0, 10);
				try{
					@SuppressWarnings("unchecked")
					List<Access> access = (List<Access>) q.execute();
                    if(req.getParameter("edit")!=null||req.getParameter("del")!=null){
                    	if(req.getParameter("edit")!=null){
                    		req.setAttribute("facturas", access);
                    		resp.setContentType("text/plain");
                    		String s=req.getParameter("edit");
                    		int i=(Integer.parseInt((String)s.substring(5)));
                    		Long ad=Long.parseLong((String) req.getParameter("id"+i));
                    		q.setFilter("id == ad");
                        	q.declareParameters("Long ad");
                        	List<Resource> results = (List<Resource>) q.execute(ad);
                        	req.setAttribute("fact", results);
                        	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/Facturas/editFacturas2.jsp");
                        	rd.forward(req, resp);
                    		//resp.getWriter().println(i);
                    	}
                    	else if(req.getParameter("del")!=null){
                    	resp.setContentType("text/plain");
                    	String s=req.getParameter("del");
                    	Long ad=null;
                    	int e=(Integer.parseInt((String)s.substring(4)));
                    	int o=0;
                    	for( Access p : access ) {
                    		if(e==o){
                    			ad=Long.parseLong(p.getId());
                    		}
                    		o++;
                    	}
                    	q.setFilter("id == ad");
                    	q.declareParameters("Long ad");
                    	q.deletePersistentAll(ad);
                    	resp.sendRedirect("resources");
                    	}
					}
                    else{
                    req.setAttribute("access", access);
					RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Access/index.jsp");
					rd.forward(req, resp);
                    }
				
				}catch(Exception e){
					System.out.println(e);
				}finally{
					q.closeAll();
					pm.close();
				}
						
		}
	}

	