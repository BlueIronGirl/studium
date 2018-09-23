package de.w3l.anw.avplus.applikationslogik;

import java.util.Observable;

import de.w3l.anw.avplus.dao.ArtikelTO;
import de.w3l.anw.utility.Geld;

public class Artikel extends Observable {

  private int artikelnr;
  private String artikelbezeichnung;
  private Geld preis;
  private int mindestbestand;
  private int bestand;

  /**
   * Konstruktor zur Erzeung von Artikel-Objekten. Da
   * artikelnr, bezeichnung, preis, mindestbestand und
   * bestand mussattribute sind, enth�lt der Konstruktor
   * diese als Parameter.
   */
  public Artikel(int artikelnr, String bezeichnung,
                 Geld preis, int mindestbestand, int bestand) {
    this.artikelnr = artikelnr;
    artikelbezeichnung = bezeichnung;
    this.preis = new Geld(preis);
    this.mindestbestand = mindestbestand;
    this.bestand = bestand;
  }

  /**
   * Einen neuen Artikel mit Hilfe eines
   * Artikel-Transfer-Objects (ArtikelTO) erzeugen
   *
   * @param artTO
   */
  public Artikel(ArtikelTO artTO) throws ArtikelException {
    this(artTO.artikelnr, artTO.artikelbezeichnung,
        new Geld(artTO.preis), artTO.mindestbestand,
        artTO.bestand);
  }

  /**
   * Aus einem Artikel ein Artikel-Transfer-Objekt
   * (ArtikelTO) generieren.
   */
  public ArtikelTO getTO() {
    ArtikelTO ergebnis = new ArtikelTO();
    ergebnis.artikelnr = this.artikelnr;
    ergebnis.artikelbezeichnung = this.artikelbezeichnung;
    ergebnis.bestand = this.bestand;
    ergebnis.mindestbestand = this.mindestbestand;
    ergebnis.preis = this.preis;
    return ergebnis;
  }

  private void aktualisieren() {
    this.setChanged(); // Observable hat sich ge�ndert ...
    this.notifyObservers(); // und Observer m�ssen
    // benachrichtigt werden
  }

  // Getter und Setter-Methoden
  public String getArtikelbezeichnung() {
    return artikelbezeichnung;
  }

  public void setArtikelbezeichnung(
      String artikelbezeichnung) throws ArtikelException {
    if ((artikelbezeichnung == null)
        || (artikelbezeichnung.trim().isEmpty())) {
      throw new ArtikelException(
          "Bezeichnung darf nicht leer sein.");
    }
    this.artikelbezeichnung = artikelbezeichnung;
    this.aktualisieren();
  }

  public int getBestand() {
    return bestand;
  }

  public void setBestand(int bestand)
      throws ArtikelException {
    if (bestand < 0) {
      throw new ArtikelException(
          "Bestand kann nicht negativ sein.");
    }
    this.bestand = bestand;
    this.aktualisieren();
  }

  public int getMindestbestand() {
    return mindestbestand;
  }

  public void setMindestbestand(int mindestbestand)
      throws ArtikelException {
    if (mindestbestand < 0) {
      throw new ArtikelException(
          "Mindestbestand kann nicht negativ sein.");
    }
    this.mindestbestand = mindestbestand;
    this.aktualisieren();
  }

  public Geld getPreis() {
    return preis;
  }

  public void setPreis(Geld preis) throws ArtikelException {
    if (preis.istNegativ()) {
      throw new ArtikelException(
          "Preis kann nicht negativ sein.");
    }
    this.preis = preis;
    this.aktualisieren();
  }

  public int getArtikelnr() {
    return artikelnr;
  }
}
