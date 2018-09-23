package de.w3l.anw.avplus.dao;

/**
 * In dieser Schnittstelle sind alle Methoden aufgef�hrt,
 * die f�r ein ArtikelDAO zus�tzlich zu den
 * Standard-Methoden realisiert werden m�ssen.
 */
public interface ArtikelDAO extends DAO<ArtikelTO> {

  /**
   * Alle Artikel (als Artikel-Transferobjekte)
   * zur�ckliefern, deren Bestand kleiner als der
   * Mindestbestand ist.
   */
  public java.util.Iterator<ArtikelTO> findeKleinerMindestbestand();
}
