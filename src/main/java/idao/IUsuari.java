package idao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuari;

public interface IUsuari {
	// Atributos de la Interfaz:
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
	EntityManager entityManager = factory.createEntityManager();
	
	// Métodos de la Interfaz:
	public ArrayList<Usuari> getUsuaris();
}
