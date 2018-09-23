package de.w3l.anw.avplus.applikationslogik;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import de.w3l.anw.avplus.dao.AuftragTO;
import de.w3l.anw.avplus.dao.AuftragspositionTO;
import de.w3l.anw.utility.Geld;

public class Auftrag extends Observable implements Observer {

  private int auftragsnr;

  private LocalDate auftragsdatum;

  private LocalDate lieferdatum;

  private Kunde kunde;

  private Currency waehrung;

  private Vector<Auftragsposition> apositionen =
      new Vector<>();

  /**
   * Ein neuer Auftrag wird erzeugt. Ein neuer Auftrag hat
   * zun�chst noch keine Auftragspositionen.
   */
  public Auftrag(int auftragsnr, LocalDate auftragsdatum,
                 LocalDate lieferdatum, Kunde kunde, Currency waehrung)
      throws AuftragException {
    this.auftragsnr = auftragsnr;
    setAuftragsdatum(auftragsdatum);
    setLieferdatum(lieferdatum);
    setKunde(kunde);
    setWaehrung(waehrung);
  }

  /**
   * Aus dem Transfer-Objekt ergebnisTO wird ein
   * Auftrag-Objekt erzeugt.
   *
   * @param ergebnisTO
   * @throws AuftragException
   */
  public Auftrag(AuftragTO ergebnisTO)
      throws AuftragException {
    if (ergebnisTO == null)
      return;
    try {
      this.auftragsnr = ergebnisTO.auftragsnr;
      setAuftragsdatum(ergebnisTO.auftragsdatum);
      setLieferdatum(ergebnisTO.lieferdatum);
      setKunde(Kundenverwaltung.getInstance().finden(
          ergebnisTO.kunde));
      setWaehrung(ergebnisTO.waehrung);

      // Aus allen AuftragspositionTO-Objekten wieder
      // "richtige" Auftragspositionen machen.
      Vector<AuftragspositionTO> apos =
          ergebnisTO.apositionen;
      for (int i = 0; i < apos.size(); i++) {
        this
            .einfuegenPos(new Auftragsposition(apos.get(i)));
      }
    } catch (Exception ex) {
      throw new AuftragException(
          "Fehler bei Umwandlung Transfer-Objekt.");
    }
  }

  /**
   * Aus einem Auftrag-Objekt wird ein Transfer-Objekt
   * erzeugt.
   */
  public AuftragTO getTO() {
    Vector<AuftragspositionTO> aposTO =
        new Vector<>();

    // Nacheinander f�r alle Auftragspositionen
    // Transfer-Objekte erzeugen und im AuftragTO verstauen.
    for (int i = 0; i < this.apositionen.size(); i++)
      aposTO.add(this.apositionen.get(i).getTO());
    AuftragTO ergebnis =
        new AuftragTO(this.auftragsnr, this.auftragsdatum,
            this.lieferdatum, this.kunde.getKundennr(),
            this.waehrung, aposTO);
    return ergebnis;
  }

  public boolean einfuegenPos(Auftragsposition neueApos) {
    boolean rc = apositionen.add(neueApos);
    if (rc)
      this.aktualisieren();
    return rc;
  }

  public boolean entfernenPos(Auftragsposition apos) {
    boolean rc = apositionen.remove(apos);
    if (rc)
      this.aktualisieren();
    return rc;
  }

  /**
   * L�scht einen vorhandenen Auftrag.
   */
  public void auftragLoeschen() {
    Iterator<Auftragsposition> positionen =
        apositionen.iterator();
    while (positionen.hasNext()) {
      entfernenPos(positionen.next());
    }
  }

  public LocalDate getAuftragsdatum() {
    return auftragsdatum;
  }

  public void setAuftragsdatum(LocalDate auftragsdatum) {
    this.auftragsdatum = auftragsdatum;
    this.aktualisieren();
  }

  /**
   * Die Auftragssumme berechnet sich aus der Summe aller
   * Positionen. Ist keine Position vorhanden wird ein
   * Betrag von 0 in der Auftragsw�hrung zur�ckgegeben
   */
  public Geld getAuftragssumme() {
    Iterator<Auftragsposition> positionen =
        apositionen.iterator();
    Geld ergebnis = new Geld(0, 0, waehrung);
    if (positionen.hasNext())
      ergebnis = new Geld(positionen.next().getBetrag());
    while (positionen.hasNext()) {
      ergebnis.addieren(positionen.next().getBetrag());
    }
    return ergebnis;
  }

  public Kunde getKunde() {
    return kunde;
  }

  public void setKunde(Kunde kunde) {
    this.kunde = kunde;
    this.aktualisieren();
  }

  public Currency getWaehrung() {
    return waehrung;
  }

  public void setWaehrung(Currency waehrung) {
    this.waehrung = waehrung;
    this.aktualisieren();
  }

  public int getAuftragsnr() {
    return auftragsnr;
  }

  /**
   * Ein Auftrag ist abgeschlossen, wenn alle Positionen
   * abgeschlossen sind. Sind keine Positionen vorhanden,
   * gilt der Auftrag als abgeschlossen.
   */
  public boolean getAuftragAbgeschlossen() {
    Iterator<Auftragsposition> positionen =
        apositionen.iterator();
    boolean abgeschlossen = true;
    while (positionen.hasNext() && abgeschlossen) {
      abgeschlossen &= positionen.next().istAbgeschlossen();
    }
    return abgeschlossen;
  }

  public LocalDate getLieferdatum() {
    return lieferdatum;
  }

  public void setLieferdatum(LocalDate ldat)
      throws AuftragException {
    if (ldat.isBefore(auftragsdatum)) {
      throw new AuftragException(
          "Lieferdatum liegt vor Auftragsdatum");
    }
    lieferdatum = ldat;
    this.aktualisieren();
  }

  private void aktualisieren() {
    this.setChanged();
    this.notifyObservers(this);
  }

  public void update(Observable o, Object obj) {
    /*
     * Wir bekommen Bescheid, wenn sich an einer Position
     * etwas ge�ndert hat. Wir melden diese �nderung einfach
     * nach oben weiter. obj ist die ge�nderte
     * Auftragsposition
     */
    this.notifyObservers(obj);
  }
}