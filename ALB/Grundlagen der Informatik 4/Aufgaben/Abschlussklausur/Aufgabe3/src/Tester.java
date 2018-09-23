/**
 * Created by Alice on 18.09.2016.
 */
public class Tester {
  public static void main(String[] args) {
    OpenHashTable<String> openHashTable = new OpenHashTable<String>(20);
    LinProbHashTable<String> linProbHashTable = new LinProbHashTable<String>(20);

    for (int i = 0; i < 17; i++) {
      ElementType<String> value = new ElementType<String>();
      value.key = String.valueOf(i);
      linProbHashTable.add(value);
    }

    for (int i = 0; i < 30; i++) {
      ElementType<String> value = new ElementType<String>();
      value.key = String.valueOf(10);
      openHashTable.add(value);
    }

    System.out.println(openHashTable.size());
    System.out.println(linProbHashTable.size());
  }
}
