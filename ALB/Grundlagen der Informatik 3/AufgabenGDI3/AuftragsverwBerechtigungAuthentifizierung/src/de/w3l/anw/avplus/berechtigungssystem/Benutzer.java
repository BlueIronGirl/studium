package de.w3l.anw.avplus.berechtigungssystem;

import javafx.beans.Observable;

import de.w3l.anw.avplus.dao.BenutzerTO;

/**
 * Klasse zum Festhalten der benutzerspezifischen Daten.
 * Dies sind z.B. der Benutzername und das Kennwort (als
 * Hashwert), die vorhandenen Berechtigungen sowie sonstige
 * Einstellungen des Benutzers (z.B. W�hrungsformate, ...)
 * �ber ein BenutzerTO-Objekt und die �bergeordnete
 * Sammlungsklasse Benutzerverwaltung werden Benutzerdaten
 * aus der Datenbank geladen.
 */
public class Benutzer extends java.util.Observable {

  private int benutzerid;

  private String id;

  private int kennwortHash;

  /* Es folgen sonstige Attribute, wie z.B. Rollen */

  public Benutzer(int benutzerid, String id, int passwort) {
    this.benutzerid = benutzerid;
    this.id = id;
    this.kennwortHash = passwort;
  }

  public String getId() {
    return id;
  }

  public int getBenutzerid() {
    return benutzerid;
  }

  /**
   * Pr�ft, ob das eingegebene Passwort zum Benutzer passt
   */
  public boolean passwortOk(String passwort) {
    return (passwort.hashCode() == this.kennwortHash);
  }

  /**
   * Erzeuge ein Kunde-Objekt aus einem KundeTO-Objekt
   */
  public Benutzer(BenutzerTO kunde) {
    this(kunde.getBenutzerId(), kunde.getId(), kunde.getKennwortHash());
  }

  public BenutzerTO getTO() {
    return new BenutzerTO(benutzerid, id, kennwortHash);
  }
}
