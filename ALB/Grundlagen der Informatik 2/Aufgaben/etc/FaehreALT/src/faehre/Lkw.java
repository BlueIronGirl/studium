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
public class Lkw extends Fahrzeug {
  public Lkw(Beladung fahrer,double breite,double laenge, double gewicht)
  {
    super(fahrer,breite,laenge,gewicht);
    this.setBeifahrer(2);
  }
}
