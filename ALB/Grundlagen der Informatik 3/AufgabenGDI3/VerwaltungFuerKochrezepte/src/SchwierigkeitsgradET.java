import java.util.Vector;

/**
 * Created by Alice on 25.06.2016.
 */
public class SchwierigkeitsgradET {
  public static Vector<String> schwierigkeitsgrade = new Vector<>();

  public SchwierigkeitsgradET() {
    schwierigkeitsgrade.addElement("leicht");
    schwierigkeitsgrade.addElement("mittel");
    schwierigkeitsgrade.addElement("schwer");
  }

  public static Vector<String> getSchwierigkeitsgrade() {
    return schwierigkeitsgrade;
  }
}
