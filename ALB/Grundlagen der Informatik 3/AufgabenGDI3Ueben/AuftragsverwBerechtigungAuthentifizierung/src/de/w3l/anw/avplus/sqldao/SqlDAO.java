package de.w3l.anw.avplus.sqldao;

import de.w3l.anw.avplus.dao.*;

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
      /*
       * 1. Schritt: Einbinden des JDBC-Treibers Binden Sie
       * an dieser Stelle den Treiber f�r die von Ihnen
       * verwendete Datenbank ein. Hier wird Java DB
       * ("Derby") verwendet.
       */
      Class.forName("org.apache.derby.jdbc.ClientDriver");

      /*
       * 2. Schritt: Spezifizieren der Verbindung
       * (Connection). Im Beispiel wird die Datenbank
       * "Auftragsverwaltung" genutzt, die auf dem lokalen
       * Rechner liegt. Verwenden Sie den Rechnernamen, wenn
       * Ihre Datenbank auf einem entfernten Rechner liegt.
       */
      verbindung =
          DriverManager
              .getConnection(
                  "jdbc:derby://localhost:1527/Auftragsverwaltung",
                  "Benutzer", "Kennwort");

      cache = new HashMap<Integer, T>();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Methode zum Finden eines Objekts anhand des Schl�ssels
   * im Speicher oder aus der Datenbank. Ist das Objekt
   * weder im Speicher noch in der Datenbank vorhanden, wird
   * null zur�ckgeliefert.
   */
  public T read(int key) throws DAOException {
    T result = cache.get(key); // Zun�chst im
    // Cache suchen
    if (result != null)
      return result;

    try {
      /*
       * Das Objekt ist noch nicht im Speicher, laden aus
       * der DB
       */
      PreparedStatement findStatement =
          verbindung
              .prepareStatement(findStatementString());
      ResultSet rs = null;
      findStatement.setInt(1, key);
      rs = findStatement.executeQuery();

      if (rs.next()) {

        // Es gibt ein Objekt
        result = load(verbindung, rs);
        cache.put(key, result);
      }

      return result;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new DAOException(ex.toString());
    }
  }

  /**
   * Abstrakte Methode, die einen String zur�ckliefern muss,
   * der als Grundlage f�r ein PreparedStatement dienen
   * kann, wenn �ber einen eindeutigen numerischen Schl�ssel
   * gesucht wird. Muss in der Kindklasse implementiert
   * werden.
   */
  public abstract String findStatementString();

  /**
   * Abstrakte Methode zum Laden eines Objekts aus dem
   * Recordset rs Erzeugt ein Objekt aus einer Kindklasse
   * von TransferObject. Die Connection verbindung wird
   * mitgegeben, damit eventuelle weitere Anfragen f�r
   * abh�ngige Objekte gestellt werden k�nnen. Muss in der
   * Kindklasse implementiert werden.
   */
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

  /**
   * Optionale Methode zum L�schen abh�ngiger Elemente (z.B.
   * Auftragspositionen von Auftr�gen)
   *
   * @param verbindung Verbindung zur Datenbank
   * @param key        Schl�ssel des �bergeordneten Elements
   */
  public abstract void deleteDependendItems(
      Connection verbindung, int key) throws SQLException;

  public void delete(int key) throws DAOException {
    // Zun�chst aus dem Cache entfernen
    cache.remove(key);

    // Dann aus der Datenbank
    try {
      PreparedStatement deleteStatement =
          verbindung
              .prepareStatement(deleteStatementString());
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

  public void update(T objekt)
      throws DAOException {
    // Ge�nderten Artikel in den Cache packen
    int key = objekt.getKey();
    cache.put(key, objekt);

    // Datenbank aktualisieren
    try {
      PreparedStatement updateStatement =
          verbindung
              .prepareStatement(updateStatementString());
      doUpdate(verbindung, objekt, updateStatement);

      updateStatement.executeUpdate();
    } catch (Exception ex) {
      throw new DAOException(ex.toString());
    }
  }

  /**
   * Liefert den n�chsten freien Schl�ssel zur�ck Benutzt
   * dazu eine entsprechende Funktion der relationalen
   * Datenbank.
   */
  public abstract int nextKey() throws DAOException;

  /**
   * Einen Iterator �ber alle Objekte zur�ckliefern.
   *
   * @see java.lang.Iterable#iterator()
   */
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

  /**
   * Abstrakte Methode, die ein SELECT-Statement zur
   * R�ckgabe der Schl�ssel aller Datens�tze einer Tabelle
   * liefern muss.
   */
  public abstract String selectKeyStatement();
}
