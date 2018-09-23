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
public class FelderAlsParameter {
  public static void main(String args[])
  {
     String kunden[][] = 
     {{"1","Bedow","Alice","Essen"},
             {"2","E","Z","Berlin"},
             {"3","F","X","Frankfurt"},
             {"4","A","Y","Mainz"},
             {"5","D","M","Ingolstadt"}};
    
    char auswahl = 0;
    while(auswahl != '9')
    {
      System.out.println("Möchten Sie sortieren(0) oder suchen(1)?");
      auswahl = Console.readChar();
      if(auswahl == '0') //sortieren
      {
        System.out.println("Nr(0), Name(1), Vorname(2), Ort(3)");
        auswahl = Console.readChar();
        if(auswahl == '0'||auswahl=='1'||auswahl=='2'||auswahl=='3') //suchkriterium
        {
          int index = auswahl - '0';
          System.out.println("Aufsteigend(0), Absteigend(1)");
          auswahl = Console.readChar();
          if(auswahl == '0'||auswahl=='1')  //aufsteigend absteigend
          {
            sortieren(kunden,index,auswahl);
            ausgabe(kunden);
          }
        }
      }
      else if(auswahl == '1') //suchen
      {
        System.out.println("Welcher Kundenname?");
        String name = Console.readString();
        suchen(kunden,name);
      }
      
    }
  }
  private static void sortieren(String kunden[][],int index,char sortierArt)
  {
    for(int i=0;i<kunden.length;i++)
      for(int j=0;j<kunden[i].length;j++)
      {
        int posMin = i*4+j;
        String min = kunden[posMin/4][index];
        for(int pos=posMin+1;pos<kunden.length*kunden[i].length;pos++)
        {
          boolean vergleich = false;
          if(sortierArt == '0')//aufsteigend
          {
            vergleich = kunden[pos/4][index].compareTo(min)<0;
          }
          else if(sortierArt == '1')//absteigend
          {
            vergleich = kunden[pos/4][index].compareTo(min)>0;
          }
          if(vergleich)
          {
            posMin = pos;
            min = kunden[posMin/4][index];
            
          }
        }
        //vertauschen
        for(int k=0;k<kunden[i].length;k++)
        {
          String temp = kunden[i][k];
          kunden[i][k] = kunden[posMin/4][k];
          kunden[posMin/4][k] = temp;
        }
      }
  }
  private static void ausgabe (String[][] kunden)
  {
    System.out.println("Nr\tName\tVorname\tOrt");
    for(int i=0;i<kunden.length;i++)
    {
      for(int j=0;j<kunden[i].length;j++)
      {
        System.out.print(kunden[i][j]+"\t");
      }
      System.out.println();
    }    
  }
  private static void suchen(String[][] kunden, String name)
  {
    System.out.println("Nr\tName\tVorname\tOrt");
    for(int i=0;i<kunden.length;i++)
    {
      for(int j=0;j<kunden[i].length;j++)
      {
        if(kunden[i][j].equals(name))
        {
          for(int k=0;k<kunden[i].length;k++)
          {
            System.out.print(kunden[i][k]+"\t");
          }
          System.out.println();
        }
      }
    }
  }
}
