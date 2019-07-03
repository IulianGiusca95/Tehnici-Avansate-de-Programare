import java.util.*;

class Text2 implements Comparable<Text2>{
int lungime;                               //retinem lungimea textului
int indice;
static int ind=0;

Text2(){
 lungime=0;
 indice=0;
}
Text2(int i){
 lungime=i;
 ind=ind+1;
 indice=ind;
}

public String toString(){
 return "Text "+indice;
}

public int compareTo(Text2 o){               //suprascriem metoda dupa lungime
 if(this.lungime < o.lungime)
    return -1;
 if(this.lungime == o.lungime)
    return 0;
 return 1;
}
}

class Greedy2b{

public static void main(String [] arg){

Scanner sc=new Scanner(System.in);
int nr_text,nr_benzi,i,l,poz,contor;
System.out.print("Numarul de texte este ");
nr_text=sc.nextInt();
System.out.print("Numarul de benzi este ");
nr_benzi=sc.nextInt();
if(nr_benzi==0){
   System.out.println("Nu se poate");                      //daca numarul de benzi este 0, terminam executia
   return;
   }
Text2[] t=new Text2[nr_text];                             //creez vector de obiecte ptr fiecare text citit
Text2[][] b=new Text2[nr_benzi][nr_text+1];               //creez o matrice, in care fiecare linie reprezinta banda    
for(i=0;i<nr_text;i++){
  System.out.print("Lungimea textului "+(i+1)+" este ");
  l=sc.nextInt();
  t[i]=new Text2(l);
}
Arrays.sort(t);
contor=0;
poz=0;
for(i=0;i<nr_text;i++){
    b[i%nr_benzi][poz]=t[i];                               
    contor++;
    if(contor==nr_benzi){
       contor=0;
       poz=poz+1;
     }
}
for(i=0;i<nr_benzi;i++){
   poz=0;
   System.out.print("Banda "+(i+1)+":");
   while(b[i][poz]!=null){
        System.out.print(b[i][poz]+"/");
        poz++;
        }
   System.out.println("");
    }
}
}
  
  