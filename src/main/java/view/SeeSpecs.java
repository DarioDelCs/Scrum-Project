package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import components.Specs;
import daoImpl.Conexion;
import model.Especificaciones;
import model.Project;

public class SeeSpecs extends JInternalFrame {

	private JDesktopPane pjdPanel;
	private JFrame pFrame;
	private Project pProject;
	
	private JPanel pNorthPanel, pPanelOfScroll;
	private JScrollPane psCenterPanel;
	
	private JButton pbGuardar, pbAnadir, pbEliminar;
	
	private List<Especificaciones> specs;
	
	public SeeSpecs(JFrame frame, JDesktopPane dPanel, Project project) {
		this.pjdPanel = dPanel;
		this.pFrame = frame;
		this.pProject = project;
		
		view();

		setTitle("Especificaciones");//hard
		setResizable(true);
		setClosable(true);
		setMaximumSize(new Dimension(this.pFrame.getWidth()/5*3,this.pFrame.getHeight()/3*2));
		pack();
//		setLocation(pFrame.getHeight()/2-this.getHeight(), pFrame.getWidth()/2-this.getWidth());
		setVisible(true);
	}
	
	private void view() {
		pPanelOfScroll = new JPanel();
		psCenterPanel = new JScrollPane(pPanelOfScroll);
		pPanelOfScroll.setLayout(new BoxLayout(pPanelOfScroll, BoxLayout.Y_AXIS));

		pNorthPanel = new JPanel();
		pNorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		pbGuardar = new JButton("Guardar");
		pNorthPanel.add(pbGuardar);
		
		pbAnadir= new JButton("Añadir");
		pNorthPanel.add(pbAnadir);
		
		pbEliminar= new JButton("Eliminar");
		pNorthPanel.add(pbEliminar);

//		pbAnadir.addActionListener(this);

		specs = Conexion.getISpecs().getAllSpecs();
		
		for (Especificaciones spec : specs) {
			if(spec.getIdProject()==pProject.getpID()) {
				pPanelOfScroll.add(new Specs(spec.getMarcada(), spec.getDescripcion(), spec.getHoras(), spec.getIdProject(), spec.getSprint()));
			}
		}
		
		add(psCenterPanel, BorderLayout.CENTER);
		add(pNorthPanel, BorderLayout.NORTH);
	}

}
