/**
 * Created by Alice on 18.09.2016.
 */

public interface SimpleQueue<E>
{
  /**
   * Abfragen des ersten Elements der Schlange
   *
   */
  public E element();

  /**
   * Entnehmen des ersten Elements aus der Schlange
   *
   */
  public E poll();

  /**
   * Anfügen eines Elements an die Schlange
   *
   */
  public boolean offer(E element);

  /**
   * Loeschen eines Elements
   */
  public E delete(E element);
}
