package daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import idao.IProject;
import idao.IUsuari;
import main.Main;

public class Conexion {

	public static IUsuari iUser;
	public static IProject iProject;
	
	public static boolean isConnected() {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
			EntityManager entityManager = factory.createEntityManager();
			iUser = new MySQLUsuariImpl();
			iProject = new MySQLProjectImpl();
			return true;
		}catch (Exception e){
			iUser = new SQLiteUsuariImpl();
			iProject = new SQLiteProjectImpl();
			return false;
		}
	}
	
	//esto se puede omitir si ponermos los atributos publicos
	/*public static IUsuari getIUser() {
		if(iUser == null) {
			isConnected();
		}
		return iUser;
	}
	
	public static IProject getIProject() {
		if(iProject == null) {
			isConnected();
		}
		return iProject;
	}*/
}

