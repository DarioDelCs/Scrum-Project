package daoImpl;

import idao.IConexion;
import model.Project;
import model.Usuari;

public class SQLiteImpl implements IConexion{

	public Project getProject(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addProject(String pNameProject, String pDescripcion, int pScrumMaster, int pProductOwner) {
		// TODO Auto-generated method stub
		
	}

	public Usuari getUsuari(String nombre, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail) {
		// TODO Auto-generated method stub
		
	}

	public void resultados() {
		// TODO Auto-generated method stub
		System.out.println("Conectado a embebida");
	}

}
