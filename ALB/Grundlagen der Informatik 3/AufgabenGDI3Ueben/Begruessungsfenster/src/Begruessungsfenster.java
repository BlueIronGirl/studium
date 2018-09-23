import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 23.07.2016.
 */
public class Begruessungsfenster extends JFrame {
  public Begruessungsfenster(String titel) {
    super(titel);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        initGui();
      }
    });
  }

  public static void initGui() {
    Begruessungsfenster begruessungsfenster = new Begruessungsfenster("Begrüßungsfenster");
    begruessungsfenster.setSize(400, 300); //Fenstergroesse
    begruessungsfenster.setLayout(null); //Null-Layout
    begruessungsfenster.getContentPane().setBackground(Color.red); //roter Hintergrund
    begruessungsfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Anwendung beenden bei schliessen des Fensters

    JLabel jLabel = new JLabel("Hallo und herzlich Willkommen!");
    jLabel.setForeground(Color.yellow); //Schriftfarbe
    jLabel.setBounds(10, 10, 200, 30);
    begruessungsfenster.add(jLabel);

    JLabel jLabel2 = new JLabel("Mein Name ist Alice Bedow");
    jLabel2.setForeground(Color.white); //Schriftfarbe
    jLabel2.setBounds(10, 50, 200, 30);
    begruessungsfenster.add(jLabel2);

    begruessungsfenster.setVisible(true);
  }
}
