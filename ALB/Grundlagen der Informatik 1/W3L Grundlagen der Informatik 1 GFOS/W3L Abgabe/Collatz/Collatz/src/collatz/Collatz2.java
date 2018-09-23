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
public class Collatz2 {
    public static void main(String[] args)
    {
        System.out.println("Geben Sie eine ganze Zahl ein:");
        int n = Console.readInt();
        int z=0;
        System.out.println("Folge:");
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
            System.out.print(n);
            if(n!=1)
                System.out.print(" -> ");
            
        }
        System.out.println();
        System.out.println("Folgenlänge: "+z);
        
    }
}
