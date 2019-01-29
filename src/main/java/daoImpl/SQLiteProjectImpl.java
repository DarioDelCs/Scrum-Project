package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public boolean addProject(String pNameProject, String pDescripcion, int pScrumMaster, int pProductOwner) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			Statement stmt  = conn.createStatement();
			
			String sql =  "INSERT INTO proyecto (nombre, descripcion, scrummaster, productowner) VALUES ('"+pNameProject+"', '"+pDescripcion+"', '"+pScrumMaster+"', '"+pProductOwner+"');";
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
	}

}
