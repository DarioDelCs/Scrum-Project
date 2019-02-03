package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import idao.ISpecs;
import model.Especificaciones;
import model.Project;

public class MySQLSpecsImpl implements ISpecs{

	public List<Especificaciones> getAllSpecs(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT e from Especificaciones e";
		
		try{
			Query query = entityManager.createQuery(sql);
			List<Especificaciones> specs = query.getResultList();
			entityManager.close();
			factory.close();
			return specs;
		}catch(NoResultException e) {
			entityManager.close();
			factory.close();
			return null;
		}
	}
}
