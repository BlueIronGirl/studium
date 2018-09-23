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
public class Bildverarbeitung2 {
  public static void main(String args[])
  {
    int werte[] = {22,0,4,2,62,262,25,2,86,7,21,6,9,2,1,5,11,45};
    int werte2[] = {33,9,2,6,5,8,2,4,8,43,58,34,2,5,2, 2, 1 };
    int median;
    //sortieren
    sortieren(werte);
    sortieren(werte2);
    for(int i=0;i<werte.length;i++)
      System.out.print(werte[i]+"  ");
    System.out.println();
    for(int i=0;i<werte2.length;i++)
      System.out.print(werte2[i]+"  ");
    System.out.println();
    median = median_berechnen(werte);
    System.out.println("Der Median des ersten Feldes ist: "+median);
    median = median_berechnen(werte2);
    System.out.println("Der Median des zweiten Feldes ist: "+median);
    System.out.println(werte.length);
    System.out.println(werte2.length);
  }
  private static void sortieren(int werte[])
  {
    for(int i=0;i<werte.length;i++)
    {
      int posMin = i;
      int min = werte[i];
      for(int pos=posMin+1;pos<werte.length;pos++)
      {
        if(werte[pos]<min)
        {
          min = werte[pos];
          posMin = pos;
        }
      }
      int temp = werte[i];
      werte[i] = werte[posMin];
      werte[posMin] = temp;
    }
  }
  private static int median_berechnen(int werte[])
  {
    int median=0;
    int mitte = werte.length/2;
    if(werte.length%2==0)//gerade
    {
      median = (werte[mitte]+werte[mitte-1])/2;
    }
    else //ungerade
    {
      median = (werte[mitte]);
    }
    return median;
  }
}
