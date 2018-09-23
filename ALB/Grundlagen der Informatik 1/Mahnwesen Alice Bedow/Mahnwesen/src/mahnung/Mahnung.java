/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahnung;
import inout.Console;
/**
 *
 * @author Alice Bedow
 * @date 03.10.2015
 * Programm zur Auswahl zwischen drei verschiedenen Mahntexten
 * und der Ausgabe des gewaehlten Mahntextes
 */
public class Mahnung {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String mahntext1 = "Mahntext 1";
        String mahntext2 = "Mahntext 2";
        String mahntext3 = "Mahntext 3";
        System.out.println("Auswahl von Mahntexten");
        linie_ausgeben(); //Methodenaufruf zur Ausgabe einer Linie
        //Ausgabe der Mahntexte
        for(int i=1;i<4;i++)
            System.out.println("Mahntext: "+i);
        System.out.println("Bitte wählen Sie:");
        linie_ausgeben(); //Methodenaufruf zur Ausgabe einer Linie
        //Einlesen des gewaehlten Mahntextes
        char eingabe = Console.readChar();
        //Ausgabe entsprechend der Auswahl
        switch(eingabe)
        {
            case '1': System.out.println(mahntext1); break;
            case '2': System.out.println(mahntext2); break;
            case '3': System.out.println(mahntext3); break;
            //Ausgabe bei nicht vorhandenem Mahntext 
            default: System.out.println("Mahntext nicht vorhanden");
        }
    }
    //Methode zur Ausgabe einer Linie
    public static void linie_ausgeben()
    {
        System.out.println("______________________");
    }
    
}
