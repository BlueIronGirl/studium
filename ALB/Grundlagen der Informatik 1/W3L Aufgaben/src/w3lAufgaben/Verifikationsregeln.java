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
public class Verifikationsregeln {
    public static void main(String args[])
    {
        int n=5,summe,i,pvor,pnach;
        summe = 0;
        i = 1;
        System.out.println("i\tsumme\ti2\tsumme2\tn");
        System.out.println(i+"\t"+summe+"\t"+i+"\t"+summe+"\t"+n);
        while(i != n + 1)
        {
            pvor = summe;
            for(int j=i;j<n+1;j++)
                pvor += j;
            System.out.println("Pvor: "+pvor);
            System.out.print(i+"\t"+summe+"\t");
            summe = summe + i;
            i = i + 1;
            System.out.print(i+"\t"+summe+"\t"+n);
            System.out.println();
            pnach = summe;
            for(int j=i;j<n+1;j++)
                pnach += j;
            System.out.println("Pnach: "+pnach);
            
        }
         System.out.println();
          System.out.println();
        System.out.println(summe);
    }
}
