package daoImpl;

import java.util.ArrayList;

import idao.IUsuari;
import model.Usuari;

public class JPAUsuariImpl implements IUsuari{
	
	public ArrayList<Usuari> getUsuaris() {
		ArrayList<Usuari> usuaris = new ArrayList<Usuari>();
		entityManager.getTransaction().begin();

		int count=1;
		Usuari usuari;
		while((usuari = entityManager.find(Usuari.class, count))!=null) {
			usuaris.add(usuari);
			count++;
		}

		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
		return usuaris;
	}


}
