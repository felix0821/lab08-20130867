package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Resource {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String name;
	
	@Persistent
	private String created;
	
	@Persistent
	private boolean status;
	
	

	public Resource(String name, String created) {
		super();
		this.name = name;
		this.created = created;
		this.status=true;
		
	}
	
	public String getId() {
		return Long.toString(id);
	}

	public void setId(String idPersona) {
		Long clave =Long.parseLong(idPersona);
		this.id = clave;
	}

	public String getName() {return this.name;}

	public void setName(String name) {this.name = name;}

	public boolean getStatus() {return this.status;}

	public void setStatus(boolean status) {this.status = status;}

	public String getCreated() {return this.created;}

	public void setCreated(String created) {this.created = created;}
	

}
