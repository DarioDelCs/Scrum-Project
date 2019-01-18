package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminView extends JInternalFrame implements ActionListener{
	
	private JFrame pFrame;
	private JDesktopPane pjdPanel;
	
	private JMenuBar ppMenu;
	private JMenu pmProyecto;
	private JMenu pmUsuarios;
	private JMenuItem pmiNewUser;
	
	private JLabel plUser;

	private JLabel plNombre, plLogin, plPass, plPass2, plMail;
	private JTextField ptfNombre, ptfLogin, ptfMail;
	private JPasswordField ppfPass, ppfPass2;
	private JButton pbGenerarPass, pbEnviar;
	
	public AdminView(JFrame frame, JDesktopPane dPanel/*,Usuari usauri*/) {
		this.pFrame = frame;
		this.pjdPanel = dPanel;

		addMenu();
		addTitle();
		
	}
	
	private void addMenu() {
		ppMenu=new JMenuBar();
		pmProyecto=new JMenu("Proyecto");//hard
		pmUsuarios=new JMenu("Usuarios");//hard
		pmiNewUser=new JMenuItem("Nuevo Usuario");//hard
		pmUsuarios.add(pmiNewUser);
		ppMenu.add(pmProyecto);
		ppMenu.add(pmUsuarios);
		pFrame.add(ppMenu, BorderLayout.NORTH);
		
		pmiNewUser.addActionListener(this);
	}
	
	private void addTitle() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill = GridBagConstraints.BOTH;
		
		plUser = new JLabel("Usuario: "/*+usuari.getLogin*/);//hard
		constraints.gridx=0;
		constraints.gridy=0;
		panel.add(plUser, constraints);
		
		
		pjdPanel.add(panel, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pmiNewUser) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();

			constraints.insets = new Insets(10, 10, 10, 10);
			constraints.fill = GridBagConstraints.BOTH;
			
			plNombre = new JLabel("Nombre");//hard
			constraints.gridx=0;
			constraints.gridy=0;
			panel.add(plNombre, constraints);
			ptfNombre = new JTextField();
			constraints.gridx=1;
			constraints.gridy=0;
			panel.add(ptfNombre, constraints);
			
			plLogin = new JLabel("Login generado");//hard		//LUEGO HACER AUTOGENERADO
			constraints.gridx=0;
			constraints.gridy=1;
			panel.add(plLogin, constraints);
			ptfLogin = new JTextField();
			constraints.gridx=1;
			constraints.gridy=1;
			panel.add(ptfLogin, constraints);
			
			plPass = new JLabel("Password");//hard
			constraints.gridx=0;
			constraints.gridy=2;
			panel.add(plPass, constraints);
			ppfPass = new JPasswordField();
			constraints.gridx=1;
			constraints.gridy=2;
			panel.add(ppfPass, constraints);
			pbGenerarPass = new JButton("Generar password");//hard
			constraints.gridx=2;
			constraints.gridy=2;
			panel.add(pbGenerarPass, constraints);
			
			plPass2 = new JLabel("Repita password");//hard		//LUEGO HACER QUE SE COMPRUEBE
			constraints.gridx=0;
			constraints.gridy=3;
			panel.add(plPass2, constraints);
			ppfPass2 = new JPasswordField();
			constraints.gridx=1;
			constraints.gridy=3;
			panel.add(ppfPass2, constraints);
			
			plMail = new JLabel("Mail");//hard					//LUEGO HACER QUE SE COMPRUEBE
			constraints.gridx=0;
			constraints.gridy=4;
			panel.add(plMail, constraints);
			ptfMail = new JTextField();
			constraints.gridx=1;
			constraints.gridy=4;
			panel.add(ptfMail, constraints);
			
			pbEnviar = new JButton("Enviar");//hard
			constraints.gridx=1;
			constraints.gridy=5;
			panel.add(pbEnviar, constraints);
			
			//dimension longitud cuadro textos
			ptfNombre.setColumns(15);
			
			
			
			
			add(panel);
			setTitle("Nuevo Usuario");//hard
			setResizable(true);
			setClosable(true);
//			setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
			pack();
//			setLocation(pFrame.getHeight()/2-this.getHeight()/2, pFrame.getWidth()/2-this.getWidth()/2);
			setVisible(true);
		}
	}

}
