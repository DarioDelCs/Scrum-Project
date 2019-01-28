package main;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.Tittle;
import daoImpl.Conexion;
import idao.OLD_IUsuari;
import model.UserType;
import view.Login;

public class Main extends JFrame/* implements ActionListener*/{

	private String[] alUserString = {"Developer","ProductOwner","ScrumMaster","Administrador"};
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
		
		tryConnect();
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
	
	private void tryConnect() {
		if (Conexion.checkOnline()) {
			setTitle("Scrum Program - (ONLINE)");
			isOnline = true;
		}else {
			setTitle("Scrum Program - (OFFLINE)");
			isOnline = false;
		}
		Conexion.getConexion();
		Conexion.getConexion().resultados();
	
	}
	
	
//	private void addTitle() {
//		pNorthPanel = new JPanel();
//		pNorthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		
////		slUser = new JLabel("Usuario: " + sUsuari.getpName() + " ("+sUsuari.getpProfile()+")");//hard
//		slUser = new JLabel("Prueba");
//		pNorthPanel.add(slUser);
//
//		sbSalir = new JButton("Salir");
//		pNorthPanel.add(sbSalir);
//		
////		pbSalir.addActionListener(this);
//		
//		pjdPanel.add(pNorthPanel, BorderLayout.NORTH);
//		add(pNorthPanel, BorderLayout.NORTH);
//	}

//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == pbSalir) {
////			if(pCenterPanel!=null) {
////				pCenterPanel.setVisible(false);
////			}
////			this.dispose();
////			pjdPanel.add(new Login(this, pjdPanel));
//		}
//	}

}
