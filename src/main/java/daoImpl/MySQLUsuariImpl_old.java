package daoImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import idao.OLD_IUsuari;
import model.Usuari;

public class MySQLUsuariImpl_old implements OLD_IUsuari{
	
	public int countUsers() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT COUNT(*) FROM Usuari";
		Query query = entityManager.createQuery(sql);
		
		entityManager.close();
		factory.close();
		return query.getFirstResult();
	}
	
	public Usuari getUsuari(int number) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT u from Usuari u LIMIT "+number+",1";
		Query query = entityManager.createQuery(sql);
		Usuari usuari = (Usuari) query.getSingleResult();

		entityManager.close();
		factory.close();
		return usuari;
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
	
//	public ArrayList<Usuari> getUsuaris(String loginName) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
//		EntityManager entityManager = factory.createEntityManager();
//		
//		ArrayList<Usuari> usuaris = new ArrayList<Usuari>();
//		String sql =  "SELECT u from Usuari u where u.pLoginId ='"+loginName+"'";
//		Query query = entityManager.createQuery(sql);
//		usuaris = (ArrayList<Usuari>) query.getResultList();
//	
//		
//		entityManager.close();
//		factory.close();
//		return usuaris;
//	}

}
