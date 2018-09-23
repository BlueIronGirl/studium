/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prüfungssoftware;

/**
 *
 * @author Alice Bedow
 * @date 07.12.15
 */
public class Lehrveranstaltung implements CreditPointI {
  String name;
  int creditpoints;
  /**
   * Konstruktor
   * @param name
   * @param creditpoints 
   */
  public Lehrveranstaltung(String name, int creditpoints)
  {
    this.name = name;
    this.creditpoints = creditpoints;
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
   * Creditpoints ermitteln
   * @return 
   */
  @Override
  public int getCPs()
  {
    return creditpoints;
  }
}
