package daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import idao.IConexion;
import idao.OLD_IUsuari;
import main.Main;

public class Conexion {

	private static IConexion conector;
	
	public static IConexion getConexion() {
		if (conector== null) {
			conector = generarConector();			
		}
		return conector;
	}
	
	private static IConexion generarConector()  {
		if (Main.isOnline) {
			conector = new MySQLImpl();
			return conector;
		}else {
			conector = new SQLiteImpl();
			return conector;
		}
	}
	
	public static boolean checkOnline() {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
			EntityManager entityManager = factory.createEntityManager();
			return true;
		}catch (Exception e){
			return false;
		}
	}
}

