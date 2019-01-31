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
			Project project = (Project) query.getSingleResult();
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

			int scrumID = new MySQLUsuariImpl().getUsuari(pScrumMaster.split("\\(")[1].split("\\)")[0]).getpID();
			int ownerID = new MySQLUsuariImpl().getUsuari(pProductOwner.split("\\(")[1].split("\\)")[0]).getpID();
			
			Project newProject = new Project(pNameProject, pDescripcion, scrumID, ownerID);
	
//			if(!existProject(newProject.getpName())) {
				entityManager.merge(newProject);
				entityManager.getTransaction().commit();
//			}else {
//	            JOptionPane.showMessageDialog(null, "El proyecto ya existe", "Error añadir proyecto", JOptionPane.WARNING_MESSAGE);
//	            return false;
//			}
			entityManager.close();
			factory.close();

			if(!new SQLiteProjectImpl().addProject(pNameProject, pDescripcion, pScrumMaster, pProductOwner)) {
//	            JOptionPane.showMessageDialog(null, "No se ha podido añadir el proyecto en la db embebida", "Error añadir proyecto", JOptionPane.ERROR_MESSAGE);
			}
			
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
} 