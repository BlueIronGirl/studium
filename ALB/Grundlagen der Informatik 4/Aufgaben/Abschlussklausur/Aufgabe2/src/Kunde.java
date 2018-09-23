/**
 * Created by Alice on 18.09.2016.
 */
public class Kunde extends ElementType<String> {
  private String name;

  public Kunde(String name, String stadt) {
    this.name = name;
    this.key = stadt;
  }
}
