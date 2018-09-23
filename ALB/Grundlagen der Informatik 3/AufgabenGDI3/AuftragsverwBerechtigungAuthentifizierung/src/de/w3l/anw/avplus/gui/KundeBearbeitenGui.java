package de.w3l.anw.avplus.gui;

import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import de.w3l.anw.avplus.applikationslogik.AuftragTableModel;
import de.w3l.anw.avplus.applikationslogik.Kunde;

public class KundeBearbeitenGui extends FormularGUI {
  private static final long serialVersionUID = 1L;

  private Locale meinLocale;

  private KundeFormular kundeF;

  private JTable auftraegeTbl;

  private AVSchaltflaeche kdLoeschenBt;

  public KundeBearbeitenGui(Locale locale) {
    meinLocale = locale;
    kdLoeschenBt =
        new AVSchaltflaeche(
            Messages.getString("AV.CUSTBTN_DELETE"),
            "DELETE");
    getSchaltflaechenPnl().add(kdLoeschenBt);

    getUeberschriftLbl().setText(
        Messages.getString("AV.CUST_UPD")); //$NON-NLS-1$
    kundeF = new KundeFormular(meinLocale);

    JSplitPane splitPane =
        new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    splitPane.add(kundeF, JSplitPane.TOP);

    JScrollPane jScrollPane1 = new JScrollPane();
    {
      TableModel auftraegeTblModel =
          new AuftragTableModel();
      auftraegeTbl = new JTable(auftraegeTblModel);
      jScrollPane1.setViewportView(auftraegeTbl);
      auftraegeTbl.setSize(406, 250);
      auftraegeTbl.setPreferredSize(new java.awt.Dimension(
          406, 32));
      auftraegeTbl.setFont(new java.awt.Font("Dialog", 0,
          10));
      auftraegeTbl.getTableHeader().setFont(
          new java.awt.Font("Dialog", 1, 10));
    }

    splitPane.add(jScrollPane1, JSplitPane.BOTTOM);
    getZentralPnl().add(splitPane);
  }

  /* Methoden zur Kommunikation zwischen Model und View */

  /**
   * Anzeigen eines Kunden
   *
   * @param kd : Anzuzeigender Kunde
   */
  public void setKunde(Kunde kd) {
    kundeF.setKunde(kd);
  }

  /**
   * Einlesen eines Kunden aus dem View
   *
   * @return neuer Kunde
   */
  public Kunde getKunde() {
    return kundeF.getKunde();
  }

  /*
   * Hilfsmethoden, um den Umgang mit dem GUI zu erleichtern
   */

  /**
   * Eingabebildschirm Kundeneingabe in Ausgangszustand
   * versetzen.
   */
  public void loeschen() {
    kundeF.loeschen();
  }

  public AVSchaltflaeche getLoeschenBtn() {
    return kdLoeschenBt;
  }

  public void kundeAnzeigen(Kunde kd) {
    kundeF.setKunde(kd);
  }

  public void setTableModel(
      AuftragTableModel auftragTableModel) {
    auftraegeTbl.setModel(auftragTableModel);
  }
}