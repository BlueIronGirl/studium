package de.w3l.anw.avplus.dao;

import java.util.Iterator;

public interface AuftragDAO extends DAO<AuftragTO> {

  public java.util.Vector<AuftragspositionTO> readDetails(
      int key) throws DAOException;

  public Iterator<AuftragTO> findeAuftraegeZuArtikel(
      int artikelnr) throws DAOException;

  public Iterator<AuftragTO> findeAuftraegeZuKunde(
      int kundennr) throws DAOException;
}
