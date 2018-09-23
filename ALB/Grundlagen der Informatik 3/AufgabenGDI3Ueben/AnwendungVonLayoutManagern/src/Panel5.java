import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by Alice on 23.07.2016.
 */
public class Panel5 extends JPanel{
  public Panel5() {
    Container container = this;
    container.setLayout(new FlowLayout());
    container.add(new JLabel("Taschenrechner-Telefon-Kombination"));
  }
}
