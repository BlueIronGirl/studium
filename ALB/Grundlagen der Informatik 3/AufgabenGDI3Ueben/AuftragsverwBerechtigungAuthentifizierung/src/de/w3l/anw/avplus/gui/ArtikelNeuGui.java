package de.w3l.anw.avplus.gui;

import java.util.Locale;

import de.w3l.anw.avplus.applikationslogik.Artikel;

public class ArtikelNeuGui extends FormularGUI { // Visuelle Vererbung

  private static final long serialVersionUID = 1L;

  /**
   * Der GUI braucht ein Locale, damit Betr�ge korrekt
   * ausgegeben werden (mit Dezimalpunkt oder Dezimalkomma)
   */
  private Locale meinLocale;

  private ArtikelFormular artikelF;

  public ArtikelNeuGui(Locale locale) {
    super();
    this.meinLocale = locale;
    initGUI();
  }

  public ArtikelNeuGui() {
    super();
    initGUI();
  }

  /**
   * Aufbau der Benutzungsoberfl�che: Belegen des JPanels
   * mit verschiedenen Oberfl�chenelementen.
   */
  private void initGUI() {
    try {
      getUeberschriftLbl().setText(
          Messages.getString("AV.ART_INP")); //$NON-NLS-1$
      artikelF = new ArtikelFormular(meinLocale);
      getZentralPnl().add(artikelF);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Eingabebildschirm Kundeneingabe in Ausgangszustand
   * versetzen.
   */
  public void loeschen() {
    artikelF.loeschen(); // Methode aus ArtikelFormular
  }

  /*
   * Methoden f�r die Kommunikation mit dem View
   */

  /**
   * Anzeigen eines Model-Objekts im View
   *
   * @param art : Anzuzeigender Artikel
   */
  public void anzeigen(Artikel art) {
    artikelF.setArtikel(art);
  }

  /**
   * R�ckgabe der Daten vom View als Model-Objekt
   *
   * @return neuer Artikel
   * @throws java.text.ParseException
   */
  public Artikel einlesen() throws java.text.ParseException {
    return artikelF.getArtikel();
  }
}