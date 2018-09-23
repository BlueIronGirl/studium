/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personenverwaltungid;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author ALB
 */
public class MainUI_d {

  private static List<Person> liste = PersonContainer.getListe();

  public static void main(String[] args) {
    
    //Filtern: Alle Personen ausgeben, deren Name auf "er" endet
    Predicate<Person> predicate
      = name -> (name.getName().charAt(name.getName().length() - 1) == 'r'
      && name.getName().charAt(name.getName().length() - 2) == 'e');
    filterMethod(predicate)
      .forEach(System.out::println);
    System.out.println("");

    //Filtern: Alle Personen ausgeben, deren Name mit "M" beginnt
    predicate = person -> person.getName().startsWith("M");
    for (Person p : filterMethod(predicate)) {
      System.out.println(p);
    }
    System.out.println("");

    //Sortieren: Nach Name aufsteigend
    Comparator<Person> comparator = (p1, p2) -> p1.namenDifferenz(p2);
    for (Person p : sortedMethod(comparator)) {
      System.out.println(p);
    }
    System.out.println("");

    //Sortieren: Nach Alter aufsteigend
    comparator = (p1, p2) -> p1.alterDifferenz(p2);
    for (Person p : sortedMethod(comparator)) {
      System.out.println(p);
    }
    System.out.println("");

    //Reduzieren: Älteste Person
    BinaryOperator<Person> op = (p1, p2) -> p1.getAlter() >= p2.getAlter() ? p1 : p2;
    System.out.println(reduceMethod(op));
    System.out.println("");

    //Reduzieren: Person mit längstem Namen
    op = (p1, p2) -> p1.getName().length() >= p2.getName().length() ? p1 : p2;
    System.out.println(reduceMethod(op));
    System.out.println("");
  }

  private static List<Person> filterMethod(Predicate<Person> predicate) {
    return 
      liste
        .stream()
        .filter(predicate)
        .collect(toList());
  }

  private static List<Person> sortedMethod(Comparator<Person> comparator) {
    return 
      liste
        .stream()
        .sorted(comparator)
        .collect(toList());
  }

  private static Person reduceMethod(BinaryOperator<Person> op) {
    return liste
      .stream()
      .reduce(op)
      .get();
  }
}
