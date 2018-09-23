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
public class Langzahlarithmetik {
    public static void main(String args[])
    {
        int langzahl[]= new int[20]; //Speicherarray
        long quad=1;
        long quadMerker=1;
        //int zahl = 0;
        int un=0,ua=0,j;
        for(int i=1;i<64;i++)//64 Felder des Schachbretts
        {
            quad=quadMerker;
            if(i>1)
                quad*=2;
            System.out.println("Quad: "+quad);
            j=0;
            long temp = 0;
            quadMerker=quad;
            int zahl[]=new int[20];
            do
            {
                if(j>0)
                    quad/=10;
               zahl[j]=(int)(quad%10);
                
                //System.out.println("quad: "+quad%10);
                j++;
            }while(quad/10!=0);
            System.out.print("Zahl: ");
            for(int y=(zahl.length-1);y>=0;y--)
            {
                System.out.print(zahl[y]);
            }
            System.out.println();
            j=0;
            int counter=0;
            while(zahl[counter]!=0)
            {
                counter++;
            }
            counter=20;
            j=0;ua=0;un=0;
            //System.out.println("Counter: "+counter);
            while(j<counter)
            {
                un=(int)((langzahl[j]+zahl[j]+ua)/10);
                langzahl[j]=(int)(langzahl[j]+zahl[j]+ua)%10;
                ua=un;
                j++;
                int k=j;
                while(ua!=0)
                {
                    un=(langzahl[k]+ua)/10;
                    langzahl[k]=(langzahl[k]+ua)%10;
                    ua=un;
                    k++;
                }
            }
            System.out.print("Langzahl: ");
            for(int y=(langzahl.length-1);y>=0;y--)
            {
                System.out.print(langzahl[y]);
            }
            System.out.println();
        }
        for(int y=langzahl.length-1;y>=0;y--)
        {
            System.out.print(langzahl[y]);
        }
    }
}
