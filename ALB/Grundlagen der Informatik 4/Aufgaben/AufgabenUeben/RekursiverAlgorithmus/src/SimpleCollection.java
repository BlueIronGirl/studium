/**
 * Created by Alice on 31.07.2016.
 */
public interface SimpleCollection<E> {
  public boolean add(E element);

  public boolean remove(E element);

  public boolean isEmpty();

  public int size();

  public boolean contains(E element);

  public SimpleIterator<E> iterator();
}
