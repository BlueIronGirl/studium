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
public class Collatz1 {
  public static void main(String args[])
  {
    int n;
    System.out.println("Geben Sie den Wert ein:");
    n = Console.readInt();
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
      System.out.print(n);
      if(n>1)
        System.out.print(" -> ");
    }    
  }
}
