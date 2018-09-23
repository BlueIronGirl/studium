package de.w3l.anw.avplus.sqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.w3l.anw.avplus.dao.*;

public class KundeSqlDAO extends SqlDAO<KundeTO> implements KundeDAO {

  @Override
  public String deleteStatementString() {
    return "DELETE FROM KUNDE WHERE KDNR = ?";
  }

  @Override
  public String findStatementString() {
    return ("SELECT KDNR, KANREDE, KNAME, KVORNAME, ORT, PLZ, STRASSE, DEBNR "
        + "FROM KUNDE WHERE KDNR=?");
  }

  @Override
  public String insertStatementString() {
    return "INSERT INTO KUNDE VALUES(?,?,?,?,?,?,?,?)";
  }

  @Override
  public KundeTO load(Connection verbindung, ResultSet rs)
      throws DAOException {
    try {
      KundeTO result =
          new KundeTO(rs.getInt("KDNR"), rs
              .getString("KANREDE"), rs.getString("KNAME"),
              rs.getString("KVORNAME"),
              rs.getString("ORT"), rs.getString("PLZ"), rs
              .getString("STRASSE"), rs
              .getString("DEBNR")); // Debitorennummer
      return result;
    } catch (Exception ex) {
      throw new DAOException(ex.toString());
    }
  }

  @Override
  public String selectKeyStatement() {
    return "SELECT KDNR FROM KUNDE ORDER BY KDNR ASC";
  }

  @Override
  public String updateStatementString() {
    return ("UPDATE KUNDE SET KANREDE = ?, KNAME = ?, KVORNAME = ?, ORT = ?,"
        + "PLZ = ?, STRASSE = ?, DEBNR= ? "
        + "WHERE KDNR = ?");
  }

  @Override
  public void save(Connection verbindung,
                   KundeTO kd, PreparedStatement statement)
      throws DAOException {
    try {
      statement.setInt(1, kd.kundennr);
      statement.setString(2, kd.anrede);
      statement.setString(3, kd.name);
      statement.setString(4, kd.vorname);
      statement.setString(5, kd.ort);
      statement.setString(6, kd.plz);
      statement.setString(7, kd.strasse);
      statement.setString(8, kd.debitorennr);
    } catch (Exception ex) {
      throw new DAOException(ex.toString());
    }
  }

  @Override
  public void doUpdate(Connection verbindung,
                       KundeTO kd, PreparedStatement statement)
      throws DAOException {
    try {
      statement.setInt(8, kd.kundennr);
      statement.setString(1, kd.anrede);
      statement.setString(2, kd.name);
      statement.setString(3, kd.vorname);
      statement.setString(4, kd.ort);
      statement.setString(5, kd.plz);
      statement.setString(6, kd.strasse);
      statement.setString(7, kd.debitorennr);
    } catch (Exception ex) {
      throw new DAOException(ex.toString());
    }
  }

  @Override
  public void deleteDependendItems(Connection verbindung,
                                   int key) throws SQLException {
    // Keine abh�ngigen Objekte, daher leere Implementierung
  }

  @Override
  public int nextKey() throws DAOException {
    throw new DAOException("nicht implementiert");
  }
}
