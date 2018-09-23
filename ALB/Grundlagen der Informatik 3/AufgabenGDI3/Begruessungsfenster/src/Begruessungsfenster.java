import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 11.06.2016.
 */
public class Begruessungsfenster extends JFrame {
  public Begruessungsfenster() {
    init();
  }

  public void init() {
    setSize(400, 300);
    setLayout(null);
    setTitle("Begrüßungsfenster");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(Color.RED);
    JLabel label = new JLabel("Hallo und herzlich willkommen!");
    JLabel label2 = new JLabel("Mein Name ist Alice Bedow");
    label.setBounds(10,10, 300, 20);
    label.setForeground(Color.YELLOW);
    label2.setBounds(10,30, 300, 20);
    label2.setForeground(Color.WHITE);
    add(label);
    add(label2);
    setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Begruessungsfenster();
      }
    });
  }
}
