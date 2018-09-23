/**
 * Created by alice_b on 29.07.2016.
 */
public class Sammelkarte extends ElementType<String> {
  private String name;

  public Sammelkarte(String nr, String name) {
    this.key = nr;
    this.name = name;
  }

  public String toString() {
    return "#" + key + " " + name;
  }

  public int compareTo(Sammelkarte karte) {
    return karte.key.compareTo(this.key);
  }
}
