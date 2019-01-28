package idao;

import model.Usuari;

public interface OLD_IUsuari {	
	// Métodos de la Interfaz:
	public int countUsers();
	public Usuari getUsuari(int number);
	public void addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail);
//	public ArrayList<Usuari> getUsuaris(String loginName);
}
