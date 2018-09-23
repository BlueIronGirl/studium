package de.w3l.anw.wetterstation.applikation;

/* Programmname: Wetter
 * Container-Klasse: WetterContainer
 * Aufgabe: Verwaltung der Wetterdaten
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Vector;

import de.w3l.anw.wetterstation.datenhaltung.ObjektDatei;

public class WetterContainer {

  private Vector<Wetter> meineWetterdaten =
      new Vector<Wetter>();

  private Wetterquelle meineWetterquelle =
      new Wetterquelle();

  private ObjektDatei eineObjektDatei;

  private Ort meinOrt;

  @SuppressWarnings("unchecked")
  public WetterContainer(String dateiname) {
    try {
      // Bestehende Daten lesen...
      eineObjektDatei = new ObjektDatei(dateiname);
      Vector<Wetter> geleseneWetterdaten =
          (Vector<Wetter>) eineObjektDatei.leseObjekt();
      if (geleseneWetterdaten != null) {
        meineWetterdaten = geleseneWetterdaten;
      }
      int anzahlDatensaetze = getAnzahlDatensaetze();
      if (anzahlDatensaetze > 0)
        meinOrt = getWetter(anzahlDatensaetze - 1).getOrt();

      // ... und anschlie�end einen Datensatz hinzuf�gen
      datensatzHinzufuegen();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Einen Datensatz aus der aktuellen Wetterquelle
   * hinzuf�gen
   */
  public void datensatzHinzufuegen() {
    Wetter einWetter = null;
    try {
      einWetter =
          meineWetterquelle.holeAktuellesWetter(meinOrt);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    if (einWetter != null) {
      if (getAnzahlDatensaetze() > 0) {
        Wetter letztesWetter =
            getWetter(getAnzahlDatensaetze() - 1);

        /*
         * Der Datensatz wird nur hinzugef�gt, wenn er neuer
         * als der bislang letzte Datensatz ist
         */
        if (letztesWetter.getZeit().isBefore(
            einWetter.getZeit()))
          meineWetterdaten.addElement(einWetter);
      } else {
        meineWetterdaten.addElement(einWetter);
      }
    }
  }

  /**
   * einen Datensatz entfernen
   *
   * @param einWetter
   */
  public void datensatzEntfernen(Wetter einWetter) {
    meineWetterdaten.removeElement(einWetter);
  }

  /**
   * liefert eine Aufzaehlung (Enumeration) ueber alle
   * Datensaetze zurueck
   */
  public Enumeration<Wetter> elements() {
    return meineWetterdaten.elements();
  }

  /**
   * Sucht nach einem bestimmten Wetter-Objekt und liefert
   * den Index zur�ck. Wird das Objekt nicht gefunden, wird
   * -1 zur�ckgeliefert.
   *
   * @param w
   * @return
   */
  public int findeWetterIndex(Wetter w) {
    return meineWetterdaten.indexOf(w);
  }

  public int getAnzahlDatensaetze() {
    return meineWetterdaten.size();
  }

  /**
   * liefert die Wetterdaten an der Position "Nummer" Bei
   * einem ung�ltigen Index wird null zur�ckgeliefert.
   *
   * @param Nummer
   */
  public Wetter getWetter(int Nummer) {
    try {
      return (Wetter) meineWetterdaten.elementAt(Nummer);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * @return Vector<String> der alle Tage im gew�nschten
   * Format enth�lt
   */
  public Vector<String> getVorhandeneTage(DateTimeFormatter dtf) {
    Vector<String> ergebnis = new Vector<String>();
    int anzahlSaetze = getAnzahlDatensaetze();
    for (int i = 0; i < anzahlSaetze; i++) {
      String datum = meineWetterdaten.get(i).getZeit().format(dtf);
      if (!ergebnis.contains(datum))
        ergebnis.add(datum);
    }
    return ergebnis;
  }

  /**
   * @return Vector<String> der alle lokalen Zeiten f�r
   * das vorgegebene Datum im gew�nschten Format enth�lt,
   * f�r die Wetterdaten vorhanden sind.
   */
  public Vector<String> getVorhandeneZeiten(LocalDate datum, DateTimeFormatter dtf) {
    Vector<Wetter> daten = getWetterFuerDatum(datum);
    Vector<String> ergebnis = new Vector<String>();
    int anzahlSaetze = daten.size();
    for (int i = 0; i < anzahlSaetze; i++) {
      LocalTime wetterdaten = daten.get(i).getZeit().toLocalTime();
      ergebnis.add(wetterdaten.format(dtf));
    }
    return ergebnis;
  }

  /**
   * Liefert das Wetter f�r einen gew�nschten Zeitpunkt
   *
   * @param gewZeitpunkt
   */
  public Wetter getWetterFuerZeitpunkt(LocalDateTime gewZeitpunkt) {
    Wetter ergebnis = null;
    int anzahlDaten = getAnzahlDatensaetze();
    for (int i = 0; i < anzahlDaten; i++) {
      Wetter w = getWetter(i);
      int vergleich = (w.getZeit().compareTo(gewZeitpunkt));
      if (vergleich == 0) {
        ergebnis = w; // Wir haben es gefunden!
        break;
      }
      if (vergleich > 0)
        break; // Nichts gefunden
    }
    return ergebnis;
  }

  /**
   * @return Vector<Wetter> der alle Wetterdaten f�r das
   * gew�nschte Datum (Tag) enth�lt.
   */
  public Vector<Wetter> getWetterFuerDatum(LocalDate gewDatum) {
    Vector<Wetter> ergebnis = new Vector<Wetter>();
    int anzahlDaten = getAnzahlDatensaetze();
    for (int i = 0; i < anzahlDaten; i++) {
      Wetter w = getWetter(i);
      int vergleich = w.getZeit().toLocalDate().compareTo(gewDatum);
      if (vergleich > 0)
        break;
      if (vergleich == 0)
        ergebnis.add(w);
    }
    return ergebnis;
  }

  // speichert beim Beenden der Anwendung alle Datensaetze
  public void endeAnwendung() {
    eineObjektDatei.speichereObjekt(meineWetterdaten);
  }
}
