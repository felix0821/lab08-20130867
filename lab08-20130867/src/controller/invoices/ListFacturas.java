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
	public class ListFacturas extends HttpServlet{
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
					req.setAttribute("facturas", factura);
                    if(req.getParameter("id")!=null){
                    	resp.setContentType("text/plain");
                    	Long ad=Long.parseLong((String) req.getParameter("id"));
                    	q.setFilter("id == ad");
                    	q.declareParameters("Long ad");
                    	List<Factura> results = (List<Factura>) q.execute(ad);
                    	req.setAttribute("fact", results);
                    	RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Invoices/listFacturas2.jsp");
    					rd.forward(req, resp);
                    	/*for (Factura p : results) {
                    		resp.getWriter().println(p.getDireccion());
                    	    }*/
					}
                    else{
					RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Invoices/listFacturas.jsp");
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

	