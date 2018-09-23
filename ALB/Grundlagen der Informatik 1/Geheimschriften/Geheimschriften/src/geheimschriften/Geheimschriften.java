/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geheimschriften;
import inout.Console;
/**
 *
 * @author Alice Bedow
 * @date 07.11.2015
 */
public class Geheimschriften {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char daten[][] = new char[2][];
        System.out.println("Waehlen Sie aus, ob Sie einen Text verschluesseln(v) oder entschluesseln(e) wollen:");
        char auswahl = Console.readChar();
   
        if(auswahl == 'v' || auswahl == 'e')
        {
            verschluesselnOderEntschluesseln(daten,auswahl); //verschluesseln oder entschluesseln entsprechend der Auswahl
        }
        else //falsche/andere Eingabe
        {
            System.out.println("Bitte geben Sie (v) oder (e) ein.");
        }    
    }
    
    public static void verschluesselnOderEntschluesseln(char daten[][],char auswahl)
    {
        System.out.println("Geben Sie das zu verwendende Schluesselwort ein: ");
        char schluessel[] = Console.readCharArray();
        boolean ok = ueberpruefen(schluessel); //ueberpruefung des Schluesselwortes
        if(ok == false) //Abbruch bei Eingabe anderer Zeichen als Grossbuchstaben
            return;
        if(auswahl == 'v')
            System.out.println("Geben Sie den zu verschluesselnden Klartext (in GROSSBUCHSTABEN) ein:");
        else if(auswahl == 'e')
            System.out.println("Geben Sie den zu entschluesselnden Codetext (in GROSSBUCHSTABEN) ein:");
        daten[0] = Console.readCharArray();
        daten[1] = new char[daten[0].length]; 
        ok = ueberpruefen(daten[0]);  //ueberpruefung des eingegebenen Wertes
        if(ok == true) //nur bei Eingabe von Grossbuchstaben
        {
            int z=0;
            System.out.println("Länge: "+daten[0].length);
            for(int i=0;i<daten[0].length;i++)
            {
                //die Addition von ausgleich verhindert negative Werte
                int ausgleich=26+(26*(i/26));
                if(auswahl == 'v')
                    daten[1][i] = (char)((ausgleich+daten[0][i]-'A'+(schluessel[z]-'A'-i))%26+'A'); //verschluesseln
                else if(auswahl == 'e')
                    daten[1][i] = (char)((ausgleich+daten[0][i]-'A'-(schluessel[z]-'A'-i))%26+'A'); //entschluesseln
                z++;
                if(z==schluessel.length) //Erreichen Ende schluesselwort --> Wiederholung Schluesselwort
                    z=0;
            }
            ausgabe(daten,schluessel,auswahl); //Ausgabe der Werte
        }
    }
    
    public static void ausgabe(char daten[][],char schluessel[],char auswahl)
    {
        System.out.println("Schlüsselwort:");
        for(int i=0;i<schluessel.length;i++)
            System.out.print(schluessel[i]);
        System.out.println();
        System.out.println("Text");
        for(int i=0;i<daten[0].length;i++)
            System.out.print(daten[0][i]);
        System.out.println();            
        if(auswahl == 'v')
            System.out.println("Codetext: ");
        else if(auswahl == 'e')
            System.out.println("Klartext: "); 
        for(int i=0;i<daten[1].length;i++)
        {
            System.out.print(daten[1][i]);
        }
        System.out.println();
    }
    
    public static boolean ueberpruefen(char daten[])
    {
        for(int i=0;i<daten.length;i++)
        {
            if(daten[i] < 'A' || daten[i] > 'Z') //nur Grossbuchstaben
            {
                System.out.println("Bitte nur Grossbuchstaben eingeben.");
                return false; 
            } 
        }
        return true;
    }
   
}
