package controller.invoices;
import model.*;
import model.entity.Factura;

import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.PMF;

@SuppressWarnings("serial")
public class SaveFactura extends HttpServlet {
	private int pedido=0;
	private ArrayList<Double>can=new ArrayList<Double>();
	private ArrayList<Double>uni=new ArrayList<Double>();
	private ArrayList<Double>val=new ArrayList<Double>();
	private ArrayList<String>des=new ArrayList<String>();
	private double total=0;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String nameCL ="Juan-Perez";
		String rucCL="20102040304";
		String direccion = "Av-benavides-1230-Miraflores";
		String fecha = "2018-06-04";
		//LocalDateTime horaPeru=LocalDateTime.now(ZoneId.of("America/Lima"));
		if(pedido>0&&req.getParameter("nameCL")==null||req.getParameter("rucCL")==null){
			pedido=0;
			can.clear();
			uni.clear();
			val.clear();
			des.clear();
			total=0;
		}
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		if(pedido>0&&req.getParameter("enviar")!=null){
		nameCL = req.getParameter("nameCL");
		rucCL = req.getParameter("rucCL");
		direccion = req.getParameter("direccion");
		fecha = req.getParameter("fecha");
		if(pedido==1){
			des.add(req.getParameter("des0"));
			can.add(Double.parseDouble((String) req.getParameter("can0")));
			uni.add(Double.parseDouble((String) req.getParameter("uni0")));
			val.add(can.get(0)*uni.get(0));
			total=total+val.get(0);
		}else{
		for(int i=0;i<pedido;i++){
			if(i<pedido-1){
				des.set(i, req.getParameter("des"+i));
				can.set(i, Double.parseDouble((String) req.getParameter("can"+i)));
				uni.set(i, Double.parseDouble((String) req.getParameter("uni"+i)));
				val.set(i, can.get(i)*uni.get(i));
			}
			if(i==pedido-1){
			des.add(req.getParameter("des"+i));
			can.add(Double.parseDouble((String) req.getParameter("can"+i)));
			uni.add(Double.parseDouble((String) req.getParameter("uni"+i)));
			val.add(can.get(i)*uni.get(i));
			total=total+val.get(i);
			}
		}
		}
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Factura p = new Factura(nameCL, rucCL, fecha, direccion, can, des, uni, val, total);
		try{
			pm.makePersistent(p);
			resp.getWriter().println("Persona grabada correctamente.");
			resp.sendRedirect("/invoices/listFacturas");
		}catch(Exception e){
			System.out.println(e);
			resp.getWriter().println("Ocurrió un error, vuelva a intentarlo.");
			resp.sendRedirect("/invoices/saveFacturas");
		}finally{
			pm.close();
			pedido=0;
		}
		}
		else{
			if(req.getParameter("agr")!=null){
				pedido=pedido+1;
			}
			if(pedido>0&&req.getParameter("nameCL")!=null&&req.getParameter("rucCL")!=null&&
					req.getParameter("direccion")!=null&&req.getParameter("fecha")!=null){
				nameCL = req.getParameter("nameCL");
				rucCL = req.getParameter("rucCL");
				direccion = req.getParameter("direccion");
				fecha = req.getParameter("fecha");
				//........
				total=0;
				for(int i=0;i<pedido-1;i++){
					boolean pedV=(req.getParameter("des"+i)!=null&&req.getParameter("can"+i)!=null&&req.getParameter("uni"+i)!=null);
					if(i<pedido-2&&pedV){
						des.set(i, req.getParameter("des"+i));
						can.set(i, Double.parseDouble((String) req.getParameter("can"+i)));
						uni.set(i, Double.parseDouble((String) req.getParameter("uni"+i)));
						val.set(i, can.get(i)*uni.get(i));
						total=total+val.get(i);
					}
					if(i==pedido-2){
					if(pedV){
					des.add(req.getParameter("des"+i));
					can.add(Double.parseDouble((String) req.getParameter("can"+i)));
					uni.add(Double.parseDouble((String) req.getParameter("uni"+i)));
					val.add(can.get(i)*uni.get(i));
					total=total+val.get(i);
					}
					else pedido=pedido-1;
				    }
				    }
				req.setAttribute("cant", can);
				req.setAttribute("desc", des);
				req.setAttribute("unit", uni);
				req.setAttribute("valo", val);
				req.setAttribute("final", total);
			}
			
				req.setAttribute("nameCL", nameCL);
				req.setAttribute("rucCL", rucCL);
				req.setAttribute("direccion", direccion);
				req.setAttribute("fecha", fecha);
				req.setAttribute("pedido", pedido);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Invoices/saveFactura.jsp");
		    try {
		    	dispatcher.forward(req, resp);
		    }catch(ServletException e) {
		    	e.printStackTrace();
		    }
		}
	}
}