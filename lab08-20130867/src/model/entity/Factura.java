package model.entity;

import javax.jdo.annotations.IdentityType;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Factura{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private int numFactura=0;
	
	@Persistent
	private String nameCL;
	
	@Persistent
	private String rucCL;
	
	@Persistent
	private String fecha;
	
	@Persistent
	private String direccion;
	
	@Persistent
	private ArrayList<Double>can;
	
	@Persistent
	private ArrayList<String>des;
	
	@Persistent
	private ArrayList<Double>uni;
	
	@Persistent
	private ArrayList<Double>val;
	
	@Persistent
	private double total;

	public Factura(String nameCL, String rucCL, String fecha, String direccion, ArrayList<Double>can, 
			ArrayList<String>des, ArrayList<Double>uni, ArrayList<Double>val, double total) {
		super();
		this.numFactura=numFactura+1;
		this.nameCL = nameCL;
		this.rucCL = rucCL;
		this.fecha = fecha;
		this.direccion = direccion;
		this.can=can;
		this.des=des;
		this.val=val;
		this.uni=uni;
		this.total=total;
	}
	

	public String getId() {
		return Long.toString(id);
	}

	public void setId(String idPersona) {
		Long clave =Long.parseLong(idPersona);
		this.id = clave;
	}
	
	public int getNumF(){return numFactura;}

	public String getNameCL() {return this.nameCL;}

	public void setNameCL(String nameCL) {this.nameCL = nameCL;}

	public String getRucCL() {return this.rucCL;}

	public void setRucCL(String rucCL) {this.rucCL = rucCL;}

	public String getFecha() {return this.fecha;}

	public void setFecha(String fecha) {this.fecha = fecha;}

	public String getDireccion() {return this.direccion;}

	public void setDireccion(String direccion) {this.direccion = direccion;}
	
	public ArrayList<String> getDes(){return this.des;}
	public ArrayList<Double> getCan(){return this.can;}
	public ArrayList<Double> getUni(){return this.uni;}
	public ArrayList<Double> getVal(){return this.val;}
	public Double getTotal(){return this.total;}
	

}