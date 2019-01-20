package idao;

import java.util.ArrayList;

import model.Usuari;

public interface IUsuari {	
	// Métodos de la Interfaz:
	public ArrayList<Usuari> getUsuaris();
	public boolean isConnected();
}
