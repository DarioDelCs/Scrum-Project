package components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

public class Specs extends JPanel {
	
	private JComboBox<String> pcbSprints;
	private JLabel plHoras;
	private JSpinner psHoras;
	private JButton pbGuardar;
	private JCheckBox pcbMarcar;
	
	private JScrollPane psPanel;
	private JTextArea ptaDescripcion;
	
	public Specs(int marcada, String Descripcion, double Horas, int IdProject, int Sprint){//la info de las especificaciones
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.insets = new Insets(10, 4, 10, 4);
		constraints.fill = GridBagConstraints.BOTH;
		
		pcbSprints = new JComboBox<String>();
		constraints.gridx=0;
		constraints.gridy=0;
		add(pcbSprints, constraints);
		pcbSprints.addItem("Sprint "+Sprint);
		pcbSprints.setEnabled(false);
		
		plHoras = new JLabel("Numero de horas");
		constraints.gridx=1;
		constraints.gridy=0;
		add(plHoras, constraints);
		
		psHoras = new JSpinner(new SpinnerNumberModel((int)Horas, 0, 99, 1));
		constraints.gridx=2;
		constraints.gridy=0;
		add(psHoras, constraints);
		psHoras.setEnabled(false);
		
		pbGuardar = new JButton("Guradar cambios");
		constraints.gridx=3;
		constraints.gridy=0;
		add(pbGuardar, constraints);
		
		pcbMarcar = new JCheckBox("Marcar");
		constraints.gridx=4;
		constraints.gridy=0;
		add(pcbMarcar, constraints);
		pcbMarcar.setSelected(marcada==1);
		pcbMarcar.setEnabled(false);

		psPanel = new JScrollPane(new JTextArea(Descripcion));
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=5;
		add(psPanel, constraints);
		psPanel.setPreferredSize(new Dimension(0, 80));
	}

}
