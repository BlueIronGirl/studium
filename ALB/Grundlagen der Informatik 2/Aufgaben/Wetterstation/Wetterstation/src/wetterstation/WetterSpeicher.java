/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wetterstation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alice Bedow
 * @date 4.12.15
 * 
 */
public class WetterSpeicher {

  private static String DATEIPFAD = "messungen.txt";

  public void leseWerte() {
    try (FileReader eineDatei = new FileReader(DATEIPFAD);
      BufferedReader datei = new BufferedReader(eineDatei);) {
      String s;
      while ((s = datei.readLine()) != null) {
        System.out.println(s);
      }

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void speichereWerte(ArrayList<Messwert> werte) {
    try (PrintWriter datei = new PrintWriter(DATEIPFAD);) {
      for (int i = 0; i < werte.size(); i++) {
        StringBuilder temp = new StringBuilder();
        temp.append(String.valueOf(werte.get(i).getTag()));
        temp.append(".");
        temp.append(String.valueOf(werte.get(i).getMonat()));
        temp.append(".");
        temp.append(String.valueOf(werte.get(i).getJahr()));
        temp.append(", ");
        temp.append(String.valueOf(werte.get(i).getTemperatur()));
        temp.append(";");
        datei.println(temp.toString());
        werte.get(i);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
