package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String email;
	
	@Persistent
	private String name;
	
	@Persistent
	private Long role;
	
	@Persistent
	private String birth;
	
	@Persistent
	private boolean gender;
	
	@Persistent
	private String created;
	
	@Persistent
	private boolean status;
	
	

	public User(String email, Long role, String birth, boolean gender, String created) {
		super();
		this.email = email;
		this.role=role;
		this.birth=birth;
		this.gender=gender;
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

	public String getEmail() {return this.email;}

	public void setEmail(String email) {this.email = email;}
	
	public Long getRole(){return role;}
	
	public String getBirth() {return this.birth;}
	
	public void setBirth(String birth) {this.birth = birth;}
	
	public void setLong(Long role){this.role=role;}
	
	public boolean getGender(){return gender;}
	
	public void setGnder(boolean gender){this.gender=gender;}

	public boolean getStatus() {return this.status;}

	public void setStatus(boolean status) {this.status = status;}

	public String getCreated() {return this.created;}

	public void setCreated(String created) {this.created = created;}
	

}
