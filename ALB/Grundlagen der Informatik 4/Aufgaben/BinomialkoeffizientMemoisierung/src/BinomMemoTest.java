/**
 * Created by Alice on 22.07.2016.
 */
public class BinomMemoTest {
  public static void main(String[] args) {
    Stopwatch stopwatch = new Stopwatch();
    stopwatch.starten();
    System.out.println(binom(40, 5));
    stopwatch.stoppen();
    System.out.println(stopwatch.getLaufzeit());
    stopwatch.starten();
    System.out.println(binom_memo(40, 5, null));
    stopwatch.stoppen();
    System.out.println(stopwatch.getLaufzeit());
  }

  public static int binom_memo(int n, int k, int[][] memo) {
    if (memo == null) {
      memo = new int[n + 1][k + 1];
    }
    if (memo[n][k] != 0) {
      return memo[n][k];
    }
    if (n == k || k == 0) {
      return memo[n][k] = 1;
    }
    return memo[n][k] = binom_memo(n - 1, k - 1, memo) + binom_memo(n - 1, k, memo);
  }

  /**
   * Laufzeit Gegentest
   * @param n
   * @param k
   * @return
   */
  public static int binom(int n, int k) {
//    System.out.println("n: " + n + " k: " + k);
    if (n == k || k == 0) {
      return 1;
    }
    return binom(n - 1, k - 1) + binom(n - 1, k);
  }
}
