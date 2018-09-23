/**
 * Created by alice_b on 19.07.2016.
 */
public class MainBS

{

  public static void main(String[] args) {
    Kunde[] data = { //1. Zuweisung
        new Kunde("Müller", "Berlin"),
        new Kunde("Mustermann", "Frankfurt"),
        new Kunde("Schmidt", "München"),
        new Kunde("Meyer", "Hamburg")
    };
    //Ausgeben vor Sortierung
    ausgeben(data); //2. Aufruf
    //Sortieren
    bubbleSort(data); //3. Aufruf
    //Ausgeben vor Sortierung
    ausgeben(data); //4. Aufruf
  }


  public static void ausgeben(Kunde[] data) {
    for (int i = 0; i < data.length; i++) //5. Schleife
    {
      System.out.println(data[i].key); //6. Ausgabe
    }
    System.out.println("+++"); //7. Ausgabe
  }


  public static void bubbleSort(Kunde[] data) {
    for (int i = 0; i < data.length - 1; i++) //8. Schleife
      for (int j = 0; j < data.length - i - 1; j++) //9. Schleife
        if (data[j].compareTo(data[j + 1]) > 1) //10. Abfrage
          swap(data, j, j + 1); //11. Aufruf
  }

  public static void swap(Kunde[] data, int indexI, int indexII) {
    Kunde tmp = data[indexI]; //12. Zuweisung
    data[indexI] = data[indexII]; //13. Zuweisung
    data[indexII] = tmp; //14. Zuweisung
  }
}

class Kunde extends ElementType<String> {
  String name;

  public Kunde(String name, String stadt) {
    this.name = name; //15. Zuweisung
    key = stadt; //16. Zuweisung
  }

  public int compareTo(Kunde kunde) {
    return key.compareTo(kunde.key); //17. Abfrage
  }
}

class ElementType<KeyType extends Comparable> {
  KeyType key;
}