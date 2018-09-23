import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 31.07.2016.
 */
public class Ereignisverarbeitung extends JFrame {
  public static void main(String[] args) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        @Override
        public void run() {
          new Ereignisverarbeitung();
        }
      });
    } catch (Exception e) {
      System.out.println("GUI-Aufbau fehlgeschlagen.");
    }
  }

  private JTextField eingabe = new JTextField();
  private JButton bestaetigung = new JButton("Bestätigung");
  private JTextArea ausgabe = new JTextArea();

  public Ereignisverarbeitung() {
    setSize(400, 300);
    getContentPane().setLayout(null);

    eingabe.setBounds(10, 10, 300, 30);
    bestaetigung.setBounds(10, 40, 300, 30);
    ausgabe.setBounds(10, 70, 300, 100);
//    bestaetigung.addActionListener(new EreignisBeobachterInnereKlasse()); //a
    bestaetigung.addActionListener(new EreignisBeobachterKlasse(eingabe, ausgabe)); //b

    add(eingabe);
    add(bestaetigung);
    add(ausgabe);

    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public class EreignisBeobachterInnereKlasse implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String text = eingabe.getText();
      String ausgabeText = "";
      int z = 0;
      if (text != null) {
        for (int i = text.length() - 1; i >= 0; i--) {
          ausgabeText += text.charAt(i);
          z++;
        }
      }
      if (ausgabe.getText() != null && !ausgabe.getText().isEmpty()) {
        ausgabe.append("\n");
      }
      ausgabe.append(ausgabeText);
    }
  }
}
