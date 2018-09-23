/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collatz;

import inout.Console;

/**
 *
 * @author Alice Bedow
 * @date 07.11.2015
 */
public class Collatz4 {
    public static void main(String[] args)
    {
        int n=0,z,w;
        System.out.println("Aktueller Wert\tFolgenlänge\tMaximum");
        for(int i=1;i<=40;i++)
        {
            w=0;z=0;n=i;
            while(n>1)
            {
                z++;
                if(n % 2 == 0) //gerade
                {
                    n = n / 2;
                }
                else if(n % 2 == 1) //ungerade
                {
                    n = 3 * n + 1;
                }
                if(n>w)
                    w=n;
            }
            System.out.print(i+"\t\t");
            System.out.print(z+"\t\t");
            System.out.print(w+"\n");        
        }    
    }
}
