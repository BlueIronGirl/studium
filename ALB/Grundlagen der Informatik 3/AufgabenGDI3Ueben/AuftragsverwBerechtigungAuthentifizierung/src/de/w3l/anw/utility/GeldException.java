package de.w3l.anw.utility;

public class GeldException extends ClassCastException {

  private static final long serialVersionUID =
      5865324333308220943L;

  GeldException(String message) {
    super(message);
  }
}
