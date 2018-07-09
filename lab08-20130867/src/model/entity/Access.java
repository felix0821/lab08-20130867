package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Access {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private Long role;
	
	@Persistent
	private Long resource;
	
	@Persistent
	private String created;
	
	@Persistent
	private boolean status;
	
	

	public Access(Long role, Long resource, String created) {
		super();
		this.role=role;
		this.resource=resource;
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
	
	public Long getRole(){return role;}
	
	public void setRole(Long role){this.role=role;}
	
    public Long getResource(){return resource;}
	
	public void setResource(Long resource){this.resource=resource;}

	public boolean getStatus() {return this.status;}

	public void setStatus(boolean status) {this.status = status;}

	public String getCreated() {return this.created;}

	public void setCreated(String created) {this.created = created;}
	

}
