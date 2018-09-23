/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faehre;

/**
 *
 * @author Alice Bedow
 * @date 2.12.15
 */
public class FaehreUI {
  private static Faehre faehre = new Faehre(3000,300_000); //AB
  public static void main(String[] args) {   
    boolean platz = true;
    double breite[][] = {{3,4.5},{2,4},{1,3},{0.5,1.5}};
    double laenge[][] = {{8,40},{3,7},{1.5,4},{1,3}};
    double gewicht[][] = {{7500,20_000},{1000,2800},{150,400},{5,20}};
    double personGewicht[] = {40,120};
    double gepaeckstueckGewicht[][] = {{500,2000},{100,300},{10,50},{1,30}};
    while(platz)
    {
      int zufall= (int)(zufall(0,3));
      Fahrzeug fz = null;
      switch(zufall)
      {
        case 0:   
          fz = new Lkw(new Beladung(zufall(40,120)),zufall(3,4.5),zufall(8,40),zufall(7500,20_000));
          break;
        case 1:
          fz = new Pkw(new Beladung(zufall(40,120)),zufall(2,4),zufall(3,7),zufall(1000,2800));
          break;
        case 2:
          fz = new Motorrad(new Beladung(zufall(40,120)),zufall(1,3),zufall(1.5,4),zufall(150,400));
          break;
        case 3:
          fz = new Fahrrad (new Beladung(zufall(40,120)),zufall(0.5,1.5),zufall(1,3),zufall(5,20));
          break;
        default: continue;
      }
      fz.setGepaeckstueck(zufall(gepaeckstueckGewicht[zufall][0],gepaeckstueckGewicht[zufall][1]));
      fz.addBeifahrer(new Beladung(zufall(personGewicht[0],personGewicht[1])));
      platz = faehre.beladen(fz);
      if(platz)
      {
       
      }
      ausgabe();         
    }
  }
  public static double zufall(double min, double max)
  {
    return Math.round(min + (Math.random()*(max-min+1))*100)/100.0;
  }
  public static void ausgabe()
  {
    int anz[] = faehre.ausgabe_beladeneFahrzeuge();
    System.out.println("Beladene Fahrzeuge: "+anz[0]+"x Lkw / "+anz[1]+"x Pkw / "+anz[2]+"x Motorrad / "+anz[3]+"x Fahrrad");
    System.out.println("Aktuelle Nutzfläche: "+faehre.getNutzflaeche());
    System.out.println("Aktuelles Nutzgewicht: "+faehre.getNutzgewicht());
    System.out.println("Durchschnittliche Nutzfläche: "+faehre.getDurchschnittsFlaeche());
    System.out.println("Durchschnittliches Nutzgewicht: "+faehre.getDurchschnittsGewicht());
    System.out.println("Anzahl Fahrzeuge (Prognose): "+faehre.berechne_freiePlaetze());
  }
}
