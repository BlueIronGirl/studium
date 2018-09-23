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

  public Faehre(double nutzflaeche, double nutzgewicht) {
    fahrzeuge = new ArrayList<>();
    this.nutzflaeche = nutzflaeche;
    this.nutzgewicht = nutzgewicht;
  }

  public boolean beladen(Fahrzeug fahrzeug) {
    double flaeche = fahrzeug.benoetigteFlaeche_berechnen();
    double gewicht = fahrzeug.benoetigteFlaeche_berechnen();
    double nutzflaeche, nutzgewicht;
    nutzgewicht = this.nutzgewicht - Fahrzeug.getGesamtGewicht();
    nutzflaeche = this.nutzflaeche - Fahrzeug.getGesamtFlaeche();

    if (flaeche < nutzflaeche && gewicht < nutzgewicht) {
      fahrzeuge.add(fahrzeug);
      return true;
    }
    return false;
  }

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
    //System.out.println("Beladene Fahrzeuge: "+anz[0]+"x Lkw / "+anz[1]+"x Pkw / "+anz[2]+"x Motorrad / "+anz[3]+"x Fahrrad");
  }

  public double getNutzflaeche() {
    return nutzflaeche;
  }

  public double getNutzgewicht() {
    return nutzgewicht;
  }
  

  public double getDurchschnittsFlaeche() {
    return Fahrzeug.getGesamtFlaeche() / fahrzeuge.size();
  }

  public double getDurchschnittsGewicht() {
    return Fahrzeug.getGesamtGewicht() / fahrzeuge.size();
  }

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
