package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import daoImpl.JPAUsuariImpl;
import idao.IUsuari;
import model.Usuari;

public class Login extends JInternalFrame implements ActionListener{

	private IUsuari pUser = new JPAUsuariImpl();
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
//		ArrayList<Usuari> usuaris= pUser.getUsuaris();
//		for (Usuari usuari : usuaris) {
//			if(ptfLogin.getText().equals(usuari.getName())) {
//				if(ppfPassword.getText().equals(usuari.getPass())) {
//					sUserGroup=usuari.getUserGroup();//usuari.getUserGroup();admin
//					this.hide();
//					JOptionPane.showMessageDialog(null, "usuari y contraseña correctos", "Log in", JOptionPane.OK_OPTION);
//					if(sUserGroup.equals("admin")) {
//						pjdPanel.add(new AdminView(this.pFrame, pjdPanel/*, usuari*/), BorderLayout.CENTER);
//					}else {
//						System.out.println("nooo");
//					}
//				}
//			}
//		}
		sUserGroup="admin";
		this.dispose();
		JOptionPane.showMessageDialog(null, "usuari y contraseña correctos", "Log in", JOptionPane.OK_OPTION);
		if(sUserGroup.equals("admin")) {
			pjdPanel.add(new AdminView(this.pFrame, pjdPanel, new Usuari(0, "Nombre", "123", "admin")), BorderLayout.CENTER);
		}
	}

}
