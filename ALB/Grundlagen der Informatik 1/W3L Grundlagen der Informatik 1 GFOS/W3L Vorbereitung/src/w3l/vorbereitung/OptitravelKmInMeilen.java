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
public class OptitravelKmInMeilen {
  public static void main(String args[])
  {
    double meilen, km;
    double faktor = 0.621371;
    System.out.println("Geben Sie die Kilometernanzahl an, die in Meilen umgerechnet werden soll: ");
    km = Console.readDoublePoint();
    meilen = km * faktor;
    System.out.println(km + " Kilometer entsprechen "+meilen+" Meilen");
    System.out.println("Geben Sie die Meilennanzahl an, die in Kilometer umgerechnet werden soll: ");
    meilen = Console.readDoublePoint();
    km = meilen / faktor;
    System.out.println(meilen + " Meilen entsprechen "+km+ " Kilometern");
    
  }
}
