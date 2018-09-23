/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personenverwaltungid;

/**
 *
 * @author ALB
 */
public class Person {

  private String name;
  private int alter;

  /**
   * Konstruktor
   * @param name
   * @param alter 
   */
  public Person(String name, int alter) {
    this.name = name;
    this.alter = alter;
  }
  
  /**
   * Name ermitteln
   * @return 
   */
  public String getName() {
    return this.name;
  }

  /**
   * Alter ermitteln
   * @return 
   */
  public int getAlter() {
    return alter;
  }

  /**
   * Name setzen
   * @param name 
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Alter setzen
   * @param alter 
   */
  public void setAlter(int alter) {
    this.alter = alter;
  }

  /**
   * Differenz zum Vergleich der Namenslaenge
   * @param person
   * @return 
   */
  public int namenDifferenz(Person person) {
    return this.name.compareTo(person.name);
  }

  /**
   * Differenz zum Vergleich des Alters
   * @param person
   * @return 
   */
  public int alterDifferenz(Person person) {
    return this.alter - person.alter;
  }

  /**
   * Ueberschriebene toString Methode fuer die Ausgabe
   * @return 
   */
  @Override
  public String toString() {
    return String.format("%s - %s", name, alter);
  }
}
