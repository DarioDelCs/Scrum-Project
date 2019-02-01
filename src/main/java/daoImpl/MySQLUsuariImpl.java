package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.swing.JOptionPane;

import idao.IUsuari;
import model.Usuari;

public class MySQLUsuariImpl implements IUsuari{

	public Usuari getUsuari(String login) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT u from Usuari u WHERE login_id = '"+login+"'";
		Usuari usuari;
		try{
			Query query = entityManager.createQuery(sql);
			usuari = (Usuari) query.getSingleResult();
		}catch(NoResultException e) {
			System.out.println(e.getMessage());
			return null;
		}

		entityManager.close();
		factory.close();
		
		return usuari;
	}

	public boolean existUser(String login) {
		return (getUsuari(login)!=null)?true:false;
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
//	            JOptionPane.showMessageDialog(null, "No se ha podido añadir el usuario en la db embebida", "Error añadir usuario", JOptionPane.ERROR_MESSAGE);
			}
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public String[] getUersProfilename(String profilename) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT u FROM Usuari u WHERE profilename = '"+profilename+"'";
		String[] nameUser;
		try{
			Query query = entityManager.createQuery(sql);
			List<Usuari> users = query.getResultList();

			nameUser = new String[users.size()];
			for (int i = 0; i < users.size(); i++) {
				nameUser[i]=users.get(i).getpName()+"("+users.get(i).getpLoginId()+")";
			}
			
			entityManager.close();
			factory.close();
			return nameUser;
		}catch(NoResultException e) {
			entityManager.close();
			factory.close();
			return null;
		}
	}
	
	public boolean inserUser(String nombre, String loginId, String pass, String profileName, String email) {
		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
			EntityManager entityManager = factory.createEntityManager();
			
			StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("insertUser", Usuari.class)//nombre procedure

			    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)//el primer valor que tipo es y si es in o out
			    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
			    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
			    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
			    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
			    
			    .setParameter(1, nombre)
			    .setParameter(2, loginId)
			    .setParameter(3, pass)
			    .setParameter(4, profileName)
			    .setParameter(5, email);
		    
			return query.execute();
//			System.out.println("Fin OK");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fin KO");
		}
		return false;
	}
}
