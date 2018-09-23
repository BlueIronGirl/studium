/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package verifikation;

/**
 *
 * @author Alice
 */
public class ggTBerechnen {
    public static void main(String args[])
    {
        int x=30,y=5;
        do
        {
            int temp = x;
            x = y;
            y = temp % y;
        }
        while(y!=0);
        System.out.println("ggt: "+x);
    }
}
