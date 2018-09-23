import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Created by Alice on 31.07.2016.
 */
public class EreignisBeobachterKlasse implements ActionListener {
  private JTextField eingabe;
  private JTextArea ausgabe;

  public EreignisBeobachterKlasse(JTextField eingabe, JTextArea ausgabe) {
    this.eingabe = eingabe;
    this.ausgabe = ausgabe;
  }

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