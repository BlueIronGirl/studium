/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bildverarbeitung;

/**
 *
 * @author Alice Bedow
 * @date 27.10.2015
 * Programm zur Erstellung eines Histogramms eines Feldes
 */
public class Bildverarbeitung1 {

    public static void main(String args[]) {
        int[][] pixel = {{34, 255, 255, 56}, {127, 204, 11, 34}, {123, 98, 127, 34}, {34, 34, 127, 17}};
        int[][] anz = new int[pixel.length*pixel[0].length][2];
        int pos,posMin,min;
        //Aufsteigend sortieren
        for(int i=0;i<pixel.length;i++) //alle Zeilen
        {
            for(int j=0;j<pixel[0].length;j++) //alle Spalten
            {
                //erste Position & ihren Wert speichern
                min=pixel[i][j];
                posMin = i*4+j;
                //kleinste Position ermitteln
                for(pos=i*4+j+1;pos<(pixel.length*pixel[0].length);pos++)
                {
                    if(pixel[pos/4][pos%4]<min)
                    {
                        min=pixel[pos/4][pos%4];
                        posMin=pos;
                    }
                }
                //Vertauschen
                int merke = pixel[i][j];
                pixel[i][j]=pixel[posMin/4][posMin%4];
                pixel[posMin/4][posMin%4]=merke;
            }
        }
        boolean check;
        int zaehler = 1;
        anz[0][0] = pixel[0][0];
        anz[0][1] = 1;
        //Anzahl der einzelnen Zahlen bestimmen
        for (int i = 0; i < pixel.length; i++) {

            for (int j = 0; j < pixel[0].length; j++) {
                if (!(i == 0 && j == 0)) //erstes Elem
                {

                    check = false;
                    for (int k = 0; k < anz.length; k++) {
                        if (anz[k][0] == pixel[i][j]) {
                            anz[k][1]++;
                            check = true;
                        }
                    }
                    if (check == false) {
                        anz[zaehler][0] = pixel[i][j];
                        anz[zaehler][1] = 1;
                        zaehler++;
                    }
                }                
            }
        }
        //Ausgabe 
        System.out.println("Zahl\tAnzahl");
        for (int i = 0; i < anz.length; i++) {
            if(anz[i][0]!=0)
            {
                System.out.print(anz[i][0]+"\t");
                System.out.print(anz[i][1]);
                System.out.println(); 
            }

        }
    }
}
