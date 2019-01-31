package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import components.Specs;

public class SeeSpecs extends JInternalFrame {

	private JDesktopPane pjdPanel;
	private JFrame pFrame;
	
	private JPanel pNorthPanel, pCenterPanel;
	
	private JButton pbGuardar, pbAnadir, pbEliminar;
	
	public SeeSpecs(JFrame frame, JDesktopPane dPanel) {
		this.pjdPanel = dPanel;
		this.pFrame = frame;
		
		view();

		setTitle("Especificaciones");//hard
		setResizable(true);
		setClosable(true);
//		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//		setSize(this.pFrame.getWidth()/2,this.pFrame.getHeight()/2);
		pack();
//		setLocation(pFrame.getHeight()/2-this.getHeight(), pFrame.getWidth()/2-this.getWidth());
		setVisible(true);
	}
	
	private void view() {
		pCenterPanel = new JPanel();
		pCenterPanel.setLayout(new BoxLayout(pCenterPanel, BoxLayout.Y_AXIS));

		pNorthPanel = new JPanel();
		pNorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		pbGuardar = new JButton("Guardar");
		pNorthPanel.add(pbGuardar);
		
		pbAnadir= new JButton("Añadir");
		pNorthPanel.add(pbAnadir);
		
		pbEliminar= new JButton("Eliminar");
		pNorthPanel.add(pbEliminar);

//		pbEnviar.addActionListener(this);
//		ptfLogin.addActionListener(this);
//		ppfPassword.addActionListener(this);

		for (int i = 0; i < 4; i++) {
			new Specs(pCenterPanel);
		}
		
		add(pCenterPanel, BorderLayout.CENTER);
		add(pNorthPanel, BorderLayout.NORTH);
//		add(pPanel);
	}

}
