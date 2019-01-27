package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.UserType;
import model.Usuari;

public class Test {

	public static String[] alUserString = {"Developer","ProductOwner","ScrumMaster","AdministradorUsers"};
	public static HashMap<UserType, String> hmUser = new HashMap<UserType, String>();
	public static UserType eUserType;
	
	public static void main(String[] args) {
		int count=0;
		for (UserType eUsers : eUserType.values()) {
			hmUser.put(eUsers, alUserString[count]);
			count++;
		}
//		try {
////	        Class.forName("com.mysql.cj.jdbc.Driver");
//	        Connection cnx = DriverManager.getConnection("jdbc:mysql://192.168.40.87:3306/bd_scrum_adc?serverTimezone=UTC", "admin", "123");
//	     } catch (SQLException ex) {
//	    	 ex.printStackTrace();
//	     }

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
//		Usuari newUser = new Usuari(2, "name", "login", "pas", "asfd", "Email");

		entityManager.persist(new Usuari("name", "login", "pas", "asfd", "Email"));
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}
