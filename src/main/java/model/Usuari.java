package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Usuari {
	
	@Column(name = "USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private int pID;
	@Column(name = "NOMBRE")
		private String pName;
	@Column(name = "PASSWORD")
		private String pPass;
	@Column(name = "USERGROUP")
		private String pUserGroup;
	
	public Usuari(int id, String name, String pass, String userGroup) {
		this.pID = id;
		this.pName = name;
		this.pPass = pass;
		this.pUserGroup = userGroup;
	}
	
	public void setId(int id) {
		this.pID = id;
	}
	
	public int getId() {
		return this.pID;
	}
	
	public void setName(String name) {
		this.pName = name;
	}
	
	public String getName() {
		return this.pName;
	}
	
	public void setLogin(String name) {
		this.pName = name;
	}
	
	public String getLogin() {
		return this.pName;
	}
	
	public void setPass(String pass) {
		this.pPass = pass;
	}
	
	public String getPass() {
		return this.pPass;
	}
	
	public void setUserGroup(String userGroup) {
		this.pUserGroup = userGroup;
	}
	
	public String getUserGroup() {
		return this.pUserGroup;
	}

}
