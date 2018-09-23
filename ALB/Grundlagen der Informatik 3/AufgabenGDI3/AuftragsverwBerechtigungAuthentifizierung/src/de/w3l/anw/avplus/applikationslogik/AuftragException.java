package de.w3l.anw.avplus.applikationslogik;

public class AuftragException extends Exception {

  private static final long serialVersionUID =
      -6080646917305130188L;

  public AuftragException(String meldung) {
    super(meldung);
  }
}