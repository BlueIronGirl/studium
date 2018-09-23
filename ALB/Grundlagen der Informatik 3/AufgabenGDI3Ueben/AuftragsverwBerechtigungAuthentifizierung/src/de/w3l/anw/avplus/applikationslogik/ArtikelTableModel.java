package de.w3l.anw.avplus.applikationslogik;

import java.util.Locale;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import de.w3l.anw.utility.Geld;

public class ArtikelTableModel extends AbstractTableModel {

  private static final long serialVersionUID =
      -8174650765907254516L;

  Vector<Artikel> artikel;

  Locale meinLocale;

  public ArtikelTableModel(Vector<Artikel> artikel,
                           Locale locale) {
    this.artikel = artikel;
    meinLocale = locale;
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    Class<?> klasse = Integer.class;
    if (columnIndex == 1)
      klasse = String.class;
    return klasse;
  }

  public int getColumnCount() {
    return 5;
  }

  @Override
  public String getColumnName(int columnIndex) {
    switch (columnIndex) {
      case (0):
        return "Artikelnr";
      case (1):
        return "Artikelbezeichnung";
      case (2):
        return "Preis";
      case (3):
        return "Mindestbestand";
      case (4):
        return "Bestand";
    }
    return null;
  }

  public int getRowCount() {
    return artikel.size();
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    Artikel art = artikel.get(rowIndex);
    switch (columnIndex) {
      case (0):
        return "" + art.getArtikelnr();
      case (1):
        return art.getArtikelbezeichnung();
      case (2):
        return Geld.getBetragMitKomma(art.getPreis(),
            meinLocale);
      case (3):
        return "" + art.getMindestbestand();
      case (4):
        return "" + art.getBestand();
    }
    return "Fehler";
  }

  @Override
  public boolean isCellEditable(int rowIndex,
                                int columnIndex) {
    // Ver�ndern der Werte ist nicht erlaubt!
    return false;
  }
}
