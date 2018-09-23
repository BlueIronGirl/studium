import java.util.Enumeration;
import java.util.Observable;
import java.util.Vector;

/**
 * Created by Alice on 25.06.2016.
 */
public class Container { //extends Observable {
  private Vector<Object> komponenten = new Vector<>();
  private static Container container = null;

  private Container() {
    komponenten.addElement(new Rezept("Alice Bedow", "Zubereitung Rezept", 10, "leicht"));
    komponenten.addElement(new Rezept("Zweites Rezept", "Zubereitung Rezept 2", 30, "mittel"));
    komponenten.addElement(new Rezept("Drittes Rezept", "Zubereitung Rezept 3", 60, "schwer"));
  }

  public static Container getContainer() {
    if(container == null)  {
      container = new Container();
    }
    return container;
  }

  public int getAnzahl() {
    return komponenten.size();
  }

  public void add(Object elem) {
    if (!komponenten.contains(elem)) {
      komponenten.addElement(elem);
//      setChanged();
    }
//    notifyObservers();
  }

  public Enumeration elements() {
    return komponenten.elements();
  }

  public Object get(int zeile) {
    try {
      return komponenten.elementAt(zeile);
    } catch (Exception e) {
      return null;
    }
  }
}
