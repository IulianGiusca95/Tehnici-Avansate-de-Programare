import java.util.*;

class Sum {

 public static void main(String argv[])

 {
   int v[]={ 3, 1, 2, -5, -2, 10, 7, 3 };   
   int x,y,z,j,k,i,ok=0;
   Arrays.sort(v);                   //metoda din clasa Arrays care sorteaza v
   for (i=0;i<=v.length-3;i++)
       {
         x=v[i];                    //x retine primul numar de adunat
         j=i+1;                     //j retine indicele care va parcurge vectorul de la stanga spre dreapta
         k=v.length-1;              //k retine indicele care va parcurge de la dreapta spre stanga
         while (j<k)                //cat timp j e in stanga lui k
            {
              y=v[j];               //y si z iau valorile celorlalte 2 numere
              z=v[k];
              if(x+y+z==0)          //daca suma e 0, am gasit triplet
                 {
                  System.out.println("Triplet gasit: "+x+" "+y+" "+z);
                  ok=1;
                  j++;
                  k--;
                 }
              if(x+y+z>0)           //daca suma > 0, trebuie sa decrementam dreapta
                   k--;
              if(x+y+z<0)          //suma < 0 , trebuie sa incrementam j
                   j++;
            }  
       }
    if(ok==0)
       System.out.print("Nu a fost gasit niciun triplet");
 }
}