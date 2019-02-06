package daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import idao.IProject;
import idao.ISpecs;
import idao.IUsuari;
import main.Main;

public class Conexion {

	private static IUsuari iUser;
	private static IProject iProject;
	private static ISpecs iSpecs;
	
	public static boolean isConnected() {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
			EntityManager entityManager = factory.createEntityManager();
			iUser = new MySQLUsuariImpl();
			iProject = new MySQLProjectImpl();
			iSpecs = new MySQLSpecsImpl();
			return true;
		}catch (Exception e){
			iUser = new SQLiteUsuariImpl();
			iProject = new SQLiteProjectImpl();
			iSpecs = new SQLiteSpecsImpl();
			return false;
		}
	}
	
	//esto se puede omitir si ponermos los atributos publicos
	public static IUsuari getIUser() {
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
	}
	
	public static ISpecs getISpecs() {
		if(iSpecs == null) {
			isConnected();
		}
		return iSpecs;
	}
}

