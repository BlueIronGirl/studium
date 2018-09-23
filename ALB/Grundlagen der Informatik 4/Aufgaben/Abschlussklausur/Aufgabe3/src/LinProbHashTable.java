/**
 * Created by Alice on 18.09.2016.
 */
public class LinProbHashTable<KeyType extends Comparable> {
  ElementType<? extends Comparable>[] ht;
  int n;

  LinProbHashTable(int n) {
    this.n = n;
    ht = new ElementType[n];
  }

  public void add(ElementType<? extends Comparable> elem) {
    int hc = elem.key.hashCode() % this.n;
    if (hc < 0) hc = -hc;
    int i = 0;
    int pos = hc;
    while ((ht[pos] != null) && (i < n)) {
      i++;
      pos = (hc + i) % n;
    }
    if (i == n)
      throw new RuntimeException("HashTable full.");
    ht[pos] = elem;
  }

  public ElementType<? extends Comparable> get(KeyType key) {
    int hc = key.hashCode() % this.n;
    if (hc < 0) hc = -hc;
    int i = 0;
    int pos = hc;
    while ((ht[pos] != null) && (i < n)) {
      if (ht[pos].key.compareTo(key) == 0)
        return ht[pos];
      i++;
      pos = (hc + i) % n;
    }
    return null;
  }

  public int size() {
    int size = 0;
    for (ElementType node : ht) {
      if (node != null) {
        size++;
      }
    }
    return size;
  }
}