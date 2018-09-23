/**
 * Created by alice_b on 29.07.2016.
 */
public class Main {
  public static void main(String argv[]) {
    OpenHashTable oht = new OpenHashTable(10);
    oht.add(new Student("Fred", 108));
    oht.add(new Student("Markus", 109));
    oht.add(new Student("Walter", 110));
    Student stud = (Student) oht.get(110);
    if (stud != null)
      System.out.println(stud.name); // Ausgabe: Walter
  }
}
