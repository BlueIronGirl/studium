/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artikelverwaltung;

/**
 * @author Alice Bedow
 * @date 24.11.2015
 * Programm zur Verwaltung von Artikeln
 */
public class ArtikelverwaltungUI {
  public static void main(String[] args) {
    Artikelverwaltung artikel[] = new Artikelverwaltung[4];
    int artikelnr[] = {4711,4712,8726,19};
    String bezeichnung[] = {"Diashow","Bildbeschriftung","100 Piktos","Alice Bedow"};
    String sprache[] = {null,null,null,"C"};
    String beschreibung[] = {"Dia-Show auf HTML-Seite","Erlaubt Beschriftung von Bildern","100 Piktogramme für HTML-Seite","Eine Person"};
    double verkaufspreis[] = {29.9,99.9,54.5,999.99};
    System.out.println("Artikelnr\tBezeichnung\tSprache\tBeschreibung\tVerkaufspreis");
    for(int i=0;i<artikel.length;i++)
    {
      artikel[i] = new Artikelverwaltung();
      artikel[i].setArtikelnr(artikelnr[i]);
      artikel[i].setBezeichnung(bezeichnung[i]);
      if (sprache[i]!=null)
        artikel[i].setSprache(sprache[i]);
      artikel[i].setBeschreibung(beschreibung[i]);
      artikel[i].setVerkaufspreis(verkaufspreis[i]);
      System.out.print(artikel[i].getArtikelnr()+"\t");
      System.out.print(artikel[i].getBezeichnung()+"\t");
      System.out.print(artikel[i].getSprache()+"\t");
      System.out.print(artikel[i].getBeschreibung()+"\t");
      System.out.print(artikel[i].getVerkaufspreis()+"\t");
      System.out.println();   
    }
  }
}
