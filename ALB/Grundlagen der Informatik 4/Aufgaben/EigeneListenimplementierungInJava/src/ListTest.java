import java.util.AbstractList;
import java.util.NoSuchElementException;

/**
 * Created by Alice on 31.07.2016.
 */
public class ListTest<E> extends AbstractList<E> {
  private E speicherstelle0 = null;
  private E speicherstelle1 = null;
  private E speicherstelle2 = null;

  public static void main(String[] args) {
    ListTest<Integer> tester = new ListTest<Integer>();
    tester.set(0, 1);
    tester.set(1, 2);
    tester.set(2, 3);
    System.out.println(tester.get(2));
    System.out.println(tester.size());
    tester.remove(2);
    System.out.println(tester.get(2));
    System.out.println(tester.size());
  }

  @Override
  public E get(int index) {
    E elem = null;
    switch (index) {
      case 0:
        elem = speicherstelle0;
        break;
      case 1:
        elem = speicherstelle1;
        break;
      case 2:
        elem = speicherstelle2;
        break;
      default:
        throw new NoSuchElementException("Index ungueltig: " + index);
    }
    return elem;
  }

  @Override
  public int size() {
    int size = 0;
    if (speicherstelle0 != null) {
      size++;
    }
    if (speicherstelle1 != null) {
      size++;
    }
    if (speicherstelle2 != null) {
      size++;
    }
    return size;
  }

  @Override
  public E set(int index, E element) {
    E elem = modify(index, element);
    return elem;
  }

  @Override
  public E remove(int index) {
    E elem = modify(index, null);
    return elem;
  }

  public E modify(int index, E element) {
    E elem = null;
    switch (index) {
      case 0:
        elem = speicherstelle0;
        speicherstelle0 = element;
        break;
      case 1:
        elem = speicherstelle1;
        speicherstelle1 = element;
        break;
      case 2:
        elem = speicherstelle2;
        speicherstelle2 = element;
        break;
      default:
        throw new NoSuchElementException("Index ungueltig: " + index);
    }
    return elem;
  }
}
