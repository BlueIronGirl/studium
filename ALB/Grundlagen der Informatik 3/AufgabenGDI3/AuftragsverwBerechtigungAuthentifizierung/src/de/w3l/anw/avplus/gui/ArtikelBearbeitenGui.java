package de.w3l.anw.avplus.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import de.w3l.anw.avplus.applikationslogik.Artikel;
import de.w3l.anw.avplus.applikationslogik.AuftragTableModel;

public class ArtikelBearbeitenGui extends FormularGUI {
  private static final long serialVersionUID = 1L;

  private Locale meinLocale;
  private ArtikelFormular artikelF;
  private JTable auftraegeTbl;

  private AVSchaltflaeche loeschenBtn;

  public ArtikelBearbeitenGui(java.util.Locale meinLocale) {
    super(meinLocale);
    this.meinLocale = meinLocale;
    initGUI();
  }

  private void initGUI() {
    setPreferredSize(new Dimension(590, 414));
    try {
      getUeberschriftLbl().setText(
          Messages.getString("AV.ART_UPD"));
      loeschenBtn = new AVSchaltflaeche(Messages
          .getString("AV.ARTBTN_DELETE"), "DELETE");
      getSchaltflaechenPnl().add(loeschenBtn, -1);

      artikelF = new ArtikelFormular(meinLocale);

      JSplitPane jsplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
      jsplitPane.add(artikelF, JSplitPane.TOP);

      JScrollPane jScrollPane1 = new JScrollPane();
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

      jsplitPane.add(jScrollPane1, JSplitPane.BOTTOM);

      this.add(jsplitPane, BorderLayout.CENTER);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setTableModel(AuftragTableModel model) {
    auftraegeTbl.setModel(model);
  }

  public AVSchaltflaeche getLoeschenBtn() {
    return loeschenBtn;
  }
  
  /* Methoden zur Kommunikation zwischen Model und View */

  /**
   * Anzeigen eines Artikels
   *
   * @param art: Anzuzeigender Artikel
   */
  public void setArtikel(Artikel art) {
    artikelF.setArtikel(art);
  }

  /**
   * Einlesen eines Artikels aus dem View
   *
   * @return neuer Artikel
   * @throws ParseException
   */
  public Artikel getArtikel() throws ParseException {
    return artikelF.getArtikel();
  }

  /*
   * Hilfsmethoden, um den Umgang mit dem GUI zu erleichtern
   */

  /**
   * Eingabebildschirm Kundeneingabe in Ausgangszustand
   * versetzen.
   */
  public void loeschen() {
    artikelF.loeschen();
  }
}
