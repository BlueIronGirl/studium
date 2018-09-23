/**
 * Created by alice_b on 29.07.2016.
 */
public class QuickSortBesser {
  public static void main(String[] args) {
    Comparable[] zuSortieren = {
        5, 2, 9, 1, 3, 4, 7, 8, 6
    };
    System.out.println("Unsortiert:");
    ausgabe(zuSortieren);

    quicksort(zuSortieren, 0, zuSortieren.length - 1, "Zufall");

    System.out.println("Sortiert Zufall:");
    ausgabe(zuSortieren);

    Comparable[] zuSortieren2 = {5, 2, 9, 1, 3, 4, 7, 8, 6};
    System.out.println("Unsortiert:");
    ausgabe(zuSortieren2);

    quicksort(zuSortieren2, 0, zuSortieren2.length - 1, "Median");

    System.out.println("Sortiert Median:");
    ausgabe(zuSortieren2);


    Comparable[] zuSortieren3 = {
        5, 3, 4, 1, 0, 7, 2, 8, 13, 9, 15, 12, 1, 14, 10
    };

  }

  public static void ausgabe(Comparable[] data) {
    for (Comparable zahl : data)
      System.out.print(zahl + " ");
    System.out.println();
  }

  public static int getPivotIndex(Comparable[] data, int from, int to, String art) {
    int partition = 0;
    if (art.equals("Zufall")) {
      return getPivotZufall(data, from, to);
    } else if (art.equals("Median")) {
      return getPivotMedian(data, from, to);
    }
    return partition;
  }

  public static int getPivotZufall(Comparable[] data, int from, int to) {
    return zufall(from, to);
  }

  public static int zufall(int from, int to) {
    int zufall = (int) ((Math.random()) * (to - from + 1) + from);
    return zufall;
  }

  public static int getPivotMedian(Comparable[] data, int from, int to) {
    int median = to;
    int center = (to - from) / 2;
    if ((data[from].compareTo(data[center]) >= 0 && data[from].compareTo(data[to]) <= 0)
        || (data[from].compareTo(data[to]) >= 0 && data[from].compareTo(data[center]) <= 0)) {
      median = from;
    } else if ((data[center].compareTo(data[from]) >= 0 && data[center].compareTo(data[to]) <= 0)
        || data[center].compareTo(data[from]) <= 0 && data[center].compareTo(data[to]) >= 0) {
      median = center;
    }
    return median;
  }

  public static void quicksort(Comparable[] data,
                               int from, int to, String art) {
    if (from < to) {
      int pivotIndex = partition(data, from, to, art);
      quicksort(data, from, pivotIndex - 1, art);
      quicksort(data, pivotIndex + 1, to, art);
    }
  }

  public static int partition(Comparable[] data, int from, int to, String art) {
    int pivotIndex = getPivotIndex(data, from, to, art); //Geandert vorher: pivotIndex = to
    Comparable pivot = data[pivotIndex];
    int left = from;
    int right = to;
    do {
      while (right > from && data[right].compareTo(pivot) >= 0) //bis <
        right--;
      while (data[left].compareTo(pivot) < 0) //bis >=
        left++;
      if (left < right)
        swap(data, left, right);
    }
    while (left < right);
    swap(data, pivotIndex, left); //Geaendert
    return left;
  }


  public static void swap(Comparable[] data, int from, int to) {
    Comparable temp = data[from];
    data[from] = data[to];
    data[to] = temp;
  }
}
