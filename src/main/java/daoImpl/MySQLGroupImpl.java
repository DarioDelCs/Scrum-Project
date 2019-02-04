package daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import idao.IGroup;
import model.Grupo;

public class MySQLGroupImpl implements IGroup{

	public int getGroupId(int projectId) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrum_adc");
		EntityManager entityManager = factory.createEntityManager();
		
		String sql =  "SELECT g from Grupo g WHERE idproyecto = '"+projectId+"'";
		Grupo grupo;
		try{
			Query query = entityManager.createQuery(sql);
			grupo = (Grupo) query.getSingleResult();
		}catch(NoResultException e) {
			System.out.println(e.getMessage());
			return -1;
		}

		entityManager.close();
		factory.close();
		
		return grupo.getpID();
	}

}
