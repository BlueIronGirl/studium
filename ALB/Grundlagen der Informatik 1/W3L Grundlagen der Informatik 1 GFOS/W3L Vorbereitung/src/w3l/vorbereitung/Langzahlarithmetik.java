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
public class Langzahlarithmetik {
  public static void main(String args[])
  {
    int koerner[] = new int[20];
    int summe[] = new int[20];
    koerner[0] = 1;
    for(int i=1;i<=64;i++)
    {
      if(i>1)
        koerner_multiply(koerner);  
      summe_add(koerner,summe);
      ausgabe(koerner,summe,i);
    }
    
  }
  private static void koerner_multiply(int koerner[])
  {
    int ua=0,un=0;
    for(int i=0;i<koerner.length;i++)
    {
      un = ua;
      ua = (koerner[i]*2+un)/10;
      koerner[i] = (koerner[i]*2+un)%10;
    }
  }
  private static void summe_add(int koerner[], int summe[])
  {
    int ua=0,un=0;
    for(int i=0;i<koerner.length;i++)
    {
      un = ua;
      ua = (koerner[i]+summe[i]+un)/10;
      summe[i] = (koerner[i]+summe[i]+un)%10;
    }
  }
  private static void ausgabe(int koerner[],int summe[],int index)
  {
    if(index==1)
      System.out.println("Nr\tKoerner\t\t\tSumme");
    System.out.print(index+"\t");
    for(int i=koerner.length-1;i>=0;i--)
      System.out.print(koerner[i]);
    System.out.print("\t");
    for(int i=summe.length-1;i>=0;i--)
      System.out.print(summe[i]);
    System.out.println();
  }
}
