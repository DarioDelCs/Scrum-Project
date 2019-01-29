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
import idao.IUsuari;
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

	public static String sUserGroup;
	
	public Login(JFrame frame, JDesktopPane dPanel) {
		this.pjdPanel = dPanel;
		this.pFrame = frame;
		
		view();

		setTitle("Login");//hard
		setResizable(true);
		setClosable(true);
//		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
		Usuari user = Conexion.iUser.getUsuari(ptfLogin.getText(), ppfPassword.getText());
		if(user != null) {
			sUserGroup=user.getpProfile();
			this.hide();
			JOptionPane.showMessageDialog(null, "Usuari y contraseña correctos", "Log in", JOptionPane.INFORMATION_MESSAGE);
			try {
				this.setClosed(true);
			} catch (PropertyVetoException e1) {
				System.out.println("Error, no se ha podido cerrar la ventana de login");
			}
			if(sUserGroup.equals(Main.hmUser.get(UserType.AdministradorUsers))) {
				Tittle.smiNewUser.setEnabled(true);
				Tittle.slUser.setText("Usuari: "+user.getpName()+" ("+user.getpProfile()+")");//cambiar por nombre y grupo
				Tittle.sbSalirLogin.setText("Salir");
			}else if(sUserGroup.equals(Main.hmUser.get(UserType.ScrumMaster))){
				Tittle.smiNewProyect.setEnabled(true);
				Tittle.slUser.setText("Usuari: "+user.getpName()+" ("+user.getpProfile()+")");//cambiar por nombre y grupo
				Tittle.sbSalirLogin.setText("Salir");
			}else {
				JOptionPane.showMessageDialog(null, "Grupo de usuario no implementado", "Disculpen las molestias", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Usuario/contraseña incorrecto", "Error en el login", JOptionPane.WARNING_MESSAGE);
		}
		
		/*//admin
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e1) {
			System.out.println("Error, no se ha podido cerrar la ventana de login");
		}
		Tittle.smiNewUser.setEnabled(true);
		Tittle.slUser.setText("Usuari: "+ptfLogin.getText());//cambiar por nombre y grupo
		Tittle.sbSalirLogin.setText("Salir");
		
		//scrummaster
		Tittle.smiNewProyect.setEnabled(true);
		//mas todo lo de arriba (menos lo de smiNewUser)
		*/
	}

}
