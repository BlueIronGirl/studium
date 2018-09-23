import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Created by Alice on 23.07.2016.
 */
public class Listenfenster extends JDialog {
  private JPanel inhalt;
  private JScrollPane scrollPane;
  private JTable tabelle = new JTable();
  private RezeptModell rezeptModell;

  public Listenfenster(JFrame fenster) {
    super(fenster, "Listensicht");
    setSize(500, 300);
    setBackground(Color.gray);
    inhalt = new JPanel();
    add(inhalt);

    rezeptModell = new RezeptModell();
    tabelle.setModel(rezeptModell);
    tabelle.setBounds(10,10, 450, 250);
    scrollPane = new JScrollPane(tabelle);
    add(scrollPane);
    setVisible(true);
  }
}
