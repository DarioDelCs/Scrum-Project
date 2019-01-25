package components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import daoImpl.JPAUsuariImpl;
import idao.IUsuari;
import model.Usuari;
import view.AdminView;

public class Tittle implements ActionListener{

	public static JFrame sFrame;
	public static JDesktopPane jdPanel;
	
	public static JMenuBar spMenu;
	public static JMenu smProyecto;
	public static JMenu smUsuarios;
	public static JMenuItem smiNewUser;
	
	public static JPanel sNorthPanel;

	public static JButton sbSalir;
	
	public static JLabel slUser;
	public static Usuari sUsuari;
	
	public static void addMenu(JFrame frame) {
		spMenu=new JMenuBar();
		smProyecto=new JMenu("Proyecto");//hard
		smUsuarios=new JMenu("Usuarios");//hard
		smiNewUser=new JMenuItem("Nuevo Usuario");//hard
		smUsuarios.add(smiNewUser);
		spMenu.add(smProyecto);
		spMenu.add(smUsuarios);
		
		smiNewUser.setEnabled(false);
		
		frame.add(spMenu, BorderLayout.NORTH);
	}
	
	public static JPanel addTitle(JFrame frame, JDesktopPane dPanel) {
		sFrame = frame;
		jdPanel = dPanel;
		
		sNorthPanel = new JPanel();
		sNorthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
//		slUser = new JLabel("Usuario: " + sUsuari.getpName() + " ("+sUsuari.getpProfile()+")");//hard
		slUser = new JLabel("Prueba");
		sNorthPanel.add(slUser);

		sbSalir = new JButton("Salir");
		sNorthPanel.add(sbSalir);
		
//		pbSalir.addActionListener(this);
		
		return sNorthPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Tittle.smiNewUser) {
			new AdminView(sFrame, jdPanel);
		}/*else if (e.getSource() == pbEnviar){
			IUsuari users = new JPAUsuariImpl();
			users.addUsuari(ptfNombre.getText(), ptfLogin.getText(), ppfPass.getText(), pcbPerfil.getSelectedItem().toString(), ptfMail.getText());
			JOptionPane.showMessageDialog(null, "Usuario insertado", "Insert", JOptionPane.INFORMATION_MESSAGE);
			ptfNombre.setText("");
			ptfLogin.setText("");
			ptfMail.setText("");
			ppfPass.setText("");
			ppfPass2.setText("");
		}*/
	}

}
