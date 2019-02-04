package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import components.WriteLogController;
import idao.ISpecs;
import main.Main;
import model.Especificaciones;

public class SQLiteSpecsImpl implements ISpecs {

	public List<Especificaciones> getAllSpecs(){
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			
			String sql =  "SELECT * from especificaciones";
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			List<Especificaciones> specs=new ArrayList<Especificaciones>();
	        while(rs.next()) {
	        	specs.add(new Especificaciones(rs.getInt("marcada"),rs.getString("descripcion"),rs.getDouble("horas"),
		        		rs.getInt("idproject"),rs.getInt("sprint")));
	        }
	        
	        stmt.close();
	        rs.close();
	        conn.close();
	        return specs;
	        
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean createSpec(String desc, double horas, int idProject, int sprint) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			
			String sql =  "INSERT INTO especificaciones (Marcada,Descripcion,Horas,idProyecto,Sprint) VALUES (0,'"+desc+"',"+horas+","+idProject+","+sprint+")";
			Statement stmt  = conn.createStatement();
			
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

	public boolean existSpec(String desc, double horas, int idProject, int sprint) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			
			String sql =  "Select * from especificaciones where Descripcion ='"+desc+"' and Horas ='"+horas+"' and idProyecto ='"+idProject+"'and Sprint = '"+sprint+"';";
			Statement stmt  = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			stmt.close();
			rs.close();
	        conn.close();
	        return true;
	        
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
