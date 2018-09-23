package de.w3l.anw.avplus.applikationslogik;

import java.time.LocalDate;
import java.util.Observable;

import de.w3l.anw.avplus.dao.AuftragspositionTO;
import de.w3l.anw.utility.Geld;

public class Auftragsposition extends Observable {

  private int posNr;

  private Artikel artikel;

  private int menge;

  private Geld betrag;

  private LocalDate tatsLieferdatum;

  public Auftragsposition(int posNr, Artikel artikel,
                          int menge, Geld betrag, LocalDate tatsLDat)
      throws AuftragException {
    this.posNr = posNr;
    setArtikel(artikel);
    setMenge(menge);
    setBetrag(new Geld(betrag));
    setTatsLieferdatum(tatsLDat);
  }

  public Auftragsposition(AuftragspositionTO aposTO)
      throws AuftragException {
    this.posNr = aposTO.posNr;
    setMenge(aposTO.menge);
    setBetrag(new Geld(aposTO.betrag));
    setTatsLieferdatum(aposTO.tatsLieferdatum);
    try {
      setArtikel(Artikelverwaltung.getInstance().finden(
          aposTO.artikel));
    } catch (Exception ex) {
      throw new AuftragException(
          "Artikel in Auftragsposition nicht gefunden.");
    }
  }

  public AuftragspositionTO getTO() {
    return new AuftragspositionTO(this.posNr, this.artikel
        .getArtikelnr(), this.menge, this.betrag,
        this.tatsLieferdatum);
  }

  // Getter und Setter-Methoden
  public int getPosNr() {
    return posNr;
  }

  public Geld getBetrag() {
    return betrag;
  }

  /**
   * Betrag f�r eine Auftragsposition setzen. Auch negative
   * betr�ge sollen m�glich sein, daher keine Exception.
   */
  public void setBetrag(Geld betrag) {
    this.betrag = betrag;
    aktualisieren();
  }

  public int getMenge() {
    return menge;
  }

  /**
   * Menge f�r eine Auftragsposition setzen. Auch negative
   * Mengen sollen m�glich sein, daher keine Exception.
   */
  public void setMenge(int menge) {
    this.menge = menge;
    aktualisieren();
  }

  public LocalDate getTatsLieferdatum() {
    return tatsLieferdatum;
  }

  public void setTatsLieferdatum(LocalDate tatsLieferdatum2) {
    this.tatsLieferdatum = tatsLieferdatum2;
    aktualisieren();
  }

  public Artikel getArtikel() {
    return artikel;
  }

  public void setArtikel(Artikel neuerArtikel) {
    artikel = neuerArtikel;
    aktualisieren();
  }

  public boolean istAbgeschlossen() {
    return (tatsLieferdatum != null);
  }

  private void aktualisieren() {
    this.setChanged();
    this.notifyObservers(this);
  }
}
