package view;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import daoImpl.MySQLProjectImpl;
import model.Usuari;

public class CreateProject extends JInternalFrame implements ActionListener {

	private JFrame pFrame;
	private JPanel pCenterPanel;
	private JDesktopPane pjdPanel;
	private Usuari pUsuari;
	
	private MySQLProjectImpl project;

	private JLabel plNombreProyecto, plDescripcion, plScrumMaster, plProductOwner;
	private JTextField ptfNombreProyecto, ptfDescripcion;
	private JComboBox<String> pcbScrumMaster, pcbProductOwner;
	private JButton pbAnadir;

	public CreateProject(JFrame frame, JDesktopPane dPanel) {
		this.pFrame = frame;
		this.pjdPanel = dPanel;
		
		view();
	}

	public void view() {
		// if(e.getSource() == Tittle.smiNewUser) {
		pCenterPanel = new JPanel();
		pCenterPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;

		plNombreProyecto = new JLabel("Nombre");// hard
		constraints.gridx = 0;
		constraints.gridy = 0;
		pCenterPanel.add(plNombreProyecto, constraints);
		ptfNombreProyecto = new JTextField();
		constraints.gridx = 1;
		constraints.gridy = 0;
		pCenterPanel.add(ptfNombreProyecto, constraints);

		plDescripcion = new JLabel("Descripcion");
		constraints.gridx = 0;
		constraints.gridy = 1;
		pCenterPanel.add(plDescripcion, constraints);
		ptfDescripcion = new JTextField();
		constraints.gridx = 1;
		constraints.gridy = 1;
		pCenterPanel.add(ptfDescripcion, constraints);

		pbAnadir = new JButton("Anadir");// hard
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		pCenterPanel.add(pbAnadir, constraints);
		pbAnadir.addActionListener(this);

		// dimension longitud cuadro textos
		ptfNombreProyecto.setColumns(15);
		constraints.gridwidth = 1;

		pcbProductOwner = new JComboBox<String>();
		constraints.gridx = 0;
		constraints.gridy = 5;
		pCenterPanel.add(pcbProductOwner, constraints);
//		project.getUsers("ProductOwner");//Metodo para coger a los ProductOwners
	
		pcbScrumMaster = new JComboBox<String>();
		constraints.gridx = 0;
		constraints.gridy = 5;
		pCenterPanel.add(pcbScrumMaster, constraints);
//		project.getUsers("ScrumMaster");//Metodo para coger a los ScrumMasters
		
		setTitle("Nuevo Proyecto");// hard
		setResizable(true);
		setClosable(true);
//		setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
//		setLocation(pFrame.getHeight()/2-this.getHeight()/2, pFrame.getWidth()/2-this.getWidth()/2);
		setVisible(true);
		setSize(680,532);
		add(pCenterPanel, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pbAnadir) {
			ptfNombreProyecto.setText("");
			ptfDescripcion.setText("");

		}
	}
	
	

}
