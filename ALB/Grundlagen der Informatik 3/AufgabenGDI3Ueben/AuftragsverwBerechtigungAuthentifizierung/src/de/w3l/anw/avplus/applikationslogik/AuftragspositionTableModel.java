package de.w3l.anw.avplus.applikationslogik;

import java.util.ArrayList;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import de.w3l.anw.utility.*;

public class AuftragspositionTableModel extends
    AbstractTableModel {

  private static final long serialVersionUID =
      -1020369865883392372L;

  ArrayList<Auftragsposition> apos;

  private Locale meinLocale;

  public AuftragspositionTableModel(Locale meinLocale) {
    apos = new ArrayList<Auftragsposition>();
    this.meinLocale = meinLocale;
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    switch (columnIndex) {
      case (0):
        return Boolean.class;
      case (1):
        return Integer.class;
      case (2):
        return String.class;
      case (3):
        return Integer.class;
      case (4):
        return Integer.class; // Eigentlich: Geld.class;
      case (5):
        return Integer.class; // Eigentlich: Geld.class;
    }
    return null;
  }

  public int getColumnCount() {
    return 6;
  }

  @Override
  public String getColumnName(int columnIndex) {
    switch (columnIndex) {
      case (0):
        return "L�schen?";
      case (1):
        return "Artikelnr";
      case (2):
        return "Bezeichnung";
      case (3):
        return "Menge";
      case (4):
        return "Einzelpreis";
      case (5):
        return "Gesamtpreis";
    }
    return null;
  }

  public int getRowCount() {
    return apos.size();
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    Auftragsposition auf = apos.get(rowIndex);
    Artikel art = auf.getArtikel();
    switch (columnIndex) {
      case (0):
        return false;
      case (1):
        return art.getArtikelnr();
      case (2):
        return art.getArtikelbezeichnung();
      case (3):
        return auf.getMenge();
      case (4):
        return Geld.getBetragMitKomma(art.getPreis(),
            meinLocale);
      case (5):
        return Geld.getBetragMitKomma(art.getPreis()
            .multiplizieren(auf.getMenge()), meinLocale);
    }
    return null;
  }

  @Override
  public boolean isCellEditable(int rowIndex,
                                int columnIndex) {
    return (columnIndex == 0);
  }

  @Override
  public void setValueAt(Object aValue, int row, int column) {
    fireTableCellUpdated(row, column);
  }

  /**
   * Hinzuf�gen einer Auftragsposition zum
   * AuftragspositionTableModel
   */
  public boolean addAuftragsposition(Auftragsposition aufpos) {
    boolean ergebnis = apos.add(aufpos);
    fireTableDataChanged();
    return ergebnis;
  }

  /**
   * Entfernen der Auftragsposition, die durch rowIndex
   * spezifiziert wird.
   */
  public Auftragsposition removeAuftragsposition(
      int rowIndex) {
    Auftragsposition ergebnis = apos.remove(rowIndex);
    fireTableDataChanged();
    return ergebnis;
  }

  /**
   * Auftragsposition von der durch rowIndex bezeichneten
   * Position zur�ckliefern.
   */
  public Auftragsposition getAuftragsposition(int rowIndex) {
    if (rowIndex < apos.size())
      return apos.get(rowIndex);
    return null;
  }

  /**
   * Gesamtsumme �ber alle Positionen errechnen
   */
  public Geld getTotalAmount(String waehrung) {
    Geld total = new Geld("0", meinLocale, waehrung);
    for (int i = 0; i < getRowCount(); i++) {
      total =
          total.addieren(apos.get(i).getArtikel()
              .getPreis());
    }
    return total;
  }
}
