package de.w3l.anw.avplus.sqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.w3l.anw.avplus.dao.BenutzerDAO;
import de.w3l.anw.avplus.dao.BenutzerTO;
import de.w3l.anw.avplus.dao.DAOException;

/**
 * Created by Alice on 02.07.2016.
 */
public class BenutzerSqlDAO extends SqlDAO<BenutzerTO> implements BenutzerDAO {
  @Override
  public String findStatementString() {
    return ("SELECT BENUTZERID, ID, PASSWORT FROM BENUTZER WHERE ID=?");
  }

  @Override
  public BenutzerTO load(Connection verbindung, ResultSet rs) throws DAOException {
    try {
      BenutzerTO benutzerTO = new BenutzerTO(rs.getInt("BENUTZERID"), rs.getString("ID"), rs.getInt("PASSWORT"));
      return benutzerTO;
    } catch (Exception e) {
      throw new DAOException(e.toString());
    }
  }

  @Override
  public String insertStatementString() {
    return "INSERT INTO BENUTZER VALUES(?,?,?)";
  }

  @Override
  public void save(Connection verbindung, BenutzerTO obj, PreparedStatement statement) throws DAOException {
    try {
      statement.setInt(1, obj.getBenutzerId());
      statement.setString(2, obj.getId());
      statement.setInt(3, obj.getKennwortHash());
    } catch (Exception e) {
      throw new DAOException(e.toString());
    }
  }

  @Override
  public String deleteStatementString() {
    return "DELETE FROM BENUTZER WHERE BENUTZERID = ?";
  }

  @Override
  public void deleteDependendItems(Connection verbindung, int key) throws SQLException {

  }

  @Override
  public String updateStatementString() {
    return "UPDATE BENUTZER SET BENUTZERID = ?, ID = ?, PASSWORT = ? WHERE BENUTZERID = ?";
  }

  @Override
  public void doUpdate(Connection verbindung, BenutzerTO objekt, PreparedStatement ps) throws DAOException {
    try {
      ps.setInt(1, objekt.getBenutzerId());
      ps.setString(2, objekt.getId());
      ps.setInt(3, objekt.getKennwortHash());
    } catch (Exception e) {
      throw new DAOException(e.toString());
    }
  }

  @Override
  public int nextKey() throws DAOException {
    return 0;
  }

  @Override
  public String selectKeyStatement() {
    return "SELECT BENUTZERID FROM BENUTZER ORDER BY BENUTZERID";
  }

  @Override
  public BenutzerTO findeBenutzer(String sName) {
    BenutzerTO benutzerTO = null;
    try {
      ResultSet rs = this.verbindung.createStatement().executeQuery("SELECT BENUTZERID, ID, " +
          "PASSWORT FROM BENUTZER WHERE ID = " + sName);
      while (rs.next()) {
        benutzerTO = new BenutzerTO(rs.getInt("BENUTZERID"), rs.getString("ID"), rs.getInt("PASSWORT"));
      }
    } catch (Exception e) {
    }
    return benutzerTO;
  }
}
