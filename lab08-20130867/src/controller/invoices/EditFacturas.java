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
import model.entity.Factura;

	@SuppressWarnings("serial")
	public class EditFacturas extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			final PersistenceManager pm = PMF.get().getPersistenceManager();
			final Query q = pm.newQuery(Factura.class);
				//q.setOrdering("id ascending");
				//q.setOrdering("id descending");
				//q.setRange(0, 10);
				try{
					@SuppressWarnings("unchecked")
					List<Factura> factura = (List<Factura>) q.execute();
					
                    if(req.getParameter("edit")!=null||req.getParameter("del")!=null){
                    	if(req.getParameter("edit")!=null){
                    		req.setAttribute("facturas", factura);
                    		resp.setContentType("text/plain");
                    		String s=req.getParameter("edit");
                    		int i=(Integer.parseInt((String)s.substring(5)));
                    		Long ad=Long.parseLong((String) req.getParameter("id"+i));
                    		q.setFilter("id == ad");
                        	q.declareParameters("Long ad");
                        	List<Factura> results = (List<Factura>) q.execute(ad);
                        	req.setAttribute("fact", results);
                        	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Invoices/editFacturas2.jsp");
                        	rd.forward(req, resp);
                    		//resp.getWriter().println(i);
                    	}
                    	else if(req.getParameter("del")!=null){
                    	resp.setContentType("text/plain");
                    	String s=req.getParameter("del");
                    	int i=(Integer.parseInt((String)s.substring(4)));
                    	Long ad=Long.parseLong((String) req.getParameter("id"+i));
                    	q.setFilter("id == ad");
                    	q.declareParameters("Long ad");
                    	//List<Factura> results = (List<Factura>) q.execute(ad);
                    	q.deletePersistentAll(ad);
                    	resp.sendRedirect("editFacturas");
    					
                    	/*try{
    						q.deletePersistentAll(results);
    						resp.getWriter().println("Se han borrado facturas.");
    						resp.sendRedirect("/WEB-INF/views/editFacturas.jsp");
    					}catch(Exception e){
    							System.out.println(e);
    							resp.getWriter().println("No se han podido borrar datos.");
    							resp.sendRedirect("/index.html");
    					}finally{
    						q.closeAll();
    						pm.close();
    					}*/
    					
                    	}
					}
                    else{
                    req.setAttribute("facturas", factura);
					RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Invoices/editFacturas.jsp");
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

	