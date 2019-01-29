package daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import idao.IProject;
import model.Project;
import model.Usuari;


public class MySQLProjectImpl implements IProject {

	public boolean existProject(String nombreProject) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();

		String sql =  "SELECT p from Project p WHERE nombre = '"+nombreProject+"'";
		try{
			Query query = entityManager.createQuery(sql);
			Usuari usuari = (Usuari) query.getSingleResult();
		}catch(NoResultException e) {
			return false;
		}
		
		entityManager.close();
		factory.close();
		return true;
	}
	
	public boolean addProject(String pNameProject, String pDescripcion, String pScrumMaster, String pProductOwner) {
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			
			Project newUser = new Project(pNameProject, pDescripcion, pScrumMaster, pProductOwner);
	
			entityManager.merge(newUser);
			entityManager.getTransaction().commit();
			entityManager.close();
			factory.close();

			if(!new SQLiteProjectImpl().addProject(pNameProject, pDescripcion, pScrumMaster, pProductOwner)) {
	            JOptionPane.showMessageDialog(null, "No se ha podido añadir el usuario en la db embebida", "Error añadir usuario", JOptionPane.ERROR_MESSAGE);
			}
			
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
} 