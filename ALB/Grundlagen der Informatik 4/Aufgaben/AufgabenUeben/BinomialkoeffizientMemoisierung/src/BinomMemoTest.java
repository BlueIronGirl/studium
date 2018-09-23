/**
 * Created by Alice on 18.09.2016.
 */
public class BinomMemoTest {
  public static void main(String[] args) {
    System.out.println(binom_memo(10, 3, null));
  }

  public static int binom_memo(int n, int k, int[][] memo) {
    if (memo == null) {
      memo = new int[n + 1][k + 1];
    }
    if (n == k || k == 0) {
      return memo[n][k] = 1;
    } else if (memo[n][k] != 0) {
      return memo[n][k];
    }
    return memo[n][k] = binom_memo(n - 1, k - 1, memo) + binom_memo(n - 1, k, memo);
  }
}
