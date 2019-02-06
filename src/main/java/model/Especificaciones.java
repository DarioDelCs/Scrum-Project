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
	@Column(name = "MARCADA")
	private int pMarcada;
	@Column(name = "DESCRIPCION")
	private String pDescripcion;
	@Column(name = "HORAS")
	private double pHoras;
	@Column(name = "IDPROYECTO")
	private int pIdProject;
	@Column(name = "SPRINT")
	private int pSprint;
	
	public Especificaciones() {}
	
	public Especificaciones(int marcada, String Descripcion, double Horas, int IdProject, int Sprint) {
		this.pMarcada = marcada;
		this.pDescripcion = Descripcion;
		this.pHoras = Horas;
		this.pIdProject = IdProject;
		this.pSprint = Sprint;
	}

	public int getID() {
		return pID;
	}
	
	public int getMarcada() {
		return pMarcada;
	}

	public void setName(int marcada) {
		this.pMarcada = marcada;
	}

	public String getDescripcion() {
		return pDescripcion;
	}

	public void setDescripcion(String pDescripcion) {
		this.pDescripcion = pDescripcion;
	}

	public double getHoras() {
		return pHoras;
	}

	public void setHoras(double horas) {
		this.pHoras = horas;
	}

	public int getIdProject() {
		return pIdProject;
	}

	public void setIdProject(int pIdProject) {
		this.pIdProject = pIdProject;
	}

	public int getSprint() {
		return pSprint;
	}

	public void setSprint(int pSprint) {
		this.pSprint = pSprint;
	}
}
