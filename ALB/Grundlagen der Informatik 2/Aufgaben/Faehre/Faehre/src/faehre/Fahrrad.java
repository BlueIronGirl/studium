/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faehre;

/**
 *
 * @author ALB
 */
public class Fahrrad extends Fahrzeug {

  /**
   * Konstruktor
   */
  public Fahrrad(Beladung fahrer, double breite, double laenge, double gewicht) {
    super(fahrer, breite, laenge, gewicht);
    this.setBeifahrer(0);
  }

  /**
   * Rueckgabe Gesamtgewicht (ohne Beifahrer)
   *
   * @return
   */
  @Override
  public double gesamtgewicht_berechnen() {
    gewicht += fahrer.getGewicht();
    gewicht += gepaeckstueck.getGewicht();
    return gewicht;
  }
}
