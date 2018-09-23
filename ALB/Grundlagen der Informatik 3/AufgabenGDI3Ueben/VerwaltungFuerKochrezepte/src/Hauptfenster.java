import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 23.07.2016.
 */
public class Hauptfenster extends JFrame {
  private static Listenfenster listenfenster;

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        initGUI();
      }
    });
  }
  public static void initGUI() {
    Hauptfenster hauptfenster = new Hauptfenster();
    hauptfenster.setSize(500,500);
    hauptfenster.setLayout(null);
    hauptfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton liste = new JButton("Liste");
    liste.setBounds(10, 10, 100, 30);
    liste.addActionListener(ae -> {
      listenfenster = new Listenfenster(hauptfenster);
    });
    hauptfenster.add(liste);

    hauptfenster.setVisible(true);
  }
}
