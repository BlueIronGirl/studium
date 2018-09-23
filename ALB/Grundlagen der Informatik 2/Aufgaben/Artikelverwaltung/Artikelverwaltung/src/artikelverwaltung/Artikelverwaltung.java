/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artikelverwaltung;

/**
 *
 * @author Alice Bedow
 * @date 24.11.2015
 * Programm zur Verwaltung von Artikeln
 */
public class Artikelverwaltung {
  private int artikelnr;
  private String bezeichnung;
  private String sprache = "Java";
  private String beschreibung;
  private double verkaufspreis = 0;
  public Artikelverwaltung()
  {
    
  }
  public int getArtikelnr()
  {
    return artikelnr;
  }
  public String getBezeichnung()
  {
    return bezeichnung;
  }
  public String getSprache()
  {
    return sprache;
  }
  public String getBeschreibung()
  {
    return beschreibung;
  }
  public double getVerkaufspreis()
  {
    return verkaufspreis;
  }
  public void setArtikelnr(int artikelnr)
  {
    this.artikelnr = artikelnr;
  }
  public void setBezeichnung(String bezeichnung)
  {
    this.bezeichnung = bezeichnung;
  }
  public void setSprache(String sprache)
  {
    this.sprache = sprache;
  }
  public void setBeschreibung(String beschreibung)
  {
    this.beschreibung = beschreibung;
  }
  public void setVerkaufspreis(double verkaufspreis)
  {
    this.verkaufspreis = verkaufspreis;
  }
}
