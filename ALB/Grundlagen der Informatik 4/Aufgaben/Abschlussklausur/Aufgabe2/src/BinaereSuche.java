/**
 * Created by Alice on 18.09.2016.
 */
public class BinaereSuche  {
  public static void main(String argv[]) {
    Kunde[] data = {
        new Kunde("Müller", "Berlin"),
        new Kunde("Meyer", "Hamburg"),
        new Kunde("Schmidt", "München"),
        new Kunde("Mustermann", "Frankfurt")};
    System.out.println(getIndexOf(data, "München"));
    System.out.println(getIndexOf(data, "Frankfurt"));
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
