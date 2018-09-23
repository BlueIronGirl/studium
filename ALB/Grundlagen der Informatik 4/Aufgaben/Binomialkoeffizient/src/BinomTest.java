/**
 * Created by alice_b on 19.07.2016.
 */
public class BinomTest {
  public static void main(String[] args) {
    System.out.println(binom(10, 3));
  }

  public static int binom(int n, int k) {
    if (n == k || k == 0) {
      return 1;
    }
    return n * binom(n - 1, k - 1) / k;
  }
}
