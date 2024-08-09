package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Buttons implements ActionListener {

	// Zum Angeben, welcher Spieler dran ist, X fängt an
	private int round;

	// Zum Überprüfen, ob die Runde zuende ist
	private boolean roundOver;

	// Instanz für Menü-Komponente
	private Menu menu;

	// Spielfelder des Konstrukts
	private JButton buttons[][];

	// Ergebnis der Runde: Wer hat gewonnen / Kam es zu einem Unentschieden?
	private String result;

	// Überprüft den Zustand des Spiels
	private int check[][];

	// Zählervariable für die Anzahl der bereits belegten Spielfelder
	private int counter;

	public Buttons() {

		// Startzustand des Spiels
		check = new int[3][3];
		counter = 0;

		// Initialisieren jeden Indexes des Arrays mit einer 2, welche für ein leeres Feld steht
		for (int i = 0; i < check.length; i++) {
			Arrays.fill(check[i], 2);
		}

		// Knöpfe erstellen
		buttons = new JButton[3][3];
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j] = new JButton();
			}
		}

		// Anwenden der Konfigurationen auf jedem Knopf
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				setupButtons(buttons[i][j]);
			}
		}

		menu = new Menu();

	}

	// Konfigurationen der Knöpfe
	private void setupButtons(JButton button) {

		button.addActionListener(this);
		button.setFont(new Font("Canva Sans", Font.BOLD, 200));
		button.setFocusable(false);
		button.setBackground(new Color(255, 255, 255));

		button.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				button.setBackground(new Color(230, 230, 230));
			}

			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(255, 255, 255));
			}

			public void mousePressed(MouseEvent e) {
				button.setBackground(new Color(255, 255, 255));
			}

			public void mouseReleased(MouseEvent e) {
				button.setBackground(new Color(230, 230, 230));
			}

		});

	}

	// Überprüfen, ob das Spiel beendet ist und ggf. welches Ergebnis erzielt wurde
	private void checkForEnd() {
		if (checkForWinner(1)) {
			roundOver = true;
			result = " X won!";
		} else if (checkForWinner(0)) {
			roundOver = true;
			result = " O won!";
		} else if (isBoardFull()) {
			roundOver = true;
			result = "  Draw";
		}

		if (roundOver) {
			RoundOver();
		}
	}

	// Überprüft, ob ein Sieg erzielt wurde
	private boolean checkForWinner(int player) {
		// Zeilen und Spalten prüfen
		for (int i = 0; i < 3; i++) {
			if ((check[i][0] == player && check[i][1] == player && check[i][2] == player) ||
					(check[0][i] == player && check[1][i] == player && check[2][i] == player)) {
				return true;
			}
		}

		// Diagonalen prüfen
		if ((check[0][0] == player && check[1][1] == player && check[2][2] == player) ||
				(check[0][2] == player && check[1][1] == player && check[2][0] == player)) {
			return true;
		}

		return false;
	}

	// Überprüft, ob das Spielfeld voll ist = Ermitteln eines Unentschiedens
	private boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (check[i][j] == 2) {
					return false;
				}
			}
		}
		return true;
	}


	// Konfigurationen der Knopftexte
	public void setText(JButton button, int i, int j) {
		if (button.getText().isEmpty()) {

			if (round % 2 == 0) {
				button.setText("X");
				check[i][j] = 1;
			} else {
				button.setText("O");
				check[i][j] = 0;
			}

			round++;
			checkForEnd();
		}
	}

	// Getter für das Hauptpanel
	public JButton[][] getButtons() {
		return buttons;
	}

	// Weiterleitung des Fensters zum Menü-Fenster bei Rundenende
	private void RoundOver() {
		if (roundOver) {
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(buttons[0][0]);

			SwingUtilities.invokeLater(() -> {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(menu);
				menu.setWinner(result);
				currentFrame.revalidate();
				currentFrame.repaint();
			});
		}
	}

	// Nach jedem Knopfdruck wird der Knopftext verändert und nach einem Rundenende überprüft
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				if (e.getSource() == buttons[i][j]) {
					setText(buttons[i][j], i, j);
					checkForEnd();
					return;
				}
			}
		}
	}
}
