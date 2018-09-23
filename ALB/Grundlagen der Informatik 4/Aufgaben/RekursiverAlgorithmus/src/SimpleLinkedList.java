/**
 * Created by Alice on 21.08.2016.
 */

public class SimpleLinkedList<E> implements SimpleList<E> {
  /**
   * Innere Klasse für die Elemente der verketteten Liste
   */
  class Entry {
    // Attribute
    E data;      // Verweis auf gespeichertes Objekt
    Entry next;  // Verweis auf Nachfolger in der Liste

    // Konstruktor
    Entry(E o, Entry next) {
      this.data = o;
      this.next = next;
    }
  }

  /**
   * Innere Klasse für einen Iterator zum sequenziellen Durchlauf
   * durch die Liste
   */
  class SimpleLinkedListIterator implements SimpleIterator<E> {
    /**
     * aktuelle Position des Iterators
     */
    Entry e;

    /**
     * Konstruktor mit Anfangsposition vor dem 1. Element
     */
    SimpleLinkedListIterator() {
      e = head;
    }

    /**
     * Überprüfung, ob der Iterator noch ein weiteres Element
     * besuchen kann
     *
     * @see SimpleIterator#hasNext()
     */
    public boolean hasNext() {
      return e.next != null;
    }

    /**
     * Schritt zum nächsten Element und Rückgabe des aktuellen
     * Elements als Ergebnis
     *
     * @see SimpleIterator#next()
     */
    public E next() {
      e = e.next;
      if (e != null)
        return e.next.data;
      else
        return null;
    }

    /**
     * Das zuletzt mit next() gelieferte Element wird gelöscht
     *
     * @see SimpleIterator#remove()
     */
    public E remove() {
      Entry prev = head;
      while (prev.next != e)
        prev = prev.next;
      removeAfter(prev);
      return prev.data;
    }
  }

  // Attribute (Klasse SimpleLinkedList)
  private Entry head;  // Anfang der einfach verketteten Liste
  private Entry tail;  // Ende der einfach verketteten Liste
  private int size;    // Anzahl der Elemente in der Liste

  // Konstruktor (Klasse SimpleLinkedList)
  public SimpleLinkedList() {
    this.head = new Entry(null, null);
    this.tail = this.head;
    this.size = 0;
  }

  /**
   * Bestimmung des Elements zu einem vorgegebenen Index
   *
   * @param index Position des gesuchten Entry-Objekts
   */
  private Entry entry(int index) {
    Entry e = this.head;
    for (int i = 0; i <= index; i++)
      e = e.next;
    return e;
  }

  /**
   * Überprüfung, ob ein Index im gültigen Bereich liegt
   *
   * @param minIndex die Untergrenze für den Index
   * @param index    der zu überprüfende Index
   * @param maxIndex die Obergrenze für den Index
   */
  private static void rangeCheck(int minIndex, int index,
                                 int maxIndex) {
    if (index < minIndex || index > maxIndex)
      throw new IndexOutOfBoundsException();
  }

  /**
   * Einfügen eines Elements an der Position index
   *
   * @param index Position, an der eingefügt werden soll
   * @param o     einzufügendes Element
   */
  public void add(int index, E o) {
    if (o != null) {
      rangeCheck(0, index, this.size);
      Entry prev = entry(index - 1);
      addAfter(o, prev);
    }
  }

  /**
   * Entfernen des Elements an der Position index
   * und Rückgabe des an dieser Position gespeicherten Objekts
   *
   * @param index Position, an der gelöscht werden soll
   */
  public E remove(int index) {
    rangeCheck(0, index, this.size - 1);
    Entry prev = entry(index - 1);
    return removeAfter(prev);
  }

  /**
   * Rückgabe des an der Position index gespeicherten Objekts
   * als Ergebnis
   *
   * @param index Position, deren Element zurückgeliefert wird
   */
  public E get(int index) {
    rangeCheck(0, index, this.size - 1);
    return entry(index).data;
  }

  /**
   * Bestimmung des Index des ersten Elements der Liste,
   * dessen gespeichertes Objekt zu o inhaltsgleich ist
   * oder -1, falls kein solches Element vorhanden
   *
   * @param o gesuchtes Element
   */
  public int indexOf(E o) {
    int index = -1;
    Entry e = this.head.next;
    while (e != null) {
      index++;
      if (o.equals(e.data))
        return index;
      else
        e = e.next;
    }
    return -1;
  }

  /**
   * Einfügen eines neuen Elements nach dem Element prev
   *
   * @param o    einzufügendes Element
   * @param prev Entry-Objekt, hinter dem einzufügen ist
   */
  private void addAfter(E o, Entry prev) {
    Entry e = new Entry(o, prev.next);
    prev.next = e;
    if (prev == this.tail)
      this.tail = e; // Listenende korrigieren
    this.size++;
  }

  /**
   * Entfernen des Elements nach dem Element prev
   *
   * @param prev Entry-Objekt, hinter dem gelöscht werden soll
   */
  private E removeAfter(Entry prev) {
    Entry e = prev.next;
    E result = e.data;
    prev.next = e.next;
    if (e == this.tail)
      this.tail = prev; // Listenende korrigieren
    this.size--;
    return result;
  }

  @Override
  public boolean add(E o) {
    if (o != null) {
      addAfter(o, tail);
    }
    return true;
  }

  @Override
  public boolean remove(E o) {
    if (o != null) {
      removeAfter(entry(indexOf(tail.data) - 1)); //letzen Eintrag loeschen
    }
    return true;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size - 1; //erstes Elem ist immer leer
  }

  @Override
  public boolean contains(E element) {
    return false;
  }

  /**
   * Erzeugung eines Iterators zum sequentiellen Durchlauf durch
   * die Liste
   *
   * @see SimpleCollection#iterator()
   */
  public SimpleIterator<E> iterator() {
    return new SimpleLinkedListIterator();
  }
}
