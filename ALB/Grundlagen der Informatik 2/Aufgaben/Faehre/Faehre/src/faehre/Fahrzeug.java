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
public abstract class Fahrzeug {

  protected Beladung fahrer;
  protected Beladung gepaeckstueck;
  private double breite;
  private double laenge;
  protected double gewicht;
  private double flaeche;
  private int beifahrerAnz;
  private ArrayList<Beladung> beifahrer;
  private static double gesamtGewicht;
  private static double gesamtFlaeche;

  /**
   * Konstruktor
   *
   * @param fahrer
   * @param breite
   * @param laenge
   * @param gewicht
   */
  protected Fahrzeug(Beladung fahrer, double breite, double laenge, double gewicht) {
    this.fahrer = fahrer;
    this.breite = breite;
    this.laenge = laenge;
    this.gewicht = gewicht;
    this.flaeche = benoetigteFlaeche_berechnen();
    beifahrer = new ArrayList<>(3);
  }

  /**
   * Rueckgabe berechnetes Gesamtgewicht
   *
   * @return
   */
  protected double gesamtgewicht_berechnen() {
    gewicht += fahrer.getGewicht();
    gewicht += gepaeckstueck.getGewicht();
    for (Beladung i : beifahrer) {
      gewicht += i.getGewicht();
    }
    return gewicht;
  }

  /**
   * Rueckgabe berechnete benoetigte Flaeche
   *
   * @return
   */
  protected double benoetigteFlaeche_berechnen() {
    flaeche = breite * laenge;
    flaeche *= 1.5; //Standflaeche
    return flaeche;
  }

  /**
   * Beifahrer hinzufuegen
   *
   * @param beifahrer
   */
  protected void addBeifahrer(Beladung beifahrer) {
    if (this.beifahrer.size() < beifahrerAnz) {
      this.beifahrer.add(beifahrer);
    }
  }

  /**
   * Beifahrer-ArrayList einstellen
   *
   * @param beifahrer
   */
  protected void setBeifahrer(int beifahrer) {
    this.beifahrerAnz = beifahrer;
    if (beifahrerAnz > 0) {
      this.beifahrer = new ArrayList<>(beifahrerAnz);
    }
  }

  /**
   * Gepaeckstueck zuweisen
   *
   * @param gewicht
   */
  protected void setGepaeckstueck(double gewicht) {
    gepaeckstueck = new Beladung(gewicht);
  }

  /**
   * Rueckgabe Gesamtgewicht
   */
  protected static double getGesamtGewicht() {
    return gesamtGewicht;
  }

  /**
   * Rueckgabe Gesamtflaeche
   *
   * @return
   */
  protected static double getGesamtFlaeche() {
    return gesamtFlaeche;
  }

  /**
   * Klassenmethode: Gesamtgewicht erhoehen
   *
   * @param gewicht
   */
  protected static void setGesamtGewicht(double gewicht) {
    Fahrzeug.gesamtGewicht += gewicht;
  }

  /**
   * Klassenmethode: Gesamtflaeche erhoehen
   *
   * @param flaeche
   */
  protected static void setGesamtFlaeche(double flaeche) {
    Fahrzeug.gesamtFlaeche += flaeche;
  }

}
