package de.w3l.anw.avplus.sqldao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import de.w3l.anw.avplus.dao.DAO;
import de.w3l.anw.avplus.dao.DAOException;
import de.w3l.anw.avplus.dao.TransferObject;

/**
 * Die Klasse SqlDAO soll alle Methoden bereitstellen, die
 * in der Schnittstelle DAO im Paket de.w3l.anw.avplus.dao
 * spezifiziert wurden. Alles, was allgemein f�r alle
 * Fachklassen bzw. Transfer-Objekt-Klassen realisiert
 * werden kann, wird direkt in SqlDAO implementiert. SqlDAO
 * ist daher f�r alle Kindklassen von TransferObjekt geeignet.
 * Daher heisst es in der Deklaration <T extends TransferObject>
 * Teile der Realisierung dieser Methoden aber davon sind,
 * mit welche Fachklassen bzw. Transfer-Objekt-Klassen
 * gearbeitet wird, enth�lt SqlDAO eine Reihe von abstrakten
 * Methoden, die durch entsprechende Kindklassen realisiert
 * werden m�ssen. Zur Speicherung verwenden SqlDAO und deren
 * Kindklassen die relationale Datenbank JavaDB, die �ber
 * JDBC-Mechanismen eingebunden wird.
 */
public abstract class SqlDAO<T extends TransferObject>
    implements DAO<T> {

  protected Connection verbindung;
  protected HashMap<Integer, T> cache;

  public SqlDAO() {
    try {
      //Einbinden des JDBC-Treibers
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      //Verbindung herstellen
      verbindung = DriverManager.getConnection(
          "jdbc:derby://localhost:1527/Auftragsverwaltung", "Benutzer", "Kennwort");
      cache = new HashMap<Integer, T>();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public T read(int key) throws DAOException {
    T result = cache.get(key);
    if (result != null)
      return result;
    try {
      PreparedStatement findStatement =
          verbindung.prepareStatement(findStatementString());
      ResultSet rs = null;
      findStatement.setInt(1, key);
      rs = findStatement.executeQuery();
      if (rs.next()) {
        result = load(verbindung, rs);
        cache.put(key, result);
      }
      return result;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new DAOException(ex.toString());
    }
  }

  //Spezieller SQL zum Suchen mit Keyfeld
  public abstract String findStatementString();

  //laden
  public abstract T load(
      Connection verbindung, ResultSet rs)
      throws DAOException;

  public abstract String insertStatementString();

  public void create(T obj)
      throws DAOException {
    int key = obj.getKey();
    if (cache.containsKey(key))
      throw new DAOException("Schl�ssel bereits vergeben");
    try {
      PreparedStatement insertStatement =
          verbindung
              .prepareStatement(insertStatementString());
      insertStatement.setInt(1, key);
      save(verbindung, obj, insertStatement);
      // Datenbank aktualisieren
      insertStatement.executeUpdate();
      // und den Artikel zus�tzlich in den Cache packen
      cache.put(key, obj);
    } catch (Exception ex) {
      throw new DAOException(ex.toString());
    }
  }

  public abstract void save(Connection verbindung,
                            T obj, PreparedStatement statement)
      throws DAOException;

  public abstract String deleteStatementString();

  public abstract void deleteDependendItems(
      Connection verbindung, int key) throws SQLException;

  public void delete(int key) throws DAOException {
    cache.remove(key);
    try {
      PreparedStatement deleteStatement =
          verbindung.prepareStatement(deleteStatementString());
      deleteStatement.setInt(1, key);
      deleteStatement.executeUpdate();
      deleteDependendItems(verbindung, key);
    } catch (Exception ex) {
      throw new DAOException(ex.toString());
    }
  }

  public abstract String updateStatementString();

  /**
   * F�hrt den Update durch; inklusive Update der abh�ngigen
   * Tabellen
   *
   * @param verbindung
   * @param objekt
   * @param ps
   * @throws DAOException
   */
  public abstract void doUpdate(Connection verbindung,
                                T objekt, PreparedStatement ps)
      throws DAOException;

  public void update(T objekt) throws DAOException {
    int key = objekt.getKey();
    cache.put(key, objekt);
    try {
      PreparedStatement updateStatement =
          verbindung.prepareStatement(updateStatementString());
      doUpdate(verbindung, objekt, updateStatement);
      updateStatement.executeUpdate();
    } catch (Exception ex) {
      throw new DAOException(ex.toString());
    }
  }

  public abstract int nextKey() throws DAOException;

  public Iterator<T> iterator() {
    try {
      ResultSet rs =
          verbindung.createStatement().executeQuery(
              selectKeyStatement()); // abstrakte Methode
      Vector<Integer> allKeys = new Vector<>();
      while (rs.next()) {
        allKeys.add(rs.getInt(1));
      }
      return new SqlDAOIterator<T>(this, allKeys);
    } catch (Exception ex) {
      return null;
    }
  }

  //alle Schluessel in Tabelle
  public abstract String selectKeyStatement();
}
