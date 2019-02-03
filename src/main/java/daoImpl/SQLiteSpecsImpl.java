package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import idao.ISpecs;
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
}
