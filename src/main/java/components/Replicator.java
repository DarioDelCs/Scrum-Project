package components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import daoImpl.Conexion;
import main.Main;
import model.Especificaciones;
import model.Project;
import model.UserType;
import model.Usuari;

public class Replicator extends Thread {
	ArrayList<String> qPendientes = new ArrayList<String>();
	static ArrayList<String> qErrores = new ArrayList<String>();
	static EntityManagerFactory factory;
	static EntityManager entityManager;
	private static File log = new File(
			"src" + File.separator + "main" + File.separator + "resources" + File.separator + "log.txt");
	private static BufferedReader br;
	private static BufferedWriter bw;

	public void run() {

		try {
			br = new BufferedReader(new FileReader(log));
			factory = Persistence.createEntityManagerFactory("scrum_adc");
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			while (br.ready()) {
				qPendientes.add(br.readLine());
			}
			for (String a : qPendientes) {
				System.out.println(a);
				executeQuery(a);
			}
			entityManager.close();
			factory.close();
			br.close();
			if (qErrores.size() > 0) {
				volcarError();
			}
			bw = new BufferedWriter(new FileWriter(log));
			bw.write("");
			bw.close();
			cerrarIO();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

	private static void cerrarIO() throws IOException {
		if (br != null) {
			br.close();
		}
		if (bw != null) {
			bw.close();
		}
	}

	private static void executeQuery(String a) {
		String tipo = a.split(" ")[0];
		if (tipo.equals("INSERT")) {
			ejecutarInsert(a);
		} else if (tipo.equals("UPDATE")) {
			ejecutarUpdate(a);
		} else {
			ejecutarDelete(a);
		}
	}

	private static void ejecutarDelete(String a) {

	}

	private static void ejecutarInsert(String query) {
		String tmp = query.split(" VALUES ")[1];
		String tabla = query.split(" ")[2];
		if (tabla.equals("users")) {
			tmp = tmp.substring(1, tmp.length() - 2);
			String[] valores = tmp.split(", ");
			String pEmail = (valores[4].substring(1, valores[4].length() - 1));
			String pLoginId = (valores[1].substring(1, valores[1].length() - 1));
			String pName = (valores[0].substring(1, valores[0].length() - 1));
			String pPass = (valores[2].substring(1, valores[2].length() - 1));
			String pProfileId = (valores[3].substring(1, valores[3].length() - 1));
			try {
				Usuari a = new Usuari(pEmail, pLoginId, pName, pPass, pProfileId, true);
				if (!Conexion.getIUser().existUser(pLoginId)) {
					entityManager.persist(a);
					entityManager.getTransaction().commit();
				} else {
					qErrores.add(query);
				}

			} catch (Exception e) {
				qErrores.add(query);
			}
		} else if (tabla.equals("proyecto")) {
			tmp = tmp.substring(1, tmp.length() - 2);
			String[] valores = tmp.split(", ");
			String pNameProject = valores[0].substring(1, valores[0].length() - 1);
			String pDescripcion = valores[1].substring(1, valores[1].length() - 1);
			String scrumID = valores[2].substring(1, valores[2].length() - 1);
			String ownerID = valores[3].substring(1, valores[3].length() - 1);
			try {
				Project p = new Project(pNameProject, pDescripcion, Integer.parseInt(scrumID),
						Integer.parseInt(ownerID));
				if (!Conexion.getIProject().existProject(pNameProject)) {
					entityManager.persist(p);
					entityManager.getTransaction().commit();
				} else {
					qErrores.add(query);
				}
				entityManager.persist(p);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				qErrores.add(query);
			}
		}else if (tabla.equals("especificaciones")) {
			tmp = tmp.substring(1, tmp.length() - 1);	
			String[] valores = tmp.split(",");
			int marcada = 0;
			String Descripcion = valores[1];
			Double Horas =	Double.parseDouble(valores[2]);
			int IdProject =Integer.parseInt(valores[3]);
			int Sprint =Integer.parseInt(valores[4]);
			try {
				Especificaciones e =  new Especificaciones(marcada, Descripcion, Horas, IdProject, Sprint);
						if (!Conexion.getISpecs().existSpec(Descripcion, Horas, IdProject, Sprint)) {
							entityManager.persist(e);
							entityManager.getTransaction().commit();
						} else {
							qErrores.add(query);
						}
						entityManager.persist(e);
						entityManager.getTransaction().commit();
			}catch (Exception e) {
				qErrores.add(query);
			}
		}

	}

	private static void ejecutarUpdate(String query) {

	}

	private static void volcarError() throws IOException {
		File log = new File(
				"src" + File.separator + "main" + File.separator + "resources" + File.separator + "logErrores.txt");
		String list = "";

		try {
			bw = new BufferedWriter(new FileWriter(log, true));
			for (String a : qErrores) {
				list = list + a + "\n";
				bw.append(a + "\n");

			}

			JOptionPane.showMessageDialog(null, "Las siguientes queries no se han podido replicar:\n" + list, "Error",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			System.out.println("Error al volcar detalle ERROR LOG ejecuciones query");
		} finally {
			bw.close();
		}
	}

	public static void main(String[] args) {
		Replicator a = new Replicator();
		a.run();
	}

}
