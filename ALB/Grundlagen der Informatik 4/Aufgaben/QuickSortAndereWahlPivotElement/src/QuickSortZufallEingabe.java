/**
 * Created by alice_b on 29.07.2016.
 */
public class QuickSortZufallEingabe {
  public static void main(String[] args) {
    Comparable[] zuSortieren3 = new Comparable[15];
    for (int i = 0; i < zuSortieren3.length; i++) {
      int zufall = (int) ((Math.random()) * (15 - 0 + 1) + 0);
      zuSortieren3[i] = zufall(0, 15);
    }
    System.out.println("Unsortiert Test:");
    ausgabe(zuSortieren3);
    quicksort(zuSortieren3, 0, zuSortieren3.length - 1, "Median");

    System.out.println("Sortiert Test:");
    ausgabe(zuSortieren3);
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
//    return data[zufall(from, to)];
    return zufall(from, to);
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
      ausgabe(data);
      quicksort(data, from, pivotIndex - 1, art);
      quicksort(data, pivotIndex + 1, to, art);
    }
  }

  public static int partition(Comparable[] data, int from, int to, String art) {
//    int pivotIndex = getPivotIndex(data, from, to, art); //Geandert
    int pivotIndex = to;
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
