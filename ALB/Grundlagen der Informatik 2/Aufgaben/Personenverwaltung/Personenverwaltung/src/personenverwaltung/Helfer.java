/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personenverwaltung;

/**
 *
 * @author Alice Bedow
 * @date 8.12.15
 */
public class Helfer extends Person<Helfer> {

  private boolean ausgebildet;

  public Helfer(int nummer, String name, String strasse, String ort, boolean ausgebildet) {
    super(nummer, name, strasse, ort);
    this.ausgebildet = ausgebildet;
  }

  public boolean getAusgebildet() {
    return this.ausgebildet;
  }

  public void setAusgebildet(boolean ausgebildet) {
    this.ausgebildet = ausgebildet;
  }

}
