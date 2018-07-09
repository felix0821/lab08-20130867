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
	private int birthD;
	
	@Persistent
	private String birthM;
	
	@Persistent
	private int birthY;
	
	@Persistent
	private boolean gender;
	
	@Persistent
	private String created;
	
	@Persistent
	private boolean status;
	
	

	public User(String email, Long role, int birthD, String birthM, int birthY, boolean gender, String created) {
		super();
		this.email = email;
		this.role=role;
		this.birthD=birthD;
		this.birthM=birthM;
		this.birthY=birthY;
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
	public void setRole(Long role){this.role=role;}
	
	public int getBirthD(){return this.birthD;}
	public void setBirthD(int birthD) {this.birthD = birthD;}
	
	public String getBirthM() {return this.birthM;}
	public void setBirthM(String birthM) {this.birthM = birthM;}
	
	public int getBirthY(){return this.birthY;}
	public void setBirthY(int birthY) {this.birthY = birthY;}
	
	public String getBirth() {return birthD+"/"+birthM+"/"+birthY;}
	
	public boolean getGender(){return gender;}
	public void setGender(boolean gender){this.gender=gender;}

	public boolean getStatus() {return this.status;}
	public void setStatus(boolean status) {this.status = status;}

	public String getCreated() {return this.created;}
	public void setCreated(String created) {this.created = created;}
	

}
