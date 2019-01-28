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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import components.Tittle;
import idao.IUsuari;

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
//		boolean badLogin=false;
//		for (int i = 0; i < pUser.countUsers(); i++) {
//			Usuari user = pUser.getUsuari(i);
//			if(ptfLogin.getText().equals(user.getpLoginId())) {
//				if(ppfPassword.getText().equals(user.getpPass())) {
//					sUserGroup=user.getpProfile();
//					this.hide();
//					JOptionPane.showMessageDialog(null, "Usuari y contraseña correctos", "Log in", JOptionPane.INFORMATION_MESSAGE);
//					if(sUserGroup.equals(Main.hmUser.get(UserType.AdministradorUsers))) {
//						Tittle.smiNewUser.setEnabled(true);
//						Tittle.slUser.setText("Usuari: "+ptfLogin.getText());//cambiar por nombre y grupo
////						pjdPanel.add(new AdminView(this.pFrame, pjdPanel));
//					}else {
//						System.out.println("Grupo de usuarios no implementado");
//					}
//					badLogin=false;
//					break;
//				}else {
//					JOptionPane.showMessageDialog(null, "Contraseña incorrecto", "Error en el login", JOptionPane.WARNING_MESSAGE);
//					badLogin = false;
//					break;
//				}
//			}else {
//				badLogin = true;
//			}
//		}
//		if(badLogin) {
//			JOptionPane.showMessageDialog(null, "Usuario incorrecto", "Error en el login", JOptionPane.WARNING_MESSAGE);
//		}
		
		//admin
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
		
		
		
		//como se hacia antes:
		/*ArrayList<Usuari> usuaris= pUser.getUsuaris();
		for (Usuari usuari : usuaris) {
			if(ptfLogin.getText().equals(usuari.getpLoginId())) {
				if(ppfPassword.getText().equals(usuari.getpPass())) {
					sUserGroup=usuari.getpProfile();
					this.hide();
					JOptionPane.showMessageDialog(null, "Usuari y contraseña correctos", "Log in", JOptionPane.INFORMATION_MESSAGE);
					if(sUserGroup.equals(Main.hmUser.get(UserType.AdministradorUsers))) {
						Tittle.smiNewUser.setEnabled(true);
						Tittle.slUser.setText("Usuari: "+ptfLogin.getText());//cambiar por nombre y grupo
//						pjdPanel.add(new AdminView(this.pFrame, pjdPanel));
					}else {
						System.out.println("Grupo de usuarios no implementado");
					}
					badLogin=false;
					break;
				}else {
					badLogin = true;
				}
			}else {
				badLogin = true;
			}
		}*/
	}

}
