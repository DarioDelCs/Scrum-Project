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
	@Column(name = "LOGIND_ID")
	private String pLoginId;
	@Column(name = "PASSWORD")
	private String pPass;
	@Column(name = "USERGROUP")
	private String pUserGroup;
	@Column(name = "PROFILENAME")
	private UserType pProfile;
	@Column(name = "EMAIL")
	private String pEmail;

	public Usuari(int id, String name, String pass, String userGroup) {
		this.pID = id;
		this.pName = name;
		this.pPass = pass;
		this.pUserGroup = userGroup;
	}

	public Usuari(int pID, String pName, String pLoginId, String pPass, String pUserGroup, String pProfileId,
			String pEmail) {
		super();
		this.pID = pID;
		this.pName = pName;
		this.pLoginId = pLoginId;
		this.pPass = pPass;
		this.pUserGroup = pUserGroup;
		if (pProfileId.equalsIgnoreCase(UserType.AdministradorUsers.toString())) {
			this.pProfile = UserType.AdministradorUsers;
		} else if (pProfileId.equalsIgnoreCase(UserType.Developer.toString())) {
			this.pProfile = UserType.Developer;
		} else if (pProfileId.equalsIgnoreCase(UserType.ScrumMaster.toString())) {
			this.pProfile = UserType.ScrumMaster;
		} else {
			this.pProfile = UserType.ProductOwner;
		}
		this.pEmail = pEmail;
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
