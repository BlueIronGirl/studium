package de.w3l.anw.avplus.berechtigungssystem;

import java.util.Observable;

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
public class Benutzer extends Observable {
  private int benutzerId;

  private String id;

  private int kennwortHash;

  /* Es folgen sonstige Attribute, wie z.B. Rollen */

  public Benutzer(int benutzerId, String benutzerid, int passwort) {
    this.id = benutzerid;
    this.kennwortHash = passwort;
    this.benutzerId = benutzerId;
  }

  public Benutzer(BenutzerTO benutzerTO) {
    this.id = benutzerTO.id;
    this.benutzerId = benutzerTO.benutzerId;
    this.kennwortHash = benutzerTO.kennwortHash;
  }

  public BenutzerTO getTO() {
    return new BenutzerTO(benutzerId, id, kennwortHash);
  }

  public String getId() {
    return id;
  }

  public int getBenutzerId() {
    return benutzerId;
  }

  public int getKennwortHash() {
    return kennwortHash;
  }

  public void setBenutzerId(int benutzerId) {
    this.benutzerId = benutzerId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setKennwortHash(int kennwortHash) {
    this.kennwortHash = kennwortHash;
  }

  /**
   * Pr�ft, ob das eingegebene Passwort zum Benutzer passt
   */
  public boolean passwortOk(String passwort) {
    return (passwort.hashCode() == this.kennwortHash);
  }
}
