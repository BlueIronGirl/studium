/**
 * Created by Alice on 31.07.2016.
 */
public class SimpleArrayList<E> implements SimpleList<E> {
  public static void main(String[] args) {
    SimpleArrayList<Integer> normal = new SimpleArrayList<Integer>(10);
    SimpleArrayList<Integer> maxSize = new SimpleArrayList<Integer>(10, 10);
    for (int i = 0; i < 11; i++) {
      normal.add(i);
      maxSize.add(i);
    }
  }

  /**
   * Feld zur Speicherung der Nutzdaten (Elemente)
   */
  private E[] data;

  /**
   * aktuelle Anzahl der Elemente in der Liste
   */
  private int size;

  private int maxSize = -1;


  /**
   * Erzeugung einer leeren Liste mit Angabe initialer Größe
   */
  @SuppressWarnings("unchecked")  // wg. Cast auf E[]
  public SimpleArrayList(int initialCapacity, int maxSize) {
    this.data = (E[]) new Object[initialCapacity];
    this.maxSize = maxSize;
    this.size = 0;
  }

  /**
   * Erzeugung einer leeren Liste mit Angabe initialer Größe
   */
  @SuppressWarnings("unchecked")  // wg. Cast auf E[]
  public SimpleArrayList(int initialCapacity) {
    this.data = (E[]) new Object[initialCapacity];
    this.size = 0;
  }

  /**
   * Erzeugung einer leeren Liste
   */
  public SimpleArrayList() {
    this(10);
  }

  /**
   * Sicherstellen, dass das Feld groß genug ist, um mind.
   * minCapacity Objekte speichern zu können.
   * Ist das nicht der Fall, wird ein größeres Feld erzeugt,
   * und die Elemente werden in das neue Feld umkopiert.
   *
   * @param minCapacity sicherzustellende Mindestkapazität
   */
  @SuppressWarnings("unchecked")
  protected void ensureCapacity(int minCapacity) throws Exception {
    if (maxSize > 0 && minCapacity > maxSize) {
      throw new Exception("Die maximale Groesse wurde ueberschritten: " + maxSize);
    }
    int oldCapacity = this.data.length;
    if (minCapacity > oldCapacity) // Speicherplatz zu klein
    {
      E[] oldData = this.data;

      // Hier erfolgt die Erzeugung des neuen Feldes.
      // Für die Größe des neuen Feldes sind verschiedene
      // Strategien denkbar.
      int newCapacity = (2 * oldCapacity <= oldCapacity + 500) ?
          (2 * oldCapacity) : (oldCapacity + 500);
      if (newCapacity < minCapacity)
        newCapacity = minCapacity;

      // Erzeugen des neuen größeren Feldes
      this.data = (E[]) new Object[newCapacity];

      // Kopieren der Daten aus dem alten in das neue Feld
      for (int i = 0; i < oldCapacity; i++)
        this.data[i] = oldData[i];
    }
  }

  /**
   * Elemente ab der Position index um 1 nach rechts schieben.
   * Das Element an der Position index wird überschrieben.
   *
   * @param index die Position, ab der verschoben werden soll
   */
  private void shiftRight(int index) {
    for (int i = this.size; i > index; i--)
      this.data[i] = this.data[i - 1];
  }

  /**
   * Elemente ab der Position index+1 um 1 nach links schieben
   * Das Element an der Position index wird überschrieben.
   *
   * @param index die Position, ab der verschoben werden soll
   */
  private void shiftLeft(int index) {
    for (int i = index; i < this.size - 1; i++)
      this.data[i] = this.data[i + 1];
    this.data[this.size - 1] = null;  // Referenz entfernen
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
   * Sicherstellen, dass das Feld verkleinert wird, wenn sehr
   * viele Elemente wieder entfernt wurden.
   */
  @SuppressWarnings("unchecked")
  protected void shrinkCapacity() {
    int oldCapacity = this.data.length;
    int newCapacity = oldCapacity;

    // Hier erfolgt die Berechnung der neuen Feldgröße.
    // Dafür sind verschiedene Strategien denkbar.
    if (oldCapacity - this.size > 500)
      newCapacity = oldCapacity - 500;
    if (this.size < oldCapacity / 2)
      newCapacity = oldCapacity / 2;

    // Bei Bedarf das Feld "schrumpfen"...
    if (newCapacity < oldCapacity) {
      E[] oldData = this.data;
      // Erzeugen des neuen Feldes
      this.data = (E[]) new Object[newCapacity];

      // Kopieren der Daten aus dem alten in das neue Feld
      for (int i = 0; i < newCapacity; i++)
        this.data[i] = oldData[i];
    }
  }

  /**
   * Einfügen eines Elements am Ende der Liste
   *
   * @see SimpleCollection#add(java.lang.Object)
   */
  public boolean add(E o) {
    try {
      if (o != null) {
        // Ausreichende Größe des Feldes sicherstellen

        ensureCapacity(this.size + 1);

        // Objekt speichern
        this.data[this.size] = o;
        // Anzahl Elemente erhöhen
        this.size++;
        return true;
      } else
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Entfernen des ersten Elements der Liste, das zu o
   * inhaltsgleich ist
   *
   * @see SimpleCollection#remove(java.lang.Object)
   */
  public boolean remove(E o) {
    // Bestimmung des Elements mit dem kleinsten Index, das
    // zu o inhaltsgleich ist
    int index = this.indexOf(o);
    if (index >= 0)  // Element gefunden
    {
      // Elemente ab Position index+1 um 1 nach links schieben
      shiftLeft(index);
      // Anzahl der Elemente reduzieren
      this.size--;
      return true;
    } else
      return false;
  }

  /**
   * Überprüfung, ob die Liste leer ist
   *
   * @see SimpleCollection#isEmpty()
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Bestimmung der Anzahl der Elemente in der Liste
   *
   * @see SimpleCollection#size()
   */
  public int size() {
    return this.size;
  }

  /**
   * Überprüfung, ob ein zu o inhaltsleiches Objekt in der
   * Liste enthalten ist
   *
   * @see SimpleCollection#contains(java.lang.Object)
   */
  public boolean contains(E o) {
    return (this.indexOf(o) >= 0);
  }

  /**
   * Bestimmung des Index des ersten Elements der Liste, das zu o
   * inhaltsgleich ist
   *
   * @see SimpleList#indexOf(java.lang.Object)
   */
  public int indexOf(E o) {
    if (o != null) {
      int i = 0;
      while (i < this.size) {
        if (o.equals(this.data[i]))
          return i;
        i++;
      }
    }
    return -1;
  }

  /**
   * Rückgabe des an der Position index gespeicherten Elements
   *
   * @see SimpleList#get(int)
   */
  public E get(int index) {
    rangeCheck(0, index, this.size - 1);
    return this.data[index];
  }

  /**
   * Einfügen eines Elements an der Position index
   *
   * @see SimpleList#add(int, java.lang.Object)
   */
  public void add(int index, E o) {
    try {
      if (o != null) {
        rangeCheck(0, index, this.size);
        // Ausreichende Größe des Feldes sicherstellen
        ensureCapacity(this.size + 1);


        // Elemente ab Position index um 1 nach rechts schieben
        shiftRight(index);
        // Objekt an der Position index speichern
        this.data[index] = o;
        // Anzahl der Elemente erhöhen
        this.size++;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Entfernen des Elements an der Position index und Rückgabe
   * des entfernten Elements
   *
   * @see SimpleList#remove(int)
   */
  public E remove(int index) {
    rangeCheck(0, index, this.size - 1);
    E result = this.data[index];
    // Elemente ab der Position index+1 um 1 nach links schieben
    shiftLeft(index);
    // Anzahl der Elemente reduzieren
    this.size--;
    return result;
  }

  /**
   * Erzeugung eines Iterators zum sequentiellen Durchlauf durch
   * die Liste
   *
   * @see SimpleCollection#iterator()
   */
  public SimpleIterator<E> iterator() {
    return new SimpleArrayListIterator();
  }

  class SimpleArrayListIterator implements SimpleIterator<E> {
    /**
     * aktuelle Position des Iterators
     */
    int pos;

    /**
     * Konstruktor mit Anfangsposition vor dem 1. Element
     */
    SimpleArrayListIterator() {
      pos = -1;
    }

    /**
     * Überprüfung, ob der Iterator noch ein weiteres Element
     * besuchen kann
     *
     * @see SimpleIterator#hasNext()
     */
    public boolean hasNext() {
      return pos < size - 1;
    }

    /**
     * Schritt zum nächsten Element und Rückgabe des aktuellen
     * Elements als Ergebnis
     *
     * @see SimpleIterator#next()
     */
    public E next() {
      pos++;
      if (pos < size)
        return data[pos];
      else
        return null;
    }

    /**
     * Das zuletzt mit next() gelieferte Element wird gelöscht
     *
     * @see SimpleIterator#remove()
     */
    public E remove() {
      shiftLeft(pos);
      // Anzahl der Elemente reduzieren
      size--;
      return data[pos];
    }
  }
}
