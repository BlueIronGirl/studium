package de.w3l.anw.avplus.dao;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Vector;

public class AuftragTO implements TransferObject {

  private static final long serialVersionUID =
      -5570722863444250367L;

  public int auftragsnr;

  public LocalDate auftragsdatum;

  public LocalDate lieferdatum;

  public int kunde; // bei komplexen unabh�ngigen Objekten

  // nur den Schl�ssel mitnehmen
  public Currency waehrung;

  public Vector<AuftragspositionTO> apositionen =
      new Vector<AuftragspositionTO>();

  public AuftragTO(int auftragsnr, LocalDate auftragsdatum,
                   LocalDate lieferdatum, int kunde, Currency waehrung,
                   Vector<AuftragspositionTO> apositionen) {
    this.auftragsnr = auftragsnr;
    this.auftragsdatum = auftragsdatum;
    this.lieferdatum = lieferdatum;
    this.kunde = kunde;
    this.waehrung = waehrung;
    this.apositionen = apositionen;
  }

  public int getKey() {
    return auftragsnr;
  }

  public void setKey(int newKey) {
    auftragsnr = newKey;
  }
}
