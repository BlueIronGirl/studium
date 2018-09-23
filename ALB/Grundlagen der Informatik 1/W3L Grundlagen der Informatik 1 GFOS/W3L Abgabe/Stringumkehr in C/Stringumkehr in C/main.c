#include <stdio.h>
#include <stdlib.h>
#include "stringumkehr.h"

int main()
{
    char zeile[]="Alice Bedow";
    printf("%s\n",zeile);
    stringumkehr(zeile);
    printf("%s\n",zeile);
    return 0;
}
