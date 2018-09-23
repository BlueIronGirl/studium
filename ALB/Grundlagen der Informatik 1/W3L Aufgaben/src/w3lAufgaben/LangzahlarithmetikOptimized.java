/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package w3lAufgaben;

/**
 *
 * @author Alice
 */
public class LangzahlarithmetikOptimized {
    public static void main(String args[])
    {
        int langzahl[]= new int[20]; //Speicherarray Summe der Koerner
        int koerner[]=  new int[20]; //Speicherarray Koerner
        koerner[0]=1;
        int un=0,ua=0,j;
        int schachfeldZaehler = 0;
        for(int i=1;i<64;i++)//64 Felder des Schachbretts
        {
            schachfeldZaehler++;
            if(i>1)
            {   
                koerner = KoernerMultiply(koerner);
            }
            langzahl = SummeDerKoerner(langzahl,koerner);
            Ausgabe(langzahl,koerner,schachfeldZaehler);
        }
    }
    public static int[] KoernerMultiply (int[] koerner)
    {
        int ua=0,un=0,k=0;
        while(k<koerner.length)
        {
            do
            {
                un=(koerner[k]*2+ua)/10;
                koerner[k]=(koerner[k]*2+ua)%10;
                ua=un;
                k++;
            }while(ua!=0);
        }
        
       return koerner;
    }
    public static int[] SummeDerKoerner (int[] langzahl,int[] koerner)
    {
        int j=0,ua=0,un=0;
        while(j<koerner.length)
        {
            do
            {
                un=(langzahl[j]+koerner[j]+ua)/10;
                langzahl[j]=(langzahl[j]+koerner[j]+ua)%10;
                ua=un;
                j++;
            }while(ua!=0);
        }
        return langzahl;
    }
    public static void Ausgabe(int[] langzahl, int[] koerner,int schachfeldZaehler)
    {
        System.out.println("Zaehler\tKoerner\t\t\tSumme der Koerner");
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
