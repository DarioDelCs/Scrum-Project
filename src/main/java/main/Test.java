package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
	
	public static void main(String[] args) {
		
		try {
//	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection cnx = DriverManager.getConnection("jdbc:mysql://192.168.1.38:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "admin", "123");
	     } catch (SQLException ex) {
	    	 ex.printStackTrace();
	     }
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
	}

}
