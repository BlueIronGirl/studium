package de.w3l.anw.avplus.applikationslogik;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import de.w3l.anw.avplus.dao.AuftragDAO;
import de.w3l.anw.avplus.dao.AuftragTO;
import de.w3l.anw.avplus.dao.DAOFactory;

/**
 * @author JPriemer Verwaltet eine Sammlung von Auftr�gen
 *         und greift daf�r auf ein Data Access-Objekt zur�ck.
 */
public class Auftragsverwaltung implements Observer {

  private static Auftragsverwaltung eineAuftragsverwaltung =
      new Auftragsverwaltung();

  // Zugriff auf ein DAO bekommen
  private AuftragDAO datenquelle =
      DAOFactory.getInstance().createAuftragDAO();

  public static Auftragsverwaltung getInstance() {
    return eineAuftragsverwaltung;
  }

  public void hinzufuegen(Auftrag auf)
      throws AuftragException {
    int auftragsnr = auf.getAuftragsnr();

    try {
      if (datenquelle.read(auftragsnr) != null)
        throw new AuftragException(
            "Auftrag mit dieser Nummer ist schon vorhanden");

      // Neuen Auftrag an DAO weiergeben
      datenquelle.create(auf.getTO());

      // Auf �nderungen achten
      auf.addObserver(this);
    } catch (Exception ex) {
      throw new AuftragException("Auftrag " + auftragsnr
          + " konnte nicht gespeichert werden!");
    }
  }

  /**
   * Einen bestehenden Auftrag entfernen.
   *
   * @throws AuftragException
   */
  public void entfernen(Auftrag auf)
      throws AuftragException {
    int nr = auf.getAuftragsnr();

    // Nicht mehr auf �nderungen achten
    auf.deleteObserver(this);

    // Im DAO l�schen
    try {
      datenquelle.delete(nr);
    } catch (Exception ex) {
      throw new AuftragException("Auftrag " + nr
          + " konnte nicht gel�scht werden!");
    }
  }

  /**
   * @param auftragsnr
   * @return Auftrag, falls ein Auftrag mit dieser Nummer
   * vorhanden ist
   * @throws AuftragException
   */
  public Auftrag finden(int auftragsnr)
      throws AuftragException {
    Auftrag ergebnis = null;

    // Im DAO nachschauen
    try {
      AuftragTO ergebnisTO =
          (AuftragTO) datenquelle.read(auftragsnr);
      if (ergebnisTO != null) {
        ergebnis = new Auftrag(ergebnisTO);
      }
    } catch (Exception ex) {
      throw new AuftragException("Auftrag " + auftragsnr
          + " konnte nicht gefunden werden");
    }
    return ergebnis;
  }

  public Vector<Auftrag> findeAuftraegeZuArtikel(Artikel art)
      throws AuftragException {
    try {
      Vector<Auftrag> ergebnis = new Vector<>();
      Iterator<AuftragTO> it =
          datenquelle.findeAuftraegeZuArtikel(art
              .getArtikelnr());
      while (it.hasNext()) {
        ergebnis.add(new Auftrag(it.next()));
      }
      return ergebnis;
    } catch (Exception ex) {
      throw new AuftragException(
          "Auftr�ge zu Artikel nicht abrufbar.");
    }
  }

  public Vector<Auftrag> findeAuftraegeZuKunde(Kunde kd)
      throws AuftragException {
    try {
      Vector<Auftrag> ergebnis = new Vector<>();
      Iterator<AuftragTO> it =
          datenquelle.findeAuftraegeZuKunde(kd
              .getKundennr());
      while (it.hasNext()) {
        ergebnis.add(new Auftrag(it.next()));
      }
      return ergebnis;
    } catch (Exception ex) {
      throw new AuftragException(
          "Auftr�ge zu Kunde nicht abrufbar.");
    }
  }

  public Auftrag aktualisieren(Auftrag zuaktAuf)
      throws AuftragException {
    // TODO noch zu implementieren
    return null;
  }

  public int naechsteAuftragsnr() throws AuftragException {
    try {
      return datenquelle.nextKey();
    } catch (Exception ex) {
      throw new AuftragException(
          "Auftragsnummer konnte nicht generiert werden");
    }
  }

  public void update(Observable o, Object arg) {
    Auftrag auf = (Auftrag) o;
    try {
      datenquelle.update(auf.getTO());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /*
   * TODO Ab hier kommen �nderungen wegen Java DB Eine
   * M�glichkeit auf die generierten Schl�ssel zuzugreifen
   * sieht wie folgt aus Statement stmt =
   * conn.createStatement(); int rows =
   * stmt.executeUpdate("INSERT INTO TABLE1 (C11, C12)
   * VALUES (1,1)", Statement.RETURN_GENERATED_KEYS);
   * ResultSet rs = stmt.getGeneratedKeys(); aus:
   * http://db.apache.org/derby/docs/10.0/manuals/reference/sqlj229.html
   */
}
