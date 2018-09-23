package de.w3l.anw.wetterstation.applikation;

/* Programmname: Wetterstation
 * Fachkonzept-Klasse: Wetter
 * Aufgabe: Ein Datensatz
 */

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Jedes Objekt stellt einen Wetter-Datensatz dar. Der
 * Aufbau des Datensatzes und der Zugriff auf seine Elemente
 * soll unabhängig davon sein, aus welcher Quelle die Daten
 * geholt wurden.
 */
public class Wetter implements Serializable
{
  private static final long serialVersionUID =
      -5498017834252630337L;

  // Attribute
  private Ort ort;

  /* Alle Zeiten beziehen sich
  * immer auf einen Ort.
  * Da hier schon ein Offset vorliegt, ist
  * es für andere Zeiten nicht
  * mehr notwendig. */
  private LocalDateTime zeit;

  private float luftfeuchte;

  private float temperatur;

  private float taupunkt;

  private float gefuehlteTemperatur;

  private float luftdruck;

  private int uvIndex;

  private float windgeschwindigkeit;

  private float windrichtung;

  private LocalTime sonnenaufgang;
  private LocalTime sonnenuntergang;

  private String wetterzustand;

  // get- und set-Operationen
  public void setOrt(Ort ort)
  {
    this.ort = ort;
  }

  public Ort getOrt()
  {
    return ort;
  }

  public void setZeit(LocalDateTime zeit)
  {
    this.zeit = zeit;
  }

  public LocalDateTime getZeit()
  {
    return zeit;
  }

  public void setLuftfeuchte(float Luftfeuchte)
  {
    this.luftfeuchte = Luftfeuchte;
  }

  public float getLuftfeuchte()
  {
    return luftfeuchte;
  }

  public void setWindgeschwindigkeit(
      float Windgeschwindigkeit)
  {
    this.windgeschwindigkeit = Windgeschwindigkeit;
  }

  public float getWindgeschwindigkeit()
  {
    return windgeschwindigkeit;
  }

  public void setWindrichtung(float Windrichtung)
  {
    this.windrichtung = Windrichtung;
  }

  public float getWindrichtung()
  {
    return windrichtung;
  }

  public LocalTime getSonnenaufgang()
  {
    return sonnenaufgang;
  }

  public void setSonnenaufgang(LocalTime sonnenaufgang)
  {
    this.sonnenaufgang = sonnenaufgang;
  }

  public LocalTime getSonnenuntergang()
  {
    return sonnenuntergang;
  }

  public void setSonnenuntergang(LocalTime sonnenuntergang)
  {
    this.sonnenuntergang = sonnenuntergang;
  }

  public float getTaupunkt()
  {
    return taupunkt;
  }

  public void setTaupunkt(float taupunkt)
  {
    this.taupunkt = taupunkt;
  }

  public float getTemperatur()
  {
    return temperatur;
  }

  public void setTemperatur(float temperatur)
  {
    this.temperatur = temperatur;
  }

  public int getUvIndex()
  {
    return uvIndex;
  }

  public void setUvIndex(int uvIndex)
  {
    this.uvIndex = uvIndex;
  }

  public String getWetterzustand()
  {
    return wetterzustand;
  }

  public void setWetterzustand(String url)
  {
    this.wetterzustand = url;
  }

  public float getGefuehlteTemperatur()
  {
    return gefuehlteTemperatur;
  }

  public void setGefuehlteTemperatur(
      float gefuehlteTemperatur)
  {
    this.gefuehlteTemperatur = gefuehlteTemperatur;
  }

  public float getLuftdruck()
  {
    return luftdruck;
  }

  public void setLuftdruck(float luftdruck)
  {
    this.luftdruck = luftdruck;
  }

  public boolean equals(Object wetter2)
  {
    // Zwei Wetter-Objekte sind gleich, wenn Sie denselben
    // Ort und dieselbe Zeit betreffen.
    if (wetter2.getClass() != Wetter.class)
      return false;
    Wetter w2 = (Wetter) wetter2;
    return (this.getOrt().equals(w2.getOrt()) && this
        .getZeit().equals(w2.getZeit()));
  }

  public String toString()
  {
    String ergebnis =
        ort.getName() + ", " + zeit + ": "  
            + " Sonnenaufgang: " + sonnenaufgang
            + " Sonnenuntergang: " + sonnenuntergang
            + " Temperatur: " + temperatur
            + " Luftfeuchte: " + luftfeuchte
            + " Luftdruck: " + luftdruck
            + " Windrichtung: " + windrichtung
            + " Windgeschwindigkeit: "
            + windgeschwindigkeit;
    return ergebnis;
  }
}
