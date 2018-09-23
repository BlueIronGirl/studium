package de.w3l.anw.avplus.dao;

import java.time.LocalDate;

import de.w3l.anw.utility.Geld;

public class AuftragspositionTO implements TransferObject {

  private static final long serialVersionUID =
      -2536274837249525911L;

  public int posNr;

  public int artikel;

  public int menge;

  public Geld betrag;

  public LocalDate tatsLieferdatum;

  public AuftragspositionTO(int posNr, int artikel,
                            int menge, Geld betrag, LocalDate tatsLieferdatum) {
    this.posNr = posNr;
    this.artikel = artikel;
    this.menge = menge;
    this.betrag = betrag;
    this.tatsLieferdatum = tatsLieferdatum;
  }

  public int getKey() {
    return posNr;
  }

  public void setKey(int newKey) {
    posNr = newKey;
  }
}
