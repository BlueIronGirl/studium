/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verifikation;
import inout.Console;
/**
 *
 * @author ALB
 */
public class Verifikation {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    int summe = summiere(Console.readInt());
    System.out.println(summe);
  }
  static int summiere(int n)
  {
    int i,summe;
    assert(n>0); //Anfangsbedingung
    
    summe = 0;
    i = 1;
    while(i!=(n+1))
    {
      summe = summe + i;
      i = i + 1;
    }
    assert(summe == (n + 1)*n/2); //Endebedingung
    return summe;
  }
  
}
