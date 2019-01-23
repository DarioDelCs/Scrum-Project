package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class Project {

	@Column(name = "IDPROJECT")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pID;
	@Column(name = "NOMBRE")
	private String pName;
	@Column(name = "DESCRIPCION")
	private String pDescripcion;
	@Column(name = "SCRUMMASTER")
	private int pScrumMaster;
	@Column(name = "PRODUCTOWNER")
	private int pProductOwner;
	
	public Project() {}
	
	public Project(int ID, String Name, String Descripcion, int ScrumMaster, int ProductOwner) {
		this.pName = Name;
		this.pDescripcion = Descripcion;
		this.pScrumMaster = ScrumMaster;
		this.pProductOwner = ProductOwner;
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

	public String getpDescripcion() {
		return pDescripcion;
	}

	public void setpDescripcion(String pDescripcion) {
		this.pDescripcion = pDescripcion;
	}

	public int getpScrumMaster() {
		return pScrumMaster;
	}

	public void setpScrumMaster(int pScrumMaster) {
		this.pScrumMaster = pScrumMaster;
	}

	public int getpProductOwner() {
		return pProductOwner;
	}

	public void setpProductOwner(int pProductOwner) {
		this.pProductOwner = pProductOwner;
	}
}
