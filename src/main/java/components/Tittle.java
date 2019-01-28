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

public class Tittle {

	public static JFrame sFrame;
	public static JDesktopPane jdPanel;
	
	public static JMenuBar spMenu;
	public static JMenu smProyecto;
	public static JMenu smUsuarios;
	public static JMenuItem smiNewUser,smiSearchModUser,smiNewProyect,smiSeeProyects;
	
	public static JPanel sNorthPanel;

	public static JButton sbSalirLogin;
	
	public static JLabel slUser;
	public static Usuari sUsuari;
	
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
			sbSalirLogin.setText("Login");
			slUser.setText("");
			Tittle.smiNewUser.setEnabled(false);
			Tittle.smiNewProyect.setEnabled(false);
			jdPanel.removeAll();
			jdPanel.repaint();
			jdPanel.add(new Login(sFrame, jdPanel));
		}else if(sbSalirLogin.getText().equals("Login") && jdPanel.getAllFrames().length==0) {
			jdPanel.add(new Login(sFrame, jdPanel));
		}
	}
	
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource() == smiNewUser) {
//			new AdminView(sFrame, jdPanel);
//		}/*else if (e.getSource() == pbEnviar){
//			IUsuari users = new JPAUsuariImpl();
//			users.addUsuari(ptfNombre.getText(), ptfLogin.getText(), ppfPass.getText(), pcbPerfil.getSelectedItem().toString(), ptfMail.getText());
//			JOptionPane.showMessageDialog(null, "Usuario insertado", "Insert", JOptionPane.INFORMATION_MESSAGE);
//			ptfNombre.setText("");
//			ptfLogin.setText("");
//			ptfMail.setText("");
//			ppfPass.setText("");
//			ppfPass2.setText("");
//		}*/
//	}

}
