import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 23.07.2016.
 */
public class AktivitaetenListe extends JApplet {
  public void init() {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        @Override
        public void run() {
          initGUI();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void initGUI() {
    setLayout(null);
    setSize(400, 300);

    final JTextField textField = new JTextField();
    textField.setBounds(10, 10, 200, 30);
    add(textField);

    final JTextArea textbereich = new JTextArea("Bedow Alice");
    textbereich.setBounds(10, 70, 200, 100);
    add(textbereich);

    JButton kopiere = new JButton("Kopiere");
    kopiere.setBounds(10, 40, 100, 30);
    kopiere.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (textField.getText() != null && !textField.getText().isEmpty()) {
          textbereich.append("\n" + textField.getText());
          textField.setText(""); //Textfeld leeren
        }
      }
    });
    add(kopiere);

    setVisible(true);
  }
}
