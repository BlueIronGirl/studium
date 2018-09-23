package de.w3l.anw.avplus.gui;

import java.util.Locale;

import de.w3l.anw.avplus.applikationslogik.Kunde;

public class KundeNeuGui extends FormularGUI {
  private static final long serialVersionUID = 1L;
  private Locale meinLocale;
  private KundeFormular kundeF;

  public KundeNeuGui(Locale locale) {
    meinLocale = locale;
    initGUI();
  }

  private void initGUI() {
    getUeberschriftLbl().setText(Messages.getString("AV.CUST_INP")); //$NON-NLS-1$
    kundeF = new KundeFormular(meinLocale);
    getZentralPnl().add(kundeF);
  }
  
  /* Methoden zur Kommunikation zwischen Model und View */

  /**
   * Anzeigen eines Kunden
   *
   * @param kd: Anzuzeigender Kunde
   */
  public void anzeigen(Kunde kd) {
    kundeF.setKunde(kd);
  }

  /**
   * Einlesen eines Kunden aus dem View
   *
   * @return neuer Kunde
   */
  public Kunde einlesen() {
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
}