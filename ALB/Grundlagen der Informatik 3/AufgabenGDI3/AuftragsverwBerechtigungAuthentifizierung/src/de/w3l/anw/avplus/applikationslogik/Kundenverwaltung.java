package de.w3l.anw.avplus.applikationslogik;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import de.w3l.anw.avplus.dao.DAOFactory;
import de.w3l.anw.avplus.dao.KundeDAO;
import de.w3l.anw.avplus.dao.KundeTO;

/**
 * Verwaltet eine Sammlung von Kunden und greift daf�r auf
 * ein Kunden Data Access-Objekt (KundeDAO) zur�ck.
 */
public class Kundenverwaltung implements Observer {

  private static Kundenverwaltung eineKundenverwaltung =
      new Kundenverwaltung();

  private HashMap<Integer, Kunde> kundenliste =
      new HashMap<>();

  // Zugriff auf ein KundenDAO bekommen
  private KundeDAO datenquelle =
      DAOFactory.getInstance().createKundenDAO();

  public static Kundenverwaltung getInstance() {
    return eineKundenverwaltung;
  }

  private Kundenverwaltung() {
  }

  /**
   * Einen neuen Kunden hinzuf�gen (sowohl in der internen
   * Liste als auch im DAO)
   *
   * @throws KundeException
   */
  public void hinzufuegen(Kunde kd) throws KundeException {
    int kundennr = kd.getKundennr();

    // Zuerst in der eigenen Liste nachschauen, dann ggf.
    // beim ArtikelDAO
    // nachfragen
    try {
      if ((kundenliste.containsKey(kundennr))
          || (datenquelle.read(kundennr) != null))
        throw new ArtikelException(
            "Kunde mit dieser Nummer ist schon vorhanden");

      // Kunden in die eigene Liste schreiben und an DAO
      // weiergeben
      kundenliste.put(kundennr, kd);
      datenquelle.create(kd.getTO());

      // Auf �nderungen achten
      kd.addObserver(this);
    } catch (Exception ex) {
      throw new KundeException("Kunde " + kundennr
          + " konnte nicht gespeichert werden!");
    }
  }

  /**
   * Einen bestehenden Kunden aus Liste und DAO entfernen.
   *
   * @throws KundeException
   */
  public void entfernen(Kunde kd) throws KundeException {
    int nr = kd.getKundennr();

    // Nicht mehr auf �nderungen achten
    kd.deleteObserver(this);

    // Zuerst in der eigenen Liste l�schen
    kundenliste.remove(nr);

    // dann in der DAO
    try {
      datenquelle.delete(nr);
    } catch (Exception ex) {
      throw new KundeException("Kunde " + nr
          + " konnte nicht gel�scht werden!");
    }
  }

  /**
   * @param kundennr
   * @return Kunde, falls ein Kunde mit kundennr vorhanden
   * ist
   * @throws KundeException
   */
  public Kunde finden(int kundennr) throws KundeException {
    Kunde ergebnis = null;

    // Erst in der eigenen Liste nachschauen
    ergebnis = kundenliste.get(kundennr);
    if (ergebnis != null)
      return ergebnis;

    // ... und dann in der DAO
    try {
      KundeTO ergebnisTO =
          datenquelle.read(kundennr);
      if (ergebnisTO != null) {
        ergebnis = new Kunde(ergebnisTO);

        // jetzt in die eigene Liste einf�gen
        kundenliste.put(kundennr, ergebnis);
      }
    } catch (Exception ex) {
      throw new KundeException("Kunde " + kundennr
          + " konnte nicht gefunden werden");
    }
    return ergebnis;
  }

  public Kunde aktualisieren(Kunde zuAktKd)
      throws KundeException {
    Kunde ergebnis = finden(zuAktKd.getKundennr());
    if (ergebnis != null) {
      try {
        ergebnis.setDebitorennr(zuAktKd.getDebitorennr());
        ergebnis.setAnrede(zuAktKd.getAnrede());
        ergebnis.setName(zuAktKd.getName());
        ergebnis.setOrt(zuAktKd.getOrt());
        ergebnis.setPlz(zuAktKd.getPlz());
        ergebnis.setStrasse(zuAktKd.getStrasse());
        // Auch in der Datenbank aktualisieren
        datenquelle.update(ergebnis.getTO());
        return ergebnis;
      } catch (Exception ex) {
        throw new KundeException(
            "Kunde konnte nicht aktualisiert werden: "
                + zuAktKd.getKundennr());
      }
    } else {
      throw new KundeException(
          "Kunde bei Aktualisierung nicht vorhanden: "
              + zuAktKd.getKundennr());
    }
  }

  public void update(Observable o, Object arg) {
    Kunde kd = (Kunde) o;
    try {
      datenquelle.update(kd.getTO());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
