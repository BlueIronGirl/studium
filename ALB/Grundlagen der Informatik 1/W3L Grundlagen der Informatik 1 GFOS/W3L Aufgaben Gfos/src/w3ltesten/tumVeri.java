/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3ltesten;
import inout.Console;
/**
 *
 * @author ALB
 */
public class tumVeri {
  public static void main(String args[])
  {
    int a,i,x,z;
    a = Console.readInt();
    i=0;
    x=-1;
    z=0;
    System.out.println("x\tz\ti\tx\tz\ti");
    while(i!=a)
    {
      System.out.print(x+"\t"+z+"\t"+i+"\t");
      x=x+2;
      z=z+x;
      i++;
      System.out.print(x+"\t"+z+"\t"+i);
      System.out.println();
    }
    
  }
}
