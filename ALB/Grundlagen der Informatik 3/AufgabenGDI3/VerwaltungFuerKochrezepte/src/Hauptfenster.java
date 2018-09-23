import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 25.06.2016.
 */
public class Hauptfenster extends JFrame {
  private JDialog inhalt;
  private JPanel flaeche;
  private SchwierigkeitsgradET schwierigkeitsgradET = new SchwierigkeitsgradET();

  public Hauptfenster() {
    super("Liste der Rezepte");
    setLayout(null);
    setSize(800,500);

    flaeche = new JPanel();
    add(flaeche);
    setVisible(true);

    JButton liste = new JButton("Liste");
    liste.setBounds(20, 20, 120, 40);
    add(liste);
    liste.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listeZeigen();
      }
    });

    JButton erfassung = new JButton("Erfassung");
    erfassung.setBounds(145, 20, 120, 40);
    add(erfassung);
    erfassung.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        rezeptZeigen();
      }
    });

    //Fenster schliessen
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        dispose(); //Freigabe der Ressourcen
        System.exit(0); //Beenden
      }
    });
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Hauptfenster();
      }
    });
  }

  public void listeZeigen() {
    new Liste(this, "Liste der Rezepte");
  }

  public void rezeptZeigen() {
    new Erfassung(this, "Neu Rezept", null);
  }
}
