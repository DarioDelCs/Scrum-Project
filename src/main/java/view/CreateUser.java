package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import daoImpl.Conexion;
import idao.IUsuari;
import model.Usuari;

public class CreateUser extends JInternalFrame implements ActionListener, FocusListener{
	
	private JFrame pFrame;
	private JPanel pCenterPanel;
	private JDesktopPane pjdPanel;
	private Usuari pUsuari;

	private JLabel plNombre, plLogin, plPass, plPass2, plMail,plPass2Check;
	private JTextField ptfNombre, ptfLogin, ptfMail;
	private JPasswordField ppfPass, ppfPass2;
	private JComboBox<String> pcbPerfil;
	private JButton pbGenerarPass, pbEnviar;
	
	public CreateUser(JFrame frame, JDesktopPane dPanel) {
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
		ptfNombre.addFocusListener(this);
		pCenterPanel.add(ptfNombre, constraints);
		
		plLogin = new JLabel("Login generado");//hard		//LUEGO HACER AUTOGENERADO
		constraints.gridx=0;
		constraints.gridy=1;
		pCenterPanel.add(plLogin, constraints);
		ptfLogin = new JTextField();
		ptfLogin.setEditable(false);
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
		
		plPass2Check = new JLabel("Las contraseñas han de coincidir");
		constraints.gridx = 2;
		constraints.gridy = 3;
		plPass2Check.setForeground(Color.red);
		pCenterPanel.add(plPass2Check,constraints);
		plPass2Check.setVisible(false);
		
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
		pcbPerfil.addItem("Product Owner");
		pcbPerfil.addItem("Scrum Master");
		pcbPerfil.addItem("Developer");
		pcbPerfil.addItem("Administrador");
		
		setTitle("Nuevo Usuario");//hard
		setResizable(true);
		setClosable(true);
		pack();
	
//		setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
//		setLocation(pFrame.getHeight()/2-this.getHeight()/2, pFrame.getWidth()/2-this.getWidth()/2);
		setVisible(true);
		setSize(680,532);
		add(pCenterPanel, BorderLayout.CENTER);
	
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pbGenerarPass) {
			String key = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String pass = "";
			for (int i = 0; i < 6; i++) {
				pass+=(key.charAt((int)(Math.random() * key.length())));
			}

			ppfPass.setText(pass);
			ppfPass2.setText(pass);

		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(pass), null);
			JOptionPane.showMessageDialog(null, "La contraseña es: "+pass+"\n(copiada al portapapeles)");
		}else if (e.getSource() == pbEnviar){
			boolean correoOk,passOk;
			if(!ptfNombre.getText().equals("") && pcbPerfil.getSelectedIndex()!=0) {
				correoOk = comprobarEmail(ptfMail.getText());
				passOk = comprobarPass();
				if (correoOk && passOk ) {
					
					Conexion.getConexion().addUsuari(ptfNombre.getText(), ptfLogin.getText(), ppfPass.getText(), pcbPerfil.getSelectedItem().toString(), ptfMail.getText());
					JOptionPane.showMessageDialog(null, "Usuario insertado", "Insert", JOptionPane.INFORMATION_MESSAGE);
					ptfNombre.setText("");
					ptfLogin.setText("");
					ptfMail.setText("");
					ppfPass.setText("");
					ppfPass2.setText("");
				} else {
					if (!correoOk) {
			            JOptionPane.showMessageDialog(null, "El formato del email no es valido", "Error email", JOptionPane.ERROR_MESSAGE);
			            ptfMail.setText("");
					}
					plPass2Check.setVisible(!passOk);
				}
			}else {
				JOptionPane.showMessageDialog(null, "No puede haber campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	
	private boolean comprobarEmail(String email) {
        Pattern pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
	}
	
	private boolean comprobarPass() {
		String pass1 = ppfPass.getText();
		String pass2 = ppfPass2.getText();
		return (pass1.equals(pass2) && !pass1.equals(""));
	}

	public void focusLost(FocusEvent e) {
		if(e.getSource()==ptfNombre) {
			try{
				String[] nom = ptfNombre.getText().split(" ");
				if(!ptfNombre.getText().equals("")) {
					ptfLogin.setText(nom[0].charAt(0)+""+nom[nom.length-2].charAt(0)+""+nom[nom.length-1]);
				}
			}catch (Exception err) {
				JOptionPane.showMessageDialog(null, "Este nombre no es valido", "Error", JOptionPane.WARNING_MESSAGE);
				ptfNombre.setText("");
			}
		}
	}
	
	public void focusGained(FocusEvent e) {	
	}

}
