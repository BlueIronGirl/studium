/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3lUebungen;

/**
 *
 * @author ALB
 */
public class SelectionSort {
  public static void main(String args[])
  {
    String[] name = {"Meyer","Schulz","Balzert","Maier","Peters","Meyer"};
    System.out.println("Unsortiert");
    for(int i=0;i<name.length;i++)
    {
      System.out.println(name[i]);
    }
    selectionSort(name);
    System.out.println();
    System.out.println("Sortiert");
    for(int i=0;i<name.length;i++)
    {
      System.out.println(name[i]);
    }
  }
  public static void selectionSort(String[] name)
  {
    for(int i=0;i<name.length;i++)
    {
      int posMin = i;
      String min = name[i];
      for(int pos=i+1;pos<name.length;pos++)
      {
        if(name[pos].compareTo(min)<0)
        {
          posMin = pos;
          min = name[pos];
        }
      }
      //Vertauschen
      String temp = name[posMin];
      name[posMin] = name[i];
      name[i] = temp;
    }    
    
  }
}

