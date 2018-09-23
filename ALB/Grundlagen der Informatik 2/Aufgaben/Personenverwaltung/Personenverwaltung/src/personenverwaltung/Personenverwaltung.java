/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personenverwaltung;

import java.util.ArrayList;

/**
 *
 * @author Alice Bedow
 * @date 8.12.15
 * @param <P>
 */
public class Personenverwaltung<P extends Person> {

  private ArrayList<P> patienten = new ArrayList<>();
  private ArrayList<P> helfer = new ArrayList<>();
  private static Personenverwaltung verwaltung = null;

  /**
   * Konstruktor: Singleton-Muster
   */
  private Personenverwaltung() {

  }

  public static Personenverwaltung erstellePersonenverwaltung() {
    if (verwaltung == null) {
      verwaltung = new Personenverwaltung();
    }
    return verwaltung;
  }

  public void addPatient(P person) {
    patienten.add(person);
  }

  public void addHelfer(P person) {
    helfer.add(person);
  }

  public ArrayList getPatienten() {
    return this.patienten;
  }

  public ArrayList getHelfer() {
    return this.helfer;
  }
}
