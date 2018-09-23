/**
 * Created by Alice on 18.09.2016.
 */
public class Fakultaet {
  public static void main(String[] args) {
    System.out.println(fakRek(5));
    System.out.println("----------------");
    System.out.println(fakIterativ(5));
  }

  private static Integer fakRek(Integer n) {
    if (n <= 1) {
      return 1;
    } else {
      return n * fakRek(n - 1);
    }
  }

  private static Integer fakIterativ(Integer n) {
    class Auftrag {
      Integer n;

      public Auftrag(Integer n) {
        this.n = n;
      }
    }
    SimpleLinkedStack<Auftrag> auftraege = new SimpleLinkedStack<Auftrag>();
    auftraege.push(new Auftrag(n));
    Integer result = 1;
    while (!auftraege.empty()) {
      Auftrag auftrag = auftraege.pop();
      if (auftrag.n > 1) {
        auftraege.push(new Auftrag(auftrag.n - 1));
        result *= auftrag.n;
      }
    }
    return result;
  }
}
