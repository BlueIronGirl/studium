import java.util.ArrayList;

/**
 * Created by Alice on 18.09.2016.
 */
public class BubbleSort {
  private Sammelkarte[] karten = new Sammelkarte[7];
  private ArrayList<Sammelkarte> doppel = new ArrayList<Sammelkarte>(karten.length);
  private ArrayList<Sammelkarte> einzel = new ArrayList<Sammelkarte>(karten.length);

  public static void main(String[] args) {
    new BubbleSort();
  }

  public BubbleSort() {
    einlesen();
    sortieren();
    ausgeben();
    pruefe();
    ausgebenPruefe();
  }

  private void einlesen() {
    karten[0] = new Sammelkarte("0025", "Rudi Völler");
    karten[6] = new Sammelkarte("1265", "David Beckham");
    karten[1] = new Sammelkarte("1265", "David Beckham");
    karten[2] = new Sammelkarte("0456", "Wayne Rooney");
    karten[3] = new Sammelkarte("0765", "Franz Beckenbauer");
    karten[4] = new Sammelkarte("0456", "Wayne Rooney");
    karten[5] = new Sammelkarte("0257", "Helmut Rahn");
//    karten[6] = new Sammelkarte("0025", "Rudi Völler");
  }

  private void sortieren() { //bubblesort
    for (int i = 0; i < karten.length; i++) {
      for (int j = 0; j < karten.length-1; j++) {
        if (karten[j].key.compareTo(karten[j+1].key) > 1)
          swap(j,j+1);
      }
    }
  }

  private void swap(int i, int j) {
    Sammelkarte temp = karten[i];
    karten[i] = karten[j];
    karten[j] = temp;
  }

  private void pruefe() {
    Sammelkarte dopp = karten[0];
    Sammelkarte einz = karten[0];
    int tempDex = 0;
    for (int i = 0; i < karten.length; i++) {
      einz = karten[i];
      if (i != karten.length - 1 && einz.key.compareTo(karten[i + 1].key) == 0) {
        dopp = karten[i];
        doppel.add(dopp);
        while (i < karten.length - 1 && karten[i].key.compareTo(karten[i + 1].key) == 0) {
          i++;
        }
      } else {
        einzel.add(einz);
      }
    }
  }

  private void ausgeben() {
    for (Sammelkarte k : karten) {
      System.out.println(k);
    }
  }

  private void ausgebenPruefe() {
    System.out.println("----------");
    System.out.println("Einzelne Karten:");
    for (Sammelkarte einzel : this.einzel) {
      System.out.println(einzel);
    }
    System.out.println("----------");
    System.out.println("Doppelte Karten:");
    for (Sammelkarte doppel : this.doppel) {
      System.out.println(doppel);
    }
  }
}
