package idao;

import model.Project;


public interface IProject {
	// Métodos de la Interfaz:
	public int countProjects();
	public Project getProject(int number);
	public void addProject(String pNameProject, String pDescripcion, int pScrumMaster, int pProductOwner);
//	public ArrayList<Usuari>  getUsers(String tipeUser);
}
