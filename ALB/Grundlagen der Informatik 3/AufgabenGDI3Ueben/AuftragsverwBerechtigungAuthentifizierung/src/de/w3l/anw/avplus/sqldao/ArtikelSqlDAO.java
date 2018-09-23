package de.w3l.anw.avplus.sqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Iterator;
import java.util.Vector;

import de.w3l.anw.avplus.dao.*;
import de.w3l.anw.utility.Geld;

public class ArtikelSqlDAO extends SqlDAO<ArtikelTO> implements
    ArtikelDAO {

  @Override
  public String deleteStatementString() {
    return "DELETE FROM ARTIKEL WHERE ARTIKELNR = ?";
  }

  @Override
  public String findStatementString() {
    return ("SELECT ARTIKELNR, ARTIKELBEZEICHNUNG, BETRAG, NKS, WKZ, MBEST, BEST "
        + "FROM ARTIKEL WHERE ARTIKELNR=?");
  }

  @Override
  public String insertStatementString() {
    return "INSERT INTO ARTIKEL VALUES(?,?,?,?,?,?,?)";
  }

  @Override
  public ArtikelTO load(Connection verbindung, ResultSet rs)
      throws DAOException {
    try {
      ArtikelTO result = new ArtikelTO();
      result.artikelnr = rs.getInt(1);
      result.artikelbezeichnung = rs.getString(2);
      result.preis =
          new Geld(rs.getLong(3), rs.getInt(4), Currency
              .getInstance(rs.getString(5)));
      result.mindestbestand = rs.getInt(6);
      result.bestand = rs.getInt(7);
      return result;
    } catch (Exception ex) {
      throw new DAOException(ex.toString());
    }
  }

  @Override
  public String selectKeyStatement() {
    return "SELECT ARTIKELNR FROM ARTIKEL ORDER BY ARTIKELNR ASC";
  }

  @Override
  public String updateStatementString() {
    return ("UPDATE ARTIKEL SET ARTIKELBEZEICHNUNG = ?, BETRAG = ?,"
        + "NKS = ?, WKZ = ?, MBEST= ?, BEST = ? "
        + "WHERE ARTIKELNR = ?");
  }

  @Override
  public void save(Connection verbindung,
                   ArtikelTO art, PreparedStatement statement)
      throws DAOException {
    try {
      statement.setInt(1, art.artikelnr);
      statement.setString(2, art.artikelbezeichnung);
      statement.setLong(3, art.preis.getBetrag());
      statement.setInt(4, art.preis.getAnzNachkomma());
      statement.setString(5, art.preis.getWaehrung()
          .toString());
      statement.setInt(6, art.mindestbestand);
      statement.setInt(7, art.bestand);
    } catch (Exception ex) {
      throw new DAOException(ex.toString());
    }
  }

  @Override
  public void doUpdate(Connection verbindung,
                       ArtikelTO art, PreparedStatement statement)
      throws DAOException {
    try {
      statement.setInt(7, art.artikelnr);
      statement.setString(1, art.artikelbezeichnung);
      statement.setLong(2, art.preis.getBetrag());
      statement.setInt(3, art.preis.getAnzNachkomma());
      statement.setString(4, art.preis.getWaehrung()
          .toString());
      statement.setInt(5, art.mindestbestand);
      statement.setInt(6, art.bestand);
    } catch (Exception ex) {
      throw new DAOException(ex.toString());
    }
  }

  @Override
  public void deleteDependendItems(Connection verbindung,
                                   int key) throws SQLException {
    // Es gibt keine Depended-Items, daher leere
    // Implementierung
  }

  public Iterator<ArtikelTO> findeKleinerMindestbestand() {
    try {
      ResultSet rs =
          this.verbindung
              .createStatement()
              .executeQuery(
                  "SELECT ARTIKELNR FROM ARTIKEL WHERE BEST < MBEST");
      Vector<Integer> allKeys = new Vector<Integer>();
      while (rs.next()) {
        allKeys.add(rs.getInt(1));
      }
      return (Iterator<ArtikelTO>) new SqlDAOIterator<ArtikelTO>(this, allKeys);
    } catch (Exception ex) {
      return null;
    }
  }

  @Override
  public int nextKey() throws DAOException {
    throw new DAOException("nicht implementiert");
  }
}
