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
public class Person <P> {

  private int nummer;
  private String name;
  private String strasse;
  private String ort;
  public Person(int nummer, String name, String strasse, String ort)
  {
    this.nummer = nummer;
    this.name = name;
    this.strasse = strasse;
    this.ort = ort;
  }
  public int getNummer() {
    return this.nummer;
  }

  public String getName() {
    return this.name;
  }

  public String getStrasse() {
    return this.strasse;
  }

  public String getOrt() {
    return this.ort;
  }

  public void setNummer(int nummer) {
    this.nummer = nummer;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStrasse(String strasse) {
    this.strasse = strasse;
  }

  public void setOrt(String ort) {
    this.ort = ort;
  }
}
