/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3lUebungen;
import inout.Console;
/**
 *
 * @author ALB
 */
public class GeschachtelteKontrollstrukturen {
  public static void main(String args[])
  {
    char[] name = Console.readCharArray();
    zahlen();
    nameVertikal(name);
    nameHorizontal(name);
  }
  public static void zahlen()
  {
    System.out.println("Zahlen");
    for(int i=1;i<10;i++)
    {
      for(int j=0;j<i;j++)
        System.out.print(i+" ");
      System.out.println();
    }
  }
  public static void nameVertikal(char[] name)
  {
    System.out.println("Name vertikal");
    for(int i=0;i<name.length;i++)
    {
      for(int j=0;j<=i;j++)
        System.out.print(name[i]+" ");
      System.out.println();
    }
  }
  public static void nameHorizontal(char[] name)
  {
    System.out.println("Name horizontal");
    for(int i=0;i<name.length;i++)
    {
      for(int j=0;j<=i;j++)
        System.out.print(name[j]+" ");
      System.out.println();
    }
  }
}

