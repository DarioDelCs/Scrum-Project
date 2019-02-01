package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import components.Specs;

public class SeeSpecs extends JInternalFrame {

	private JDesktopPane pjdPanel;
	private JFrame pFrame;
	
	private JPanel pNorthPanel, pPanelOfScroll;
	private JScrollPane psCenterPanel;
	
	private JButton pbGuardar, pbAnadir, pbEliminar;
	
	public SeeSpecs(JFrame frame, JDesktopPane dPanel) {
		this.pjdPanel = dPanel;
		this.pFrame = frame;
		
		view();

		setTitle("Especificaciones");//hard
		setResizable(true);
		setClosable(true);
//		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(this.pFrame.getWidth()/5*3,this.pFrame.getHeight()/3*2);
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

//		pbEnviar.addActionListener(this);
//		ptfLogin.addActionListener(this);
//		ppfPassword.addActionListener(this);

		for (int i = 0; i < 5; i++) {
			new Specs(pPanelOfScroll);
		}
		
		add(psCenterPanel, BorderLayout.CENTER);
		add(pNorthPanel, BorderLayout.NORTH);
//		add(pPanel);
	}

}
