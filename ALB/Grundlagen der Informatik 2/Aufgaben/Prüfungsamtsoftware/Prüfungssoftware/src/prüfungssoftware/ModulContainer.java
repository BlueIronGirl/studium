/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prüfungssoftware;
//import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author Alice Bedow
 * @date 07.12.15
 */
public class ModulContainer //implements Iterator,Iterable
{
  private ArrayList<Modul> module = new ArrayList<>();
  private static ModulContainer einContainer = null;
//  private static int zaehler = 0;
  /**
   * Singleton-Muster: privater Konstruktor verhindert Erzeugen eines Objekts
   */
  private ModulContainer()
  {
    
  }
  /**
   * Oeffentliche Methode, um genau ein Objekt zu erzeugen
   * @return 
   */
  public static ModulContainer erzeugeModulContainer()
  {
    if(einContainer == null)
      einContainer = new ModulContainer();
    return einContainer;
  }
  /**
   * Modul hinzufuegen
   * @param modul 
   */
  public void addModul(Modul modul)
  {
    this.module.add(modul);
  }
  /**
   * alle Module ermitteln
   * @return 
   */
  public ArrayList<Modul> alleModule()
  {
    return module;
  }
//  public Modul next()
//  {
//    return module.get(zaehler-1);
//  }
//  public boolean hasNext()
//  {
//    zaehler++;
//    if(zaehler <= module.size())
//      return true;
//    else
//      return false;
//  }
//  public Iterator iterator()
//  {
//    return this;
//  }
}
