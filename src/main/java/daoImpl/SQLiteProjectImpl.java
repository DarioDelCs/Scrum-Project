package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
		if(!existProject(pNameProject)) {
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
		}else {
            JOptionPane.showMessageDialog(null, "El proyecto ya existe", "Error añadir proyecto", JOptionPane.WARNING_MESSAGE);
            return false;
		}
	}
}
