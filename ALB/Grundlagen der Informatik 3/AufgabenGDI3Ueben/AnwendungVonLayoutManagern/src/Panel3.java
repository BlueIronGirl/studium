import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Created by Alice on 23.07.2016.
 */
public class Panel3 extends JPanel{
  public Panel3() {
    Container container = this;
    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
    container.add(new JButton("Neu"));
    container.add(new JButton("Speichern"));
    container.add(new JButton("Drucken"));
  }
}
