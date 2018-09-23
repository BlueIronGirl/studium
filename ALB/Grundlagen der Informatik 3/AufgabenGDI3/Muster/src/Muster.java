import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 04.08.2016.
 */
public class Muster extends JFrame {

  public static void main(String[] args) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        @Override
        public void run() {
          new Muster();
        }
      });
    } catch (Exception e) {
      System.out.println("GUI-Aufbau fehlgeschlagen.");
    }
  }

  public Muster() {
    setSize(400, 300); //Fenstergroesse
    getContentPane().setLayout(null); //Layout-Manager
    //Komponenten hinzufuegen

    setVisible(true); //Fenster sichtbar
    //Programm beenden, wenn Fenster geschlossen wird
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}

