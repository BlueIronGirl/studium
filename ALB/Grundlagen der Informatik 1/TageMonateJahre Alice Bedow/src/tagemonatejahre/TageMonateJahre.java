/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tagemonatejahre;
import  inout.Console;
/**
 * @author Alice Bedow
 * @date 03.10.2015
 */
public class TageMonateJahre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Geben Sie die Anzahl Tage an, die in Jahre,"
                + " Monate und restliche Tage umgerechnet werden soll: ");
        int eingabeAnzahlTage = Console.readInt();
        System.out.println("Eingegebener Wert: "+eingabeAnzahlTage+" Tage");
        int jahre = eingabeAnzahlTage / 365;
        eingabeAnzahlTage = eingabeAnzahlTage % 365;
        int monate = eingabeAnzahlTage / 30;
        eingabeAnzahlTage = eingabeAnzahlTage % 30;   
        int tage = eingabeAnzahlTage; 
        System.out.println("Jahre: " + jahre);
        System.out.println("Monate: " + monate);
        System.out.println("Tage: " + tage);
    }
    
}
