import java.util.Comparator;
import java.util.Date;

/**
 * Created by Alice on 18.09.2016.
 */
public class Aufgabe {
  String titel;
  int gewicht;
  Date frist;

  public Aufgabe(String titel, int gewicht, Date frist) {
    this.titel = titel;
    this.gewicht = gewicht;
    this.frist = frist;
  }

  @Override
  public String toString() {
    return "Aufgabe{" +
        "titel='" + titel + '\'' +
        ", gewicht=" + gewicht +
        ", frist=" + frist +
        '}';
  }
}
