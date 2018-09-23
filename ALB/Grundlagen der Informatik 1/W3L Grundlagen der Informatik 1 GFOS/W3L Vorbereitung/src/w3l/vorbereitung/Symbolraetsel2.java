/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3l.vorbereitung;

/**
 *
 * @author ALB
 */
public class Symbolraetsel2 {
  public static void main(String args[])
  {
    int anzahlKombinationen = 0;
    for(int h=0;h<10;h++)
      for(int e=0;e<10;e++)
        for(int s=0;s<10;s++)
          for(int t=0;t<10;t++)
            for(int b=0;b<10;b++)
            {
              if(h!=e && h!=s && h!=t && h!=b && e!=s && e!=t && e!=b && s!=t && s!=b && t!=b)
              {
                int hes = h*100+e*10+s;
                int the = t*100+h*10+e;
                int best = b*1000+e*100+s*10+t;
                if(hes + the == best)
                {
                  anzahlKombinationen++;
                  System.out.println("Kombination Nr: "+anzahlKombinationen);
                  System.out.println(""+h+e+s);
                  System.out.println("+"+t+h+e);
                  System.out.println("-----");
                  System.out.println(""+b+e+s+t);
                }
              }
            }
    System.out.print("Es gibt "+anzahlKombinationen);
    if(anzahlKombinationen>1)
      System.out.println(" Kombinationen");
    else
      System.out.println(" Kombination");
  }
}
