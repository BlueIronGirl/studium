package de.w3l.anw.avplus.dao;

public class KundeTO implements TransferObject {

  private static final long serialVersionUID =
      -4035387477258469055L;

  public int kundennr;

  public String name;

  public String strasse;

  public String plz;

  public String ort;

  public String debitorennr;

  public String anrede;

  public String vorname;

  public KundeTO(int kundennr, String anrede, String name,
                 String vorname, String ort, String plz,
                 String strasse, String debitorennr) {
    this.kundennr = kundennr;
    this.anrede = anrede;
    this.name = name;
    this.vorname = vorname;
    this.strasse = strasse;
    this.plz = plz;
    this.ort = ort;
    this.debitorennr = debitorennr;
  }

  public int getKey() {
    return kundennr;
  }

  public void setKey(int newKey) {
    kundennr = newKey;
  }
}
