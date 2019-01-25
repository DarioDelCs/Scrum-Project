package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
	
	private JFrame pFrame;
	private JPanel pCenterPanel;
	private JDesktopPane pjdPanel;
	private Usuari pUsuari;

	private JLabel plNombre, plLogin, plPass, plPass2, plMail;
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
//		setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
//		setLocation(pFrame.getHeight()/2-this.getHeight()/2, pFrame.getWidth()/2-this.getWidth()/2);
		setVisible(true);
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
			IUsuari users = new JPAUsuariImpl();
			users.addUsuari(ptfNombre.getText(), ptfLogin.getText(), ppfPass.getText(), pcbPerfil.getSelectedItem().toString(), ptfMail.getText());
			JOptionPane.showMessageDialog(null, "Usuario insertado", "Insert", JOptionPane.INFORMATION_MESSAGE);
			ptfNombre.setText("");
			ptfLogin.setText("");
			ptfMail.setText("");
			ppfPass.setText("");
			ppfPass2.setText("");
		}
	}

}
