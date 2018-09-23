/**
 * Created by Alice on 23.09.2016.
 */
public class BubbleSort {
  private int[] data;

  public BubbleSort(int[] data) {
    this.data = data;
  }

  public static void main(String[] args) {
    int[] zuSortieren3 = new int[18];
    for (int i = 0; i < zuSortieren3.length; i++) {
      int zufall = (int) ((Math.random()) * (15 - 0 + 1) + 0);
      zuSortieren3[i] = zufall;
    }
    BubbleSort bubbleSort = new BubbleSort(zuSortieren3);

    System.out.println("ZuSortierien: ");
    bubbleSort.ausgabe();
    bubbleSort.bubblesort();

    System.out.println("Sortiert: ");
    bubbleSort.ausgabe();
  }

  public void ausgabe() {
    for (int zahl : data)
      System.out.print(zahl + " ");
    System.out.println();
  }

  private void bubblesort() { //bubblesort
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data.length - 1; j++) {
        if (data[j] > data[j + 1])
          swap(j, j + 1);
      }
      ausgabe();
    }
  }

  private void swap(int i, int j) {
    int temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }
}
