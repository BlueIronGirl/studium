package de.w3l.anw.avplus.dao;

import java.io.Serializable;

/**
 * Vorgaben f�r alle Transfer-Objekt-Klassen
 */
public interface TransferObject extends Serializable {
  public int getKey();

  public void setKey(int newKey);
}
