package de.w3l.anw.avplus.dao;

import de.w3l.anw.utility.*;

/**
 * Eine Hilfsklasse zum Transport von Daten zwischen einer
 * technologiespezifischen Implementierung (z.B. JDBC) und
 * der Artikel-Fachklasse. Auf diese Weise muss die
 * JDBC-spezifische Implementierung die Fachklasse nicht
 * kennen.
 */
public class ArtikelTO implements TransferObject {

  private static final long serialVersionUID =
      -1451446324745479570L;

  public int artikelnr;

  public String artikelbezeichnung;

  public int bestand;

  public int mindestbestand;

  public Geld preis;

  public int getKey() {
    return artikelnr;
  }

  public void setKey(int newKey) {
    artikelnr = newKey;
  }
}
