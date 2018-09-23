/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cdsammlung;
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
    public static void einfuegen()
    {
        //Ueberpruefung, ob die CD Sammlung ihr Maximum erreicht hat
        if(anzahl<LAENGE)
        {   
            System.out.println("Geben Sie den Titel und den Interpret ein: ");
            String titel = Console.readString();
            String interpret = Console.readString();
            //Ueberpruefung, ob der Datensatz schon vorhanden ist
            boolean vorhanden = false;
            for(int i=0;i<anzahl;i++)
            {
                if(sammlung[i][0].equals(titel)&&sammlung[i][1].equals(interpret))
                    vorhanden = true;
            }
            if(vorhanden == false) //wenn der Datensatz noch nicht vorhanden ist, einfuegen
            {
                anzahl++; //Anzahl Cds in Sammlung erhöhen
                sammlung[anzahl-1][0]=titel;
                sammlung[anzahl-1][1]=interpret; 
                System.out.println("Der eingegebene Datensatz wurde erfolgreich eingefuegt.");
            }  
            else
                System.out.println("Der eingegebene Datensatz ist schon vorhanden");
            
        }
        else
            System.out.println("Die Sammlung ist voll!");
        
    }
    public static void loeschen()
    {
        if(anzahl > 0)
        {
            System.out.println("Geben Sie an, ob Sie einen bestimmten Titel eines Interpreten (1) "
                    + "oder alle Lieder von einem Interpreten (2) loeschen moechten");
            //Auswahl, ob ein Titel oder alle Lieder eines Interpreten geloescht werden sollen
            char titelOderInterpret = Console.readChar();

            String cdInterpret;       
            if(titelOderInterpret=='1' || titelOderInterpret =='2')
            {
                System.out.println("Geben Sie den Interpreten ein: ");  
            }
            else
            {
                System.out.println("Die erfolgte Eingabe steht nicht zur Auswahl.");
                return;
            }
            //Auswahl welcher Titel oder Interpret geloescht werden soll
            cdInterpret = Console.readString();
            String cdTitel = "";
            int entscheidung = titelOderInterpret-'0'-1;
            if(entscheidung == 0)
            {    
                System.out.println("Geben Sie den gewuenschten Titel ein: ");
                cdTitel = Console.readString();            
            }
            //loeschen anhand der eingegebenen Parameter
            boolean geloescht = false;
            for(int i=0;i<anzahl;i++) //ganzes Array
                        {
                if( (sammlung[i][1].equals(cdInterpret)&&entscheidung==1) ||
                        (sammlung[i][0].equals(cdTitel)&&sammlung[i][1].equals(cdInterpret)&&entscheidung==0) )
                {
                    int loeschPos = i;
                    for(int j=loeschPos;j<anzahl-1;j++)
                    {
                        //vertauschen  
                        String temp[] = new String[sammlung[j].length];
                        for(int k=0;k<temp.length;k++)
                        {
                            temp[k] = sammlung[j][k];
                            sammlung[j][k]=sammlung[j+1][k];
                            sammlung[j+1][k]=temp[k];
                        }
                        
                    }
                    anzahl--;//ein Titel weniger
                    geloescht = true;
                    //durch Vertauschen muss die Stelle erneut ueberprueft werden, da sich dort ein neuer Wert befindet
                    i--; 
                }
            }
            if(geloescht == false)
                System.out.println("Zu Ihrer Eingabe konnte kein passender Datensatz gefunden werden");
            else
                System.out.println("Die Datensaetze zu Ihrer Eingabe wurden erfolgreich geloescht.");
        }
        else
            System.out.println("Die Sammlung ist leer.");
    }
    public static void anzeigen()
    {
        if(anzahl > 0) //wenn es Datensaetze gibt
        {
            System.out.println("CD Sammlung");
            System.out.println("Titel\tInterpret");
            System.out.println("____________________");
            for(int i=0;i<anzahl;i++)
            {
                for(int j=0;j<sammlung[0].length;j++)
                {   
                    System.out.print(sammlung[i][j]);
                    if(j!=1)
                        System.out.print("\t");
                }
                System.out.println();
            }
        }
        else
            System.out.println("Die Sammlung ist leer.");
    }
    public static void main(String args[])
    {
        char auswahl=0;
        //Methodenaufrufe entsprechend der Auswahl
        while(auswahl!='9')
        {
            System.out.println("Geben Sie an, ob Sie einen Titel einfuegen (1),"
                + " einen Titel oder alle Titel eines Interpreten loeschen (2)"
                + ", ob Sie die CD-Sammlung anzeigen (3) oder ob sie das Programm abbrechen (9) moechten");
        
            auswahl = Console.readChar();
            switch(auswahl)
            {
                case '1': 
                    einfuegen();
                    break;
                case '2': 
                    loeschen();
                    break;
                case '3': 
                    anzeigen();
                    break;
                case '9': System.out.println("Das Programm ist zu Ende."); break;
                default: System.out.println("Die Eingabe ist nicht korrekt.");break;
            }
        }
    }
}
