/**
 * Created by Alice on 31.07.2016.
 */
public interface SimpleIterator<E> {
  public boolean hasNext();

  public E next();

  public E remove();
}
