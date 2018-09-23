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
public class WetterUI {

  public static void main(String[] args) {
   WetterContainer container = WetterContainer.erzeugeWetterContainer();
   WetterSpeicher speicher = new WetterSpeicher();
   container.addMesswert(1.0,14,12,2012);
   container.addMesswert(2.0,15,2,2012);
   container.addMesswert(13.4,16,2,2012);
   container.addMesswert(14.5,17,2,2012);
   speicher.speichereWerte(container.getMesswerte());
   speicher.leseWerte();
   
  }
}
