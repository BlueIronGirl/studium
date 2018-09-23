import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * Created by Alice on 26.06.2016.
 */
public class Anwendungsfenster extends JFrame {
  public Anwendungsfenster() {
    super("Layout-Manager");
    setSize(800, 500);

    Container container = getContentPane();
    //fuenf Komponenten koennen verwaltet werden (oben, links, mitte, rechts, unten)
    //kann weg gelassen werden, da Voreinstellung fuer JFrame!
    container.setLayout(new BorderLayout(0,0));

    container.add(BorderLayout.SOUTH,new Panel1());
    container.add(BorderLayout.CENTER,new Panel2());
    container.add(BorderLayout.NORTH,new Panel3());
    container.add(BorderLayout.WEST,new Panel4());
    container.add(BorderLayout.EAST,new Panel5());

    setVisible(true);

    //Fenster schliessen
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        dispose();
        System.exit(0);
      }
    });
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Anwendungsfenster();
      }
    });
  }
}
