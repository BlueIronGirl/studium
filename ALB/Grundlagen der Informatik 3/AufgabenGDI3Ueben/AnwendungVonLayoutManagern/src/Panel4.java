import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Created by Alice on 23.07.2016.
 */
public class Panel4 extends JPanel{
  public Panel4() {
    Container container = this;
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton telefon = new JRadioButton("Telefon");
    JRadioButton taschenrechner = new JRadioButton("Taschenrechner");
    buttonGroup.add(telefon);
    buttonGroup.add(taschenrechner);
    container.add(telefon);
    container.add(taschenrechner);
  }
}
