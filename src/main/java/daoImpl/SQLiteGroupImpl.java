package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import idao.IGroup;

public class SQLiteGroupImpl implements IGroup{

	public int getGroupId(int projectId) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			
			String sql =  "SELECT * from grupo WHERE idproyecto = '"+projectId+"'";
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

	        while(rs.next()) {
	        	int id = rs.getInt("idgrupo");
		        stmt.close();
		        rs.close();
		        conn.close();
		        return id;
	        }
	        
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}

}
