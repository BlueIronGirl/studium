import java.util.Stack;

/**
 * Created by Alice on 18.09.2016.
 */
public class HanoiIterativ {
  public static void main(String[] args) {
    bewegeRekursiv('A', 'B', 'C', 3);

    System.out.println("-----------------");

    bewegeIterativ('A', 'B', 'C', 3);
  }

  public static void bewegeRekursiv(char Ausgangsstapel, char Hilfsstapel,
                                    char Zielstapel, int n) {
    if (n == 1) {
      System.out.println("Bewege eine Scheibe von" +
          Ausgangsstapel + " nach " + Zielstapel);
    } else {
      bewegeRekursiv(Ausgangsstapel, Zielstapel, Hilfsstapel, n - 1);
      System.out.println("Bewege eine Scheibe von" +
          Ausgangsstapel + " nach " + Zielstapel);
      bewegeRekursiv(Hilfsstapel, Ausgangsstapel, Zielstapel, n - 1);
    }
  }

  public static void bewegeIterativ(char Ausgangsstapel, char Hilfsstapel, char Zielstapel, int n) {
    class Auftrag {
      char Ausgangsstapel;
      char Hilfsstapel;
      char Zielstapel;
      int n;

      public Auftrag(char ausgangsstapel, char hilfsstapel, char zielstapel, int n) {
        Ausgangsstapel = ausgangsstapel;
        Hilfsstapel = hilfsstapel;
        Zielstapel = zielstapel;
        this.n = n;
      }
    }

    Stack<Auftrag> auftragStack = new Stack();
    auftragStack.push(new Auftrag(Ausgangsstapel, Hilfsstapel, Zielstapel, n));
    while (!auftragStack.isEmpty()) {
      Auftrag auftrag = auftragStack.pop();
      if (auftrag.n == 1) {
        System.out.println("Bewege eine Scheibe von " +
            auftrag.Ausgangsstapel + " nach " + auftrag.Zielstapel);
      } else {
        auftragStack.push(new Auftrag(auftrag.Hilfsstapel, auftrag.Ausgangsstapel, auftrag.Zielstapel, auftrag.n - 1));
        auftragStack.push(new Auftrag(auftrag.Ausgangsstapel, auftrag.Hilfsstapel, auftrag.Zielstapel, 1)); //Ausgabe
        auftragStack.push(new Auftrag(auftrag.Ausgangsstapel, auftrag.Zielstapel, auftrag.Hilfsstapel, auftrag.n - 1));
      }
    }
  }
}
