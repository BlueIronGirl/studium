package de.w3l.anw.avplus.dao;

import de.w3l.anw.avplus.sqldao.ArtikelSqlDAO;
import de.w3l.anw.avplus.sqldao.AuftragSqlDAO;
import de.w3l.anw.avplus.sqldao.BenutzerSqlDAO;
import de.w3l.anw.avplus.sqldao.KundeSqlDAO;

public class DAOFactory {
  //Singleton-Muster
  private static DAOFactory daoFabrik = null;
  private ArtikelDAO artDao = null;
  private KundeDAO kdDao = null;
  private AuftragDAO aufDao = null;
  private BenutzerDAO benutzerDAO = null;

  public static synchronized DAOFactory getInstance() {
    if (daoFabrik == null)
      daoFabrik = new DAOFactory();
    return daoFabrik;
  }
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
    if (benutzerDAO == null) {
      benutzerDAO = new BenutzerSqlDAO();
    }
    return benutzerDAO;
  }
}
