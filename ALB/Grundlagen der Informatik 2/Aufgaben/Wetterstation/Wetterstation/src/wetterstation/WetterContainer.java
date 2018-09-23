/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wetterstation;

import java.util.ArrayList;

/**
 *
 * @author Alice Bedow
 * @date 4.12.15
 * 
 */
public class WetterContainer {
  private static WetterContainer einContainer = null;
  private ArrayList<Messwert> werte = new ArrayList<>(4);
  //Singleton : nur ein Objekt der Klasse WetterContainer
  private WetterContainer()
  {

  }
  public void addMesswert(double temperatur, int tag, int monat, int jahr){
    werte.add(new Messwert(temperatur,tag,monat,jahr));
  }
  public ArrayList<Messwert> getMesswerte()
  {
    return this.werte;
  }
  public static WetterContainer erzeugeWetterContainer()
  {
    if(einContainer == null)
      einContainer = new WetterContainer();
    return einContainer;
  }
}
