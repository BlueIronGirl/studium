/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package w3lAufgaben;
import inout.Console;
/**
 *
 * @author Alice Bedow
 * @date 03.10.2015
 */
//Programm zur Verwaltung einer CD-Sammlung
public class CDSammlung {
    private static int LAENGE = 3;
    private static int anzahl = 0;
    private static String sammlung[][]=new String[LAENGE][2];
    public static void einfuegen(String titel, String interpret)
    {
        if(anzahl<LAENGE)
        {            
            anzahl++; //Anzahl Cds in Sammlung erhöhen
            sammlung[anzahl-1][0]=titel;
            sammlung[anzahl-1][1]=interpret; 
        }
        else
            System.out.println("Die Sammlung ist voll!");
        
    }
    public static void loeschen(String cd, char titelOderInterpret)
    {
        int entscheidung = titelOderInterpret-'0'-1;
        
        for(int i=0;i<anzahl;i++) //ganzer Array
        {
            if(sammlung[i][entscheidung].equals(cd))
            {
                int loeschPos = i;
                for(int j=loeschPos;j<anzahl-1;j++)
                {
                    //vertauschen
                    String temp = sammlung[j][entscheidung];
                    sammlung[j][entscheidung]=sammlung[j+1][entscheidung];
                    sammlung[j+1][entscheidung]=temp;
                }
                anzahl--;//ein Titel weniger
                //durch Vertauschen muss die Stelle erneut ueberprueft werden, da sich dort ein neuer Wert befindet
                i--; 
            }
        }
    }
    public static void anzeigen()
    {
        System.out.println("CD Sammlung");
        System.out.println("Titel | Interpret");
        System.out.println("____________________");
        for(int i=0;i<anzahl;i++)
        {
            for(int j=0;j<sammlung[0].length;j++)
            {   
                System.out.print(sammlung[i][j]);
                if(j!=1)
                    System.out.print("\t| ");
            }
            System.out.println();
        }
    }
    public static void main(String args[])
    {
        char auswahl=0;
        while(auswahl!=9)
        {
            System.out.println("Geben Sie an, ob Sie einen Titel einfuegen (1),"
                + " einen Titel oder alle Titel eines Interpreten loeschen (2)"
                + ", ob Sie die CD-Sammlung anzeigen (3) oder ob sie das Programm abbrechen (9) moechten");
        
            auswahl = Console.readChar();
            switch(auswahl)
            {
                case '1': 
                    System.out.println("Geben Sie den Titel und den Interpret ein: ");
                    String titel = Console.readString();
                    String interpret = Console.readString();
                    einfuegen(titel,interpret);
                    break;
                case '2': 
                    System.out.println("Geben Sie an, ob Sie einen bestimmten Titel (1) "
                            + "oder alle Lieder von einem Interpreten (2) loeschen moechten");
                    char titelOderInterpret = Console.readChar();
                    String cd;
                    if(titelOderInterpret=='1')
                    {
                        System.out.println("Geben Sie den zu loeschenden Titel ein: ");
                        cd = Console.readString();
                    }
                    else if(titelOderInterpret=='2')
                    {
                        System.out.println("Geben Sie den zu loeschenden Interpreten ein: ");
                        cd = Console.readString();
                    }
                    else
                    {
                        System.out.println("Die erfolgte Eingabe ist nicht korrekt.");
                        break;
                    }
                    loeschen(cd,titelOderInterpret);
                    break;
                case '3': anzeigen();break;
                case '9': System.out.println("Das Programm ist zu Ende."); break;
                default: System.out.println("Die Eingabe ist nicht korrekt.");break;
            }
        }
    }
}
