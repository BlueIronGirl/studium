/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package summenberechnungtesten;
import inout.FileIO;
/* Sekunde in Stunden, Minuten, Sekunden aufteilen
* author: Alice Bedow
* date: 08.11.2015
* A = 1 * 1000 + B = 2 = 1002
*/
public class SummenberechnungTesten {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int wert,stunden,minuten,sekunden;
        FileIO.openInFile("bed1ein.txt");
        FileIO.openOutFile("bed1aus.txt");
        int laenge = FileIO.readIntFromFile();
        System.out.println("Anzahl Testdaten: "+laenge);
        FileIO.out("Anzahl Testfaelle: "+laenge);
        for(int i=0;i<laenge;i++)
        {
            wert = FileIO.readIntFromFile();
            FileIO.out("Testfall " + i + " Wert Stunden Minuten Sekunden");
            FileIO.out(wert);
            System.out.println("Testfall "+i);
            System.out.println("Eingelesener Wert: "+wert);
            stunden = wert / 3600;
            wert -= stunden * 3600;
            minuten = wert / 60;
            wert -= minuten * 60;
            sekunden = wert;
            System.out.println("Stunden: "+stunden);
            System.out.println("Minuten: "+minuten);
            System.out.println("Sekunden: "+sekunden);
            FileIO.out(stunden);
            FileIO.out(minuten);
            FileIO.out(sekunden);
        } 
        FileIO.closeInFile();
        FileIO.closeOutFile();
        
    }    
}
