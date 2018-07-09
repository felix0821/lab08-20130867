package controller.invoices;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.Factura;

@SuppressWarnings("serial")
public class RemoveFactura extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/plain");		
		final PersistenceManager pm = PMF.get().getPersistenceManager();

		final Query q = pm.newQuery(Factura.class);
			try{
				q.deletePersistentAll();
				resp.getWriter().println("Se han borrado facturas.");
				resp.sendRedirect("/users/index");
			}catch(Exception e){
					System.out.println(e);
					resp.getWriter().println("No se han podido borrar datos.");
					resp.sendRedirect("/users/index");
			}finally{
				q.closeAll();
				pm.close();
			}				
	}
}