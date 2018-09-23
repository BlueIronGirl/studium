/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wetterstation;

/**
 *
 * @author Alice Bedow
 * @date 4.12.15
 *
 */
public class Messwert {

  private double temperatur;
  private int tag;
  private int monat;
  private int jahr;

  public Messwert(double temperatur, int tag, int monat, int jahr) {
    this.temperatur = temperatur;
    this.tag = tag;
    this.monat = monat;
    this.jahr = jahr;
  }

  public double getTemperatur() {
    return this.temperatur;
  }

  public int getTag() {
    return this.tag;
  }

  public int getMonat() {
    return this.monat;
  }

  public int getJahr() {
    return this.jahr;
  }
}
