/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3lUebungen;

import java.util.Scanner;

/**
 *
 * @author ALB
 */
public class auswahlanweisungen {
  public static void main(String args[])
  {
    int zahlAus,zahlEin;
    Scanner sc = new Scanner(System.in);
    zahlEin = sc.nextInt();
    assert(zahlEin > 0):"asdf";
    try
    {
      zahlAus = 100 / zahlEin;
      
    }
    catch (Exception e)
    {
      System.out.println("Keine 0 eingeben");
    }
  }
}
