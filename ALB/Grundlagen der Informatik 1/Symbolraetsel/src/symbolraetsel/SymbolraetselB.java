/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbolraetsel;

/**
 *
 * @author Alice Bedow
 * @date 07.11.2015
 */
public class SymbolraetselB {
    public static void main(String args[])
    {
        int anzahlKombinationen = 0;
        for(int b=0;b<10;b++) //mit fuehrenden Nullen
            for(int e=0;e<10;e++)
                for(int h=0;h<10;h++) //mit fuehrenden Nullen
                    for(int s=0;s<10;s++)
                        for(int t=0;t<10;t++) //mit fuehrenden Nullen
                        {
                            if(b!=e && b!=h && b!=s && b!=t && e!=h && e!= s && e!=t && h!=s && h!=t && s!=t) //wenn jeder Buchstabe unterschiedlich ist
                            {
                                if((h*100+e*10+s)+(t*100+h*10+e)==(b*1000+e*100+s*10+t) || (h==0 && t==0)) //hes + the = best
                                {
                                    anzahlKombinationen++;
                                    System.out.println("Durchlaufnr: " + anzahlKombinationen + "\t" + "b: " +
                                            b + " e: " + e + " h: " + h + " s: " + s + " t: " + t); //Ausgabe der Lösungswerte
                                    System.out.println(" " + h + e + s);
                                    System.out.println("+" + t + h + e);
                                    System.out.println("-----");
                                    System.out.println("" + b + e + s + t);
                                }
                            }
                        }
        if(anzahlKombinationen > 1)
            System.out.println("Es gibt "+anzahlKombinationen+" Kombinationen");
        else
            System.out.println("Es gibt "+anzahlKombinationen+" Kombination");
    }
}
