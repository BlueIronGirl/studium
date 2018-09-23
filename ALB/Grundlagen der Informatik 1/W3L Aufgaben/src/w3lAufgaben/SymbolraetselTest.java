/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3lAufgaben;

/**
 *
 * @author alice_b
 */
public class SymbolraetselTest {
    public static void main(String args[])
    {
        for(int b=1;b<10;b++) //ohne fuehrende Nullen
            for(int e=0;e<10;e++)
                for(int h=1;h<10;h++) //ohne fuehrende Nullen
                    for(int s=0;s<10;s++)
                        for(int t=1;t<10;t++) //ohne fuehrende Nullen
                        {
                            if(b!=e && b!=h && b!=s && b!=t && e!=h && e!= s && e!=t && h!=s && h!=t && s!=t) //wenn jedem Buchstaben eine andere Zahl zugeordnet wurde
                            {
                                if((h*100+e*10+s)+(t*100+h*10+e)==b*1000+e*100+s*10+t) //hes + the = best
                                {
                                    System.out.println("b: "+b+" e: "+e+" h: "+h+" s: "+s+" t: "+t); //Ausgabe der Lösungswerte
                                    System.out.println(" "+h+e+s);
                                    System.out.println("+"+(t)+(h)+(e));
                                    System.out.println("_____");
                                    System.out.println(""+b+e+s+t);
                                }
                            }
                        }
        for(int i=0;i<10;i++)
            for(int b=0;b<10;b++)
                for(int l=0;l<10;l++)
                    if(i!=b&&i!=b&&b!=l&&i!=l)
                    {
                        if(i+(10*b+b)==(100*i+l*10+l))
                        {    
                            System.out.println(" "+i);
                            System.out.println("+"+b+b);
                            System.out.println("-----");
                            System.out.println(""+i+l+l);
                        }
                    }
    }
}
