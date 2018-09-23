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
  // TODO: Implementieren private DAO database =
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
    benutzerliste.put(2, new Benutzer(2,
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
    try {
      if (benutzerliste.containsKey(b.getBenutzerId())) {
        throw new BenutzerException("Benutzer schon vorhanden");
      } else {
        benutzerliste.put(b.getBenutzerId(), b);
        database.create(b.getTO());
        b.addObserver(this);
      }
    } catch (Exception e) {
      throw new BenutzerException(e.toString());
    }
  }


  /**
   * Einen bestehenden Benutzer aus Liste und DAO entfernen.
   *
   * @throws BenutzerException
   */
  public void entfernen(Benutzer b) throws
      BenutzerException {
    b.deleteObserver(this);
    benutzerliste.remove(b.getBenutzerId());
    try {
      database.delete(b.getBenutzerId());
    } catch (Exception e) {
      throw new BenutzerException("Benutzer konnte nicht geloescht werden: " + b.getBenutzerId());
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
    Benutzer benutzer = null;

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

  public Benutzer aktualisieren(Benutzer b) throws
      BenutzerException {
    Benutzer benutzer = finden(b.getBenutzerId());
    if (benutzer != null) {
      try {
        benutzer.setBenutzerId(b.getBenutzerId());
        benutzer.setId(b.getId());
        benutzer.setKennwortHash(b.getKennwortHash());
        database.update(benutzer.getTO());
      } catch (Exception ex) {
        throw new BenutzerException("Benutzer konnte nicht aktualisiert werden: " + b.getBenutzerId());
      }
    }
    return benutzer;
  }


  public void update(Observable arg0, Object arg1) {
    Benutzer benutzer = (Benutzer) arg0;
    try {
      database.update(benutzer.getTO());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Benutzer findeUser(String name) {
    Benutzer benutzer = null;
    for (Benutzer b : benutzerliste.values()) {
      if (b.getId().equals(name)) {
        return benutzer;
      }
    }
    benutzer = new Benutzer(database.findeBenutzer(name));
    if (benutzer != null) {
      benutzerliste.put(benutzer.getBenutzerId(), benutzer);
    }
    return benutzer;
  }
}
