import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by Alice on 26.06.2016.
 */
public class Panel5 extends JPanel {
  public Panel5() {
    super();
    Container container = this;
    //kein Layoutmanager sinnvoll (Voreinstellung Flow-Layout)
    JLabel text = new JLabel("Taschenrechner-Telefon-Kombination");
    container.add(text);
  }
}
