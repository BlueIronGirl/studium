/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meinkraftsport;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Alice Bedow
 * @date 17.12.15
 * 
 */
public class TrainingstageContainer {

  private static TrainingstageContainer container = null;
  private Datenbasis basis;
  private ObjektDatei objektDatei;

  /**
   * privater Konstruktor (Singleton-Muster)
   */
  private TrainingstageContainer() {
    objektDatei = new ObjektDatei("Datenbasis.ser");
    try {
      basis = (Datenbasis) objektDatei.leseObjekt();
    } catch (Exception e) {
      System.out.println("Keine Datenbasis zum Auslesen vorhanden");
    }

    if (basis == null) {
      System.out.println("Es wurde eine neue Datenbasis angelegt");
      basis = new Datenbasis();
    }
  }

  /**
   * Klassenmethode, um Objekt zu erzeugen (Singleton-Muster)
   * @return 
   */
  public static TrainingstageContainer getObjektReferenz() {
    if (container == null) {
      container = new TrainingstageContainer();
    }
    return container;
  }

  /**
   * Rueckgabe Anzahl Trainingstage
   * @return 
   */
  public int getAnzahlTage() {
    return basis.getTage().size();
  }

  /**
   * Rueckgabe berechnetes Durchschnittsgewicht
   * @return 
   */
  public double getDurchschnittsgewicht() {
    return basis.getTage()
      .stream()
      .mapToInt(Trainingstag::getDurchschnittsgewicht)
      .average()
      .orElse(0);
  }

  /**
   * Rueckgabe berechnetes maximales Durchschnittsgewicht
   * @return 
   */
  public int getMaximalesDurchschnittsgewicht() {
    return basis.getTage()
      .stream()
      .mapToInt(Trainingstag::getDurchschnittsgewicht)
      .max()
      .orElse(0);
  }

  /**
   * Rueckgabe berechnetes minimales Durchschnittsgewicht
   * @return 
   */  
  public int getMinimalesDurchschnittsgewicht() {
    return basis.getTage()
      .stream()
      .mapToInt(Trainingstag::getDurchschnittsgewicht)
      .min()
      .orElse(0);
  }

  /**
   * Rueckgabe ermittelter Tendenz
   * @return 
   */
  public Tendenz getTendenz() {
    int sinkend=0;
    int steigend=0;
    int gleichbleibend=0;
    List<Trainingstag> test = 
      basis.getTage().stream()
        .sorted((w1,w2) -> w1.getTag() - w2.getTag())
        .collect(toList());
    for(int i=0;i<test.size()-1;i++)
    {
      int differenz = test.get(i+1).getGesamtgewicht()
        - test.get(i).getGesamtgewicht(); 
      if(differenz > 0)
        steigend++;
      else if(differenz == 0)
        gleichbleibend++;
      else
        sinkend++;     
    }
    if(steigend > sinkend && steigend > gleichbleibend)
      return Tendenz.I;
    else if(gleichbleibend > sinkend)
      return Tendenz.S;
    else if(gleichbleibend == steigend && gleichbleibend == sinkend)
      return Tendenz.S;
    else
      return Tendenz.D;
  }
  /**
   * Erstellung eines Trainingstages und derer Einsortierung in die Bisherigen
   * @param tag 
   */
  public void erzeugeTrainingstag(Trainingstag tag)
  {
    basis.getTage().add(tag);
    //Sortierung nach Tag
    Collections.sort(basis.getTage(), (w1,w2) -> w1.getTag() - w2.getTag());
  }
  
  /**
   * Iterator, um die Liste aller Trainingstage durchlaufen zu koennen
   * @return 
   */
  public Iterator<Trainingstag> iteratorTrainingstag()
  {
    return basis.getTage().iterator();
  }
  
  /**
   * Aufruf der Methode, welche die Daten in der Datei speichert zum Ende des Programms
   */
  public void endeAnwendung()
  {
    objektDatei.speichereObjekt(basis);
    System.out.println("Daten gespeichert.");
  }
}
