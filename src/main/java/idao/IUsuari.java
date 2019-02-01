package idao;

import model.Usuari;

public interface IUsuari {	
	// Métodos de la Interfaz:
	public Usuari getUsuari(String login);
	public boolean existUser(String login);
	public boolean addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail);
	public String[] getUersProfilename(String profilename);
	public boolean inserUser(String nombre, String loginId, String pass, String profileName, String email);
}
