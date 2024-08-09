package design;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class RoundButton extends JButton {

    // Stärke der Eckenrundung des Knopfes
    private final int power;

    public RoundButton(String text, int power) {
    	super(text);
        this.power = power;

        // Entfernen des Standard-Designs des Knopfes
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Aktivieren von Anti-Aliasinng
        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));      

        // Die Füllung des Knopfes
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), power, power));

        // Der Rand des Knopfes
        g2d.setColor(getBackground().darker());
        g2d.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, power, power));

        super.paintComponent(g);
    }

}