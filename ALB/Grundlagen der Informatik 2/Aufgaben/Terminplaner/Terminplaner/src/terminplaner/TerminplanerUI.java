/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminplaner;
import inout.Console;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
/**
 *
 * @author Alice Bedow
 * @date 27.11.2015
 * Programm zur Verwaltung von Terminen
 */
public class TerminplanerUI {
  private static ArrayList<Termin> termin = new ArrayList(); //init 10 Elem
  /**
   * Hauptprogramm
   * @param args 
   */
  public static void main(String args[])
  {
    persoenlicherTermin();
    char auswahl = 0;
    while(auswahl != '3')
    {
      linie_ausgeben();
      System.out.println("Bitte wählen Sie zwischen den folgenden Optionen:");
      System.out.println();
      System.out.println("Neuer Termin: 1");
      System.out.println("Termine anzeigen: 2");
      System.out.println("Anwendung beenden: 3");
      linie_ausgeben();    
      auswahl = Console.readChar();
      switch(auswahl)
      {
        case '1': terminEinfuegen(); break;
        case '2': termineAnzeigen(); break;
        case '3': 
          linie_ausgeben();
          System.out.println("Die Anwendung wurde beendet.");
          linie_ausgeben();
          break;
        default: System.out.println("Keine gültige Eingabe."); break;
      }
    }
  }
  /**
   * Methode zum Ausgeben einer Linie
   */
  private static void linie_ausgeben()
  {
    System.out.println("----------------------------");
  }
  /**
   * Methode zum Einfuegen eines Termins
   */
  private static void terminEinfuegen()
  {
    try
    {
      StringBuilder datum=null;
      System.out.println("Bitte geben Sie den Termin ein:");
      System.out.println("Jahr:");
      datum = new StringBuilder(Console.readString());
      datum.append("-");
      System.out.println("Monat:");
      datum.append(Console.readString());
      datum.append("-");
      System.out.println("Tag:");
      datum.append(Console.readString());
      LocalDate temp = LocalDate.parse(datum);
      System.out.println("Terminbeschreibung");
      String beschreibung = Console.readString();
      if(temp.isAfter(LocalDate.now()))
      { 
        Termin neuerTermin = new Termin();
        neuerTermin.setZeitpunkt(temp);
        neuerTermin.setBeschreibung(beschreibung);
        int laenge = termin.size();
        char ueberpruefen = 0;
        for(int i=0;i<laenge;i++) //Position ermitteln
        {
          ueberpruefen = Termin.ueberpruefenZeitpunkt(termin.get(i),neuerTermin);
          if(ueberpruefen == 'n')
          {  
            termin.add(i,neuerTermin);
            break;
          }
        }
        if(ueberpruefen != 'n')
          termin.add(termin.size(),neuerTermin);
      }
      else
        System.out.println("Termin liegt in der Vergangenheit.");
    }
    catch(java.time.format.DateTimeParseException e)
    {
      System.out.println("Keine gueltige Datumseingabe.");
    }
    
  }
  /**
   * Methode zum Anzeigen aller Termine
   */
  private static void termineAnzeigen()
  {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MMMM.YYYY",Locale.GERMAN);      
    for(int i=0;i<termin.size();i++)
    {
      System.out.println("Termin: Nr. "+(i+1));   
      System.out.println("Datum: "+termin.get(i).getZeitpunkt().format(format));
      System.out.println("Beschreibung: "+termin.get(i).getBeschreibung());
      System.out.println();
    }
  }
  /**
   * Methode zum Einfuegen des persönlichen Termins
   */
  private static void persoenlicherTermin()
  {
    Termin persoenlich = new Termin(LocalDate.parse("1996-06-13"),"Alice Bedow");
    termin.add(persoenlich);
  }
  
}
