/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faehre;

import java.util.ArrayList;

/**
 *
 * @author ALB
 */
public class Fahrzeug {

  protected Beladung fahrer;
  protected Beladung gepaeckstueck;
  protected double breite;
  protected double laenge;
  protected double gewicht;
  protected double flaeche;
  protected int beifahrerAnz;
  protected ArrayList<Beladung> beifahrer;
  private static double gesamtGewicht;
  private static double gesamtFlaeche;

  protected Fahrzeug(Beladung fahrer, double breite, double laenge, double gewicht) {
    this.fahrer = fahrer;
    this.breite = breite;
    this.laenge = laenge;
    this.gewicht = gewicht;
    Fahrzeug.gesamtGewicht += gewicht;
    Fahrzeug.gesamtFlaeche += benoetigteFlaeche_berechnen();
    beifahrer = new ArrayList<>(3);
  }

  protected double gesamtgewicht_berechnen() {
    gewicht += fahrer.getGewicht();
    gewicht += gepaeckstueck.getGewicht();
    for (Beladung i : beifahrer) {
      gewicht += i.getGewicht();
    }
    return gewicht;
  }

  protected double benoetigteFlaeche_berechnen() {
    flaeche = breite * laenge;
    flaeche *= 1.5; //Standflaeche
    return flaeche;
  }

  public void addBeifahrer(Beladung beifahrer) {
    if (this.beifahrer.size() < beifahrerAnz) {
      this.beifahrer.add(beifahrer);
    }
  }

  protected void setBeifahrer(int beifahrer) {
    this.beifahrerAnz = beifahrer;
    if (beifahrerAnz > 0) {
      this.beifahrer = new ArrayList<>(beifahrerAnz);
    }
  }

  protected void setGepaeckstueck(double gewicht) {
    gepaeckstueck = new Beladung(gewicht);
  }

  public static double getGesamtGewicht() {
    return gesamtGewicht;
  }

  public static double getGesamtFlaeche() {
    return gesamtFlaeche;
  }

}
