/**
 * Created by alice_b on 29.07.2016.
 */
public class Auftrag extends ListNode<Integer> {
  private String bezeichnung;

  public Auftrag(int auftragsnummer, String bezeichnung) {
    this.bezeichnung = bezeichnung;
    this.key = auftragsnummer;
  }

  public static void main(String[] args) {
    Auftrag ersterAuftrag = new Auftrag(10, "erster Auftrag");
    Auftrag zweiterAuftrag = new Auftrag(20, "zweiter Auftrag");
    Auftrag dritterAuftrag = new Auftrag(30, "dritter Auftrag");
    ersterAuftrag.next = zweiterAuftrag;
    zweiterAuftrag.next = dritterAuftrag;
    Auftrag gefunden = (Auftrag) firstNodeWith(ersterAuftrag, 20);
    System.out.println(gefunden.bezeichnung);
  }

  public static ListNode<?> firstNodeWith(ListNode<?> list,
                                          Object search_key) {
    ListNode<?> node = list;
    while ((node != null) && !(node.key.equals(search_key))) {
      node = node.next;
    }
    return node;
  }
}
