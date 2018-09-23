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
public class Datenbasis implements Serializable {
  private ArrayList<Trainingstag> tage = new ArrayList<>();
  /**
   * Rueckgabe der Trainingstage
   * @return 
   */
  public ArrayList<Trainingstag> getTage()
  {
    return tage;
  }
  /**
   * Trainingstage zuweisen
   * @param tage 
   */
  public void setTage(ArrayList<Trainingstag> tage)
  {
    this.tage = tage;
  }
}
