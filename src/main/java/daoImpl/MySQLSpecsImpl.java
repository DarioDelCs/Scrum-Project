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

	public boolean createSpec(String desc, double horas, int idProject, int sprint) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Especificaciones spec = new Especificaciones(0, desc, horas, idProject, sprint);
		
		if(!new SQLiteSpecsImpl().createSpec(desc, horas, idProject, sprint)) {
//            JOptionPane.showMessageDialog(null, "No se ha podido añadir el proyecto en la db embebida", "Error añadir proyecto", JOptionPane.ERROR_MESSAGE);
		}
		
		try{
			entityManager.merge(spec);
			entityManager.getTransaction().commit();
			entityManager.close();
			factory.close();
			return true;
		}catch(NoResultException e) {
			entityManager.close();
			factory.close();
			return false;
		}
	}

	public boolean existSpec(String desc, double horas, int idProject, int sprint) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();

		String sql =  "SELECT e from Especificaciones e WHERE DESCRIPCION = '"+desc+"' and HORAS ="+horas+" and IDPROYECTO ="+idProject+" and SPRINT ="+sprint;
		try{
			Query query = entityManager.createQuery(sql);
			query.getSingleResult();
			entityManager.close();
			factory.close();
			return true;
		}catch(NoResultException e) {
			entityManager.close();
			factory.close();
			return false;
		}
	}
}
