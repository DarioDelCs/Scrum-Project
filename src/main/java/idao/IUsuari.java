package idao;

import model.Usuari;

public interface IUsuari {	
	// M�todos de la Interfaz:
	public Usuari getUsuari(String login);
	public boolean existUser(String login);
	public String getUsuariFromId(int user_id);
	public boolean addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail);
	public String[] getUersProfilename(String profilename);
}
