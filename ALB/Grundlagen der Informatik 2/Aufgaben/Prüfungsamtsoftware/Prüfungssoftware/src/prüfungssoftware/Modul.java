/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prüfungssoftware;

import java.util.ArrayList;

/**
 *
 * @author Alice Bedow
 * @date 07.12.15
 */
public class Modul implements CreditPointI{
  ArrayList<Lehrveranstaltung> lehrveranstaltungen = new ArrayList<>();
  int creditpoints;
  String name;
  /**
   * Konstruktor
   * @param name 
   */
  public Modul(String name)
  {
    this.name = name;
  }
  /**
   * Creditpoints ermitteln
   * @return 
   */
  @Override
  public int getCPs()
  {
    creditpoints = 0;
    for(Lehrveranstaltung i: lehrveranstaltungen)
    {
      creditpoints += i.getCPs();
    }
    return creditpoints;
  }
  /**
   * Name ermitteln
   * @return 
   */
  @Override
  public String getName()
  {    
    return name;
  }
  /**
   * Lehrveranstaltung hinzufuegen
   * @param lehrveranstaltung 
   */
  public void addLehrveranstaltung(Lehrveranstaltung lehrveranstaltung)
  {
    this.lehrveranstaltungen.add(lehrveranstaltung);
  }
}
