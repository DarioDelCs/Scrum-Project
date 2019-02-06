package components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.Usuari;
import view.CreateUser;
import view.CreateProject;
import view.Login;
import view.SeeProject;

public class Tittle {

	private static JFrame sFrame;
	private static JDesktopPane jdPanel;
	
	private static JMenuBar spMenu;
	private static JMenu smProyecto;
	private static JMenu smUsuarios;
	private static JMenuItem smiNewUser,smiSearchModUser,smiNewProyect,smiSeeProyects;
	
	private static JPanel sNorthPanel;

	private static JButton sbSalirLogin;
	
	private static JLabel slUser;
	private static Usuari sUsuari;
	
	public static void addMenu(JFrame frame, JDesktopPane dPanel) {
		sFrame = frame;
		jdPanel = dPanel;
		
		spMenu=new JMenuBar();
		smProyecto=new JMenu("Proyecto");//hard
		smUsuarios=new JMenu("Usuarios");//hard
		smiNewUser=new JMenuItem("Nuevo Usuario");//hard
		smiSearchModUser=new JMenuItem("Buscar/modificar usuario");//hard
		smiNewProyect=new JMenuItem("Crear Proyecto");//hard
		smiSeeProyects=new JMenuItem("Mostrar proyectos");//hard

		smUsuarios.add(smiNewUser);
		smUsuarios.add(smiSearchModUser);
		smProyecto.add(smiNewProyect);
		smProyecto.add(smiSeeProyects);
		spMenu.add(smProyecto);
		spMenu.add(smUsuarios);

		smiNewUser.setEnabled(false);
		smiSearchModUser.setEnabled(false);
		smiNewProyect.setEnabled(false);
		smiSeeProyects.setEnabled(false);
		
		smiNewUser.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				jdPanel.add(new CreateUser(sFrame, jdPanel));
			}
		});
		smiNewProyect.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				jdPanel.add(new CreateProject(sFrame, jdPanel));
			}
		});
		smiSeeProyects.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				jdPanel.add(new SeeProject(sFrame, jdPanel));
			}
		});
		frame.add(spMenu, BorderLayout.NORTH);
	}
	
	public static JPanel addTitle(JFrame frame, JDesktopPane dPanel) {
		sFrame = frame;
		jdPanel = dPanel;
		
		sNorthPanel = new JPanel();
		sNorthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		slUser = new JLabel("");
		sNorthPanel.add(slUser);

		sbSalirLogin = new JButton("Login");//mas tarde poner tambien como login
		sNorthPanel.add(sbSalirLogin);
		
		sbSalirLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				listenerLoginExit();
			}
		});
		
		return sNorthPanel;
	}
	
	public static void listenerLoginExit() {		
		if(sbSalirLogin.getText().equals("Salir")) {
			buttonExitLoginText("Login");
			userInUse("");
			allMenuItems(false);
			jdPanel.removeAll();
			jdPanel.repaint();
			jdPanel.add(new Login(sFrame, jdPanel));
		}else if(sbSalirLogin.getText().equals("Login") && jdPanel.getAllFrames().length==0) {
			jdPanel.add(new Login(sFrame, jdPanel));
		}
	}
	
	//Menu items enabled
	public static void newUser(boolean enabled) {
		smiNewUser.setEnabled(enabled);
	}
	public static void searchModUser(boolean enabled) {
		smiSearchModUser.setEnabled(enabled);
	}
	public static void newProyect(boolean enabled) {
		smiNewProyect.setEnabled(enabled);
	}
	public static void seeProyects(boolean enabled) {
		smiSeeProyects.setEnabled(enabled);
	}
	public static void allMenuItems(boolean enabled) {
		smiNewUser.setEnabled(enabled);
		smiSearchModUser.setEnabled(enabled);
		smiNewProyect.setEnabled(enabled);
		smiSeeProyects.setEnabled(enabled);
	}
	
	//name of user loged
	public static void userInUse(String user) {
		Tittle.slUser.setText(user);//cambiar por nombre y grupo
	}
	
	//text of button to exit or login
	public static void buttonExitLoginText(String text) {
		Tittle.sbSalirLogin.setText(text);
	}
}
