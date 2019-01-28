package daoImpl;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import idao.OLD_IProject;
import model.Project;
import model.Usuari;


public class MySQLProjectImpl_old implements OLD_IProject {

	public int countProjects() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
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
} 