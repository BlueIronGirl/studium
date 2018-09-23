/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personenverwaltungid;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALB
 */
public class PersonContainer {

  private static PersonContainer container = null;
  private static ArrayList<Person> personen = new ArrayList<Person>();

  /**
   * Konstruktor: Singleton-Muster
   */
  private PersonContainer() {

  }

  /**
   * Methode zum Erstellen genau eines Containers
   * @return 
   */
  public static PersonContainer erzeugePersonContainer() {
    if (container == null) {
      container = new PersonContainer();
    }
    return container;
  }

  /**
   * Person hinzufuegen
   * @param person 
   */
  public static void addPerson(Person person) {
    personen.add(person);
  }

  /**
   * alle Personen zurueckgeben
   * @return 
   */
  public static List getListe() {
    addPerson(new Person("Meier", 40));
    addPerson(new Person("Müller", 55));
    addPerson(new Person("Beyer", 37));
    addPerson(new Person("Albrecht", 42));
    addPerson(new Person("Hansen", 56));
    addPerson(new Person("Alice Bedow", 19));
    return personen;
  }

}
