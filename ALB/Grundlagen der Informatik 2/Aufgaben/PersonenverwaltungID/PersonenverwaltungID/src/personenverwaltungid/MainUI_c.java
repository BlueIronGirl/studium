/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personenverwaltungid;

import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author ALB
 */
public class MainUI_c {

  private static List<Person> liste = PersonContainer.getListe();

  public static void main(String[] args) {
    //Filtern: Alle Personen ausgeben, deren Name auf "er" endet
    for (Person p : filtern1()) {
      System.out.println(p);
    }

    System.out.println("");

    //Filtern: Alle Personen ausgeben, deren Name mit "M" beginnt
    for (Person p : filtern2()) {
      System.out.println(p);
    }

    System.out.println("");

    //Sortieren: Nach Name aufsteigend
    for (Person p : sortieren1()) {
      System.out.println(p);
    }
    System.out.println("");

    //Sortieren: Nach Alter aufsteigend
    for (Person p : sortieren2()) {
      System.out.println(p);
    }
    System.out.println("");

    //Reduzieren: Älteste Person
    System.out.println(reduzieren1());
    System.out.println("");

    //Reduzieren: Person mit längstem Namen
    System.out.println(reduzieren2());
    System.out.println("");
  }

  private static List<Person> filtern1() {
    return liste.stream()
      .filter(name -> name.getName().charAt(name.getName().length() - 1) == 'r')
      .filter(name -> name.getName().charAt(name.getName().length() - 2) == 'e')
      .collect(toList());
  }

  private static List<Person> filtern2() {
    return liste.stream()
      .filter(person -> person.getName()
        .startsWith("M")).collect(toList());

  }

  private static List<Person> sortieren1() {
    return liste.stream()
      .sorted((name1, name2) -> name1.namenDifferenz(name2))
      .collect(toList());
  }

  private static List<Person> sortieren2() {
    return liste.stream()
      .sorted((name1, name2) -> name1.alterDifferenz(name2))
      .collect(toList());
  }

  private static Person reduzieren1() {
    return liste.stream()
      .reduce(liste.get(0), (name1, name2) -> name1.getAlter() >= name2.getAlter() ? name1 : name2);
  }

  private static Person reduzieren2() {
    return liste.stream()
      .max((p1, p2) -> Integer.compare(p1.getName().length(), p2.getName().length()))
      .get();
  }
}
