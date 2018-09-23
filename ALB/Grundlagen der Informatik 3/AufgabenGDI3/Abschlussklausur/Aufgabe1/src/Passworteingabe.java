import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 31.07.2016.
 */
public class Passworteingabe extends JFrame {
  public static void main(String[] args) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        @Override
        public void run() {
          new Passworteingabe();
        }
      });
    } catch (Exception e) {
      System.out.println("GUI-Aufbau fehlgeschlagen.");
    }
  }

  public Passworteingabe() {
    setSize(500, 200);
    getContentPane().setLayout(null);
    JPasswordField eingabeFeld = new JPasswordField();
    JButton bestaetigung = new JButton("Bestätigung");
    JProgressBar jProgressBar = new JProgressBar(0, 5); //Laenge max 5
    eingabeFeld.setBounds(10, 10, 300, 30);
    jProgressBar.setBounds(10, 40, 300, 30);
    bestaetigung.setBounds(10, 70, 300, 30);
    add(eingabeFeld);
    add(jProgressBar);
    add(bestaetigung);

    bestaetigung.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (eingabeFeld.getText() != null && eingabeFeld.getText().length() == 5) {
          JOptionPane.showMessageDialog(null, "Passwort ok.",
              "Bestaetigung", JOptionPane.PLAIN_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(null, "Bitte Eingabe überprüfen.",
              "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    eingabeFeld.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) { }

      @Override
      public void keyPressed(KeyEvent e) { }

      @Override
      public void keyReleased(KeyEvent e) {
        //erst bei KeyReleases, da ansonsten der Text noch nicht im Passwortfeld gespeichert ist
        int laenge = eingabeFeld.getPassword().length;
        String text = laenge + " von " + jProgressBar.getMaximum();
        jProgressBar.setValue(laenge); //Wert festlegen
        jProgressBar.setStringPainted(true); //Text anzeigen
        jProgressBar.setString(text); //Anzeigetext aendern
      }
    });
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
