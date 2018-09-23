package de.w3l.anw.avplus.sqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Iterator;
import java.util.Vector;

import de.w3l.anw.avplus.dao.AuftragDAO;
import de.w3l.anw.avplus.dao.AuftragTO;
import de.w3l.anw.avplus.dao.AuftragspositionTO;
import de.w3l.anw.avplus.dao.DAOException;
import de.w3l.anw.utility.Geld;

public class AuftragSqlDAO extends SqlDAO<AuftragTO> implements
    AuftragDAO {
  // gr��te bislang vergebene Auftragsnummer
  private int maxAufnr;

  public AuftragSqlDAO() {
    super();
    try {
      ResultSet maxkey =
          verbindung.createStatement().executeQuery(
              "SELECT MAX(AUFNR) FROM AUFTRAG");
      maxkey.next();

      /*
       * Maximale Auftragsnummer holen. Falls noch kein
       * Datensatz vorhanden ist, wird 0 zur�ckgegeben.
       */
      maxAufnr = maxkey.getInt(1);
    } catch (SQLException ex) {
      maxAufnr = -1;
      ex.printStackTrace();
    }
  }

  @Override
  public String deleteStatementString() {
    return "DELETE FROM AUFTRAG WHERE AUFNR = ?";
  }

  private String deleteDetailsStatementString() {
    return "DELETE FROM AUFTRAGSPOSITION WHERE AUFNR=?";
  }

  @Override
  public String findStatementString() {
    return ("SELECT AUFNR, AUFDATUM, LIEFDATUM, AUFKDNR, AUFCURR "
        + "FROM AUFTRAG WHERE AUFNR=?");
  }

  private String findDetailsStatementString() {
    return ("SELECT AUFNR, POSNR, ARTNR, MENGE, BETRAG, NKS, WKZ, "
        + "TLDAT FROM AUFTRAGSPOSITION WHERE AUFNR=? "
        + "ORDER BY POSNR ASC");
  }

  @Override
  public String insertStatementString() {
    return "INSERT INTO AUFTRAG VALUES(?,?,?,?,?)";
  }

  private String insertDetailsStatementString() {
    return "INSERT INTO AUFTRAGSPOSITION VALUES(?,?,?,?,?,?,?,?)";
  }

  @Override
  public AuftragTO load(Connection verbindung, ResultSet rs)
      throws DAOException {
    try {
      int key = rs.getInt(1);

      // Zuerst die Auftragspositionen holen, wenn
      // vorhanden
      Vector<AuftragspositionTO> apos = readDetails(key);
      AuftragTO result =
          new AuftragTO(rs.getInt("AUFNR"),
              rs.getDate("AUFDATUM").toLocalDate(),
              rs.getDate("LIEFDATUM").toLocalDate(),
              rs.getInt("AUFKDNR"), Currency.getInstance(rs
              .getString("AUFCURR")), apos);
      return result;
    } catch (Exception ex) {
      throw new DAOException(
          "Auftrag-LOAD fehlgeschlagen: " + ex.toString());
    }
  }

  @Override
  public String selectKeyStatement() {
    return "SELECT AUFNR FROM AUFTRAG ORDER BY AUFNR ASC";
  }

  @Override
  public String updateStatementString() {
    return ("UPDATE AUFTRAG SET AUFDATUM = ?, LIEFDATUM = ?, AUFKDNR = ?,"
        + " AUFCURR = ? WHERE AUFNR = ?");
  }

  @Override
  public void save(Connection verbindung,
                   AuftragTO auf, PreparedStatement statement)
      throws DAOException {
    try {
      statement.setInt(1, auf.auftragsnr);
      statement.setDate(2, java.sql.Date.valueOf(
          auf.auftragsdatum));
      statement.setDate(3, java.sql.Date.valueOf(
          auf.lieferdatum));
      statement.setInt(4, auf.kunde);
      statement
          .setString(5, auf.waehrung.getCurrencyCode());

      // Auch die abh�ngigen Auftragspositionen sichern
      Vector<AuftragspositionTO> apos = auf.apositionen;
      PreparedStatement insDetStatm =
          verbindung
              .prepareStatement(insertDetailsStatementString());
      AuftragspositionTO aktPos;
      for (int i = 0; i < apos.size(); i++) {
        aktPos = apos.get(i);
        insDetStatm.setInt(1, auf.auftragsnr);
        insDetStatm.setInt(2, aktPos.posNr);
        insDetStatm.setInt(3, aktPos.artikel);
        insDetStatm.setInt(4, aktPos.menge);
        insDetStatm.setLong(5, aktPos.betrag.getBetrag());
        insDetStatm.setInt(6, aktPos.betrag
            .getAnzNachkomma());
        insDetStatm.setString(7, aktPos.betrag
            .getWaehrung().getCurrencyCode());

        // ACHTUNG: Das tats�chliche Lieferdatum kann leer
        // sein
        if (aktPos.tatsLieferdatum == null)
          /*
           * Bei JavaDB muss ein nicht belegtes Feld auf
           * NULL gesetzt werden.
           */
          insDetStatm.setNull(8, java.sql.Types.DATE);
        else
          insDetStatm.setDate(8, java.sql.Date.valueOf(
              aktPos.tatsLieferdatum));

        insDetStatm.execute();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new DAOException(
          "SAVE fehlgeschlagen f�r Auftrag "
              + auf.auftragsnr);
    }
  }

  @Override
  public void doUpdate(Connection verbindung,
                       AuftragTO auf, PreparedStatement statement)
      throws DAOException {
    try {
      statement.setInt(5, auf.auftragsnr);
      statement.setDate(1, java.sql.Date.valueOf(
          auf.auftragsdatum));
      statement.setDate(2, java.sql.Date.valueOf(
          auf.lieferdatum));
      statement.setInt(3, auf.kunde);
      statement
          .setString(4, auf.waehrung.getCurrencyCode());

      /*
       * Bearbeitung der abh�ngigen Auftragspositionen: Da
       * nicht sicher ist, welche Auftragsposition ge�ndert
       * wurde oder ob Auftragspositionen hinzugef�gt oder
       * gel�scht wurden, werden zun�chst alle
       * Auftragspositionen f�r einen Auftrag gel�scht und
       * anschlie�end wieder hinzugef�gt.
       */
      deleteDependendItems(verbindung, auf.auftragsnr);

      // Jetzt wieder einf�gen
      Vector<AuftragspositionTO> apos = auf.apositionen;
      PreparedStatement detStatm =
          verbindung
              .prepareStatement(insertDetailsStatementString());
      AuftragspositionTO aktPos;
      for (int i = 0; i < apos.size(); i++) {
        aktPos = apos.get(i);
        detStatm.setInt(1, auf.auftragsnr);
        detStatm.setInt(2, aktPos.posNr);
        detStatm.setInt(3, aktPos.artikel);
        detStatm.setInt(4, aktPos.menge);
        detStatm.setLong(5, aktPos.betrag.getBetrag());
        detStatm.setInt(6, aktPos.betrag.getAnzNachkomma());
        detStatm.setString(7, aktPos.betrag.getWaehrung()
            .getCurrencyCode());
        // Das tats�chliche Lieferdatum kann leer sein
        if (aktPos.tatsLieferdatum != null) {
          detStatm.setDate(8, java.sql.Date.valueOf(
              aktPos.tatsLieferdatum));
        }
        detStatm.execute();
      }
    } catch (Exception ex) {
      throw new DAOException(
          "Update fehlgeschlagen f�r Auftrag "
              + auf.auftragsnr);
    }
  }

  @Override
  public void deleteDependendItems(Connection verbindung,
                                   int key) throws SQLException {
    PreparedStatement detStatm =
        verbindung
            .prepareStatement(deleteDetailsStatementString());
    detStatm.setInt(1, key);
    detStatm.execute();
  }

  public Vector<AuftragspositionTO> readDetails(int key)
      throws DAOException {
    Vector<AuftragspositionTO> apos =
        new Vector<AuftragspositionTO>();
    try {
      PreparedStatement findeAPos =
          verbindung
              .prepareStatement(findDetailsStatementString());
      findeAPos.setInt(1, key);
      ResultSet rsDetails = findeAPos.executeQuery();
      while (rsDetails.next()) {
        //Teillieferdatum (TLDAT) kann NULL sein
        LocalDate teillieferdatum = null;
        java.sql.Date tldat = rsDetails.getDate("TLDAT");
        if (tldat != null) teillieferdatum = tldat.toLocalDate();

        apos.add(new AuftragspositionTO(rsDetails
            .getInt("POSNR"), rsDetails.getInt("ARTNR"),
            rsDetails.getInt("MENGE"), new Geld(rsDetails
            .getLong("BETRAG"),
            rsDetails.getInt("NKS"),
            Currency.getInstance(rsDetails
                .getString("WKZ"))),
            teillieferdatum));
      }
      // In dem Feld apos sind jetzt alle
      // Auftragspositionen (als
      // AuftragspositionTO-Objekte) enthalten.
    } catch (Exception ex) {
      throw new DAOException(
          "Auftragspositionen konnten nicht gelesen werden: "
              + key);
    }
    return apos;
  }

  private String findAuftraegeZuArtikelnStatement() {
    return ("SELECT AUFNR "
        + "FROM AUFTRAG "
        + "WHERE AUFNR IN "
        + "(SELECT AUFNR FROM AUFTRAGSPOSITION WHERE ARTNR = ?) "
        + "ORDER BY AUFNR ASC");
  }

  public Iterator<AuftragTO> findeAuftraegeZuArtikel(
      int artikelnr) throws DAOException {
    try {
      PreparedStatement query =
          verbindung
              .prepareStatement(findAuftraegeZuArtikelnStatement());

      query.setInt(1, artikelnr);

      ResultSet rs = query.executeQuery();
      Vector<Integer> alleAuftraege = new Vector<Integer>();
      while (rs.next()) {
        alleAuftraege.add(rs.getInt("AUFNR"));
      }
      return new SqlDAOIterator<AuftragTO>(this, alleAuftraege);
    } catch (Exception ex) {
      return null;
    }
  }

  private String findAuftraegeZuKundeStatement() {
    return ("SELECT AUFNR " + "FROM AUFTRAG "
        + "WHERE AUFKDNR = ? " + "ORDER BY AUFNR ASC");
  }

  public Iterator<AuftragTO> findeAuftraegeZuKunde(
      int kundennr) throws DAOException {
    try {
      PreparedStatement query =
          verbindung
              .prepareStatement(findAuftraegeZuKundeStatement());

      query.setInt(1, kundennr);

      ResultSet rs = query.executeQuery();
      Vector<Integer> alleAuftraege = new Vector<Integer>();
      while (rs.next()) {
        alleAuftraege.add(rs.getInt("AUFNR"));
      }
      return new SqlDAOIterator<AuftragTO>(this, alleAuftraege);
    } catch (Exception ex) {
      return null;
    }
  }

  @Override
  public synchronized int nextKey() throws DAOException {
    /*
     * Die Methode wird als synchronized deklariert, um
     * Fehler bei der Schl�sselvergabe zu vermeiden.
     */
    if (maxAufnr < 0)
      throw new DAOException(
          "Fehler bei Schl�sselgenerierung");
    return ++maxAufnr;
  }
}