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
public class Pkw extends Fahrzeug {

  /**
   * Konstruktor
   *
   * @param fahrer
   * @param breite
   * @param laenge
   * @param gewicht
   */
  public Pkw(Beladung fahrer, double breite, double laenge, double gewicht) {
    super(fahrer, breite, laenge, gewicht);
    this.setBeifahrer(3);
  }
}
