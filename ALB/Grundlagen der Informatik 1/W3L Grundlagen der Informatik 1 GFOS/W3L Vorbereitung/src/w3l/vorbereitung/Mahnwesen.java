/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3l.vorbereitung;
import inout.Console;
/**
 *
 * @author ALB
 */
public class Mahnwesen {
  public static void main(String args[])
  {
    String mahntext1 = "Mahntext 1";
    String mahntext2 = "Mahntext 2";
    String mahntext3 = "Mahntext 3";
    char auswahl;
    System.out.println("Auswahl von Mahntexten");
    linie_ausgeben();
    for(int i=1;i<=3;i++)
      System.out.println("Mahntext: "+i);
    System.out.println("Bitte wählen Sie:");
    linie_ausgeben();
    auswahl = Console.readChar();
    switch(auswahl)
    {
      case '1': System.out.println(mahntext1);break;
      case '2': System.out.println(mahntext2);break;
      case '3': System.out.println(mahntext3);break;
      default: System.out.println("Dies steht nicht zur Auswahl.");
    }
  }
  private static void linie_ausgeben()
  {
    System.out.println("________________________");
  }
}
