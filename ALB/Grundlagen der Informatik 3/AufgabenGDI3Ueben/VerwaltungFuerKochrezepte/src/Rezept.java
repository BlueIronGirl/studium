import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Alice on 25.06.2016.
 */
public class Rezept {
  private int nr;
  private String name;
  private String zubereitung;
  private int zubereitungszeit;
  private String schwierkeitsgradET;
  private Vector<Zutat> zutaten = new Vector<>();

  private static int anz = 0;

  public void Rezept(String name) {
    this.nr = anz++;
    this.anz = anz++;
  }

  public Rezept(String name, String zubereitung, int zubereitungszeit, String schwierkeitsgradET) {
    this.nr = anz++;
    this.anz = anz++;
    this.name = name;
    this.zubereitung = zubereitung;
    this.zubereitungszeit = zubereitungszeit;
    this.schwierkeitsgradET = schwierkeitsgradET;
  }

  public int getNr() {
    return nr;
  }

  public void setNr(int nr) {
    this.nr = nr;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getZubereitung() {
    return zubereitung;
  }

  public void setZubereitung(String zubereitung) {
    this.zubereitung = zubereitung;
  }

  public int getZubereitungszeit() {
    return zubereitungszeit;
  }

  public void setZubereitungszeit(int zubereitungszeit) {
    this.zubereitungszeit = zubereitungszeit;
  }

  public String getSchwierkeitsgradET() {
    return schwierkeitsgradET;
  }

  public void setSchwierkeitsgradET(String schwierkeitsgradET) {
    this.schwierkeitsgradET = schwierkeitsgradET;
  }

  public void addZutat(Zutat zutat) {
    if (!zutaten.contains(zutat)) {
      System.out.println(zutat.getName());
      zutaten.addElement(zutat);
    }
  }

  public void removeZutat(Zutat zutat) {
    zutaten.removeElement(zutat);
  }

  public String getZutaten() {
    String concat = "";
    for (int i = 0; i < zutaten.size(); i++) {
      concat += zutaten.get(i).getMengeConcat() + " " + zutaten.get(i).getName();
      if (i < zutaten.size() - 1) {
        concat += ",";
      }
    }
    return concat;
  }

  public int getAnzahl() {
    return zutaten.size();
  }

  public void add(Object elem) {
    if (!zutaten.contains(elem)) {
      zutaten.addElement((Zutat) elem);
//      setChanged();
    }
//    notifyObservers();
  }

  public Enumeration elements() {
    return zutaten.elements();
  }

  public Object get(int zeile) {
    try {
      return zutaten.elementAt(zeile);
    } catch (Exception e) {
      return null;
    }
  }
}
