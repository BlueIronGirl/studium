/**
 * Created by alice_b on 29.07.2016.
 */
public class GGTMitRekursion {
  public static void main(String[] args) {
    System.out.println(ggt(24, 8));
    System.out.println(ggt(32, 12));
  }

  public static int ggt(int a, int b) {
    if (a == b) {
      return a;
    }
    if (a > b) {
      return ggt(a - b, b);
    } else {
      return ggt(a, b - a);
    }
  }
}
