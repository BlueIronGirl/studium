package de.w3l.anw.avplus.dao;

/**
 * Created by Alice on 02.07.2016.
 */
public class BenutzerTO implements TransferObject {
  private static final long serialVersionUID =
      -5570722863444250380L;

  private int benutzerId;

  private String id;

  private int kennwortHash;

  public BenutzerTO(int benutzerId, String id, int kennwortHash) {
    this.benutzerId = benutzerId;
    this.id = id;
    this.kennwortHash = kennwortHash;
  }

  @Override
  public int getKey() {
    return benutzerId;
  }

  @Override
  public void setKey(int newKey) {
    this.benutzerId = newKey;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public int getBenutzerId() {
    return benutzerId;
  }

  public String getId() {
    return id;
  }

  public int getKennwortHash() {
    return kennwortHash;
  }
}
