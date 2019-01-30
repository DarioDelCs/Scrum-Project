package main;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.Tittle;
import daoImpl.Conexion;
import model.UserType;
import view.Login;

public class Main extends JFrame/* implements ActionListener*/{

	private String[] alUserString = {"Developer","ProductOwner","ScrumMaster","Administrator"};
	public static HashMap<UserType, String> hmUser = new HashMap<UserType, String>();
	private UserType eUserType;
	public static boolean isOnline;
	private JPanel pPanel;
	private JDesktopPane pjdPanel;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		pPanel = new JPanel();
		pPanel.setLayout(new BorderLayout());
		pjdPanel = new JDesktopPane();
		setLayout(new BorderLayout());
		
		if(Conexion.isConnected()) {
			setTitle("Scrum Program - (ONLINE)");
			isOnline=true;
		}else {
			setTitle("Scrum Program - (OFFLINE)");
			isOnline=false;
		}
		Tittle.addMenu(this, pjdPanel);
		pjdPanel.add(new Login(this, pjdPanel));
		
		pPanel.add(Tittle.addTitle(this, pjdPanel), BorderLayout.NORTH);
		pPanel.add(pjdPanel, BorderLayout.CENTER);
		
		int count=0;
		for (UserType eUsers : eUserType.values()) {
			hmUser.put(eUsers, alUserString[count]);
			count++;
		}
		
		add(pPanel, BorderLayout.CENTER);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/*private void tryConnect() {
		if (Conexion.checkOnline()) {
			setTitle("Scrum Program - (ONLINE)");
			isOnline = true;
		}else {
			setTitle("Scrum Program - (OFFLINE)");
			isOnline = false;
		}
		Conexion.getConexion();
		Conexion.getConexion().resultados();
	
	}*/
}
