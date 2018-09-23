#include<stdio.h>
#include"stringumkehr.h"
void stringumkehr(char* str)
{
    //int anz = sizeof(str);
    int anz=0;
    int i=0;
    while(str[anz]) //Stringlänge bestimmen
    {
        anz++;
    }
    int posEnd=anz-1;
    for(i=0;i<(anz/2);i++)
    {
        //tauschen
        unsigned temp = str[i];
        str[i]=str[posEnd];
        str[posEnd]=temp;
        posEnd--;
    }

}
