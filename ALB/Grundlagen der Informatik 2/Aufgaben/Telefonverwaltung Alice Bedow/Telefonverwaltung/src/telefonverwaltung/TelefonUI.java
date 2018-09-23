/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telefonverwaltung;

import java.util.ArrayList;

/**
 *
 * @author Alice Bedow
 * @date 30.11.15
 */
public class TelefonUI {

  /**
   * @param args the command line arguments
   */
  private static ArrayList<Nutzer> nutzer = new ArrayList<>();
  /**
   * Hauptprogramm
   * @param args 
   */
  public static void main(String[] args) {
  
    nutzer.add(new Nutzer());
    nutzer.add(new Nutzer());
    nutzer.get(0).setName("Alice Bedow");
    nutzer.get(0).setTelefon(new Telefon("12345",0));
    nutzer.get(0).setTelefon(new Telefon("67890",1));
    nutzer.get(1).setName("Martina Mustermann");
    nutzer.get(1).setTelefon(new Telefon("34567",0));
    nutzer.get(1).setTelefon(new Telefon("",1));
    daten_ausgeben();    
  }
  /**
   * Nutzerdaten ausgeben
   */
  private static void daten_ausgeben()
  {
    for(Nutzer elem : nutzer)
    {
      System.out.println("Nutzer "+elem.getName());
      ArrayList<Telefon> telefon = elem.getTelefon();
      int k=0;
      for(Telefon j : telefon)
      {        
        System.out.print("Endgerät " + k + ": ");
        if(j.getNummer() != null && !(j.getNummer().equals("")))
          System.out.print("Nummer " + j.getNummer() + " und ");
        System.out.print("Typ " + j.getTyp());
        System.out.println();
        k++;
      }
    }
  }

  
}
