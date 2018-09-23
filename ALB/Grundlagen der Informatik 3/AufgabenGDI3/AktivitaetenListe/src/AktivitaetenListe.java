import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 11.06.2016.
 */
public class AktivitaetenListe extends JApplet {
  public void init() {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        @Override
        public void run() {
          initKomponents();
        }
      });
    } catch (Exception e) {
      System.out.println("GUI-Aufbau fehlgeschlagen.");
    }
  }

  public void initKomponents() {
    setSize(300, 200);
    getContentPane().setLayout(null);
    JTextField eingabeFeld = new JTextField();
    JButton kopierButton = new JButton();
    JTextArea liste = new JTextArea();

    liste.setText("Alice Bedow");
    kopierButton.setText("Kopiere");

    eingabeFeld.setBounds(10, 10, 200, 20);
    kopierButton.setBounds(10, 40, 100, 20);
    liste.setBounds(10, 70, 200, 100);

    kopierButton.addActionListener(
        event -> {
          if (eingabeFeld.getText() != null && !eingabeFeld.getText().isEmpty()) {
            if (liste.getText() == null || liste.getText().isEmpty()) {
              liste.setText(eingabeFeld.getText());
            } else {
              liste.append("\n" + eingabeFeld.getText());
            }
            eingabeFeld.setText("");
          }
        }
    );

    add(eingabeFeld);
    add(kopierButton);
    add(liste);
    setVisible(true);
  }
}
