/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3lUebungen;
import inout.Console;
/**
 *
 * @author ALB
 */
public class LagerVerwaltung {
  public static void main (String args[])
  {
    int lager[][][][] = new int[3][4][5][2];
    //1.Ebene : Lager besteht aus 3 Regalen
    //2. Ebene: Regal besteht aus 4 Reihen
    //3. Ebene: Reihe besteht aus 5 Fächern
    //4. Ebene: pro Fach wird Artikelnr und Menge gespeichert (3)
    int artikelnr;
    char weiter = 'N';
    //initialisierung
    for(int i=0;i<lager.length;i++) //alle Regale
      for(int j=0;j<lager[0].length;j++) //alle Reihen
        for(int k=0;k<lager[0][0].length;k++) //alle Fächer
        {
          //Zufallszahlen zwischen 0 und 100
          lager[i][j][k][0] = (int)(Math.random()*100.0); //Artikelnr
          //Zufallszahlen zwischen 0 und 50
          lager[i][j][k][1] = (int)(Math.random()*50.0); //Menge
        }
    do
    {
      int menge = 0;
      System.out.println();
      System.out.print("Bitte Artikelnummer eingeben (zwischen 0 und 100): ");
      artikelnr = Console.readInt();
      for(int i=0;i<lager.length;i++)
        for(int j=0;j<lager[0].length;j++)
          for(int k=0;k<lager[0][0].length;k++)
          {
            if(lager[i][j][k][0] == artikelnr)
            {
              menge = menge + lager[i][j][k][1];
              System.out.println("Regal: "+i);
              System.out.println("Reihe: "+j);
              System.out.println("Fach: "+k);
            }
          }
      System.out.println("Artikelnummer: "+artikelnr);
      System.out.println("Gelagerte Menge: "+menge);
      
      System.out.println("Noch eine Abfrage? J(a) N(ein)");
      weiter = Console.readChar();
    }while(weiter == 'J'||weiter == 'j');
  }
}
