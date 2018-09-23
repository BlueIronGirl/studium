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
public class Geheimschriften {
  public static void main(String args[])
  {
    System.out.println("Geben Sie das Schluesselwort ein");
    char schluessel[] = Console.readCharArray();
    verschluesseln(schluessel);
    entschluesseln(schluessel);
  }
  private static void verschluesseln(char schluessel[])
  {
    System.out.println("Klartext?");
    char klartext[] = Console.readCharArray();
    char codetext[] = new char[klartext.length];
    System.out.print("Codetext: ");
    int z=0;
    for(int i=0;i<klartext.length;i++)
    {
      int ausgleich = 26+klartext.length/26;
      codetext[i] = (char)((ausgleich+klartext[i]-'A'+(schluessel[z]-'A'-i))%26+'A');
      System.out.print(codetext[i]);
      z++;
      if(z==schluessel.length)
        z=0;
    }
    System.out.println();   
  }
  private static void entschluesseln(char schluessel[])
  {
    System.out.println("Codetext?");
    char codetext[] = Console.readCharArray();
    char klartext[] = new char[codetext.length];
    System.out.print("Klartext: ");
    int z=0;
    for(int i=0;i<codetext.length;i++)
    {
      int ausgleich = 26+codetext.length/26;
      klartext[i] = (char)((ausgleich+codetext[i]-'A'-(schluessel[z]-'A'-i))%26+'A');
      System.out.print(klartext[i]);
      z++;
      if(z==schluessel.length)
        z=0;
    }
    System.out.println();   
  }
}
