/**
 * Created by Alice on 31.07.2016.
 */
public class MergeSort {
  private static int n = 0;

  public static void main(String[] args) {
    Integer[] data = new Integer[]{9,8,5,4,2,3,5,7};
//    Integer[] data = new Integer[]{9, 8, 5, 4};
    for (Integer i : data) {
      System.out.print(i + " ");
    }
    System.out.println();
    mergesort(data);
    for (Integer i : data) {
      System.out.print(i + " ");
    }
    System.out.println();
    System.out.println("N: " + n);
  }

  public static int mergesort(Comparable[] data) {
    if (data.length > 1) {
      int halfSize = data.length / 2;
      Comparable[] left = new Comparable[halfSize];
      Comparable[] right = new Comparable[data.length - halfSize];
      n = n + 2;
      System.arraycopy(data, 0, left, 0, halfSize);
      System.arraycopy(data, halfSize, right, 0,
          data.length - halfSize);
      mergesort(left);
      mergesort(right);
      merge(data, left, right);
    }
    return n;
  }

  public static void merge(Comparable[] out, Comparable[] left,
                           Comparable[] right) {
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < left.length && j < right.length) {
      if (left[i].compareTo(right[j]) < 0)
        out[k++] = left[i++];
      else
        out[k++] = right[j++];
    }
    while (i < left.length)
      out[k++] = left[i++];
    while (j < right.length)
      out[k++] = right[j++];
  }
}
