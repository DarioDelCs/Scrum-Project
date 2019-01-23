package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GRUPO")
public class Grupo {

	@Column(name = "USERGROUPID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pID;
	@Column(name = "IDPROJECT")
	private int pIdProject;
	
	public Grupo() {}
	
	public Grupo(int idProject) {
		this.pIdProject = idProject;
	}

	public int getpID() {
		return pID;
	}

	public int getpIdProject() {
		return pIdProject;
	}

	public void setpIdProject(int pIdProject) {
		this.pIdProject = pIdProject;
	}

}
