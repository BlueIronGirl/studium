package de.w3l.anw.avplus.dao;

public interface DAO<T extends TransferObject> extends Iterable<T> {

  public void create(T objekt)
      throws DAOException;

  public T read(int key) throws DAOException;

  public void update(T objekt)
      throws DAOException;

  public void delete(int key) throws DAOException;

  /**
   * Generiert bei interner Nummernvergabe den n�chsten
   * Schl�ssel. Soll nur eine externe Nummernvergabe
   * erfolgen, so wird eine DAOException geworfen.
   *
   * @throws DAOException
   */
  public int nextKey() throws DAOException;
}
