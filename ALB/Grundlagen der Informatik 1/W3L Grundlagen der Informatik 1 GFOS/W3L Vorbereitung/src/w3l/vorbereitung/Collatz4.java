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
public class Collatz4 {
  public static void main(String args[])
  {
    int n,laenge=0,w;
    System.out.println("Wert\tFolgenlaenge\tw");
    for(int i=1;i<=40;i++)
    {
      n=i;
      w=0;
      laenge = 0;
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
      } 
      System.out.print(i+"\t");
      System.out.print(laenge+"\t\t");
      System.out.print(w);
      System.out.println();
    }
  }
}
