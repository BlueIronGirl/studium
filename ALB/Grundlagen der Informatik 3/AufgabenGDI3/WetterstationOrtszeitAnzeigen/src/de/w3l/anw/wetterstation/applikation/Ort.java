package de.w3l.anw.wetterstation.applikation;

import java.io.Serializable;
import java.time.ZoneOffset;

/**
 * Bezeichnet einen geografischen Ort, f�r den Wetterdaten
 * angezeigt werden k�nnen
 */
public class Ort implements Serializable {
  private static final long serialVersionUID =
      -3535076932558465372L;

  private String id; // Eindeutiger Identifizierer

  private String name;

  private float laenge;

  private float breite;

  private ZoneOffset offset; //Offset zu GMT

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getBreite() {
    return breite;
  }

  /**
   * @return Breitengrad mit Unterscheidung (N)ord und (S)�d
   */
  public String getBreiteNS() {
    String ergebnis = "" + Math.abs(breite);
    if (breite > 0)
      return ergebnis + " N";
    return ergebnis + " S";
  }

  public void setBreite(float breite) {
    this.breite = breite;
  }

  public float getLaenge() {
    return laenge;
  }

  /**
   * @return L�ngengrad mit Unterscheidung (W)est und (E)ast
   */
  public String getLaengeWE() {
    String ergebnis = "" + Math.abs(laenge);
    if (laenge < 0)
      return ergebnis + " W";
    return ergebnis + " E";
  }

  public void setLaenge(float laenge) {
    this.laenge = laenge;
  }

  public boolean equals(Object o2) {
    if (o2.getClass() != Ort.class)
      return false;
    return (this.getId() == ((Ort) o2).getId());
  }

  public ZoneOffset getOffset() {
    return offset;
  }

  public void setOffset(ZoneOffset offset) {
    this.offset = offset;
  }

  /** Setzen Offset mit Umformung Zeitverschiebung in String zu ZoneOffset */
  public void setOffset(String deltaUTC) {
    int stunden = Integer.parseInt(deltaUTC.substring(0, 3));
    int minuten = Integer.parseInt(deltaUTC.substring(3));
    offset = ZoneOffset.ofHoursMinutes(stunden, minuten);
  }
}
