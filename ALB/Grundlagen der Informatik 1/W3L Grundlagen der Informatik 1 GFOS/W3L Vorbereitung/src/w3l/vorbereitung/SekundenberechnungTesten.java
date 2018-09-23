/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3l.vorbereitung;
import inout.FileIO;
import inout.Console;
/**
 *
 * @author ALB
 */
public class SekundenberechnungTesten {
  public static void main(String args[])
  {
    FileIO.openInFile("bed1ein.txt");
    FileIO.openOutFile("bed2aus.txt");
    for(int i=0;i<10;i++)
    {
      umrechnen(FileIO.readIntFromFile());
      System.out.println("Testnr: "+i);
      FileIO.out("Testnr: "+i);
    }
    FileIO.closeInFile();
    FileIO.closeOutFile();
  }
  private static void umrechnen(int wert)
  {
    System.out.println(wert);
    FileIO.out(wert);
    int stunden = wert / 3600;
    wert -= stunden * 3600;
    int minuten = wert / 60;
    wert -= minuten * 60;
    int sekunden = wert;
    FileIO.out(stunden);
    FileIO.out(minuten);
    FileIO.out(sekunden);
    System.out.println(stunden);
    System.out.println(minuten);
    System.out.println(sekunden);
    
  }
}
