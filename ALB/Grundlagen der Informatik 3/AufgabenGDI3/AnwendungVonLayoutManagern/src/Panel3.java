import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Created by Alice on 26.06.2016.
 */
public class Panel3 extends JPanel {
  public Panel3() {
    super();
    Container container = this;
    //horizontal aneinander gereiht
    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
    container.add(new JButton("Neu"));
    container.add(new JButton("Speichern"));
    container.add(new JButton("Drucken"));
  }
}
