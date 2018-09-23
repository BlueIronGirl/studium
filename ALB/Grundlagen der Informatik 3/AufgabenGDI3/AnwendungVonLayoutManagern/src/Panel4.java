import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Created by Alice on 26.06.2016.
 */
public class Panel4 extends JPanel {
  public Panel4() {
    super();
    Container container = this;
    //vertikal aneinander gereiht
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    ButtonGroup auswahl = new ButtonGroup();
    JRadioButton telefon = new JRadioButton("Telefon");
    JRadioButton taschenrechner = new JRadioButton("Taschenrechner");
    telefon.setSelected(true);
    auswahl.add(telefon);
    auswahl.add(taschenrechner);
    container.add(telefon);
    container.add(taschenrechner);
  }
}
