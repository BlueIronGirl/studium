import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Created by Alice on 26.06.2016.
 */
public class Panel1 extends JPanel{
  public Panel1() {
    super();
    Container container = this;
    //sinnvoll fuer hintereinander Anordnen von Druckknoepfen
    container.setLayout(new FlowLayout());
    container.add(new JButton("Ja"));
    container.add(new JButton("Nein"));
    container.add(new JButton("Abbrechen"));
  }
}
