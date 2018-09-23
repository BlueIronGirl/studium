/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package langzahlarithmetik;

/**
 *
 * @author Alice Bedow
 * @date 08.11.2015
 * Programm zur Berechnung der Summe der Zahlen 2^0 bis 2^63 (also 2^64-1)
 */
public class LangzahlarithmetikOptimizedByte {
    public static void main(String args[])
    {
        byte langzahl[] = new byte[20]; //Speicherarray Summe der Koerner
        byte koerner[] =  new byte[20]; //Speicherarray Koerner
        koerner[0] = 1;
        for(int i=1;i<=64;i++)//64 Felder des Schachbretts
        {
            if(i>1)
            {   
                koernerMultiply(koerner); //Erhoehung der Anzahl der Koerner durch Multiplikation jeder Stelle mit 2
            }
            summeDerKoerner(langzahl,koerner); //Berechnung der aktuellen Summe der Koerner
            ausgabe(langzahl,koerner,i); //Ausgabe als Tabelle
        }
    }
    public static void koernerMultiply (byte[] koerner)
    {
        //Erhoehung der Anzahl der Koerner durch Multiplikation jeder Stelle mit 2
        int ua=0,un=0,k=0;
        while(k < koerner.length)
        {
            un=(koerner[k]*2+ua)/10;
            koerner[k]=(byte)((koerner[k]*2+ua)%10);
            ua=un;
            k++;
        }
    }
    public static void summeDerKoerner (byte[] langzahl,byte[] koerner)
    {
        //Berechnung der aktuellen Summe der Koerner
        int j=0,ua=0,un=0;
        while(j<koerner.length)
        {
            un=(langzahl[j]+koerner[j]+ua)/10;
            langzahl[j]=(byte)((langzahl[j]+koerner[j]+ua)%10);
            ua=un;
            j++;
        }
    }
    public static void ausgabe(byte[] langzahl, byte[] koerner,int schachfeldZaehler)
    {
        //Ausgabe als Tabelle
        if (schachfeldZaehler == 1)
            System.out.println("Zaehler\tKoerner\t\t\tSumme der Koerner"); //Ueberschrift
        System.out.print(schachfeldZaehler+"\t");
        for(int y=koerner.length-1;y>=0;y--)
        {
            System.out.print(koerner[y]);
        }
        System.out.print("\t");
        for(int y=(langzahl.length-1);y>=0;y--)
        {
            System.out.print(langzahl[y]);

        }
        System.out.println();
    }
            
}
