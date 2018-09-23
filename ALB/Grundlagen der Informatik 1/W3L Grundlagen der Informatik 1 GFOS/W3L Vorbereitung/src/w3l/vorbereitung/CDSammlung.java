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
public class CDSammlung {
  private static int CDAnzahlMax = 3;
  private static String[][] cd = new String[CDAnzahlMax][2];  
  private static int anzahl = 0;
  int anfang = 0;
  int ende = 0;
  public static void main(String args[])
  {
    char auswahl=0;
    while(auswahl !='9')
    {
      System.out.println("Cd einfuegen(0), loeschen(1), ausgeben(2)"); 
      auswahl = Console.readChar();
      switch(auswahl)
      {
        case '0': einfuegen();break;
        case '1': loeschen();break;
        case '2': ausgabe();break;
        default: System.out.println("Steht nicht zur Auswahl.");break;
      }
    }
    
    
  }
  private static void einfuegen()
  {
    if (anzahl < CDAnzahlMax)
    {
      System.out.println("Geben Sie Interpret und Titel ein:");
      String interpret = Console.readString();
      String titel = Console.readString();
      anzahl++;
      cd[anzahl-1][0]=interpret;
      cd[anzahl-1][1]=titel;    
    }
  }
  private static void loeschen()
  {
    if(anzahl>0)
    {
      char auswahl = Console.readChar();
      if(auswahl=='0'||auswahl=='1')
      {
        String name = Console.readString();
        for(int i=0;i<anzahl;i++)
        {
          if(cd[i][auswahl-'0'].equals(name))
          {
            for(int j=i;j<anzahl-1;j++)
            {
              for(int k=0;k<cd[0].length;k++)
              {
                int pos = j*4+k+1;
                String temp = cd[j][k];
                cd[j][k] = cd[j+1][k];
                cd[j+1][k]=temp;
              }
              
            }
            i--;
            anzahl--;
          }
        }
       
      }
    }
  }
  private static void ausgabe()
  {
    if(anzahl>0)
    {
      System.out.println("Interpret\tTitel");
      for(int i=0;i<anzahl;i++)
      {
        for(int j=0;j<cd[i].length;j++)
        {
          System.out.print(cd[i][j]+"\t");
        }
        System.out.println();
      }
    }
  }
}
