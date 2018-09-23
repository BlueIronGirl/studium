package de.w3l.anw.avplus.sqldao;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import de.w3l.anw.avplus.dao.TransferObject;

public class SqlDAOIterator<T extends TransferObject> implements Iterator<T> {
  private SqlDAO<T> dao = null;
  private Vector<Integer> rs = null;
  private int index = 0;

  public SqlDAOIterator(SqlDAO<T> dao, Vector<Integer> rs) {
    this.dao = dao;
    this.rs = rs;
  }

  public boolean hasNext() {
    return (rs.size() > index);
  }

  public T next() {
    try {
      return dao.read(rs.get(index++));
    } catch (Exception ex) {
      throw new NoSuchElementException();
    }
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }
}
