/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminplaner;

import java.time.LocalDate;
/**
 *
 * @author Alice Bedow
 * @date 27.11.2015
 * Programm zur Verwaltung von Terminen
 */
public class Termin {
  private LocalDate zeitpunkt;
  private String beschreibung;
  /**
   * Standardkonstruktor
   */
  public Termin()
  {
    
  }
  /**
   * Erstellen eines Termins aus festgelegten Werten
   * @param zeitpunkt
   * @param beschreibung 
   */
  public Termin(LocalDate zeitpunkt, String beschreibung)
  {
    this.zeitpunkt = zeitpunkt;
    this.beschreibung = beschreibung;
  }
  /**
   * Vergleich zweier Zeitpunkte
   * @param zeitpunkt1
   * @param zeitpunkt2
   * @return 
   */
  public static char ueberpruefenZeitpunkt(Termin zeitpunkt1, Termin zeitpunkt2)
  {
    if(zeitpunkt1.zeitpunkt.isBefore(zeitpunkt2.zeitpunkt))
      return 'f';
    else if(zeitpunkt1.zeitpunkt.isAfter(zeitpunkt2.zeitpunkt))
      return 'n';
    else if(zeitpunkt1.zeitpunkt.isEqual(zeitpunkt2.zeitpunkt))
      return 'g';
    else
      return 'e'; //Error
  }
  /**
   * Zeitpunkt ermitteln
   * @return 
   */
  public LocalDate getZeitpunkt()
  {
    return this.zeitpunkt;
  }
  /**
   * Beschreibung ermitteln
   * @return 
   */
  public String getBeschreibung()
  {
    return this.beschreibung;
  }
  /**
   * Zeitpunkt setzen
   * @param zeitpunkt 
   */
  public void setZeitpunkt(LocalDate zeitpunkt)
  {
    this.zeitpunkt = zeitpunkt;
  }
  /**
   * Beschreibung setzen
   * @param beschreibung 
   */
  public void setBeschreibung(String beschreibung)
  {
    this.beschreibung = beschreibung;
  }
  
}
