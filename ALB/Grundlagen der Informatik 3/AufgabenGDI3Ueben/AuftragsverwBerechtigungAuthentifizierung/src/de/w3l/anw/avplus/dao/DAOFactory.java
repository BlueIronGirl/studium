package de.w3l.anw.avplus.dao;

import de.w3l.anw.avplus.berechtigungssystem.Benutzer;
import de.w3l.anw.avplus.sqldao.*;

public class DAOFactory {

  private static DAOFactory daoFabrik = null;

  /*
   * In den drei folgenden Attributen merken wir uns einmal
   * erzeugte DAO-Objekte. Pro Anwendungsklasse ist nur ein
   * DAO-Objekt erforderlich, um auf die
   * Datenhaltungsschicht zuzugreifen.
   */

  private ArtikelDAO artDao = null;

  private KundeDAO kdDao = null;

  private AuftragDAO aufDao = null;

  private BenutzerDAO benutzerDAO = null;

  /**
   * DAOFactory ist als Singleton implementiert, da nur eine
   * Fabrik erforderlich ist, um alle konkreten
   * DAO-Implementierungen vom Band laufen zu lassen. Die
   * statische Methode getInstance() liefert das einzige
   * Exemplar.
   */
  public static synchronized DAOFactory getInstance() {
    if (daoFabrik == null)
      daoFabrik = new DAOFactory();
    return daoFabrik;
  }

  /**
   * Diese Klasse muss konkrete Implementierungen liefern
   * und muss daf�r nat�rlich auch die konkreten
   * ArtikelDAOs, KundenDAOs, ... kennen.
   */
  public synchronized ArtikelDAO createArtikelDAO() {
    if (artDao == null)
      artDao = new ArtikelSqlDAO();
    return artDao;
  }

  public synchronized KundeDAO createKundenDAO() {
    if (kdDao == null)
      kdDao = new KundeSqlDAO();
    return kdDao;
  }

  public synchronized AuftragDAO createAuftragDAO() {
    if (aufDao == null)
      aufDao = new AuftragSqlDAO();
    return aufDao;
  }

  public synchronized BenutzerDAO createBenutzerDAO() {
    if(benutzerDAO == null) {
      benutzerDAO = new BenutzerSqlDAO();
    }
    return benutzerDAO;
  }
}
