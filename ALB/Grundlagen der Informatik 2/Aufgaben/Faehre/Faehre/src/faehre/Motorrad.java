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
public class Motorrad extends Fahrzeug {

  /**
   * Konstruktor
   *
   * @param fahrer
   * @param breite
   * @param laenge
   * @param gewicht
   */
  public Motorrad(Beladung fahrer, double breite, double laenge, double gewicht) {
    super(fahrer, breite, laenge, gewicht);
    setBeifahrer(1);
  }
}
