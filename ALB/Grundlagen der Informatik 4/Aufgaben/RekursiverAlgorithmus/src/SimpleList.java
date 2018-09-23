/**
 * Created by Alice on 31.07.2016.
 */
public interface SimpleList<E>
    extends SimpleCollection<E> {
  public void add(int index, E element);

  public E remove(int index);

  public E get(int index);

  public int indexOf(E element);
}
