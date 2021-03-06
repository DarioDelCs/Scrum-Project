package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import main.Main;
import main.Test;

@Entity
@Table(name = "USERS")
public class Usuari {

	@Column(name = "USER_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pID;
	@Column(name = "NOMBRE")
	private String pName;
	@Column(name = "LOGIN_ID")
	private String pLoginId;
	@Column(name = "PASSWORD")
	private String pPass;
	@Column(name = "USERGROUP")
	private String pUserGroup;
	@Column(name = "PROFILENAME")
	private String pProfile;
	@Column(name = "EMAIL")
	private String pEmail;

	public Usuari() {};
	
	public Usuari(String pEmail, String pLoginId, String pName, String pPass, String pProfileId) {
		this.pName = pName;
		this.pLoginId = pLoginId;
		this.pPass = pPass;
		if (pProfileId.equalsIgnoreCase(Main.hmUser.get(UserType.AdministradorUsers).toString())) {
			this.pProfile = Main.hmUser.get(UserType.AdministradorUsers);
		} else if (pProfileId.equalsIgnoreCase(Main.hmUser.get(UserType.Developer).toString())) {
			this.pProfile = Main.hmUser.get(UserType.Developer);
		} else if (pProfileId.equalsIgnoreCase(Main.hmUser.get(UserType.ScrumMaster).toString())) {
			this.pProfile = Main.hmUser.get(UserType.ScrumMaster);
		} else if (pProfileId.equalsIgnoreCase(Main.hmUser.get(UserType.ProductOwner).toString())) {
			this.pProfile = Main.hmUser.get(UserType.ProductOwner);
		}
		this.pEmail = pEmail;
	}
	public Usuari(String pEmail, String pLoginId, String pName, String pPass, String pProfileId,boolean NoUsarFuncion) {
		this.pName = pName;
		this.pLoginId = pLoginId;
		this.pPass = pPass;
		this.pProfile = pProfileId;
		this.pEmail = pEmail;
	}
	
	public int getpID() {
		return pID;
	}
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	
	public String getpLoginId() {
		return pLoginId;
	}
	public void setpLoginId(String pLoginId) {
		this.pLoginId = pLoginId;
	}
	
	public String getpPass() {
		return pPass;
	}
	public void setpPass(String pPass) {
		this.pPass = pPass;
	}
	
	public String getpUserGroup() {
		return pUserGroup;
	}
	public void setpUserGroup(String pUserGroup) {
		this.pUserGroup = pUserGroup;
	}
	
	public String getpProfile() {
		return pProfile;
	}
	public void setpProfile(String pProfile) {
		this.pProfile = pProfile;
	}
	
	public String getpEmail() {
		return pEmail;
	}
	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}
}
