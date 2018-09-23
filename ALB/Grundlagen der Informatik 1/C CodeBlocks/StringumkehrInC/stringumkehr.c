#include <stdio.h>
#include "stringumkehr.h"
void stringumkehr(char* str)
{
    int anz;
    while(str)
    {
        anz++;
    }
    for(int i=0;i<anz/2;i++)
    {
        char temp = str[i];
        str[i] = str[anz-1-i];
        str[anz-1-i] = temp;
    }
}
