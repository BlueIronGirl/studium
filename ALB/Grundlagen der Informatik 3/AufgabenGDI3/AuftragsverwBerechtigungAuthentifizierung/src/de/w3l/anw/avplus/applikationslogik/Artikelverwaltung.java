package de.w3l.anw.avplus.applikationslogik;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import de.w3l.anw.avplus.dao.ArtikelDAO;
import de.w3l.anw.avplus.dao.ArtikelTO;
import de.w3l.anw.avplus.dao.DAOFactory;

/**
 * Verwaltet eine Sammlung von Artikeln und greift daf�r auf
 * ein Artikel Data Access-Objekt (ArtikelDAO) zur�ck.
 */
public class Artikelverwaltung implements Observer {

  private static Artikelverwaltung eineArtikelverwaltung =
      new Artikelverwaltung();

  private HashMap<Integer, Artikel> artikelliste =
      new HashMap<>();

  // Zugriff auf ein ArtikelDAO bekommen
  private ArtikelDAO datenquelle =
      DAOFactory.getInstance().createArtikelDAO();

  private Artikelverwaltung() {
  }

  /**
   * Singleton-Methode zum Zugriff auf das einzige Objekt
   */
  public static Artikelverwaltung getInstance() {
    return eineArtikelverwaltung;
  }

  /**
   * Einen neuen Artikel hinzuf�gen (sowohl in der internen
   * Liste als auch im ArtikelDAO)
   *
   * @throws ArtikelException
   */
  public void hinzufuegen(Artikel art)
      throws ArtikelException {
    int artikelnr = art.getArtikelnr();

    /*
     * Zuerst in der eigenen Liste nachschauen, dann ggf.
     * beim ArtikelDAO nachfragen
     */
    try {
      if ((artikelliste.containsKey(artikelnr))
          || (datenquelle.read(artikelnr) != null))
        throw new ArtikelException(
            "Artikel mit dieser Artikelnummer"
                + " ist schon vorhanden");

      /*
       * Artikel in die eigene Liste schreiben und an
       * ArtikelDAO weiergeben
       */
      artikelliste.put(artikelnr, art);
      datenquelle.create(art.getTO());

      // Auf �nderungen achten
      art.addObserver(this);
    } catch (Exception ex) {
      throw new ArtikelException("Artikel " + artikelnr
          + " konnte nicht gespeichert werden!");
    }
  }

  /**
   * Einen bestehenden Artikel aus Liste und ArtikelDAO
   * entfernen.
   *
   * @throws ArtikelException
   */
  public void entfernen(Artikel art)
      throws ArtikelException {
    int artikelnr = art.getArtikelnr();

    // Nicht mehr auf �nderungen achten
    art.deleteObserver(this);

    // Zuerst in der eigenen Artikelliste l�schen
    artikelliste.remove(artikelnr);

    // dann in der ArtikelDAO
    try {
      datenquelle.delete(artikelnr);
    } catch (Exception ex) {
      throw new ArtikelException("Artikel " + artikelnr
          + " konnte nicht gel�scht werden!");
    }
  }

  /**
   * @param artikelnr
   * @return Artikel, falls ein Artikel mit artikelnr
   * vorhanden ist
   * @throws ArtikelException
   */
  public Artikel finden(int artikelnr)
      throws ArtikelException {
    Artikel ergebnis = null;

    // Erst in der eigenen Artikelliste nachschauen
    ergebnis = artikelliste.get(artikelnr);
    if (ergebnis != null)
      return ergebnis;

    // ... und dann in der Datenhaltungsschicht
    try {
      ArtikelTO ergebnisTO =
          datenquelle.read(artikelnr);
      if (ergebnisTO != null) {
        ergebnis = new Artikel(ergebnisTO);

        // jetzt in die eigene Artikelliste einf�gen
        artikelliste.put(artikelnr, ergebnis);
      }
    } catch (Exception ex) {
      throw new ArtikelException("Artikel " + artikelnr
          + " konnte nicht gefunden werden");
    }
    return ergebnis;
  }

  public Vector<Artikel> findeArtikelMitUnterdeckung()
      throws ArtikelException {
    try {
      Vector<Artikel> ergebnis = new Vector<Artikel>();

      /*
       * Die Aufgabe wird delegiert an die
       * Datenhaltungsschicht, weil dieser bessere
       * Instrumente zum gezielten Suchen von Datens�tzen
       * zur Verf�gung stehen (z.B. SQL). datenquelle ist
       * das ArtikelDAO-Objekt, mit dessen Hilfe auf die
       * Datenhaltungsschicht zugegriffen wird.
       */
      Iterator<ArtikelTO> it =
          datenquelle.findeKleinerMindestbestand();
      while (it.hasNext()) {
        ergebnis.add(new Artikel(it.next()));
      }
      return ergebnis;
    } catch (Exception ex) {
      throw new ArtikelException(
          "Artikel mit Unterdeckung nicht abrufbar.");
    }
  }

  public Artikel aktualisieren(Artikel zuAktArt)
      throws ArtikelException {
    Artikel ergebnis = finden(zuAktArt.getArtikelnr());
    if (ergebnis != null) {
      try {
        ergebnis.setArtikelbezeichnung(zuAktArt
            .getArtikelbezeichnung());
        ergebnis.setBestand(zuAktArt.getBestand());
        ergebnis.setMindestbestand(zuAktArt
            .getMindestbestand());
        ergebnis.setPreis(zuAktArt.getPreis());
        // Auch in der Datenbank aktualisieren
        datenquelle.update(ergebnis.getTO());
        return ergebnis;
      } catch (Exception ex) {
        throw new ArtikelException(
            "Artikel konnte nicht aktualisiert werden: "
                + zuAktArt.getArtikelnr());
      }
    } else {
      throw new ArtikelException(
          "Artikel bei Aktualisierung nicht vorhanden: "
              + zuAktArt.getArtikelnr());
    }
  }

  public void update(Observable o, Object arg) {
    Artikel art = (Artikel) o;
    try {
      datenquelle.update(art.getTO());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
