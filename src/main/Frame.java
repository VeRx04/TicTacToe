package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

// Hauptframe des Projekts
public class Frame extends JFrame {

	public Frame() {

		// Konfigurationen dieses Frames
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Panel());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setIconImage(new ImageIcon(getClass().getResource("/tictactoe.png")).getImage());
		
	}

}
