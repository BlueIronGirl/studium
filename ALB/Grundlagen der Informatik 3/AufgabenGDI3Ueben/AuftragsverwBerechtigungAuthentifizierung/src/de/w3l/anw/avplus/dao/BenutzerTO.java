package de.w3l.anw.avplus.dao;

/**
 * Created by Alice on 23.07.2016.
 */
public class BenutzerTO implements TransferObject{
  public int benutzerId;

  public String id;

  public int kennwortHash;

  @Override
  public int getKey() {
    return benutzerId;
  }

  @Override
  public void setKey(int newKey) {
    benutzerId = newKey;
  }

  public BenutzerTO(int benutzerId, String id, int kennwortHash) {
    this.benutzerId = benutzerId;
    this.id = id;
    this.kennwortHash = kennwortHash;
  }
}
