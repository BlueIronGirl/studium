package de.w3l.anw.othello.engine;

public class Zug {

  int spalte;

  int zeile;

  Integer bewertung = null;

  Zug(int zeile, int spalte) {
    // Neuen Zug ohne Bewertung anlegen
    this.zeile = zeile;
    this.spalte = spalte;
  }

  // Wann sind zwei Z�ge gleich? Wird ben�tigt, um zu
  // pr�fen, ob
  // ein Zug in der Menge der erlaubten Z�ge ist.
  public boolean equals(Object o) {
    Zug z2 = (Zug) o;
    return ((this.zeile == z2.zeile) && (this.spalte == z2.spalte));
  }

  public Integer getBewertung() {
    return bewertung;
  }

  public void setBewertung(Integer bewertung) {
    this.bewertung = bewertung;
  }

  public int getSpalte() {
    return spalte;
  }

  public void setSpalte(int spalte) {
    this.spalte = spalte;
  }

  public int getZeile() {
    return zeile;
  }

  public void setZeile(int zeile) {
    this.zeile = zeile;
  }

  public String toString() {
    return "(" + zeile + "," + spalte + "): " + bewertung;
  }
}