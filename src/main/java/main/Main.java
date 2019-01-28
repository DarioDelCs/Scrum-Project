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
import daoImpl.MySQLUsuariImpl;
import idao.IUsuari;
import model.UserType;
import view.Login;

public class Main extends JFrame/* implements ActionListener*/{

	private IUsuari pUser = new MySQLUsuariImpl();
	
	private String[] alUserString = {"Developer","ProductOwner","ScrumMaster","Administrator"};
	public static HashMap<UserType, String> hmUser = new HashMap<UserType, String>();
	private UserType eUserType;
	
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
		
//		tryConnect();
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
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("bd_scrum_adc");
			EntityManager entityManager = factory.createEntityManager();
			setTitle("Scrum Program - (ONLINE)");
			entityManager.close();
			factory.close();
		} catch (Exception ex) {
	    	System.out.println("Error: "+ex.getMessage());
			setTitle("Scrum Program - (OFFLINE)");
			ex.printStackTrace();
	    }
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
