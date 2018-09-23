import java.util.Date;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by Alice on 18.09.2016.
 */
public class Aufgabenverwaltung implements SimpleQueue<Aufgabe> {
  private PriorityQueue<Aufgabe> aufgaben = new PriorityQueue<Aufgabe>(new AufgabenComparator());

  public static void main(String[] args) {
    new Aufgabenverwaltung();
  }

  public Aufgabenverwaltung() {
    offer(new Aufgabe("test1", 1000, new Date(2007, 10, 10)));
    offer(new Aufgabe("test2", 1000, new Date(2008, 10, 10)));
    offer(new Aufgabe("test3", 3000, new Date(2008, 10, 10)));
    offer(new Aufgabe("test4", 4000, new Date(2008, 10, 10)));
    offer(new Aufgabe("test5", 2000, new Date(2007, 6, 10)));

    delete(new Aufgabe("test3", 3000, new Date(2008, 10, 10)));

    while (!aufgaben.isEmpty()) {
      Aufgabe aufgabe = poll();
      System.out.println(aufgabe);
    }
  }

  @Override
  public Aufgabe element() {
    return aufgaben.element();
  }

  @Override
  public Aufgabe poll() {
    return aufgaben.poll();
  }

  @Override
  public boolean offer(Aufgabe element) {
    return aufgaben.offer(element);
  }

  @Override
  public Aufgabe delete(Aufgabe aufgabe) {
    Iterator<Aufgabe> aufgabeIterator = aufgaben.iterator();
    while (aufgabeIterator.hasNext()) {
      Aufgabe a = aufgabeIterator.next();
      if (a.gewicht == aufgabe.gewicht && a.frist.compareTo(aufgabe.frist) == 0 && a.titel.equals(aufgabe.titel)) {
        aufgabeIterator.remove();
      }
    }
    return null;
  }
}
