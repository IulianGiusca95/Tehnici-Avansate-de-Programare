import java.util.*;
import java.io.*;

class Graf{
 

public static void main(String argv[]){
int n;
try{
    Scanner scFisier=new Scanner(new File("numere.txt"));   //deshid fisierul
    n=scFisier.nextInt();                                   //n retine nr de varfuri
    LinkedList<Integer> []lista = new LinkedList[100];
    for(int i=1;i<=n;i++)
         lista[i]=new LinkedList<Integer>();
    int a,b;                                                //a si b reprezinta capetele muchiilor
    while (scFisier.hasNext()){
    a=scFisier.nextInt();
    b=scFisier.nextInt();
    lista[a].add(b);
    lista[b].add(a);
    }
    scFisier.close();
    Scanner sc=new Scanner(System.in);
    System.out.println("Nodul de start este: ");            
    int nod,nod2,nod3;
    int[] m= new int[100];                                  //vector de marcaj, pozitia nodului devine 1 daca a fost vizitat
    nod=sc.nextInt();                                       //nod este nodul de start
    System.out.print(nod+" ");
    m[nod]=1;
    LinkedList<Integer> lista2= new LinkedList<Integer>();  //lista in care voi introduce varfurile pe masura ce le descopar
    while(lista[nod].peek()!=null)                          //cat timp lista nodului initial nu e goala
        {
          nod2=lista[nod].poll();                           //nod2 retine valoarea varfului gasit in lista nodului initial
          lista2.add(nod2);                                 //il adaug in lista de varfuri nou gasite
          m[nod2]=1;                                        //il marchez pentru a nu il citi de mai multe ori
          System.out.print(nod2+" ");
        }
    while(lista2.peek()!=null)                              //cat timp lista de varfuri gasite nu e goala
        {
          nod2=lista2.poll();                               //extrage primul varf
          while(lista[nod2].peek()!=null){                  //executa cautarea in lista de vecini a nodului extras
                nod3=lista[nod2].poll();                    
                if(m[nod3]==0)                              //va adauga un vecin la capatul listei doar daca nu a fost marcat anterior
                 {
                   m[nod3]=1;
                   System.out.print(nod3+" ");
                   lista2.add(nod3);
                 }
          }
         }
          
    
}
catch (FileNotFoundException fnf){
    System.out.println("Fisier inexistent");
}
}
}