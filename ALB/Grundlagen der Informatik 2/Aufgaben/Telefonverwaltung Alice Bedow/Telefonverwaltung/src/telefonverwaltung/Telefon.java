/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telefonverwaltung;

/**
 *
 * @author Alice Bedow
 * @date 30.11.15
 */
public class Telefon {
  private String nummer;
  private int typ;
  /**
   * Default-Konstruktor
   */
  public Telefon() 
  {
    
  }
  /**
   * Konstruktor mit Uebergabe
   * @param nummer
   * @param typ 
   */
  public Telefon(String nummer, int typ)
  {
    this.nummer = nummer;
    this.typ = typ;
  }
  /**
   * Nummer ermitteln
   * @return 
   */
  public String getNummer()
  {
    return this.nummer;
  }
  /**
   * Typ ermitteln
   * @return 
   */
  public int getTyp()
  {
    return this.typ;
  }
  /**
   * Nummer festlegen
   * @param nummer 
   */
  public void setNummer(String nummer)
  {
    this.nummer = nummer;
  }
  /**
   * Typ festlegen
   * @param typ 
   */
  public void setTyp(int typ)
  {
    this.typ = typ;
  }
  /**
   * Waehlen
   */
  public void waehlen()
  {
    
  }
}
