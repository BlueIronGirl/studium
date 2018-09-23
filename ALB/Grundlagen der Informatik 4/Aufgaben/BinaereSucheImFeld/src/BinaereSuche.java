/**
 * Created by alice_b on 29.07.2016.
 */
public class BinaereSuche {

  public static void main(String[] args) {
    Kunde[] kunden = new Kunde[3];
    kunden[0] = new Kunde(1, "Kunde1");
    kunden[1] = new Kunde(11, "Kunde11");
    kunden[2] = new Kunde(111, "Kunde111");
    int gefunden = getIndexOf(kunden, 11);
    if (gefunden != -1) {
      System.out.println(kunden[gefunden].key);
    }
  }

  public static int getIndexOf(ElementType<? extends Comparable>[] data, Comparable search_key) {
    int first = 0;
    int last = data.length - 1;
    while (first <= last) {
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
