import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 31.07.2016.
 */
public class Schachbrett extends JFrame {
  public static void main(String[] args) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        @Override
        public void run() {
          new Schachbrett();
        }
      });
    } catch (Exception e) {
      System.out.println("GUI-Aufbau fehlgeschlagen.");
    }
  }

  public Schachbrett() {
    setSize(500, 500);
    Container container = this;
    //gleich groesse Gitterzellen, sodass alle Felder gleich gross sind
    //Feldgroesse passt sich automatisch an Fenstergroesse an
    container.setLayout(new GridLayout(8, 8, 0, 0));
    Ausgabe ausgabe = new Ausgabe(); //Ereignisbeobachter
    for (int i = 0; i < 8; i++) { //zeilen
      for (int j = 0; j < 8; j++) { //spalten
        JButton jButton = new JButton();
        jButton.setName(String.valueOf(i) + "," + String.valueOf(j));
        if ((j + i) % 2 == 0) {
          jButton.setBackground(Color.white);
        } else {
          jButton.setForeground(Color.white);
          jButton.setBackground(Color.black);
        }
        container.add(jButton);
        jButton.addActionListener(ausgabe);
      }
    }
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public class Ausgabe implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      JOptionPane.showMessageDialog(null, "Angeklicktes Kästchen: "
          + ((JButton) e.getSource()).getName());
    }
  }
}
