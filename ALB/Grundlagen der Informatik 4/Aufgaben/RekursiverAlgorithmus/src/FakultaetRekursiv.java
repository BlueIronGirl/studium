/**
 * Created by Alice on 21.08.2016.
 */
public class FakultaetRekursiv {
  public static void main(String[] args) {
    System.out.println(fak(5));
  }

  private static Integer fak(Integer n) {
    if (n <= 1) {
      return 1;
    } else {
      return n * fak(n-1);
    }
  }
}
