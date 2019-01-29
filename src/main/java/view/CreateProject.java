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
import javax.swing.JTextArea;
import javax.swing.JTextField;


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
		// if(e.getSource() == Tittle.smiNewUser) {
				pCenterPanel = new JPanel();
				pCenterPanel.setLayout(new GridBagLayout());
				GridBagLayout gridBagLayout = new GridBagLayout();
				gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
				gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0, 0, 0};
				gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
				gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				getContentPane().setLayout(gridBagLayout);
				
				plNombreProyecto = new JLabel("Nombre Proyecto: ");
				GridBagConstraints gbc_plNombre = new GridBagConstraints();
				gbc_plNombre.fill = GridBagConstraints.HORIZONTAL;
				gbc_plNombre.insets = new Insets(0, 0, 5, 5);
				gbc_plNombre.gridx = 1;
				gbc_plNombre.gridy = 1;
				getContentPane().add(plNombreProyecto, gbc_plNombre);
				
				ptfNombreProyecto = new JTextField();
				GridBagConstraints gbc_ptfNombre = new GridBagConstraints();
				gbc_ptfNombre.gridwidth = 2;
				gbc_ptfNombre.insets = new Insets(0, 0, 5, 5);
				gbc_ptfNombre.fill = GridBagConstraints.HORIZONTAL;
				gbc_ptfNombre.gridx = 2;
				gbc_ptfNombre.gridy = 1;
				getContentPane().add(ptfNombreProyecto, gbc_ptfNombre);
				ptfNombreProyecto.setColumns(10);
				
				plDescripcion = new JLabel("Descripci\u00F3n:");
				GridBagConstraints gbc_plDescripcin = new GridBagConstraints();
				gbc_plDescripcin.fill = GridBagConstraints.HORIZONTAL;
				gbc_plDescripcin.insets = new Insets(0, 0, 5, 5);
				gbc_plDescripcin.anchor = GridBagConstraints.EAST;
				gbc_plDescripcin.gridx = 1;
				gbc_plDescripcin.gridy = 3;
				getContentPane().add(plDescripcion, gbc_plDescripcin);
				
				ptfDescripcion = new JTextArea();
				GridBagConstraints gbc_textArea = new GridBagConstraints();
				gbc_textArea.gridheight = 2;
				gbc_textArea.gridwidth = 2;
				gbc_textArea.insets = new Insets(0, 0, 5, 5);
				gbc_textArea.fill = GridBagConstraints.BOTH;
				gbc_textArea.gridx = 2;
				gbc_textArea.gridy = 3;
				getContentPane().add(ptfDescripcion, gbc_textArea);
				
				plScrumMaster = new JLabel("Scrum Master:");
				GridBagConstraints gbc_plScrumMaster = new GridBagConstraints();
				gbc_plScrumMaster.fill = GridBagConstraints.HORIZONTAL;
				gbc_plScrumMaster.anchor = GridBagConstraints.EAST;
				gbc_plScrumMaster.insets = new Insets(0, 0, 5, 5);
				gbc_plScrumMaster.gridx = 1;
				gbc_plScrumMaster.gridy = 6;
				getContentPane().add(plScrumMaster, gbc_plScrumMaster);
				
				pcbScrumMaster = new JComboBox();
				GridBagConstraints gbc_pcbProductOwner = new GridBagConstraints();
				gbc_pcbProductOwner.gridwidth = 2;
				gbc_pcbProductOwner.insets = new Insets(0, 0, 5, 5);
				gbc_pcbProductOwner.fill = GridBagConstraints.HORIZONTAL;
				gbc_pcbProductOwner.gridx = 2;
				gbc_pcbProductOwner.gridy = 6;
				getContentPane().add(pcbScrumMaster, gbc_pcbProductOwner);
				
				plProductOwner = new JLabel("Product Owner: ");
				GridBagConstraints gbc_plProductOwner = new GridBagConstraints();
				gbc_plProductOwner.fill = GridBagConstraints.HORIZONTAL;
				gbc_plProductOwner.anchor = GridBagConstraints.EAST;
				gbc_plProductOwner.insets = new Insets(0, 0, 5, 5);
				gbc_plProductOwner.gridx = 1;
				gbc_plProductOwner.gridy = 8;
				getContentPane().add(plProductOwner, gbc_plProductOwner);
				
				pcbProductOwner = new JComboBox();
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.gridwidth = 2;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 2;
				gbc_comboBox.gridy = 8;
				getContentPane().add(pcbProductOwner, gbc_comboBox);
				
				pbAnadir = new JButton("A\u00F1adir:");
				GridBagConstraints gbc_pbAnadir = new GridBagConstraints();
				gbc_pbAnadir.anchor = GridBagConstraints.WEST;
				gbc_pbAnadir.insets = new Insets(0, 0, 5, 5);
				gbc_pbAnadir.gridx = 2;
				gbc_pbAnadir.gridy = 10;
				getContentPane().add(pbAnadir, gbc_pbAnadir);
				
				// dimension longitud cuadro textos
						ptfNombreProyecto.setColumns(15);
						//constraints.gridwidth = 1;

				
				setTitle("Nuevo Proyecto");// hard
				setResizable(true);
				setClosable(true);
//				setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
//				setLocation(pFrame.getHeight()/2-this.getHeight()/2, pFrame.getWidth()/2-this.getWidth()/2);
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
