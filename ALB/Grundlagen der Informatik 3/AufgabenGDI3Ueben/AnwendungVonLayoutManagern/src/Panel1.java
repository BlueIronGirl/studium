import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Created by Alice on 23.07.2016.
 */
public class Panel1 extends JPanel{
  public Panel1() {
    Container container = this;
    container.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 10));
    JButton jButton1 = new JButton("Ja");
    JButton jButton2 = new JButton("Nein");
    JButton jButton3 = new JButton("Abbrechen");
    container.add(jButton1);
    container.add(jButton2);
    container.add(jButton3);
  }
}
