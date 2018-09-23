import java.util.Comparator;
import java.util.Date;

/**
 * Created by Alice on 18.09.2016.
 */
public class AufgabenComparator implements Comparator<Aufgabe> {
  @Override
  public int compare(Aufgabe o1, Aufgabe o2) {
    Date now = new Date();

    //je hoeher das Gewicht
    int gewicht = Integer.compare(o2.gewicht, o1.gewicht);
    if (gewicht != 0) {
      return gewicht;
    }
    //Zeitdifferenz kleiner
    int zeitDiff = Long.compare(now.getTime() - o1.frist.getTime(),
        now.getTime() - o2.frist.getTime());
    return zeitDiff;
  }
}
