/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personenverwaltungid;

import java.util.List;

/**
 *
 * @author ALB
 */
public class MainUI_b {

//  private static PersonContainer container = PersonContainer.erzeugePersonContainer();
  private static List<Person> liste = PersonContainer.getListe();

  public static void main(String args[]) {
    System.out.println("Alle Personen, die auf >>er<< enden");
    filtern1();
    System.out.println("Alle Personen, die mit <<M>> beginnen");
    filtern2();
    System.out.println("Nach Name aufsteigend sortierte Liste");
    sortieren1();
    System.out.println("Nach Alter aufsteigend sortierte Liste");
    sortieren2();
    System.out.println("Die aelteste Person:");
    reduzieren1();
    System.out.println("Die Person mit dem laengsten Name:");
    reduzieren2();
  }

  /**
   * Name endet auf er
   */
  public static void filtern1() {
    
    for (Person i : liste) {
      String name = i.getName();
      if (name.charAt(name.length() - 1) == 'r' && name.charAt(name.length() - 2) == 'e') {
        System.out.println(i);
      }
    }
  }

  /**
   * Name beginnt mit M
   */
  public static void filtern2() {
    
    for (Person i : liste) {
      String name = i.getName();
      if (name.charAt(0) == 'M') {
        System.out.println(i);
      }
    }
  }

  /**
   * Nach Name aufsteigend (Selection Sort)
   */
  public static void sortieren1() {
    
    for (int i = 0; i < liste.size(); i++) {
      int posMin = i;
      String min = liste.get(i).getName();
      for (int j = i + 1; j < liste.size(); j++) {
        if (liste.get(j).getName().compareTo(min) < 0) //aufsteigend
        {
          min = liste.get(j).getName();
          posMin = j;
        }
      }
      //tauschen
      String temp = liste.get(posMin).getName();
      liste.get(posMin).setName(liste.get(i).getName());
      liste.get(i).setName(temp);
    }
    //Ausgabe sortiert
    for (Person i : liste) {
      System.out.println(i);
    }
  }

  /**
   * Nach Alter aufsteigend
   */
  public static void sortieren2() {
   
    for (int i = 1; i < liste.size(); i++) {
      Person min = liste.get(i);
      int j = 0;
      for (j = i - 1; j >= 0 && min.getAlter() < liste.get(j).getAlter(); j--) {
        if (liste.get(j).getAlter() > min.getAlter()) {
          //schieben
          liste.set(j + 1, liste.get(j));
        }
      }
      liste.set(j + 1, min);

    }
    //Ausgabe sortiert
    for (Person i : liste) {
      System.out.println(i);
    }
  }
  /**
   * Aelteste Person
   */
  public static void reduzieren1() {
    Person max = null;
    for (Person i : liste) {
      if (max == null || i.getAlter() > max.getAlter()) {
        max = i;
      }
    }
    System.out.println(max);
  }
  /**
   * Person mit laengstem Namen
   */
  public static void reduzieren2() {
    Person max = null;
    for (Person i : liste) {
      if (max == null || i.getName().length() > max.getName().length()) {
        max = i;
      }
    }
    System.out.println(max);
  }
}
