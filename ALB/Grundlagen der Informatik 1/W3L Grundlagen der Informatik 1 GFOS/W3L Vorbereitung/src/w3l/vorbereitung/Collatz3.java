/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3l.vorbereitung;
import inout.Console;
/**
 *
 * @author ALB
 */
public class Collatz3 {
  public static void main(String args[])
  {
    int n,laenge=0,w;
    System.out.println("Geben Sie den Wert ein:");
    n = Console.readInt();
    w=n;
    while(n > 1)
    {
      if(n % 2 == 0) //gerade
      {
        n /= 2;
      }
      else
      {
        n = 3 * n + 1;
      }
      if(n > w)
        w = n;
      laenge++;
      System.out.print(n);
      if(n>1)
        System.out.print(" -> ");
    } 
    System.out.println();
    System.out.println("Folgenlaenge: "+laenge);
    System.out.println("Groesster zwischendurch erreichter Wert: "+w);
  }
}
