package de.w3l.anw.avplus.applikationslogik;

public class KundeException extends Exception {

  private static final long serialVersionUID =
      -6080646917305130188L;

  public KundeException(String meldung) {
    super(meldung);
  }
}