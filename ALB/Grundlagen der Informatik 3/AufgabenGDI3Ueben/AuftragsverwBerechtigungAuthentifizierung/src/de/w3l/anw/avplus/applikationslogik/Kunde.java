package de.w3l.anw.avplus.applikationslogik;

import java.util.Observable;

import de.w3l.anw.avplus.dao.*;

/**
 * Repr�sentiert einen einzelnen Kunden auf Ebene der
 * Anwendungslogik
 */
public class Kunde extends Observable {

  private int kundennr;

  private String anrede;

  private String name;

  private String vorname;

  private String strasse;

  private String plz;

  private String ort;

  private String debitorennr;

  public Kunde(int kundennr, String anrede, String name,
               String vorname, String strasse, String plz, String ort) {
    this.kundennr = kundennr;
    this.anrede = anrede;
    this.name = name;
    this.vorname = vorname;
    this.strasse = strasse;
    this.plz = plz;
    this.ort = ort;
  }

  /**
   * Erzeuge ein Kunde-Objekt aus einem KundeTO-Objekt
   */
  public Kunde(KundeTO kunde) {
    this(kunde.kundennr, kunde.anrede, kunde.name,
        kunde.vorname, kunde.strasse, kunde.plz, kunde.ort);
    setDebitorennr(kunde.debitorennr);
  }

  public KundeTO getTO() {
    return new KundeTO(kundennr, anrede, name, vorname,
        strasse, plz, ort, debitorennr);
  }

  // Getter und Setter-Methoden
  public String getDebitorennr() {
    return debitorennr;
  }

  public void setDebitorennr(String debitorennr) {
    this.debitorennr = debitorennr;
    this.aktualisieren();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
    this.aktualisieren();
  }

  public String getOrt() {
    return ort;
  }

  public void setOrt(String ort) {
    this.ort = ort;
    this.aktualisieren();
  }

  public String getPlz() {
    return plz;
  }

  public void setPlz(String plz) {
    this.plz = plz;
    this.aktualisieren();
  }

  public String getStrasse() {
    return strasse;
  }

  public void setStrasse(String strasse) {
    this.strasse = strasse;
    this.aktualisieren();
  }

  public int getKundennr() {
    return kundennr;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
    this.aktualisieren();
  }

  public String getAnrede() {
    return anrede;
  }

  public void setAnrede(String anrede) {
    this.anrede = anrede;
    this.aktualisieren();
  }

  @Override
  public String toString() {
    return ("" + kundennr + ": " + name + ", " + vorname);
  }

  private void aktualisieren() {
    this.setChanged();
    this.notifyObservers();
  }
}