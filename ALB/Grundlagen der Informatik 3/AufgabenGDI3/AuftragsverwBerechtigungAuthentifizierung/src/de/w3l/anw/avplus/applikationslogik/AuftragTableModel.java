package de.w3l.anw.avplus.applikationslogik;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import de.w3l.anw.utility.Geld;

public class AuftragTableModel extends AbstractTableModel {

  private static final long serialVersionUID =
      -8174650765907254515L;

  Vector<Auftrag> auftraege;

  private Locale meinLocale;

  /*
   * Der nachfolgende Konstruktor ist nur vorhanden, damit
   * der GUI-Builder die Komponente darstellt.
   */
  public AuftragTableModel() {

  }

  public AuftragTableModel(Vector<Auftrag> auftraege,
                           Locale locale) {
    this.auftraege = auftraege;
    meinLocale = locale;
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    switch (columnIndex) {
      case (0):
        return Integer.class;
      case (1):
        return Integer.class; //Trick f�r rechtsb�ndige Ausgabe; eigentlich String
      case (2):
        return Integer.class; //eigentlich String
      case (3):
        return Kunde.class;
      case (4):
        return Integer.class; // eigentlich Geld
    }
    return null;
  }

  public int getColumnCount() {
    return 5;
  }

  @Override
  public String getColumnName(int columnIndex) {
    switch (columnIndex) {
      case (0):
        return "Auftragsnr";
      case (1):
        return "Auftragsdatum";
      case (2):
        return "Lieferdatum";
      case (3):
        return "Kunde";
      case (4):
        return "Auftragssumme";
    }
    return null;
  }

  public int getRowCount() {
    if (auftraege != null)
      return auftraege.size();
    return 0;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(meinLocale);
    Auftrag auf = auftraege.get(rowIndex);
    switch (columnIndex) {
      case (0):
        return auf.getAuftragsnr();
      case (1):
        return auf.getAuftragsdatum().format(dtf); //Anzeige im lokalen Format
      case (2):
        return auf.getLieferdatum().format(dtf); //Anzeige im lokalen Format
      case (3):
        return auf.getKunde();
      case (4):
        return Geld.getBetragMitKomma(auf.getAuftragssumme(),
            meinLocale);
    }
    return "Fehler";
  }

  @Override
  public boolean isCellEditable(int rowIndex,
                                int columnIndex) {
    return false;
  }
}
