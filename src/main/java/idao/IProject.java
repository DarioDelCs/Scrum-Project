package idao;

import model.Project;


public interface IProject {
	// M�todos de la Interfaz:
	public boolean existProject(String nombreProject);
	public boolean addProject(String pNameProject, String pDescripcion, String pScrumMaster, String pProductOwner);
}
