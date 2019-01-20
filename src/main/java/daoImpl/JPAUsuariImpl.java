package daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import idao.IUsuari;
import model.Usuari;

public class JPAUsuariImpl implements IUsuari{

	public boolean isConnected() {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
			EntityManager entityManager = factory.createEntityManager();
			entityManager.close();
			factory.close();
			return true;
		} catch (Exception ex) {
	    	System.out.println("Error: "+ex.getMessage());
	    	return false;
	    }
	}
	
	public ArrayList<Usuari> getUsuaris() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
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
