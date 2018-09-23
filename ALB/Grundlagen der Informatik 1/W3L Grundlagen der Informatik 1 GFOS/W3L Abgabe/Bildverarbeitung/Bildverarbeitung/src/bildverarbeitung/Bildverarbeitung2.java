/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bildverarbeitung;

/**
 *
 * @author Alice Bedow
 * @date 27.10.2015
 * Programm zur Berechnung des Medians zweier Felder
 */
public class Bildverarbeitung2 {
    public static void main(String args[])
    {
        int werte[] = {22,0,4,2,62,262,25,2,86,7,21,6,9,2,1,5,11,45};
        int werte2[] = {33,9,2,6,5,8,2,4,8,43,58,34,2,5,2, 1, 2 }; //Alice Bedow, AB = 1,2
        int median;
        //Beide Arrays Sortieren
        werte = sortieren(werte);
        werte2 = sortieren(werte2);
        median = medianBerechnen(werte);
        System.out.println("Der Median des ersten Feldes ist: "+median);
        median = medianBerechnen(werte2);
        System.out.println("Der Median des zweiten Feldes ist: "+median);
    }
    public static int[] sortieren(int[] werte)
    {
        for(int i=0;i<werte.length;i++)
        {
            int min = werte[i];
            int posMin = i;
            for(int j=i+1;j<werte.length;j++)
            {
                if(werte[j]<min)
                {
                    min = werte[j];
                    posMin = j;
                }
            }
            //Vertauschen
            int temp = werte[posMin];
            werte[posMin] = werte[i];
            werte[i] = temp;
        }
        return werte;
    }
    public static int medianBerechnen(int[] werte)
    {
        int temp = werte.length / 2;
        int median;
        if(werte.length%2==0)
            median = (werte[temp]+werte[temp+1])/2;
        else
            median = werte[temp];
        return median;
    }
}
