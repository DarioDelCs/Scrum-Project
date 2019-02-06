package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import components.Tittle;
import daoImpl.Conexion;
import main.Main;
import model.UserType;
import model.Usuari;

public class Login extends JInternalFrame implements ActionListener{

	private JDesktopPane pjdPanel;
	private JFrame pFrame;
	
	private JPanel ppanel;
	private JLabel plLogin;
	private JLabel plPassword;

	private JTextField ptfLogin;
	private JPasswordField ppfPassword;
	
	private JButton pbEnviar;

	private static Usuari user;
	
	public Login(JFrame frame, JDesktopPane dPanel) {
		this.pjdPanel = dPanel;
		this.pFrame = frame;
		
		view();

		setTitle("Login");//hard
		setResizable(true);
		setClosable(true);
//		setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
		pack();
//		setLocation(pFrame.getHeight()/2-this.getHeight(), pFrame.getWidth()/2-this.getWidth());
		setVisible(true);
	}
	
	private void view() {
		ppanel = new JPanel();
		ppanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill = GridBagConstraints.BOTH;
		
		plLogin = new JLabel("Login");//hard
		constraints.gridx=0;
		constraints.gridy=0;
		ppanel.add(plLogin, constraints);
		
		plPassword = new JLabel("Password");//hard
		constraints.gridx=0;
		constraints.gridy=1;
		ppanel.add(plPassword, constraints);

		ptfLogin = new JTextField();
//		ptfLogin.setMinimumSize(new Dimension(120, 25));
		constraints.gridx=1;
		constraints.gridy=0;
		ppanel.add(ptfLogin, constraints);
		ptfLogin.setColumns(12);
		
		ppfPassword = new JPasswordField();
		constraints.gridx=1;
		constraints.gridy=1;
		ppanel.add(ppfPassword, constraints);
		
		pbEnviar = new JButton("Enviar");
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=2;
		ppanel.add(pbEnviar, constraints);

		pbEnviar.addActionListener(this);
		ptfLogin.addActionListener(this);
		ppfPassword.addActionListener(this);
		
		add(ppanel);
	}

	public void actionPerformed(ActionEvent e) {
		Usuari user = Conexion.getIUser().getUsuari(ptfLogin.getText());
		if(user != null) {
			if(user.getpPass().equals(ppfPassword.getText())) {
				this.user=user;
				this.hide();
				JOptionPane.showMessageDialog(null, "Usuari y contraseña correctos", "Log in", JOptionPane.INFORMATION_MESSAGE);
				try {
					this.setClosed(true);
				} catch (PropertyVetoException e1) {
					System.out.println("Error, no se ha podido cerrar la ventana de login");
				}
				if(user.getpProfile().equals(Main.hmUser.get(UserType.AdministradorUsers))) {
					Tittle.newUser(true);
					Tittle.userInUse("Usuari: "+user.getpName()+" ("+user.getpProfile()+")");
					Tittle.buttonExitLoginText("Salir");
				}else if(user.getpProfile().equals(Main.hmUser.get(UserType.ScrumMaster))){
					Tittle.newProyect(true);
					Tittle.seeProyects(true);
					Tittle.userInUse("Usuari: "+user.getpName()+" ("+user.getpProfile()+")");
					Tittle.buttonExitLoginText("Salir");
				}else if(user.getpProfile().equals(Main.hmUser.get(UserType.ProductOwner))){
					Tittle.seeProyects(true);
					Tittle.userInUse("Usuari: "+user.getpName()+" ("+user.getpProfile()+")");
					Tittle.buttonExitLoginText("Salir");
				}else if(user.getpProfile().equals(Main.hmUser.get(UserType.Developer))){
					Tittle.seeProyects(true);
					Tittle.userInUse("Usuari: "+user.getpName()+" ("+user.getpProfile()+")");
					Tittle.buttonExitLoginText("Salir");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error en el login", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Usuario incorrecto", "Error en el login", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static Usuari getUser() {
		return user;
	}

}
