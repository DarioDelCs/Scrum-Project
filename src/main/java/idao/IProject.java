package idao;

import java.util.ArrayList;

import model.Project;
import model.Usuari;


public interface IProject {
		public ArrayList<Project> getProjects();
		public void addProject(String pNameProject, String pDescripcion, int pScrumMaster, int pProductOwner);
		public ArrayList<Usuari>  getUsers(String tipeUser);
	}
