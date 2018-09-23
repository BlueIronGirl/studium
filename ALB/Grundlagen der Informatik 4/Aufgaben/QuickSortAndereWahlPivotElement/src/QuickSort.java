/**
 * Created by alice_b on 29.07.2016.
 */
public class QuickSort {
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

    quicksort(zuSortieren2, 0, zuSortieren.length - 1, "Median");

    System.out.println("Sortiert Median:");
    ausgabe(zuSortieren2);


  }

  public static void ausgabe(Comparable[] data) {
    for (Comparable zahl : data)
      System.out.print(zahl + " ");
    System.out.println();
  }


  public static int zufall(int from, int to) {
    int zufall = (int) ((Math.random()) * (to - from + 1) + from);
  //  System.out.println("from: " + from + " to: " + to + " zufall: " + zufall);
    return zufall;
  }

  public static Comparable getPivot(Comparable[] data, int from, int to, String art) {
    Comparable partition = 0;
    if (art.equals("Zufall")) {
      return getPivotZufall(data, from, to);
    } else if (art.equals("Median")) {
      return getPivotMedian(data, from, to);
    }
    return partition;
  }

  public static Comparable getPivotZufall(Comparable[] data, int from, int to) {
//    return data[zufall(from, to)];
    return data[zufall(from, to)];
  }

  public static Comparable getPivotMedian(Comparable[] data, int from, int to) {
    Comparable median = null;
    int center = (to - from) / 2;
    if((data[from].compareTo(data[center]) >= 0 && data[from].compareTo(data[to]) <= 0)
        || (data[from].compareTo(data[to]) >= 0 && data[from].compareTo(data[center]) <= 0)){
      median = data[from];
    } else if((data[center].compareTo(data[from]) >= 0 && data[center].compareTo(data[to]) <= 0)
        || data[center].compareTo(data[from]) <= 0 && data[center].compareTo(data[to]) >= 0) {
      median = data[center];
    } else {
      median = data[to];
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
    Comparable pivot = getPivot(data, from, to, art); //Geandert
    int left = from;
    int right = to;
    do {
      while (right > from && data[right].compareTo(pivot) >= 0)
        right--;
      while (data[left].compareTo(pivot) < 0)
        left++;
      if (left < right)
        swap(data, left, right);
    }
    while (left < right);
    swap(data, to, left);
    return left;
  }


  public static void swap(Comparable[] data, int from, int to) {
    Comparable temp = data[from];
    data[from] = data[to];
    data[to] = temp;
  }
}
