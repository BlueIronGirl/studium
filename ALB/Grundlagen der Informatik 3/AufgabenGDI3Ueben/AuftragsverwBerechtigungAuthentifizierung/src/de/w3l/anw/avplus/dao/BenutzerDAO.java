package de.w3l.anw.avplus.dao;

/**
 * Created by Alice on 23.07.2016.
 */
public interface BenutzerDAO extends DAO<BenutzerTO> {
  public BenutzerTO findeBenutzer(String sName);
}
