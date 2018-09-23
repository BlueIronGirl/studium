/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3l.vorbereitung;

/**
 *
 * @author ALB
 */
public class Bildverarbeitung {
  public static void main(String args[])
  {
    int pixel[][] = {{34,255,255,56},{127,204,11,34},{123,98,127,34},{34,34,127,17}};
    int anz[][] = new int[pixel.length*pixel[0].length][2];
    //sortieren
    for(int i=0;i<pixel.length;i++)
      for(int j=0;j<pixel[i].length;j++)
      {
        int posMin = i*4+j;
        int min = pixel[i][j];
        for(int pos=posMin+1;pos<pixel.length*pixel[i].length;pos++)
        {
          if(pixel[pos/4][pos%4]<pixel[posMin/4][posMin%4])
          {
            posMin = pos;
            min = pixel[pos/4][pos%4];
          }
        }
        //vertauschen
        int temp = pixel[i][j];
        pixel[i][j] = pixel[posMin/4][posMin%4];
        pixel[posMin/4][posMin%4] = temp;
      }
    //Histogramm
    int z=0;
    boolean check;
    for(int i=0;i<pixel.length;i++)
    {
      for(int j=0;j<pixel[i].length;j++)
      {
        check = false;
        for(int k=0;k<anz.length;k++)
          if(anz[k][0]==pixel[i][j])
          {
            anz[k][1]++;
            check = true;
          }
        if(check==false)
        {
          anz[z][0]=pixel[i][j];
          anz[z][1]=1;
          z++;
        }
      }
    }
    for(int i=0;i<anz.length;i++)
    {
      if(anz[i][1]>0)
      {
        System.out.print(anz[i][0]+"\t");
        System.out.print(anz[i][1]);
        System.out.println(); 
      }    
    }
  }
}
