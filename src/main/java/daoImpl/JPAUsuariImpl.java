package daoImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import idao.IUsuari;
import model.Usuari;

public class JPAUsuariImpl implements IUsuari{
	
	public ArrayList<Usuari> getUsuaris() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		ArrayList<Usuari> usuaris = new ArrayList<Usuari>();
		String sql =  "SELECT u from Usuaris s";
		Query query = entityManager.createQuery(sql);
		usuaris = (ArrayList<Usuari>) query.getResultList();

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
