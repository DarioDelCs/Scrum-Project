package main;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import daoImpl.JPAUsuariImpl;
import idao.IUsuari;
import view.Login;

public class Main extends JFrame{

	private IUsuari pUser = new JPAUsuariImpl();
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		JDesktopPane panel = new JDesktopPane();
		panel.setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		
		addFrame(panel);
		
		if(pUser.isConnected()) {
			setTitle("Scrum Program - (ONLINE)");
		}else {
			setTitle("Scrum Program - (OFFLINE)");
		}
		add(panel, BorderLayout.CENTER);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private void addFrame(JDesktopPane panel) {
		panel.add(new Login(this, panel), BorderLayout.CENTER);
	}

}
