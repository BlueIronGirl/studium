/**
 * Created by alice_b on 29.07.2016.
 */
public class Student extends ElementType<Integer> {
  String name;

  public Student(String name, int matrikelnr) {
//    this.matrikelnr = matrikelnr;
    this.name = name;
    this.key = matrikelnr;
  }
}
