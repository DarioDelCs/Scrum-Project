package daoImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import idao.IUsuari;
import model.Usuari;

public class JPAUsuariImpl implements IUsuari{
	
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

		entityManager.close();
		factory.close();
		return usuaris;
	}

	public void addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Usuari newUser = new Usuari(pEmail, pLoginId, pName, pPass, pProfileId);

		entityManager.merge(newUser);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}



}
