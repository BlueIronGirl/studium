/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meinkraftsport;

import inout.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Alice Bedow
 * @date 17.12.15
 * 
 */
public class MeinKraftSportUI {

  private static TrainingstageContainer container = TrainingstageContainer.getObjektReferenz();

  /**
   * Hauptprogramm
   * @param args 
   */
  public static void main(String[] args) {
    char auswahl = 0;
    while (auswahl != '9') {
      System.out.println("Bitte Funktion auswaehlen:");
      System.out.println("1: Trainingstag erfassen");
      System.out.println("2: Liste aller Trainingstage");
      System.out.println("3: Programm beenden und Daten speichern");
      System.out.println("Abbruch: 9");
      System.out.println("Bitte Ziffer 1,2,3 oder 9 eingeben: ");
      auswahl = Console.readChar();
      switch (auswahl) {
        case '1':
          trainingstagErfassen();
          break;
        case '2':
          ausgabeTrainingstage();
          break;
        case '3':
          auswahl = '9';
          beendenUndSpeichern(); 
          System.out.println("Programm wird beendet.");
          break;
        case '9':
          System.out.println("Programm wird beendet.");
          break;
        default:
          System.out.println("Dies steht nicht zur Auswahl");
          break;
      }
    }
  }

  /**
   * Erfassen eines Trainingstages
   */
  public static void trainingstagErfassen() {
    System.out.println("Geben Sie bitte den Tag des Jahres und anschl. alle 10 Gewichte, "
      + "jeweils getrennt durch ein Leerzeichen ein:");
    Scanner sc = new Scanner(System.in);//.useDelimiter(" ");
    String[] tag = new String[11];
    Trainingstag einTag = null;
    boolean erfolg = true;
    for(int i=0;i<tag.length;i++)
    {     
      try{             
        tag[i] = sc.next();
        if (eingabeUeberpruefung(tag[i])) {
          if(i==0)
          {
            if(1 <= Integer.parseInt(tag[0]) && Integer.parseInt(tag[0]) <= 365)
              einTag = new Trainingstag(Integer.parseInt(tag[0]));
            else
            {
              System.out.println("Bitte ein gueltigen Tag eingeben.");
              erfolg = false;
              break;
            }
          }
            
          else
          {
            int gewicht = Integer.parseInt(tag[i]);
            if(gewicht % 2 == 0 && gewicht >= 0 && gewicht <= 500)
            {
              einTag.addGewicht(gewicht);
            }
            else
            {
              erfolg = false;
              System.out.println("Bitte nur gueltige Gewichte eingeben.");
              break;
            }
          }
        }
        else {
          System.out.println("Bitte geben Sie korrekte Daten ein.");
          erfolg = false;
          break;
        }
      }
      catch(NullPointerException e)
      {
        erfolg = false;
        System.out.println("Fehler beim Einlesen: "+e.getMessage());
      }
    }
    if(erfolg == true)
    {
      container.erzeugeTrainingstag(einTag);    
      System.out.println("Trainingstag gespeichert.");     
    }      
  }

  /**
   * Ausgabe aller Trainingstage
   */
  public static void ausgabeTrainingstage() {
    if(container.getAnzahlTage()>0)
    {
      System.out.println("Tag\tGewichte\t\t\tGesamtgewicht\tDurchschnittsgewicht");
      Iterator<Trainingstag> e = container.iteratorTrainingstag();
      while (e.hasNext()) {
        Trainingstag tag = e.next();
        System.out.print(tag.getTag()+" ");
        ArrayList<Integer> gewichte = tag.getGewicht(); 
        for(int i=0;i<gewichte.size();i++)
          System.out.print(gewichte.get(i)+" ");
        System.out.print(tag.getGesamtgewicht()+" ");
        System.out.print(tag.getDurchschnittsgewicht());
        System.out.println();
      }
      System.out.println("Anzahl Trainingstage: "+container.getAnzahlTage());
      System.out.println("Durchschnittsgewicht aller Trainingstage: "+
        Math.round(container.getDurchschnittsgewicht()*1000.0)/1000.0);
      System.out.println("Max Durchschnittsgewicht: "+container.getMaximalesDurchschnittsgewicht());
      System.out.println("Min Durchschnittsgewicht: "+container.getMinimalesDurchschnittsgewicht());
      System.out.println("Tendenz aller Trainingstage "+container.getTendenz().getValue());
    }
    else
    {
      System.out.println("Es wurden noch keine Trainingstage angelegt.");
    }
  }

  /**
   * Aufruf der abschliessenden Speichermethode
   */
  public static void beendenUndSpeichern() {
    container.endeAnwendung();
  }

  /**
   * Ueberpruefung der eingegebenen Werte
   * @param text
   * @return 
   */
  public static boolean eingabeUeberpruefung(String text) {
    if (!(text.matches("[0-9]+"))) { //Text ist keine Zahl?
      return false;
    }
    return true; //Erfolg wenn Eingabe Zahl ist
  }
}
