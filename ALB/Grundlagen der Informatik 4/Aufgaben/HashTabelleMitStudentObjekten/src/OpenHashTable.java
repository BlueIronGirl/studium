/**
 * Created by alice_b on 29.07.2016.
 */
public class OpenHashTable<KeyType extends Comparable> {
  HashListNode[] ht;   // eigentliche Hashtabelle
  int n;               // Größe der Hashtabelle

  class HashListNode<KeyType extends Comparable> {
    HashListNode next;
    ElementType<KeyType> elem;

    public HashListNode(ElementType<KeyType> elem, HashListNode next) {
      this.next = next;
      this.elem = elem;
    }
  }

  OpenHashTable(int n)  // Konstruktor
  {
    this.n = n;
    ht = new HashListNode[n];
  }

  public void add(ElementType<? extends Comparable> elem) {
    int hc = elem.key.hashCode() % this.n;
    if (hc < 0) hc = -hc;
    if (this.ht[hc] == null) // Position ist noch leer
    {
      this.ht[hc] = new HashListNode(elem, null);
    } else // am Ende der Liste anhängen
    {
      HashListNode node = this.ht[hc];
      while (node.next != null)
        node = node.next;
      node.next = new HashListNode(elem, null);
    }
  }

  public ElementType<? extends Comparable> get(KeyType key) {
    int hc = key.hashCode() % this.n;
    if (hc < 0) hc = -hc;
    HashListNode node = this.ht[hc];
    while (node != null) {
      if (node.elem.key.compareTo(key) == 0)
        return node.elem;
      node = node.next;
    }
    return null;
  }
}
