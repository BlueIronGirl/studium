/**
 * Created by Alice on 21.08.2016.
 */

/**
 * Implementierung der Klasse SimpleLinkedStack<E> durch eine
 * einfach verkettete Liste (SimpleLinkedList).
 *
 * Die gespeicherten Objektreferenzen dürfen im Gegensatz zur
 * Klasse java.util.Stack in der Java-Klassenbibliothek nicht
 * gleich null sein.
 */
public class SimpleLinkedStack<E> {
  private SimpleList<E> list;

  /**
   * Erzeugung eines leeren Stapels (Konstruktor)
   */
  public SimpleLinkedStack() {
    this.list = new SimpleLinkedList<E>();
  }

  /**
   * Test, ob ein Stapel leer ist.
   */
  public boolean empty() {
    return list.isEmpty();
  }

  /**
   * Zurückliefern des obersten Elements vom Stapel
   */
  public E peek() {
    if (!this.empty())
      return list.get(0);
    else
      return null;
  }

  /**
   * Zurückliefern und Löschen des obersten Elements
   * vom Stapel
   */
  public E pop() {
    if (!this.empty())
      return list.remove(0);
    else
      return null;
  }

  /**
   * Ablegen eines neuen Elements auf dem Stapel
   */
  public E push(E element) {
    list.add(0, element);
    return element;
  }

  /**
   * Suchen nach einem Element im Stapel und
   * Rückgabe dessen Tiefe im Stapel oder -1,
   * falls kein solches Element enthalten.
   */
  public int search(E element) {
    return list.indexOf(element);
  }
}
