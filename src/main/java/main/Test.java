package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import daoImpl.Conexion;
import model.UserType;
import model.Usuari;

public class Test {

	public static String[] alUserString = {"Developer","ProductOwner","ScrumMaster","AdministradorUsers"};
	public static HashMap<UserType, String> hmUser = new HashMap<UserType, String>();
	public static UserType eUserType;
	
	public static void main(String[] args) {
		aux(2,2);
	}
	
	private static void aux(int num1, int num2) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
//		EntityManager entityManager = factory.createEntityManager();
//		try{
//			entityManager.getTransaction().begin();
//			StoredProcedureQuery query = entityManager
//				.createStoredProcedureQuery("aux")//nombre procedure
//
//			    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)//el primer valor que tipo es y si es in o out
//			    .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
//			    
//			    .setParameter(1, num1)
//			    .setParameter(2, num2);
//		    
//			query.execute();
////			System.out.println("Fin OK");
//		}catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Fin KO");
//		}
//
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		factory.close();
		/*Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
//			Statement stmt  = conn.createStatement();

		    String proc3StoredProcedure = "{ call inserUser(?, ?, ?, ?, ?) }";
		    CallableStatement cs = conn.prepareCall(proc3StoredProcedure);

		    cs.setString(1, "AA");
		    cs.setString(2, "login");
		    cs.setString(3, "pass");
		    cs.setString(4, "altre");
		    cs.setString(5, "email");;

		    cs.registerOutParameter(1, Types.VARCHAR);
		    cs.registerOutParameter(2, Types.VARCHAR);
		    cs.registerOutParameter(3, Types.VARCHAR);
		    cs.registerOutParameter(4, Types.VARCHAR);
		    cs.registerOutParameter(5, Types.VARCHAR);

		    cs.execute();

		    conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		if (Conexion.isConnected()) {
			System.out.println(true);
		}else {
			System.out.println(false);
		}
		
		
		
		
		System.out.println("AAAAAAAAAAAAAA");
		
		
//		Conexion.getConexion();
		/*
		 Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
				String sql =  "SELECT * from users WHERE login_id = 'NUAdmin' AND password = '123'";
		        Statement stmt  = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(sql);
		       
		        System.out.println(rs.next());
		       // loop through the result set
		       while (rs.next()) {
		           System.out.println(rs.getString(1));
		       }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
//		Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:./data2.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");

    		String sql =  "SELECT * from usuario";
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
           
           // loop through the result set
           while (rs.next()) {
               System.out.println(rs.getString("user_id"));
           }
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
        }
		
		
		/*int count=0;
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
		factory.close();*/
	}

}
