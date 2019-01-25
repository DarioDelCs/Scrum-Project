package idao;

import java.util.ArrayList;

import model.Usuari;

public interface IUsuari {	
	// Métodos de la Interfaz:
	public ArrayList<Usuari> getUsuaris();
	public void addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail);
	public ArrayList<Usuari>  getUsuaris(String loginName);
}
