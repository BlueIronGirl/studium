/**
 * Created by Alice on 18.09.2016.
 */
public class GGTMitRekursion {
  public static void main(String[] args) {
    System.out.println(ggt(32, 8));
  }

  public static int ggt(int a, int b) {
    if (a == b) {
      return a;
    } else if (a > b) {
      return ggt(a - b, b);
    } else { //a < b
      return ggt(a, b - a);
    }
  }
}
