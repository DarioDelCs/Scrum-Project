package daoImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import idao.IConexion;
import model.Project;
import model.Usuari;

public class MySQLImpl implements IConexion{

	public int countProjects() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT COUNT(*) FROM Usuari";
		Query query = entityManager.createQuery(sql);
		
		entityManager.close();
		factory.close();
		return query.getFirstResult();
	}
	
	public Project getProject(int number) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT p from Proyectos p LIMIT "+number+",1";
		Query query = entityManager.createQuery(sql);
		Project project = (Project) query.getSingleResult();
		
		entityManager.close();
		factory.close();
		return project;
	}

	public void addProject(String pNameProject, String pDescripcion, int pScrumMaster, int pProductOwner) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Project newUser = new Project(pNameProject, pDescripcion, pScrumMaster, pProductOwner);

		entityManager.merge(newUser);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	//Metodo para coger los usuario dependiendo de si son ScrumMaster o Product Owner
//	public ArrayList<Usuari> getUsers(String tipeUser) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
//		EntityManager entityManager = factory.createEntityManager();
//		
//		ArrayList<Usuari> usuaris = new ArrayList<Usuari>();
//		String sql =  "SELECT u from Usuari u where u.pProfile ='"+tipeUser+"'";
//		Query query = entityManager.createQuery(sql);
//		usuaris = (ArrayList<Usuari>) query.getResultList();
//	
//		
//		entityManager.close();
//		factory.close();
//		return usuaris;
//	}
	public int countUsers() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT COUNT(*) FROM Usuari";
		Query query = entityManager.createQuery(sql);
		
		entityManager.close();
		factory.close();
		return query.getFirstResult();
	}
	
	public Usuari getUsuari(int number) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT u from Usuari u LIMIT "+number+",1";
		Query query = entityManager.createQuery(sql);
		Usuari usuari = (Usuari) query.getSingleResult();

		entityManager.close();
		factory.close();
		return usuari;
	}

	public void addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Usuari newUser = new Usuari(pEmail, pLoginId, pName, pPass, pProfileId);
		
		entityManager.persist(newUser);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	public void resultados() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		ArrayList<Usuari> tmp = new ArrayList<Usuari>();
		String sql = "SELECT u from Usuari u";
		Query query =  entityManager.createQuery(sql);
		tmp = (ArrayList) query.getResultList();
		for (Usuari a : tmp) {
			System.out.println(a.getpName());
		}
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
//	}s

}
