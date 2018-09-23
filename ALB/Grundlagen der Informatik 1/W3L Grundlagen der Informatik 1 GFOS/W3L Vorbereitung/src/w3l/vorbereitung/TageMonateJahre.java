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
public class TageMonateJahre {
  public static void main(String args[])
  {
    int wert,tage,monate,jahre;
    System.out.println("Geben Sie den Tageswert ein: ");
    wert = Console.readInt();
    System.out.println("Eingegebener Wert: "+wert);
    jahre = wert / 365;
    wert %= 365;
    monate = wert / 30;
    wert %= 30;
    tage = wert;
    System.out.println("Jahre: "+jahre);
    System.out.println("Monate: "+monate);
    System.out.println("Tage: "+tage);
  }
}
