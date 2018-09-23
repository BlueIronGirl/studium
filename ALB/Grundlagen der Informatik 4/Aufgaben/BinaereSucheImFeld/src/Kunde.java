/**
 * Created by alice_b on 29.07.2016.
 */
public class Kunde extends ElementType<Integer> {
  String name;

  public Kunde(int nr, String name) {
    this.name = name;
    this.key = nr;
  }
}
