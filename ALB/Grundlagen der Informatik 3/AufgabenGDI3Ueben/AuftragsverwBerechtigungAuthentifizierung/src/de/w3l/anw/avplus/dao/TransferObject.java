package de.w3l.anw.avplus.dao;

import java.io.Serializable;

/**
 * Vorgaben f�r alle Transfer-Objekt-Klassen
 */
public interface TransferObject extends Serializable {
  /**
   * Den numerischen Schl�ssel eines Transfer-Objekts
   * liefern.
   */
  public int getKey();

  /**
   * Den numerischen Schl�ssel eines Transfer-Objekts
   * �ndern.
   */
  public void setKey(int newKey);
}
