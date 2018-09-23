import java.util.Comparator;

/**
 * Created by Alice on 31.07.2016.
 */
public class Sortieralgorithmus {
  public static void main(String[] args) {
    Integer[] data = {9, 8, 2, 3, 4, 8, 3, 0};
    Comparable[] data2 = data.clone();
    xxxSort(data2, data, 0, data.length, new IntComp());
    for (Comparable e : data)
      System.out.print(e + " ");
  }

  private static void xxxSort(Object[] src, Object[] dest, int low, int high, Comparator c) {
    int length = high - low;
    if (length < 7) {
      //Bubble Sort
      for (int i = low; i < high; i++)
        for (int j = i; j > low &&
            c.compare(dest[j - 1], dest[j]) > 0; j--)
          swap(dest, j, j - 1);
      return;
    }
    //aufteilen in 2 teile (Merge Sort)
    int mid = (low + high) >>> 1;
    xxxSort(dest, src, low, mid, c);
    xxxSort(dest, src, mid, high, c);
    if (c.compare(src[mid - 1], src[mid]) <= 0) {
      System.arraycopy(src, low, dest, low, length);
      return;
    }
    for (int i = low, p = low, q = mid; i < high; i++) {
      if (q >= high || p < mid &&
          c.compare(src[p], src[q]) <= 0)
        dest[i] = src[p++];
      else
        dest[i] = src[q++];
    }
  }

  public static void swap(Object[] data, int from, int to) {
    Object temp = data[from];
    data[from] = data[to];
    data[to] = temp;
  }
}

class IntComp implements Comparator<Integer> {

  @Override
  public int compare(Integer o1, Integer o2) {
    return Integer.compare(o1, o2);
  }
}
