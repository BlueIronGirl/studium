/**
 * Created by alice_b on 07.09.2016.
 */
public class MergeSort {
  private static int anzFelder = 0;

  public static void main(String[] args) {
    Comparable[] zuSortieren = {
        5, 2, 1, 3, 4, 7, 8, 6
    };

    System.out.println("Unsortiert:");
    ausgabe(zuSortieren);

    mergesort(zuSortieren);

    System.out.println("Sortiert MergeSort:");
    ausgabe(zuSortieren);

    System.out.println("Anzahl Felder: " + anzFelder);
  }

  public static void ausgabe(Comparable[] data) {
    for (Comparable zahl : data)
      System.out.print(zahl + " ");
    System.out.println();
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

  public static void mergesort(Comparable[] data) {
    if (data.length > 1) {
      int halfSize = data.length / 2;
      Comparable[] left = new Comparable[halfSize];
      Comparable[] right = new Comparable[data.length - halfSize];
      System.arraycopy(data, 0, left, 0, halfSize);
      System.arraycopy(data, halfSize, right, 0,
          data.length - halfSize);
      anzFelder = anzFelder + 2;
      mergesort(left);
      mergesort(right);
      merge(data, left, right);
    }
  }
}
