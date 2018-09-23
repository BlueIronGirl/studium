/**
 * Created by alice_b on 29.07.2016.
 */
public class BinareSucheSteps {
  private static int steps;

  public static void main(String[] args) {
    Kunde[] kunden = new Kunde[19];
    for (int i = 0; i < kunden.length; i++) {
      kunden[i] = new Kunde(i, "Kunde" + i);
    }
    for (int i = 0; i < kunden.length; i++) {
      int gefunden = getIndexOf(kunden, i);
      System.out.print(kunden[gefunden].key + "\t");
      System.out.print("Steps: " + steps);
      System.out.println();
    }
  }

  public static int getIndexOf(ElementType<? extends Comparable>[] data, Comparable search_key) {
    int first = 0;
    int last = data.length - 1;
    steps = 0;
    while (first <= last) {
      steps++;
      int mid = (first + last) / 2;
      int cmp = data[mid].key.compareTo(search_key);
      if (cmp == 0) {
        return mid;
      }
      if (cmp > 0)
        last = mid - 1;
      else
        first = mid + 1;
    }
    return -1;
  }
}
