/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meinkraftsport;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Alice Bedow
 * @date 17.12.15
 * 
 */
public class Trainingstag implements Serializable {

  private int tag;
  private ArrayList<Integer> gewichte = new ArrayList<>();
  private int gesamtgewicht;

  /**
   * Standardkonstruktor
   */
  public Trainingstag() {

  }

  /**
   * Konstruktor
   * @param tag 
   */
  public Trainingstag(int tag) {
    this.tag = tag;
  }

  /**
   * Rueckgabe Tag
   * @return 
   */
  public int getTag() {
    return tag;
  }

  /**
   * Rueckgabe Gesamtgewicht
   * @return 
   */
  public int getGesamtgewicht() {
    return gesamtgewicht;
  }

  /**
   * Rueckgabe berechnetes Durchschnittsgewicht
   * @return 
   */
  public int getDurchschnittsgewicht() {
    return gewichte.stream()
      .reduce(0, (gewicht1, gewicht2)
      -> gewicht1 >= gewicht2 ? gewicht1 : gewicht2);
  }

  /**
   * Tag setzen
   * @param tag 
   */
  public void setTag(int tag) {
    this.tag = tag;
  }

  /**
   * Gewicht hinzufuegen und Gesamtgewicht entsprechend erhoehen
   * @param gewicht 
   */
  public void addGewicht(int gewicht) {
    gewichte.add(gewicht);
    gesamtgewicht += gewicht;
  }

  /**
   * Rueckgabe Gewichte
   * @return 
   */
  public ArrayList<Integer> getGewicht() {
    return gewichte;
  }
  
}
