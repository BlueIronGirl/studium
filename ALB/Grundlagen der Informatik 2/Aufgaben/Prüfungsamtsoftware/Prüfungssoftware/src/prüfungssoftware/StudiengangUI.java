/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prüfungssoftware;

/**
 *
 * @author Alice Bedow
 * @date 07.12.15
 */
public class StudiengangUI {
  /**
   * Hauptprogramm: Module samt Lehrveranstaltungen erzeugen
   * @param args 
   */
  public static void main(String[] args) { 
    ModulContainer module = ModulContainer.erzeugeModulContainer();
    Modul einModul = new Modul("Grundlagen der Informatik");
    einModul.addLehrveranstaltung(new Lehrveranstaltung("Grundlagen der Informatik 1",5));
    einModul.addLehrveranstaltung(new Lehrveranstaltung("Grundlagen der Informatik 2",5));
    module.addModul(einModul);
    ausgeben(einModul);
    einModul = new Modul("Mathematik");
    einModul.addLehrveranstaltung(new Lehrveranstaltung("Algebra",5));
    einModul.addLehrveranstaltung(new Lehrveranstaltung("Analysis",5));
    einModul.addLehrveranstaltung(new Lehrveranstaltung("Statistik",5));
    einModul.addLehrveranstaltung(new Lehrveranstaltung("Diskrete Mathe",5));
    module.addModul(einModul);
    ausgeben(einModul);
    einModul = new Modul("Alice Bedow");
    einModul.addLehrveranstaltung(new Lehrveranstaltung("Angewandte Mathematik",5));
    einModul.addLehrveranstaltung(new Lehrveranstaltung("BWL 1",5));
    module.addModul(einModul);
    ausgeben(einModul);  
//    ausgabe_Module();
  }
  /**
   * Methode, um Module auszugeben
   * @param cp 
   */
  public static void ausgeben(CreditPointI cp)
  {
    System.out.println("Modul \""+cp.getName()+"\" ("+cp.getCPs()+" CPs)");
  }
//  public static void ausgabe_Module()
//  {
//    while(  module.hasNext())
//    {
//      Modul einModul = module.next();
//      System.out.println("Modul \""+einModul.getName()+"\" ("+einModul.getCPs()+" CPs)");
//    }
//  }
}
