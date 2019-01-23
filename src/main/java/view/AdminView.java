package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
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

public class AdminView extends JInternalFrame implements ActionListener{
	
	private JFrame pFrame;
	private JPanel pNorthPanel, pCenterPanel;
	private JDesktopPane pjdPanel;
	private Usuari pUsuari;
	
	private JMenuBar ppMenu;
	private JMenu pmProyecto;
	private JMenu pmUsuarios;
	private JMenuItem pmiNewUser;
	
	private JLabel plUser;

	private JLabel plNombre, plLogin, plPass, plPass2, plMail;
	private JTextField ptfNombre, ptfLogin, ptfMail;
	private JPasswordField ppfPass, ppfPass2;
	private JComboBox<String> pcbPerfil;
	private JButton pbGenerarPass, pbEnviar, pbSalir;
	
	public AdminView(JFrame frame, JDesktopPane dPanel, Usuari usuari) {
		this.pFrame = frame;
		this.pjdPanel = dPanel;
		this.pUsuari = usuari;

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
		pNorthPanel = new JPanel();
		pNorthPanel.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.insets = new Insets(0, 10, 0, 10);

		plUser = new JLabel("Usuario: " + pUsuari.getpName() + " ("+pUsuari.getpProfile()+")");//hard
		constraints.gridx=0;
		constraints.gridy=0;
		pNorthPanel.add(plUser, constraints);

		
		pbSalir = new JButton("Salir");//hard
		constraints.gridx=1;
		constraints.gridy=0;
		pNorthPanel.add(pbSalir, constraints);
		
		pbSalir.addActionListener(this);
		
		pjdPanel.add(pNorthPanel, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pmiNewUser) {
			pCenterPanel = new JPanel();
			pCenterPanel.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();

			constraints.insets = new Insets(10, 10, 10, 10);
			constraints.fill = GridBagConstraints.HORIZONTAL;
			
			plNombre = new JLabel("Nombre");//hard
			constraints.gridx=0;
			constraints.gridy=0;
			pCenterPanel.add(plNombre, constraints);
			ptfNombre = new JTextField();
			constraints.gridx=1;
			constraints.gridy=0;
			pCenterPanel.add(ptfNombre, constraints);
			
			plLogin = new JLabel("Login generado");//hard		//LUEGO HACER AUTOGENERADO
			constraints.gridx=0;
			constraints.gridy=1;
			pCenterPanel.add(plLogin, constraints);
			ptfLogin = new JTextField();
			constraints.gridx=1;
			constraints.gridy=1;
			pCenterPanel.add(ptfLogin, constraints);
			
			plPass = new JLabel("Password");//hard
			constraints.gridx=0;
			constraints.gridy=2;
			pCenterPanel.add(plPass, constraints);
			ppfPass = new JPasswordField();
			constraints.gridx=1;
			constraints.gridy=2;
			pCenterPanel.add(ppfPass, constraints);
			pbGenerarPass = new JButton("Generar password");//hard
			constraints.gridx=2;
			constraints.gridy=2;
			pCenterPanel.add(pbGenerarPass, constraints);
			pbGenerarPass.addActionListener(this);
			
			plPass2 = new JLabel("Repita password");//hard		//LUEGO HACER QUE SE COMPRUEBE
			constraints.gridx=0;
			constraints.gridy=3;
			pCenterPanel.add(plPass2, constraints);
			ppfPass2 = new JPasswordField();
			constraints.gridx=1;
			constraints.gridy=3;
			pCenterPanel.add(ppfPass2, constraints);
			
			plMail = new JLabel("Mail");//hard					//LUEGO HACER QUE SE COMPRUEBE
			constraints.gridx=0;
			constraints.gridy=4;
			pCenterPanel.add(plMail, constraints);
			ptfMail = new JTextField();
			constraints.gridx=1;
			constraints.gridy=4;
			pCenterPanel.add(ptfMail, constraints);
			
			pbEnviar = new JButton("Enviar");//hard
			constraints.gridx=1;
			constraints.gridy=5;
			constraints.gridwidth = 2;
			pCenterPanel.add(pbEnviar, constraints);
			pbEnviar.addActionListener(this);
			
			//dimension longitud cuadro textos
			ptfNombre.setColumns(15);
			constraints.gridwidth = 1;
			
			pcbPerfil = new JComboBox<String>();
			constraints.gridx=0;
			constraints.gridy=5;
			pCenterPanel.add(pcbPerfil, constraints);
			pcbPerfil.addItem("Seleccione un perfil de usuario");
			pcbPerfil.addItem("ProductOwner");
			pcbPerfil.addItem("ScrumMaster");
			pcbPerfil.addItem("Developer");
			pcbPerfil.addItem("Administrator");
			
			setTitle("Nuevo Usuario");//hard
			setResizable(true);
			setClosable(true);
			setDefaultCloseOperation(HIDE_ON_CLOSE);
//			setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
//			setLocation(pFrame.getHeight()/2-this.getHeight()/2, pFrame.getWidth()/2-this.getWidth()/2);
			setVisible(true);
			add(pCenterPanel, BorderLayout.CENTER);
		}else if(e.getSource() == pbGenerarPass) {
			String key = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String pass = "";
			for (int i = 0; i < 6; i++) {
				pass+=(key.charAt((int)(Math.random() * key.length())));
			}
			ppfPass.setText(pass);
			ppfPass2.setText(pass);
			
			System.out.println("Auto Pass: "+ ppfPass.getText());
			
		}else if (e.getSource() == pbSalir) {
			if(pCenterPanel!=null) {
				pCenterPanel.setVisible(false);
			}
			pNorthPanel.setVisible(false);
			ppMenu.setVisible(false);
			this.dispose();
			pjdPanel.add(new Login(pFrame, pjdPanel));
		}else if (e.getSource() == pbEnviar){
			IUsuari users = new JPAUsuariImpl();
//			users.addUsuari(ptfNombre.getText(), ptfLogin.getText(), ppfPass.getText(), pcbPerfil.getSelectedItem().toString(), ptfMail.getText());
			JOptionPane.showMessageDialog(null, "Usuario insertado", "Insert", JOptionPane.INFORMATION_MESSAGE);
			ptfNombre.setText("");
			ptfLogin.setText("");
			ptfMail.setText("");
			ppfPass.setText("");
			ppfPass2.setText("");
		}
	}

}
