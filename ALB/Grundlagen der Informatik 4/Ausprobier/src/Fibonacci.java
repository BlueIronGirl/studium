import java.util.Stack;

/**
 * Created by Alice on 22.07.2016.
 */
public class Fibonacci {
  public static void main(String[] args) {
    System.out.println(fib_iterativ_mit_Stapel(10));
    System.out.println(fib_iterativ_ohne_Stapel(10));
  }

  public static long fib_iterativ_mit_Stapel(int n) {
    Stack<Integer> s = new Stack<Integer>();
    s.push(n);  // Initialisierung: Aufruf für n
    long ergebnis = 0;
    while (!s.empty())  // Iteration bis Stapel leer
    {
      int m = s.pop();  // obersten "Aufruf" entnehmen
      if (m <= 1)
        ergebnis += m;  // ehem. Rekursionsanker
      else {
        s.push(m - 2);    // Rekursionsschritt:
        s.push(m - 1);    // 2 neue "Aufrufe" für n-1 und n-2
      }
    }
    return ergebnis;
  }

  public static long fib_iterativ_ohne_Stapel(int n) {
    long f1 = 0;
    long f2 = 1;
    while (n > 0) {
      System.out.println("f1: " + f1 + " f2: " + f2 + " n: " + n);
      long f3 = f2;
      f2 = f1 + f2;
      f1 = f3;
      n--;
    }
    return f1;
  }
}
