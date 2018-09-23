/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3ltesten;

import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author ALB
 */
public class zuweisung {
  public static void main(String args[])
  {
    int a=0,b=1;
    a = ++b;
    System.out.println(a);
    Locale.setDefault(Locale.GERMAN);
    Scanner sc = new Scanner(System.in);
    //double test = sc.nextDouble();
    //System.out.println(test);
    String x = "asfdöh";
    String y = x + "\n" + x + "\b" + x +"\n\t\r"+x;
    System.out.println(y);
  }
}
