package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import daoImpl.Conexion;
import model.Project;

public class CreateSpec extends JInternalFrame implements ActionListener{

	private JFrame pFrame;
	private JDesktopPane pjdPanel;
	private Project pProject;
	private JPanel pCenterPanel;
	
	private JLabel plDesciption, plSprint, plHoras;
	private JTextArea ptaDescription;
	private JComboBox<String> pcbSprint;
	private JSpinner psHoras;
	private JButton pbAnadir;
	
	public CreateSpec(JFrame frame, JDesktopPane dPanel, Project project) {
		this.pFrame = frame;
		this.pjdPanel = dPanel;
		this.pProject = project;
		
		view();

		setTitle("Nueva tarea");// hard
		setResizable(true);
		setClosable(true);
		pack();
//		setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
//		setLocation(pFrame.getHeight()/2-this.getHeight()/2, pFrame.getWidth()/2-this.getWidth()/2);
		setVisible(true);
	}
	
	public void view() {
		pCenterPanel = new JPanel();
		pCenterPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 5);
		
		plDesciption = new JLabel("Descripci\u00F3n: ");
		constraints.gridx = 0;
		constraints.gridy = 0;
		pCenterPanel.add(plDesciption, constraints);
		
		ptaDescription = new JTextArea(5,20);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		pCenterPanel.add(ptaDescription, constraints);
		
		constraints.gridwidth = 1;
		
		plSprint = new JLabel("Sprint");
		constraints.gridx = 0;
		constraints.gridy = 1;
		pCenterPanel.add(plSprint, constraints);
		
		pcbSprint = new JComboBox<String>();
		constraints.gridx = 1;
		constraints.gridy = 1;
		pCenterPanel.add(pcbSprint, constraints);
		pcbSprint.addItem("Sprint 1");
		pcbSprint.addItem("Sprint 2");
		pcbSprint.addItem("Sprint 3");
		
		plHoras = new JLabel("Numero de horas");
		constraints.gridx = 2;
		constraints.gridy = 1;
		pCenterPanel.add(plHoras, constraints);
		
		psHoras = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
		constraints.gridx = 3;
		constraints.gridy = 1;
		pCenterPanel.add(psHoras, constraints);
		
		pbAnadir = new JButton("A\u00F1adir");
		constraints.gridx = 4;
		constraints.gridy = 1;
		pCenterPanel.add(pbAnadir, constraints);
		
		pbAnadir.addActionListener(this);
		add(pCenterPanel, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if(!ptaDescription.getText().equals("") && (Integer)psHoras.getValue()!=0) {
			if(!Conexion.getISpecs().existSpec(ptaDescription.getText(), Double.parseDouble(psHoras.getValue().toString()), pProject.getpID(), Integer.parseInt(pcbSprint.getSelectedItem().toString().replaceAll("Sprint ", "")))) {
				if(Conexion.getISpecs().createSpec(ptaDescription.getText(), Double.parseDouble(psHoras.getValue().toString()), pProject.getpID(), Integer.parseInt(pcbSprint.getSelectedItem().toString().replaceAll("Sprint ", "")))) {
					JOptionPane.showMessageDialog(null, "Especificacion añadida", "Insercion", JOptionPane.INFORMATION_MESSAGE);
					ptaDescription.setText("");
					psHoras.setValue(0);
					int option = JOptionPane.showConfirmDialog(null, "Abandonar pantalla para crear Tarea?", "Confirmacion", JOptionPane.OK_CANCEL_OPTION);
					if(option==JOptionPane.OK_OPTION) {
						try {
							this.setClosed(true);
						} catch (PropertyVetoException e1) {
							System.out.println("Error, no se ha podido cerrar la ventana de login");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Error al añadir una especificacion", "Insercion ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}else {
	            JOptionPane.showMessageDialog(null, "El proyecto ya existe", "Error añadir proyecto", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
