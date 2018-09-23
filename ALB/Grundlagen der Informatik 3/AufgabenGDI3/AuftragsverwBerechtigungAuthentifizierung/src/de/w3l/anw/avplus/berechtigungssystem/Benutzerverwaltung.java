package de.w3l.anw.avplus.berechtigungssystem;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import de.w3l.anw.avplus.dao.BenutzerDAO;
import de.w3l.anw.avplus.dao.BenutzerTO;
import de.w3l.anw.avplus.dao.DAOFactory;

/**
 * @author JPriemer Verwaltet eine Sammlung von
 *         Benutzerstamms�tzen und greift daf�r auf ein Benutzer
 *         Data Access-Objekt (BenutzerDAO) zur�ck. Implementierung
 *         ist nicht vollst�ndig. Vergleiche mit Implementierung von
 *         Kundenverwaltung
 */
public class Benutzerverwaltung implements Observer {

  private static Benutzerverwaltung ich =
      new Benutzerverwaltung();

  private HashMap<Integer, Benutzer> benutzerliste =
      new HashMap<>();

  // Zugriff auf ein BenutzerDAO bekommen
  private BenutzerDAO database = DAOFactory.getInstance().createBenutzerDAO();

  private Benutzerverwaltung() {
    /*
     * Achtung: Dies ist nur eine rudiment�re
     * Implementierung um genau zwei Benutzer-Objekte in der
     * Benutzerverwaltung zu haben: Es wird ein Benutzer mit
     * der Kennung "normalbenutzer" und ein Benutzer mit der
     * Kennung "administrator" erzeugt.
     */
    benutzerliste.put(1, new Benutzer(1,
        "normalbenutzer", "kennwort".hashCode()));
    benutzerliste.put(1, new Benutzer(2,
        "administrator", "passwort".hashCode()));
  }

  public static Benutzerverwaltung getInstance() {
    return ich;
  }

  /**
   * Einen neuen Benutzer hinzuf�gen (sowohl in der internen
   * Liste als auch im DAO)
   *
   * @throws BenutzerException
   */

  public void hinzufuegen(Benutzer b) throws
      BenutzerException {
    int benutzerid = b.getBenutzerid();
    try {
      if (benutzerliste.containsKey(benutzerid) && database.read(benutzerid) != null) {
        throw new BenutzerException("Benutzer schon vorhanden.");
      } else {
        database.create(b.getTO());
        benutzerliste.put(benutzerid, b);
        b.addObserver(this); //Als beobachter registrieren
      }
    } catch (Exception e) {
      throw new BenutzerException("Benutzer konnte nicht gespeichert werden.");
    }
  }


  /**
   * Einen bestehenden Benutzer aus Liste und DAO entfernen.
   *
   * @throws BenutzerException
   */

  public synchronized void entfernen(Benutzer b) throws
      BenutzerException {
    int id = b.getBenutzerid();
    if (!benutzerliste.containsKey(id)) {
      throw new BenutzerException("Benutzer ist nicht vorhanden.");
    }
    try {
      benutzerliste.remove(id); //aus map loeschen
      b.deleteObserver(this); //nicht auf aenderungen achten
      database.delete(id); //aus db loeschen
    } catch (Exception e) {
      throw new BenutzerException("Benutzer konnte nicht geloescht werden.");
    }
  }


  /**
   * @param benutzerid
   * @return Benutzer, falls ein Benutzer mit dieser id
   * vorhanden ist
   * @throws BenutzerException
   */
  public Benutzer finden(int benutzerid)
      throws BenutzerException {
    /*
     * Achtung: Dies ist nur eine Testimplementierung ohne
     * Zugriff auf die DAO-Schicht.
     */
    Benutzer benutzer;
    benutzer = benutzerliste.get(benutzerid);
    if (benutzer == null) {
      try {
        BenutzerTO benutzerTO = (BenutzerTO) database.read(benutzerid);
        if (benutzerTO != null) {
          benutzer = new Benutzer(benutzerTO);
          benutzerliste.put(benutzerid, benutzer);
        } else {
          throw new BenutzerException("Benutzer konnte nicht gefunden werden.");
        }
      } catch (Exception e) {
        throw new BenutzerException("Benutzer konnte nicht gefunden werden.");
      }
    }
    return benutzer;
  }

  public Benutzer findUserName(String sName) throws BenutzerException {
    Benutzer benutzer = null;
    benutzer = durchSucheMap(sName);
    if (benutzer == null) {
      benutzer = durcheDB(sName);
    }
    return benutzer;
  }

  public Benutzer durchSucheMap(String sName) {
    for (Benutzer benutzer : benutzerliste.values()) { //benutzer suchen
      if (benutzer.getId().equals(sName)) {
        return benutzer;
      }
    }
    return null;
  }

  public Benutzer durcheDB(String sName) {
    Benutzer benutzer = null;
    BenutzerTO benutzerTO = database.findeBenutzer(sName);
    if (benutzerTO != null) {
      benutzer = new Benutzer(benutzerTO);
    }
    return benutzer;
  }

  /* Aktualisieren eines Benutzer-Stammsatzes */

  public Benutzer aktualisieren(Benutzer b) throws
      BenutzerException {
    Benutzer ziel = finden(b.getBenutzerid());
    if (ziel != null) {
      try {
        database.update(b.getTO());
      } catch (Exception e) {
        throw new BenutzerException("Fehler beim Update.");
      }
    } else {
      throw new BenutzerException("Benutzer konnte bei Update nicht gefunden werden.");
    }
    return ziel;
  }


  public void update(Observable arg0, Object arg1) {
    Benutzer benutzer = (Benutzer) arg0;
    try {
      database.update(benutzer.getTO());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
