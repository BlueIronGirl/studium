import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 23.07.2016.
 */
public class Fenster extends JFrame {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Fenster();
      }
    });
  }

  public Fenster() {
    super("LayoutManager");
    setSize(500, 300);

    Container container = getContentPane();
    container.setLayout(new BorderLayout(0, 0));
    container.add(BorderLayout.NORTH, new Panel3());
    container.add(BorderLayout.SOUTH, new Panel1());
    container.add(BorderLayout.WEST, new Panel4());
    container.add(BorderLayout.EAST, new Panel5());
    container.add(BorderLayout.CENTER, new Panel2());

    setVisible(true);
  }
}
