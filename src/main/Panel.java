package main;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import components.Buttons;
import components.Menu;

// Hauptpanel
public class Panel extends JPanel {

	// Klassen-Instanz für die Eigenschaften, sowie das Verhalten der Knöpfe
	private Buttons buttons;

	// Anordnung der Knöpfe
	private JButton buttonTable[][];
	
	public Panel() {

		// Konfigurationen dieses Panels
		this.setPreferredSize(new Dimension(800, 800));
		this.setLayout(new GridLayout(3, 3));

		buttons = new Buttons();

		// Instanziieren eines 3x3 Arrays für das Layout des Konstrukts
		buttonTable = new JButton[3][3];

		// Zuweisen der Knopfkomponenten auf den Array
		buttonTable = buttons.getButtons();

		// Hinzufügen jedes Knopfes zum Hauptpanel
		for (int i = 0; i < buttonTable.length; i++) {
			for (int j = 0; j < buttonTable[i].length; j++) {
				this.add(buttonTable[i][j]);
			}
		}
		
	}

}

