/**
 * Created by Alice on 18.09.2016.
 */
public class Binomialkoeffizient {
  public static void main(String[] args) {
    System.out.println(binom(10, 3));
  }

  public static int binom(int n, int k) {
    if (n==k || k == 0) {
      return 1;
    }
    return binom(n - 1, k - 1) + binom(n - 1, k);
  }
}
