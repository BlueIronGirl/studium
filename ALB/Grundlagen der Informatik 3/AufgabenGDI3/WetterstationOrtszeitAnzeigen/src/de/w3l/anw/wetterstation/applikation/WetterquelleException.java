package de.w3l.anw.wetterstation.applikation;

public class WetterquelleException extends Exception {

  private static final long serialVersionUID =
      3923442397286311751L;

  public WetterquelleException(String meldung) {
    super(meldung);
  }
}
