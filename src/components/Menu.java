package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import design.RoundButton;
import main.Panel;

public class Menu extends JPanel implements ActionListener {

	// Ausgeben des Endergbenisses
	String result;

	// Label für die Abschlussnachricht: Auskunft über einen Sieger oder über ein Unentschieden
	private JLabel endMessage;

	// Knopf zum Starten einer neuen Runde
	private RoundButton again;

	// Hauptpanel-Instanz
	private Panel panel;
	
	public Menu() {

		// Konfigurationen dieses Frames
		this.setLayout(null);
		this.setPreferredSize(new Dimension(800, 800));

		// Erstellen und Aufruf der Konfigurationsmethode des Endnachrichten-Labels
		endMessage = new JLabel();
		setupLabel(endMessage);

		// Erstellen und Aufruf der Konfigurationsmethode des Knopfes zum erneutem Spielen
		again = new RoundButton("New Game", 70);
		setupButton(again);

		// Hinzufügen der wichtigen Komponente zu diesem Frame
		this.add(endMessage);
		this.add(again);
		
	}

	// Konfigurationen für JLabel
	private void setupLabel(JLabel label) {
		label.setBounds(200, 30, 450, 400);
		label.setBackground(Color.decode("#A13B6D"));
		label.setForeground(Color.white);
		label.setFont(new Font("Canva Sans", Font.BOLD, 110));
	}

	// Konfigurationen für JButtons
	private void setupButton(JButton button) {
		button.setBounds(100, 410, 600, 162);
		button.setBackground(Color.decode("#A13B6D"));
		button.setForeground(Color.white);
		button.setFont(new Font("Canva Sans", Font.BOLD, 60));
		button.setFocusable(false);
		button.setBorder(null);
		button.addActionListener(this);
		
		button.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.decode("#A13B6D").darker());
			}
			
			public void mouseExited(MouseEvent e) {
				button.setBackground(Color.decode("#A13B6D"));
			}
			
			public void mousePressed(MouseEvent e) {
				button.setBackground(Color.decode("#A13B6D"));
			}
			
			public void mouseReleased(MouseEvent e) {
				button.setBackground(Color.decode("#A13B6D").darker());
			}
			
		});
		
	}
	
	public void paintComponent(Graphics g) {
		// Hintergrund
		g.setColor(Color.decode("#432355"));
		g.fillRect(0, 0, 800, 800);
		
	}

	// Festlegen des Siegers bzw. des Unentschiedens
	public void setWinner(String result) {
		this.result = result;
		updateEndMessage();
	}

	// Anpassen der Abschlussnachricht
	private void updateEndMessage() {
		endMessage.setText(result);
	}

	// Zurücksetzen des Frames zum Hauptframe des Spiels beim Drücken des Knopfes zum Neustarten des Spiels
	@Override
	public void actionPerformed(ActionEvent e) {
		
		panel = new Panel();
		
		if (e.getSource() == again) {
			
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            
            SwingUtilities.invokeLater(() -> {
                currentFrame.getContentPane().removeAll();
                currentFrame.getContentPane().add(panel);
                currentFrame.revalidate();
                currentFrame.repaint();
            });
			
		}
		
	}

}
