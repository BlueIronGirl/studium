/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telefonverwaltung;

import java.util.ArrayList;

/**
 *
 * @author Alice Bedow
 * @date 30.11.15
 * 
 */
public class Nutzer {
  private String name;
  private ArrayList<Telefon> telefon = new ArrayList<>(2); //Vorbelegung mit 2, da höchstens 2
  /**
   * Rueckgabe Name
   * @return 
   */
  public String getName()
  {
    return this.name;
  }
  /**
   * Rueckgabe Telefone
   * @return 
   */
  public ArrayList<Telefon> getTelefon()
  {
    return telefon;
  }
  /**
   * Name setzen
   * @param name 
   */
  public void setName(String name)
  {
    this.name = name;
  }
  /**
   * Telefon hinzufuegen
   * @param telefon 
   */
  public void setTelefon(Telefon telefon)
  {
    if (this.telefon.size()<2)
      this.telefon.add(telefon); 
    else
      System.out.println("Die maximale Anzahl Telefone wurde erreicht.");
  }
  /**
   * Telefon loeschen
   * @param telefon 
   */
  public void removeTelefon(Telefon telefon)
  {
    for(int i=0;i<this.telefon.size();i++)
    {
      if(this.telefon.get(i) == telefon)
        this.telefon.remove(i);
    }
  }
}
