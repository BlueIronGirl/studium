import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Created by Alice on 25.06.2016.
 */
public class Liste extends JDialog {
  private RezeptModell rezeptModell;
  private JTable table;
  private ListSelectionModel selectionModel;
  private DefaultTableCellRenderer rechts;
  private int selectedRow = 0;
  private JFrame anwendungsfenster;
  private JPanel inhalt;
  private JPanel knopfflaeche;

  public Liste(JFrame anwendungsfenster, String titel) {
    super(anwendungsfenster, titel);
    this.anwendungsfenster = anwendungsfenster;
    rezeptModell = new RezeptModell();
    setSize(anwendungsfenster.getSize());
    setVisible(true);
    inhalt = new JPanel();
    getContentPane().add(inhalt);
    table = new JTable();
    table.setModel(rezeptModell);
    table.setBounds(70, 50, 1000, rezeptModell.getRowCount() * 100);
    table.setToolTipText("Es können nur Zeilen selektiert werden");
    table.setRowSelectionAllowed(true);

    rechts = new DefaultTableCellRenderer();
    rechts.setHorizontalAlignment(JLabel.CENTER);

    ArrayList columns = new ArrayList();
    columns.add(table.getColumn("Nummer"));
    columns.add(table.getColumn("Name"));
    columns.add(table.getColumn("Zubereitung"));
    columns.add(table.getColumn("Zubereitungszeit"));
    columns.add(table.getColumn("Schwierigkeit"));
    columns.add(table.getColumn("Zutaten"));
    renderer(columns);

    selectionModel = table.getSelectionModel();
    selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    selectionModel.addListSelectionListener(ergebnis -> selectedRow = table.getSelectedRow());
    JScrollPane jScrollPane = new JScrollPane(table);
    jScrollPane.setBounds(70, 50, 1000, rezeptModell.getRowCount() * 100);

    knopfflaeche = new JPanel();
    knopfflaeche.setBounds(10,table.getHeight()+20, 500, 40);
    knopfflaeche.setBackground(Color.red);
    add(knopfflaeche);
    System.out.println(table.getHeight());
    JButton button = new JButton("Neu");
    button.setBounds(10,320, 120, 40);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        erfassungOffnen();
      }
    });
    knopfflaeche.add(button);
    getContentPane().add(jScrollPane);

  }

  public void renderer(ArrayList<TableColumn> columns) {
    for (TableColumn column : columns) {
      column.setCellRenderer(rechts);
      column.setHeaderRenderer(rechts);
    }
  }

  public void erfassungOffnen() {
    new Erfassung(anwendungsfenster, "Neu Rezept", (Rezept) rezeptModell.getElement(selectedRow));
  }
}
