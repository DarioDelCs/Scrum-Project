package idao;

import model.Usuari;

public interface IUsuari {	
	// Métodos de la Interfaz:
	public Usuari getUsuari(String login, String password);
	public Usuari getUsuari(String name);
	public boolean existUser(String login);
	public boolean addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail);
	public String[] getUersProfilename(String profilename);
}
