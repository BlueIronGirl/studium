/**
 * Created by alice_b on 29.07.2016.
 */
public class TuermeVonHanoiRekursiv {
  public static void main(String[] args) {
    bewege('A', 'B', 'C', 3);
  }

  public static void bewege(char Ausgangsstapel, char Hilfsstapel, char Zielstapel, int n) {
    if (n == 1) {
      System.out.println("Bewege eine Scheibe von " + Ausgangsstapel + " nach " + Zielstapel);
    } else {
      bewege(Ausgangsstapel, Zielstapel, Hilfsstapel, n - 1);
      System.out.println("Bewege eine Scheibe von " + Ausgangsstapel + " nach " + Zielstapel);
      bewege(Hilfsstapel, Ausgangsstapel, Zielstapel, n - 1);
    }
  }
}
