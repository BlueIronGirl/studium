import java.util.Stack;

/**
 * Created by alice_b on 29.07.2016.
 */
public class TuermeVonHanoi {
  public static void main(String[] args) {
    bewege('A', 'B', 'C', 3);
  }

  public static void bewege(char Ausgangsstapel, char Hilfsstapel, char Zielstapel, int n) {
    class Auftrag {
      char Ausgangsstapel, Hilfsstapel, Zielstapel;
      int n;

      public Auftrag(char ausgangsstapel, char hilfsstapel, char zielstapel, int n) {
        Ausgangsstapel = ausgangsstapel;
        Hilfsstapel = hilfsstapel;
        Zielstapel = zielstapel;
        this.n = n;
      }
    }
    Stack<Auftrag> stack = new Stack<Auftrag>();
    stack.push(new Auftrag(Ausgangsstapel, Hilfsstapel, Zielstapel, n));
    do {
      Auftrag auftrag = stack.pop();
      if (auftrag.n == 1) {
        System.out.println("Bewege eine Scheibe von " + auftrag.Ausgangsstapel
            + " nach " + auftrag.Zielstapel);
      } else {
        //Zuerst da erst spaeter verarbeitet
        stack.push(new Auftrag(auftrag.Hilfsstapel, auftrag.Ausgangsstapel, auftrag.Zielstapel, auftrag.n - 1));
        //bei 1 direkt ausgeben
        stack.push(new Auftrag(auftrag.Ausgangsstapel, auftrag.Hilfsstapel, auftrag.Zielstapel, 1));
        stack.push(new Auftrag(auftrag.Ausgangsstapel, auftrag.Zielstapel, auftrag.Hilfsstapel, auftrag.n - 1));
      }
    } while (!stack.isEmpty());
  }
}
