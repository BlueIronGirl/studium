/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faehre;

import java.util.ArrayList;

/**
 *
 * @author Alice Bedow
 * @date 2.12.15
 */
public class Faehre {

  private double nutzflaeche;
  private double nutzgewicht;

  private ArrayList<Fahrzeug> fahrzeuge;

  /**
   * Konstruktor
   *
   * @param nutzflaeche
   * @param nutzgewicht
   */
  public Faehre(double nutzflaeche, double nutzgewicht) {
    fahrzeuge = new ArrayList<>();
    this.nutzflaeche = nutzflaeche;
    this.nutzgewicht = nutzgewicht;
  }

  /**
   * Beladen eines Fahrzeugs
   *
   * @param fahrzeug
   * @return
   */
  public boolean beladen(Fahrzeug fahrzeug) {
    double flaeche = fahrzeug.benoetigteFlaeche_berechnen();
    double gewicht = fahrzeug.gesamtgewicht_berechnen();
    if ((flaeche + Fahrzeug.getGesamtFlaeche() <= this.nutzflaeche) && (gewicht + Fahrzeug.getGesamtGewicht() <= this.nutzgewicht)) {
      Fahrzeug.setGesamtFlaeche(flaeche);
      Fahrzeug.setGesamtGewicht(gewicht);
      fahrzeuge.add(fahrzeug);
      return true;
    }
    return false;
  }

  /**
   * Rueckgabe der Anzahl beladener Fahrzeuge
   *
   * @return
   */
  public int[] ausgabe_beladeneFahrzeuge() {
    int[] anz = new int[4];
    for (Fahrzeug i : fahrzeuge) {
      String klasse = i.getClass().getName();
      switch (klasse) {
        case "faehre.Lkw":
          anz[0]++;
          break;
        case "faehre.Pkw":
          anz[1]++;
          break;
        case "faehre.Motorrad":
          anz[2]++;
          break;
        case "faehre.Fahrrad":
          anz[3]++;
          break;
        default:
          System.out.println("Klasse nicht vorhanden.");
          break;
      }
    }
    return anz;
  }
  /**
   * Rueckgabe der Durchschnittsflaeche
   *
   * @return
   */
  public double getDurchschnittsFlaeche() {
    return Fahrzeug.getGesamtFlaeche() / fahrzeuge.size();
  }

  /**
   * Rueckgabe des Durchschnittsgewichts
   *
   * @return
   */
  public double getDurchschnittsGewicht() {
    return Fahrzeug.getGesamtGewicht() / fahrzeuge.size();
  }

  /**
   * Berechnung der verbleibenden freien Plaetze
   *
   * @return
   */
  public int berechne_freiePlaetze() {
    int freiePlaetze = 0;
    double nutzgewicht = this.nutzgewicht - Fahrzeug.getGesamtGewicht();
    double nutzflaeche = this.nutzflaeche - Fahrzeug.getGesamtFlaeche();
    if (nutzgewicht / 20000 < nutzflaeche / 180) {
      freiePlaetze = (int) (nutzgewicht / 20000);
    } else //nutzgewicht / 20000 > nutzflaeche / 180
    {
      freiePlaetze = (int) (nutzflaeche / 180);
    }
    return freiePlaetze;
  }

}
