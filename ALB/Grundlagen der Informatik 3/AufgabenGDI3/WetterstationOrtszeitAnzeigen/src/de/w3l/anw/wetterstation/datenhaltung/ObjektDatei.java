package de.w3l.anw.wetterstation.datenhaltung;

/* Programmname: Objektspeicherung
 * Datenhaltungs-Klasse: ObjektDatei
 * Aufgabe: Einen serialisierten Vector sequentiell lesen und schreiben
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjektDatei {
  // Attribute
  String einDateiname;

  // Konstruktor
  public ObjektDatei(String einDateiname) {
    this.einDateiname = einDateiname;
  }

  // Operationen
  public void speichereObjekt(Object einObjekt) {
    // Nutzung von java.lang.AutoCloseable;
    try (ObjectOutputStream eineObjektAusgabeDatei =
             new ObjectOutputStream(new FileOutputStream(
                 einDateiname))) {
      eineObjektAusgabeDatei.writeObject(einObjekt);
    } catch (Exception e) {
      System.out.println("Konnte Datei nicht speichern!");
    }
  }

  public Object leseObjekt() {
    Object einObjekt = null;
    // Nutzung von java.lang.AutoCloseable
    try (ObjectInputStream eineObjektEingabeDatei =
             new ObjectInputStream(new FileInputStream(
                 einDateiname))) {
      einObjekt = eineObjektEingabeDatei.readObject();
    } catch (Exception e) {
      System.out.println("Konnte Datei nicht lesen!");
    }
    return einObjekt;
  }
}
