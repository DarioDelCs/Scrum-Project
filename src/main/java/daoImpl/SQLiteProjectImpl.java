package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import components.WriteLogController;
import idao.IProject;
import main.Main;
import model.Project;

public class SQLiteProjectImpl implements IProject{
	
	public boolean existProject(String nombreProject) {
		boolean result;
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			
			String sql =  "SELECT * from proyecto WHERE nombre = '"+nombreProject+"'";
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			result = rs.next();
			
	        stmt.close();
	        rs.close();
	        conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return true;
		}
	}

	public boolean addProject(String pNameProject, String pDescripcion, String pScrumMaster, String pProductOwner) {
//		if(!existProject(pNameProject)) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
				Statement stmt  = conn.createStatement();
	
				int scrumID = new SQLiteUsuariImpl().getUsuari(pScrumMaster.split("\\(")[1].split("\\)")[0]).getpID();
				int ownerID = new SQLiteUsuariImpl().getUsuari(pProductOwner.split("\\(")[1].split("\\)")[0]).getpID();
				
				String sql =  "INSERT INTO proyecto (nombre, descripcion, scrummaster, productowner) VALUES ('"+pNameProject+"', '"+pDescripcion+"', '"+scrumID+"', '"+ownerID+"');";
				stmt.executeUpdate(sql);
	
				if(!Main.isOnline) {
					new WriteLogController().writeInLog(sql);
				}
	
		        stmt.close();
		        conn.close();
		        return true;					
			} catch (SQLException e) {
				System.out.println(e.getMessage());
		        return false;
			}
//		}else {
//            JOptionPane.showMessageDialog(null, "El proyecto ya existe", "Error añadir proyecto", JOptionPane.WARNING_MESSAGE);
//            return false;
//		}
	}

	public List<Project> getProjects(int userId) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			
			String sql =  "SELECT * from proyecto WHERE idProyecto = (SELECT idProyecto from grupo WHERE IdGrupo = (SELECT USERGROUP from users WHERE USER_ID = "+userId+"))";
			
			Statement stmta  = conn.createStatement();
			ResultSet rsa = stmta.executeQuery(sql);

			List<Project> projects=new ArrayList<Project>();
			if(rsa.next()) {
				System.out.println("A");
				do {
					System.out.println("V");
		        	projects.add(new Project(rsa.getString("nombre"),rsa.getString("descripcion"),
		        			rsa.getInt("scrummaster"), rsa.getInt("productowner")));
				}while(rsa.next());
		        stmta.close();
		        rsa.close();
			}else {
		        stmta.close();
		        rsa.close();
				sql = "SELECT * from proyecto";
				Statement stmtb  = conn.createStatement();
				ResultSet rsb = stmtb.executeQuery(sql);
				while(rsb.next()) {
		        	projects.add(new Project(rsb.getString("nombre"),rsb.getString("descripcion"),
		        			rsb.getInt("scrummaster"), rsb.getInt("productowner")));
				}
		        stmtb.close();
		        rsb.close();
			}
	        
	        conn.close();
	        return projects;
	        
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
