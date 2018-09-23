/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meinkraftsport;

/**
 *
 * @author Alice Bedow
 * @date 17.12.15
 * 
 */
public enum Tendenz 
{
  I('+'),
  D('-'),
  S('o');
  private final char value;
  
  /**
   * Konstruktor
   * @param c 
   */
  private Tendenz(char c)
  {
    value = c;
  }
  
  /**
   * Rueckgabe zugeordneter Wert
   * @return 
   */
  public char getValue()
  {
    return value;
  }
}
