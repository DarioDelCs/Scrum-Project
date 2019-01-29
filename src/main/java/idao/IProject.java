package idao;

import model.Project;


public interface IProject {
	// Métodos de la Interfaz:
	public boolean existProject(String nombreProject);
	public boolean addProject(String pNameProject, String pDescripcion, String pScrumMaster, String pProductOwner);
}
