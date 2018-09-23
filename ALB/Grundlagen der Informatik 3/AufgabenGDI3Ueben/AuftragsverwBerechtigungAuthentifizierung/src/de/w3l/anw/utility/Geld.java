package de.w3l.anw.utility;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Dient zur Abbildung von Geldbetr�gen in verschiedenen
 * W�hrungen
 */
public class Geld implements Comparable<Geld> {

  private long betrag;

  private int anzNachkomma;

  private Currency waehrung;

  /**
   * @param bet      Betrag in W�hrungsuntereinheit (z.B. Cent bei Euro)
   * @param anzNKS   Anzahl der Nachkommastellen bei Obereinheit (z.B. 2 bei
   *                 Euro)
   * @param waehrung W�hrungskennzeichen
   * @throws GeldException
   */
  public Geld(long bet, int anzNKS, Currency waehrung)
      throws GeldException {
    if (anzNKS < 0)
      throw new GeldException(
          "Anzahl Nachkommastellen darf nicht kleiner 0 sein!");
    betrag = bet;
    anzNachkomma = anzNKS;
    this.waehrung = waehrung;
  }

  /**
   * Ein neues Geld-Objekt wird durch Angabe des Betrags als
   * String und der W�hrung erzeugt. F�r die Umwandlung ist
   * das Locale wesentlich. Nachkommastellen, die �ber die
   * Standardnachkommastellen der W�hrung hinausgehen,
   * werden nicht ber�cksichtigt.
   */
  public Geld(String betrag, Locale locale, String waehrung) {
    try {
      this.waehrung = Currency.getInstance(waehrung);
      this.anzNachkomma =
          this.waehrung.getDefaultFractionDigits();

      /*
       * Aus dem String, der ggf. ein Dezimalkomma in
       * lokaler Notation enth�lt, wird eine double-Zahl
       * gewonnen, die dann anschlie�end in eine long
       * umgewandelt wird.
       */
      double bet =
          NumberFormat.getInstance(locale).parse(betrag)
              .doubleValue();

      for (int i = 0; i < anzNachkomma; i++)
        bet = bet * 10;
      this.betrag = (new Double(bet).longValue());
    } catch (Exception ex) {
      throw new GeldException(ex.toString());
    }
  }

  /**
   * Erzeugt ein neues Geld-Objekt als Kopie eines
   * bestehenden.
   */
  public Geld(Geld oldObj) {
    this.betrag = oldObj.betrag;
    this.anzNachkomma = oldObj.anzNachkomma;
    this.waehrung = oldObj.waehrung;
  }

  /**
   * Addieren von zwei Geldbetr�gen
   *
   * @param betrag2
   * @return Summe der Betr�ge
   * @throws GeldException, wenn Betr�ge nicht zueinander passen
   */
  public Geld addieren(Geld betrag2) throws GeldException {
    if (this.istKompatibel(betrag2))
      return new Geld(this.betrag + betrag2.betrag,
          this.anzNachkomma, this.waehrung);
    // Hier kommen wir nur an, wenn die beiden Betr�ge nicht
    // addiert werden k�nnen
    throw new GeldException("Betr�ge sind nicht kompatibel");
  }

  /**
   * Subtrahieren von zwei Geldbetr�gen
   *
   * @param betrag2 Der abzuziehende Betrag
   * @return Differenz der Betr�ge
   * @throws GeldException, wenn Betr�ge nicht zueinander passen
   */
  public Geld subtrahieren(Geld betrag2)
      throws GeldException {
    if (this.istKompatibel(betrag2))
      return new Geld(this.betrag - betrag2.betrag,
          this.anzNachkomma, this.waehrung);

    // Hier kommen wir nur an, wenn die beiden Betr�ge nicht
    // subtrahiert werden k�nnen
    throw new GeldException("Betr�ge sind nicht kompatibel");
  }

  public Geld multiplizieren(int multiplikator)
      throws GeldException {
    return new Geld(this.betrag * multiplikator,
        this.anzNachkomma, this.waehrung);
  }

  public int compareTo(Geld betrag2)
      throws ClassCastException {
    if (this.istKompatibel(betrag2))
      return (new Long(this.betrag)).compareTo(new Long(
          betrag2.betrag));

    // Hier kommen wir nur an, wenn die beiden Betr�ge nicht
    // verglichen werden k�nnen
    throw new ClassCastException(
        "Betr�ge sind nicht kompatibel");
  }

  public boolean istNegativ() {
    return (betrag < 0);
  }

  /**
   * rechnet einen Betrag in eine neue W�hrung um. Noch
   * nicht implementiert.
   *
   * @param neueWaehrung
   * @param anzNks
   * @return
   * @throws GeldException
   */
  public Geld umrechnen(Currency neueWaaehrung, int anzNks)
      throws GeldException {
    /** Noch nicht implementiert */
    return null;
  }

  public int getAnzNachkomma() {
    return anzNachkomma;
  }

  public long getBetrag() {
    return betrag;
  }

  public Currency getWaehrung() {
    return waehrung;
  }

  /**
   * Gibt den Betrag eines Geld-Objekts als double-Wert ohne
   * W�hrungskennzeichen zur�ck.
   */
  public double betragAlsDouble() {
    double divisor = 1;
    for (int i = 0; i < anzNachkomma; i++)
      divisor = divisor * 10;
    return betrag / divisor;
  }

  @Override
  public String toString() {
    return betragAlsDouble() + " "
        + waehrung.getCurrencyCode();
  }

  private boolean istKompatibel(Geld betrag2) {
    boolean ergebnis =
        ((this.waehrung == betrag2.waehrung) && (this.anzNachkomma == betrag2.anzNachkomma));
    return ergebnis;
  }

  /**
   * Statische Hilfemethode, um die lokalisierte Ausgabe von
   * Geldbetr�gen zu vereinfachen.
   *
   * @param geld   Auszugebender Geldbetrag
   * @param locale Lokalisierung, die verwendet werden soll.
   * @return
   */
  public static String getBetragMitKomma(Geld geld,
                                         Locale locale) {
    return NumberFormat.getInstance(locale).format(
        geld.betragAlsDouble());
  }
}
