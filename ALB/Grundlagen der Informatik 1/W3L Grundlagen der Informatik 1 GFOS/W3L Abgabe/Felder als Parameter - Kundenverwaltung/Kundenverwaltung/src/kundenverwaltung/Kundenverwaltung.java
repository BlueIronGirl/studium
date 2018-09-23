/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kundenverwaltung;
import inout.Console;
/**
 *
 * @author Alice Bedow
 * @date 08.11.2015
 * Programm zum Sortieren einer Kundenverwaltung und Suchen eines enthaltenen Datensatzes
 */
public class Kundenverwaltung {
    public static void main(String args[])
    {
        String kunden[][]=
            {{"1","Bedow","Alice","Essen"},
            {"2","E","Z","Berlin"},
            {"3","F","X","Frankfurt"},
            {"4","A","Y","Mainz"},
            {"5","D","M","Ingolstadt"}}; //zweidimensionales Array zur Speicherung der Kundendaten
        System.out.println("Geben Sie an, ob Sie die Kundendaten sortieren(0) oder ob Sie nach einem bestimmten Datensatz suchen(1) möchten");
        char auswahl = Console.readChar(); //Eingabe der Auswahlmöglichkeiten: sortieren (0) oder suchen (1)
        if(auswahl == '0')
        {
            System.out.println("Geben Sie an, ob Sie nach Kundennr(0), Name(1), Vorname(2) oder Ort(3) sortieren moechten: ");
            int sortierenSpalte = Console.readInt(); //Eingabe der Auswahlmöglichkeiten: Sortierung nach Kundennummer(0), Name(1), Vorname(2), Ort(3)
            System.out.println("Geben Sie an, ob die Sortierung aufsteigend(0) oder absteigend(1) erfolgen soll:");
            int sortierenArt = Console.readInt();
            //Sortieren und vertauschen
            if(0<=sortierenSpalte && sortierenSpalte<=3 && sortierenArt>=0 && sortierenArt<=1) //wenn die Eingabe korrekt war, entsprechend der Auswahlmöglichkeiten
            {
                sortieren(kunden,sortierenSpalte,sortierenArt); //sortieren der Datensaetze
                ausgabe(sortierenArt); //Ausgabe der Sortierart
                ausgabe(kunden); //Ausgabe der sortierten Datensaetze
            }
            else
            {
                System.out.println("Dies steht nicht zur Auswahl.");
            }
        }
        else if(auswahl == '1')
        {
            System.out.println("Geben Sie an, nach welchem Datensatz Sie suchen moechten."
                    + " Es kann nur nach einem Namen gesucht werden.");
            String suche = Console.readString(); //Suchkriterium (Name oder Nr)
            suchen(kunden,suche);
        }
        else
        {
            System.out.println("Dies steht nicht zur Auswahl.");
        }
       
    }
    public static void sortieren(String[][] kunden,int sortierenSpalte,int sortierenArt)
    {
        String min,merke; 
        int pos, posMin; //Variablen für aktuelle Position, Position der kleinsten Variable des aktuellen Durchlaufs
        for(int i=0;i<kunden.length;i++) //alle Zeilen bzw. Datensätze
        {
            //kleinste Position ab i suchen
            posMin = i; min = kunden[i][sortierenSpalte]; //erste Position des aktuellen Durchlaufes speichern
            for(pos=i+1; pos<kunden.length;pos++) //alle Spalten bzw. Attribute
            {
                boolean vergleich = true;
                if(sortierenArt == 0) //aufsteigend
                    vergleich = kunden[pos][sortierenSpalte].compareTo(min)<0; //fuer Abfrage auf <
                else if(sortierenArt == 1) //absteigend
                    vergleich = kunden[pos][sortierenSpalte].compareTo(min)>0; //fuer Abfrage auf >              
                if(vergleich)
                {
                    min=kunden[pos][sortierenSpalte]; //Speichern der kleinsten Position, um sie mit den folgenden Werten vergleichen zu können
                    posMin = pos; //Kleinste Position merken
                }
            }
            //Vertauschen ganze Zeilen
            for(int j=0;j<kunden[0].length;j++) //alle Attribute
            {
                merke=kunden[i][j]; 
                kunden[i][j]=kunden[posMin][j];
                kunden[posMin][j]=merke;
            }
        }
    }
    public static void suchen(String[][] kunden,String suche)
    {
        boolean gefunden = false; //Variable zur Überprüfung, ob der Datensatz gefunden wurde
        for(int i=0;i<kunden.length;i++) //alle Zeilen
        {
            if(kunden[i][1].equals(suche)) //wenn der Datensatz mit dem Suchkriterium übereinstimmt
            {
                System.out.println("Nr\tName\tVorname\tOrt"); //Ausgabe Überschriften
                for(int j=0;j<kunden[0].length;j++) //alle Spalten
                {
                    System.out.print(kunden[i][j]+"\t"); //Ausgabe Wert & Tabzeichen
                }
                System.out.println(); //nächste Zeile
                gefunden = true; //Überprüfungsvariable setzen
            }  
        }
        if(gefunden == false) //wenn kein Datensatz gefunden wurde
                System.out.println("Der von Ihnen eingegebene Datensatz existiert nicht.");
    }
    public static void ausgabe(String[][] kunden)
    {
        for(int i=0;i<kunden.length;i++) //alle Zeilen
        {   
            for(int j=0;j<kunden[0].length;j++) //alle Spalten
            {   
                System.out.print(kunden[i][j]); //Ausgabe Wert
                System.out.print("\t"); //Tabzeichen
            }
            System.out.println(); //nächste Zeile
        }           
    }
    public static void ausgabe(int sortierenArt)
    {
        String art = "";
        if(sortierenArt == 0)
            art = "aufsteigend";
        else
            art = "absteigend";            
        System.out.println("Lexikografisch "+art+" sortierte Namensliste ");
        System.out.println("Nr\tName\tVorname\tOrt"); //Ausgabe Überschriften 
    }
}
