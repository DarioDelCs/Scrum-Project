package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import components.WriteLogController;
import idao.IUsuari;
import main.Main;
import model.Usuari;

public class SQLiteUsuariImpl implements IUsuari{

	public Usuari getUsuari(String login) {
        // create a connection to the database
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			
			String sql =  "SELECT * from users WHERE login_id = '"+login+"'";
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			Usuari user=null;
	        while(rs.next()) {
		        user = new Usuari(rs.getString("email"),rs.getString("login_id"),rs.getString("nombre"),
		        		rs.getString("password"),rs.getString("profilename"));
		        break;
	        }
	        
	        stmt.close();
	        rs.close();
	        conn.close();
	        return user;
	        
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public String getUsuariFromId(int user_id) {
        // create a connection to the database
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			
			String sql =  "SELECT * from users WHERE user_id = '"+user_id+"'";
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			Usuari user=null;
	        while(rs.next()) {
		        user = new Usuari(rs.getString("email"),rs.getString("login_id"),rs.getString("nombre"),
		        		rs.getString("password"),rs.getString("profilename"));
		        break;
	        }
	        
	        stmt.close();
	        rs.close();
	        conn.close();
	        return user.getpName();
	        
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public boolean existUser(String login) {
		boolean result;
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			
			String sql =  "SELECT * from users WHERE login_id = '"+login+"'";
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

	public boolean addUsuari(String pName, String pLoginId, String pPass, String pProfileId, String pEmail) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			Statement stmt  = conn.createStatement();
			
			String sql =  "INSERT INTO users (nombre, login_id, password, profilename, email) VALUES ('"+pName+"', '"+pLoginId+"', '"+pPass+"', '"+pProfileId+"', '"+pEmail+"');";
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

	public String[] getUersProfilename(String profilename) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:./data.sqlite");
			Statement stmt  = conn.createStatement();
			
			String sql =  "SELECT * from users WHERE profilename = '"+profilename+"'";
			ResultSet rs = stmt.executeQuery(sql);
			String nameUser = null;
			
			while(rs.next()) {
				if(nameUser!=null) {
					nameUser = nameUser+";";
				}
				nameUser = rs.getString("nombre")+"("+rs.getString("login_id")+")";
	        }
			
	        stmt.close();
	        rs.close();
	        conn.close();
			return nameUser.split(";");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
	        return null;
		}
	}
}