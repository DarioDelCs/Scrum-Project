package main;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import view.Login;

public class Main extends JFrame{
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		JDesktopPane panel = new JDesktopPane();
		panel.setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		
		addFrame(panel);
		
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
