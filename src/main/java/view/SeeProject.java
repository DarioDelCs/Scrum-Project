package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Project;
import model.UserType;
import daoImpl.Conexion;
import main.Main;

public class SeeProject extends JInternalFrame implements MouseListener {

	private JDesktopPane pjdPanel;
	private JFrame pFrame;
	
	private JPanel pPanel, pPanelS;
	private JScrollPane psPanel;
	
	
	private JLabel plNombre, plOwner, plMaster;
	private JTextField ptfNombre, ptfOwner, ptfMaster;
	
	private JTextArea ptaDescipcion; 
	
	private JButton pbMostrarSpecs;

	private List<Project> projects;
	private Project pProjectInUse;
	
	public SeeProject(JFrame frame, JDesktopPane dPanel) {
		this.pjdPanel = dPanel;
		this.pFrame = frame;
		
		view();

		setTitle("Proyectos");//hard
		setResizable(true);
		setClosable(true);
//		setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
		pack();
//		setLocation(pFrame.getHeight()/2-this.getHeight(), pFrame.getWidth()/2-this.getWidth());
		setVisible(true);
	}
	
	private void view() {
		pPanel = new JPanel();
		pPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill = GridBagConstraints.BOTH;
		
		pPanelS = new JPanel();
		pPanelS.setLayout(new BoxLayout(pPanelS, BoxLayout.Y_AXIS));
		pPanelS.setBackground(Color.WHITE);

		psPanel = new JScrollPane(pPanelS);
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=4;
		pPanel.add(psPanel, constraints);
		psPanel.setPreferredSize(new Dimension(180, 300));
		
		constraints.gridheight=1;
		
		plNombre = new JLabel("Nombre proyecto: ");//hard
		constraints.gridx=1;
		constraints.gridy=0;
		pPanel.add(plNombre, constraints);
		
		ptfNombre = new JTextField();
		constraints.gridx=2;
		constraints.gridy=0;
		pPanel.add(ptfNombre, constraints);
		ptfNombre.setColumns(12);
		
		plOwner = new JLabel("Product Owner: ");//hard
		constraints.gridx=1;
		constraints.gridy=1;
		pPanel.add(plOwner, constraints);
		
		ptfOwner = new JTextField();
		constraints.gridx=2;
		constraints.gridy=1;
		pPanel.add(ptfOwner, constraints);

		plMaster = new JLabel("Scrum Master: ");
		constraints.gridx=1;
		constraints.gridy=2;
		pPanel.add(plMaster, constraints);
		
		ptfMaster = new JTextField();
		constraints.gridx=2;
		constraints.gridy=2;
		pPanel.add(ptfMaster, constraints);

		constraints.gridwidth=2;

		ptaDescipcion = new JTextArea();
		constraints.gridx=1;
		constraints.gridy=3;
		pPanel.add(ptaDescipcion, constraints);

		pbMostrarSpecs = new JButton("Mostrar especificaciones");
		constraints.gridx=1;
		constraints.gridy=5;
		pPanel.add(pbMostrarSpecs, constraints);
		if(Login.sUserGroup.equals(Main.hmUser.get(UserType.ScrumMaster))) {
			pbMostrarSpecs.setEnabled(true);
		}else if(Login.sUserGroup.equals(Main.hmUser.get(UserType.ProductOwner))) {
			pbMostrarSpecs.setEnabled(true);
		}else {
			pbMostrarSpecs.setEnabled(false);
		}

		constraints.gridwidth=1;
		
		projects = Conexion.getIProject().getProjects();
		for (Project project : projects) {
			JLabel label = new JLabel(project.getpName());
			pPanelS.add(label);
			label.addMouseListener(this);
		}
		pbMostrarSpecs.addMouseListener(this);
		
		add(pPanel);
	}
	
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == pbMostrarSpecs) {
//			if(pProjectInUse!=null) {
				pjdPanel.add(new SeeSpecs(pFrame, pjdPanel, pProjectInUse));
//			}
		}else{//si es un JLabel de un panel
			for (Project project : projects) {
				if(((JLabel)e.getSource()).getText()==project.getpName()) {
					ptfNombre.setText(project.getpName());
			    	ptfOwner.setText(Conexion.getIUser().getUsuariFromId(project.getpProductOwner()));
			    	ptfMaster.setText(Conexion.getIUser().getUsuariFromId(project.getpScrumMaster()));
			    	ptaDescipcion.setText(project.getpDescripcion());
			    	pProjectInUse = project;
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	
}
