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
 * @param <H>
 */
public class HelferSuche <P extends Patient, H extends Helfer> {

  private static HelferSuche helferSuche = null;

  /**
   * Konstruktor: Singleton-Muster
   */
  private HelferSuche() {

  }

//  public void setHelfer(ArrayList<Patient> patienten, ArrayList<Helfer> helfer) {
  public void setHelfer(ArrayList<P> patienten, ArrayList<H> helfer) {
    SchleifePatient:
    for (int i = 0; i < patienten.size(); i++) //alle Patienten
    {
      for (int j = 0; j < helfer.size(); j++) {
        if (patienten.get(i).getHelfer() == null) //Helfer hat noch keinen Patienten
        {
          if (helfer.get(j).getOrt().equals(patienten.get(i).getOrt())) {
            if (patienten.get(i).getPflegestufe() >= 2) {
              if (helfer.get(j).getAusgebildet() == true) {
                patienten.get(i).setHelfer(helfer.get(j));
                continue SchleifePatient; //naechster Patient
              }
            } else {
              patienten.get(i).setHelfer(helfer.get(j));
              continue SchleifePatient; //naechster Patient
            }
          }
        }
      }
    }
  }

  public static HelferSuche erstelleHelferSuche() {
    if (helferSuche == null) {
      helferSuche = new HelferSuche();
    }
    return helferSuche;
  }
}
