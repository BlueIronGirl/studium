import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Created by Alice on 26.06.2016.
 */
public class Panel2 extends JPanel {
  public Panel2() {
    super();
    Container container = this;
    //gleich groesse Gitterzellen, sodass alle Tasten des Rechners gleich gross sind
    container.setLayout(new GridLayout(3,3,0,0));
    for(int i=1;i<10;i++) {
      JButton jButton = new JButton(String.valueOf(i));
      if(i==1||i==2) {
        jButton.setEnabled(false);
      }
      container.add(jButton);
    }

  }
}
