#include <stdio.h>
#include <stdlib.h>

int main()
{
    int laenge=128;
    char *ascii = malloc(sizeof(char)*laenge);
    for(int i=0;i<laenge;i++)
    {
        ascii[i] = i;
    }
    printf("Id\tWert\n");
    for(int i=32;i<=126;i++)
        printf("%i\t%c\n",i,ascii[i]);
    free(ascii);
    return 0;
}
