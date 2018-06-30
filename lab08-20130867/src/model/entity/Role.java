package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Role {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String name;
	
	@Persistent
	private boolean status;
	
	@Persistent
	private String created;
	
	

	public Role(String name, String created) {
		super();
		this.name = name;
		this.status=true;
		this.created = created;
	}
	
	public String getId() {
		return Long.toString(id);
	}

	public void setId(String id) {
		Long clave =Long.parseLong(id);
		this.id = clave;
	}

	public String getName() {return this.name;}

	public void setName(String name) {this.name = name;}

	public boolean getStatus() {return this.status;}

	public void setStatus(boolean status) {this.status = status;}

	public String getCreated() {return this.created;}

	public void setCreated(String created) {this.created = created;}
	

}
