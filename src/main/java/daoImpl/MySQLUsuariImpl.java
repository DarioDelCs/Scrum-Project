package daoImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import idao.IUsuari;
import model.Usuari;

public class MySQLUsuariImpl implements IUsuari{
	
	public Usuari getUsuari(String login, String password) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT u from Usuari u WHERE login_id = '"+login+"' AND password = '"+password+"'";
		Usuari usuari;
		try{
			Query query = entityManager.createQuery(sql);
			usuari = (Usuari) query.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}

		entityManager.close();
		factory.close();
		
		return usuari;
	}
	
	public boolean existUser(String login) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();

		String sql =  "SELECT u from Usuari u WHERE login_id = '"+login+"'";
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

	public boolean addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail) {
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			
			Usuari newUser = new Usuari(pEmail, pLoginId, pName, pPass, pProfileId);
	
			entityManager.merge(newUser);
			entityManager.getTransaction().commit();
			entityManager.close();
			factory.close();
			
			if(!new SQLiteUsuariImpl().addUsuari(pName, pLoginId, pPass, pProfileId, pEmail)) {
	            JOptionPane.showMessageDialog(null, "No se ha podido añadir el usuario en la db embebida", "Error añadir usuario", JOptionPane.ERROR_MESSAGE);
			}
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
