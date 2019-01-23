package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ESPECIFICACIONES")
public class Especificaciones {

	@Column(name = "IDESPECIFICACION")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pID;
	@Column(name = "NOMBRE")
	private String pName;
	@Column(name = "DESCRIPCION")
	private String pDescripcion;
	@Column(name = "HORAS")
	private double pHoras;
	@Column(name = "IDPROJECT")
	private int pIdProject;
	@Column(name = "NOMBRESPRINT")
	private String pNombreSprint;
	
	public Especificaciones() {}
	
	public Especificaciones(String Name, String Descripcion, double Horas, int IdProject, String NombreSprint) {
		this.pName = Name;
		this.pDescripcion = Descripcion;
		this.pHoras = Horas;
		this.pIdProject = IdProject;
		this.pNombreSprint = NombreSprint;
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

	public double getpHoras() {
		return pHoras;
	}

	public void setpHoras(double pHoras) {
		this.pHoras = pHoras;
	}

	public int getpIdProject() {
		return pIdProject;
	}

	public void setpIdProject(int pIdProject) {
		this.pIdProject = pIdProject;
	}

	public String getpNombreSprint() {
		return pNombreSprint;
	}

	public void setpNombreSprint(String pNombreSprint) {
		this.pNombreSprint = pNombreSprint;
	}
}
