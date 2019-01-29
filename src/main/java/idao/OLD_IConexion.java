package idao;

import model.Project;
import model.Usuari;

public interface OLD_IConexion {
	public Project getProject(int number);
	public void addProject(String pNameProject, String pDescripcion, int pScrumMaster, int pProductOwner);
	public Usuari getUsuari(String login, String password);
	public void addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail);
	public void resultados();
//	public ArrayList<Usuari> getUsuaris(String loginName);
}
