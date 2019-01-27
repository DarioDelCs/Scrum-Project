package daoImpl;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import idao.IProject;
import model.Project;
import model.Usuari;


public class JPAProjectImpl implements IProject {
	
	public ArrayList<Project> getProjects() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		ArrayList<Project> projects = new ArrayList<Project>();
		String sql =  "SELECT * from Usuari proyectos";
		Query query = entityManager.createQuery(sql);
		//projects = (ArrayList<projects>) query.getResultList();

		entityManager.getTransaction().begin();

		int count=1;
		Project project;
		while((project = entityManager.find(Project.class, count))!=null) {
			projects.add(project);
			count++;
		}

		entityManager.close();
		factory.close();
		return projects;
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
	public ArrayList<Usuari>  getUsers(String tipeUser) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		ArrayList<Usuari> usuaris = new ArrayList<Usuari>();
		String sql =  "SELECT u from Usuari u where u.pProfile ='"+tipeUser+"'";
		Query query = entityManager.createQuery(sql);
		usuaris = (ArrayList<Usuari>) query.getResultList();
	
		
		entityManager.close();
		factory.close();
		return usuaris;
	}
} 