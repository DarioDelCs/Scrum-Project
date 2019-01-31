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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import daoImpl.Conexion;
import model.Usuari;

public class CreateProject extends JInternalFrame implements ActionListener {

	private JFrame pFrame;
	private JPanel pCenterPanel;
	private JDesktopPane pjdPanel;
	private Usuari pUsuari;

	private JLabel plNombreProyecto, plDescripcion, plScrumMaster, plProductOwner;
	private JTextField ptfNombreProyecto;
	private JTextArea ptfDescripcion;
	private JComboBox<String> pcbScrumMaster, pcbProductOwner;
	private JButton pbAnadir;

	public CreateProject(JFrame frame, JDesktopPane dPanel) {
		this.pFrame = frame;
		this.pjdPanel = dPanel;
		
		view();
	}

	public void view() {
		pCenterPanel = new JPanel();
		pCenterPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(0, 0, 5, 5);
		
		plNombreProyecto = new JLabel("Nombre Proyecto: ");
		constraints.gridx = 1;
		constraints.gridy = 1;
		pCenterPanel.add(plNombreProyecto, constraints);
		
		ptfNombreProyecto = new JTextField();
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		pCenterPanel.add(ptfNombreProyecto, constraints);
		ptfNombreProyecto.setColumns(15);
		
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.EAST;
		
		plDescripcion = new JLabel("Descripci\u00F3n:");
		constraints.gridx = 1;
		constraints.gridy = 3;
		pCenterPanel.add(plDescripcion, constraints);

		constraints.fill = GridBagConstraints.BOTH;
		
		ptfDescripcion = new JTextArea();
		constraints.gridheight = 2;
		constraints.gridwidth = 2;
		constraints.gridx = 2;
		constraints.gridy = 3;
		pCenterPanel.add(ptfDescripcion, constraints);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.EAST;
		
		plScrumMaster = new JLabel("Scrum Master:");
		constraints.gridx = 1;
		constraints.gridy = 6;
		pCenterPanel.add(plScrumMaster, constraints);
		
		pcbScrumMaster = new JComboBox(Conexion.getIUser().getUersProfilename("ScrumMaster"));
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		pCenterPanel.add(pcbScrumMaster, constraints);

		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.EAST;
		
		plProductOwner = new JLabel("Product Owner: ");
		constraints.gridx = 1;
		constraints.gridy = 8;
		pCenterPanel.add(plProductOwner, constraints);
		
		pcbProductOwner = new JComboBox(Conexion.getIUser().getUersProfilename("ProductOwner"));
		constraints.gridx = 2;
		constraints.gridy = 8;
		constraints.gridwidth = 2;
		pCenterPanel.add(pcbProductOwner, constraints);
		
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.WEST;
		
		pbAnadir = new JButton("A\u00F1adir");
		constraints.gridx = 2;
		constraints.gridy = 10;
		pCenterPanel.add(pbAnadir, constraints);
		pbAnadir.addActionListener(this);
				
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
			if(Conexion.getIProject().existProject(ptfNombreProyecto.getText())) {
				if(Conexion.getIProject().addProject(ptfNombreProyecto.getText(), ptfDescripcion.getText(), pcbScrumMaster.getSelectedItem().toString(), pcbProductOwner.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(null, "Proyecto añadido", "Insercion", JOptionPane.INFORMATION_MESSAGE);
					ptfDescripcion.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Error al añadir un proyecto", "Insercion ERROR", JOptionPane.ERROR_MESSAGE);
				}
				ptfNombreProyecto.setText("");
			}else {
	            JOptionPane.showMessageDialog(null, "El proyecto ya existe", "Error añadir proyecto", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	

}
