package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import components.Tittle;
import daoImpl.JPAUsuariImpl;
import idao.IUsuari;
import main.Main;
import model.Usuari;

public class AdminView extends JInternalFrame implements ActionListener{
	
	private IUsuari pUser = new JPAUsuariImpl();
	private JFrame pFrame;
	private JPanel pCenterPanel;
	private JDesktopPane pjdPanel;
	private Usuari pUsuari;

	private JLabel plNombre, plLogin, plPass, plPass2, plMail,plPass2Check;
	private JTextField ptfNombre, ptfLogin, ptfMail;
	private JPasswordField ppfPass, ppfPass2;
	private JComboBox<String> pcbPerfil;
	private JButton pbGenerarPass, pbEnviar;
	
	public AdminView(JFrame frame, JDesktopPane dPanel) {
		this.pFrame = frame;
		this.pjdPanel = dPanel;
		
		view();
	}
	
	private void view() {
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
		ptfLogin.setEditable(false);
	//	ptfLogin.setText(crearLogin());
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
		
		plPass2Check = new JLabel("Las contraseņas han de coincidir");
		constraints.gridx = 2;
		constraints.gridy = 3;
		plPass2Check.setForeground(Color.red);
		pCenterPanel.add(plPass2Check,constraints);
		
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
		pack();
	
//		setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
//		setLocation(pFrame.getHeight()/2-this.getHeight()/2, pFrame.getWidth()/2-this.getWidth()/2);
		setVisible(true);
		setSize(680,532);
		add(pCenterPanel, BorderLayout.CENTER);
	
	}

	public void actionPerformed(ActionEvent e) {
//		if(e.getSource() == Tittle.smiNewUser) {
//			pCenterPanel = new JPanel();
//			pCenterPanel.setLayout(new GridBagLayout());
//			GridBagConstraints constraints = new GridBagConstraints();
//
//			constraints.insets = new Insets(10, 10, 10, 10);
//			constraints.fill = GridBagConstraints.HORIZONTAL;
//			
//			plNombre = new JLabel("Nombre");//hard
//			constraints.gridx=0;
//			constraints.gridy=0;
//			pCenterPanel.add(plNombre, constraints);
//			ptfNombre = new JTextField();
//			constraints.gridx=1;
//			constraints.gridy=0;
//			pCenterPanel.add(ptfNombre, constraints);
//			
//			plLogin = new JLabel("Login generado");//hard		//LUEGO HACER AUTOGENERADO
//			constraints.gridx=0;
//			constraints.gridy=1;
//			pCenterPanel.add(plLogin, constraints);
//			ptfLogin = new JTextField();
//			constraints.gridx=1;
//			constraints.gridy=1;
//			pCenterPanel.add(ptfLogin, constraints);
//			
//			plPass = new JLabel("Password");//hard
//			constraints.gridx=0;
//			constraints.gridy=2;
//			pCenterPanel.add(plPass, constraints);
//			ppfPass = new JPasswordField();
//			constraints.gridx=1;
//			constraints.gridy=2;
//			pCenterPanel.add(ppfPass, constraints);
//			pbGenerarPass = new JButton("Generar password");//hard
//			constraints.gridx=2;
//			constraints.gridy=2;
//			pCenterPanel.add(pbGenerarPass, constraints);
//			pbGenerarPass.addActionListener(this);
//			
//			plPass2 = new JLabel("Repita password");//hard		//LUEGO HACER QUE SE COMPRUEBE
//			constraints.gridx=0;
//			constraints.gridy=3;
//			pCenterPanel.add(plPass2, constraints);
//			ppfPass2 = new JPasswordField();
//			constraints.gridx=1;
//			constraints.gridy=3;
//			pCenterPanel.add(ppfPass2, constraints);
//			
//			plMail = new JLabel("Mail");//hard					//LUEGO HACER QUE SE COMPRUEBE
//			constraints.gridx=0;
//			constraints.gridy=4;
//			pCenterPanel.add(plMail, constraints);
//			ptfMail = new JTextField();
//			constraints.gridx=1;
//			constraints.gridy=4;
//			pCenterPanel.add(ptfMail, constraints);
//			
//			pbEnviar = new JButton("Enviar");//hard
//			constraints.gridx=1;
//			constraints.gridy=5;
//			constraints.gridwidth = 2;
//			pCenterPanel.add(pbEnviar, constraints);
//			pbEnviar.addActionListener(this);
//			
//			//dimension longitud cuadro textos
//			ptfNombre.setColumns(15);
//			constraints.gridwidth = 1;
//			
//			pcbPerfil = new JComboBox<String>();
//			constraints.gridx=0;
//			constraints.gridy=5;
//			pCenterPanel.add(pcbPerfil, constraints);
//			pcbPerfil.addItem("Seleccione un perfil de usuario");
//			pcbPerfil.addItem("ProductOwner");
//			pcbPerfil.addItem("ScrumMaster");
//			pcbPerfil.addItem("Developer");
//			pcbPerfil.addItem("Administrator");
//			
//			setTitle("Nuevo Usuario");//hard
//			setResizable(true);
//			setClosable(true);
//			setDefaultCloseOperation(HIDE_ON_CLOSE);
////			setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
////			setLocation(pFrame.getHeight()/2-this.getHeight()/2, pFrame.getWidth()/2-this.getWidth()/2);
//			setVisible(true);
//			add(pCenterPanel, BorderLayout.CENTER);
		/*}else*/ if(e.getSource() == pbGenerarPass) {
			String key = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String pass = "";
			for (int i = 0; i < 6; i++) {
				pass+=(key.charAt((int)(Math.random() * key.length())));
			}
			System.out.println(Arrays.toString(ppfPass.getPassword()));
			ppfPass.setText(pass);
			ppfPass2.setText(pass);
			
			System.out.println("Auto Pass: "+ ppfPass.getText());
			
		}else if (e.getSource() == pbEnviar){
			/*
			boolean correoOk,passOk;
			correoOk = comprobarEmail(ptfMail.getText());
			passOk = comprobarPass();
			if (correoOk && passOk ) {
			IUsuari users = new JPAUsuariImpl();
			users.addUsuari(ptfNombre.getText(), ptfLogin.getText(), ppfPass.getText(), pcbPerfil.getSelectedItem().toString(), ptfMail.getText());
			JOptionPane.showMessageDialog(null, "Usuario insertado", "Insert", JOptionPane.INFORMATION_MESSAGE);
			ptfNombre.setText("");
			ptfLogin.setText("");
			ptfMail.setText("");
			ppfPass.setText("");
			ppfPass2.setText("");
			} else {
				if (!correoOk) {
					JOptionPane.showMessageDialog(null, "El correo introducido no es un correo electronico valido", "Error", JOptionPane.WARNING_MESSAGE);
				}if (!passOk) {
					
				}
			}
		}*/
		
	}}
	private boolean comprobarEmail(String email) {
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);

		if (mather.find() == true) {
			return true;
		} else {
			return false;
		}

	}
	private boolean comprobarPass() {
		String pass1 = ppfPass.getText();
		String pass2 = ppfPass2.getText();
		if (pass1.equals(pass2) && !pass1.equals("")) {
			return true;
		}else return false;
	}
	private String crearLogin() {
		String userName = "";
		String name="",subname="",subname2;
		String[] parts = ptfNombre.getText().split(" ");
		//Fa falta fer un algoritme adequat que contempli casos com cognoms tal que: de la Rosa....
		for (int i= 0; i< parts.length;++i) {
			if (i== 0) {
				name = parts[0];
			}
			if (i== 1 ||( i == parts.length-1 && parts.length > 1)) {
				subname = parts[i];
			}
		/*
			if (i== 2) {
				subname2 = parts[2];
			}
			*/
		}
		userName = name.charAt(0)+subname;
		int cont = 1;
		ArrayList<Usuari> userList = pUser.getUsuaris();
		boolean noEncontrado = false;
		while (!noEncontrado) {
			noEncontrado = true;
			for (Usuari u : userList) {
				if (u.getpLoginId().equals(userName)) {
					noEncontrado = false;
				}
			}
			if (!noEncontrado) {
				userName = userName+cont;
				++cont;
			}
		}
		return userName;
	}

}
