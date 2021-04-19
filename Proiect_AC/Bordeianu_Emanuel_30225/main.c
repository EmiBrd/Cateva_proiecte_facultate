#include <stdio.h>
#include <stdlib.h>


int tranzitii(int n){

    if(n==0){
        return 0;
    }
    else if( n==1 ){
        return 1;
    }
    else if (n<0){
        printf("n nu poate fi mai mic decat 0\n");
        exit(0);
    }

    int x = n%2, nr=1;
    n=n/2;
    do{
        int y = n%2;
        if(x!=y){
            nr++;
        }
        x = y;
        n=n/2;
    }while(n);

    return nr;
}


int main()
{

    int n1, n2;
    printf("Introduceti n1:\n");
    scanf("%d", &n1);
    printf("Introduceti n2:\n");
    scanf("%d", &n2);
    int nr1 = tranzitii(n1);
    int nr2 = tranzitii(n2);
    printf("\nn1 are %d tranzitii\n", nr1);
    printf("n2 are %d tranzitii\n", nr2);
    if(nr1 > nr2){
        printf("n1 are mai multe tranzitii decat n2\n");
    }
    else if(nr1 == nr2){
        printf("n1 si n2 au nr egal de tranzitii\n");
    }
    else{
        printf("n2 are mai multe tranzitii decat n1\n");
    }


    return 0;
}
