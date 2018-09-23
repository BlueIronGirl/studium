package de.w3l.anw.avplus.gui;

import java.awt.BorderLayout;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Allgemeine Oberfl�che zur Anzeige einer Liste. Die Daten
 * der Liste m�ssen durch ein TableModel geliefert werden.
 */
public class ListenGui extends javax.swing.JPanel { // Erstellt mit WindowBuilder
  private static final long serialVersionUID = 1L;

  // Elemente einer Liste
  private JScrollPane tabellenPnl;

  private JLabel ueberschriftLbl;

  private JPanel ueberschriftPnl;

  private JTable tabelle;

  @SuppressWarnings("unused")
  private Locale meinLocale;

  public ListenGui(Locale meinLocale) {
    super();
    this.meinLocale = meinLocale;
    initGUI();
  }

  private void initGUI() {
    try {
      BorderLayout thisLayout = new BorderLayout();
      this.setLayout(thisLayout);
      this.setPreferredSize(new java.awt.Dimension(426, 300));
      // �berschrift
      ueberschriftPnl = new JPanel();
      this.add(ueberschriftPnl, BorderLayout.NORTH);
      ueberschriftLbl = new JLabel();
      ueberschriftPnl.add(ueberschriftLbl);
      ueberschriftLbl.setText("Listen�berschrift");
      ueberschriftLbl.setFont(new java.awt.Font("Dialog",
          1, 22));
      ueberschriftLbl
          .setHorizontalAlignment(SwingConstants.CENTER);
      // Listenbereich
      tabellenPnl = new JScrollPane();
      this.add(tabellenPnl, BorderLayout.CENTER);
      // FlowLayout felderPnlLayout = new FlowLayout();
      tabellenPnl.setBorder(new LineBorder(
          new java.awt.Color(0, 0, 0), 1, false));
      tabellenPnl.setPreferredSize(new java.awt.Dimension(
          511, 238));
      // Platzhalter-TableModel f�r Listenbereich
      TableModel tabelleModel =
          new DefaultTableModel(new String[][]{
              {"One", "Two"}, {"Three", "Four"}},
              new String[]{"Column 1", "Column 2"});
      tabelle = new JTable();
      tabellenPnl.setViewportView(getTabelle());
      tabelle.setModel(tabelleModel);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public JScrollPane getTabellenPnl() {
    return tabellenPnl;
  }

  public JLabel getUeberschriftLbl() {
    return ueberschriftLbl;
  }

  public JPanel getUeberschriftPnl() {
    return ueberschriftPnl;
  }

  public JTable getTabelle() {
    return tabelle;
  }

  /**
   * Methoden zum Anzeigen der Liste.
   *
   * @param ueberschrift : Schl�ssel f�r anzuzeigende �berschrift
   * @param daten        : Model f�r Daten
   */
  public void listeAnzeigen(String ueberschrift,
                            TableModel daten) {
    getUeberschriftLbl().setText(
        Messages.getString(ueberschrift));
    getTabelle().setModel(daten);
    this.repaint();
  }
}
