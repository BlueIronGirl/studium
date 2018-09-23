import java.util.ArrayList;

/**
 * Created by alice_b on 29.07.2016.
 */
public class BubbleSort {
  private Sammelkarte[] karten = new Sammelkarte[7];
  private ArrayList<Sammelkarte> einzigartig = new ArrayList<Sammelkarte>();
  private ArrayList<Sammelkarte> doppelt = new ArrayList<Sammelkarte>();

  public static void main(String[] args) {
    new BubbleSort();
  }

  public BubbleSort() {
    initKarten();
    checkUnique();
    ausgabe();
  }

  public void initKarten() {
    karten[0] = new Sammelkarte("0025", "Rudi Völler");
    karten[1] = new Sammelkarte("1265", "David Beckham");
    karten[2] = new Sammelkarte("0456", "Wayne Rooney");
    karten[3] = new Sammelkarte("0765", "Franz Beckenbauer");
    karten[4] = new Sammelkarte("0456", "Wayne Rooney");
    karten[5] = new Sammelkarte("0257", "Helmut Rahn");
    karten[6] = new Sammelkarte("0025", "Rudi Völler");
  }

  public void ausgabe() {
    System.out.println("Einzigartige Karten");
    for (Sammelkarte k : einzigartig) {
      System.out.println(k);
    }
    System.out.println("Doppelte Karten");
    for (Sammelkarte k : doppelt) {
      System.out.println(k);
    }
  }


  public void checkUnique() {
    Sammelkarte unique;
    Sammelkarte duplicate;
    for (int i = 0; i < karten.length; i++) {
      unique = karten[i];
      for (int j = 0; j < karten.length; j++) {
        // wenn gleich kein Duplikat und nicht gleiches Element
        if (karten[i].key.compareTo(karten[j].key) == 0 && i != j) {
          unique = null;
          if (i < j) {
            duplicate = karten[i];
            doppelt.add(duplicate);
          }
        }
      }
      if (unique != null) {
        einzigartig.add(unique);
      }
    }
  }

}
