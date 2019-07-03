import java.util.*;
import java.io.*; 

class Dinamic3{
static int minim_absolut=999;    //minim absolut va retine cea mai mica descompunere pe care a gasit-o pe masura ce a generat solutii
static int[] solutie=new int[999]; //aici retin descompunerea
public static void procedura(int n,int min,int lungime, String[]s, String binar, int[] predecesor, int pred, int[] val){
predecesor[lungime]=pred; //in predecesor retin indicele secventei de dinaintea pasului curent
if(binar.length()==0 && lungime < min)      //in acest if intra doar daca sirul pe care incerc sa il descompun are lungimea 0
      min=lungime;
      if(min < minim_absolut)
             {minim_absolut=min;
              for(int i=0;i<minim_absolut;i++)
                  solutie[i]=predecesor[i];
              solutie[minim_absolut]=pred;
             }

if(val[binar.length()]!=0)
   procedura(n,val[binar.length()],val[binar.length()],s,"",predecesor,pred,val);

int i,j,ok,nr,x;
String binar_nou;  //in acest string obtin subproblema
for(i=0;i<n+2;i++){
    if(s[i].length() > binar.length())
        continue;
    ok=1;
    for(j=0;j<s[i].length();j++)               //caut descompuneri posibile
        if(s[i].charAt(j) != binar.charAt(j)){
              ok=0;
              break;
              }
    if(ok==1)           //daca am gasit o descompunere posibila, imi generez subproblema extragand din sirul de biti secventa gasita
       {char[] ch1= binar.toCharArray();
        char[] ch2= new char[binar.length()-s[i].length()];
       for(j=s[i].length();j<binar.length();j++){
           x=j-s[i].length();
           ch2[x]=ch1[j];
           }
    binar_nou=new String(ch2);
    procedura(n,min,lungime+1,s,binar_nou,predecesor,i,val); //i devine predecesor pentru urmatorul pas
    val[binar.length()]=minim_absolut;
    }
    }
}
 
public static void main(String[] args){
try{
    Scanner scFisier=new Scanner(new File("binar.txt"));
    Scanner sc=new Scanner(System.in);
    int n,i;
    System.out.print("Nr secvente: ");
    n=sc.nextInt();
    String[] s=new String[n+2];
    String binar;
    System.out.println("Introduceti secventele:");
    for(i=0;i<n;i++)
        s[i]=sc.next();
    s[n]="0";
    s[n+1]="1";
    binar=scFisier.next();
    for(i=0;i<n+2;i++)
        System.out.print(s[i]+" ");
    System.out.print(binar);
    System.out.println();
    scFisier.close();
    int lungime=0;
    int []predecesor= new int[binar.length()+1];
    int []val= new int[binar.length()+1];
    procedura(n,minim_absolut,lungime,s,binar,predecesor,-1,val);
    System.out.println("Descompunerea minima: "+ minim_absolut);
    for(i=1;i<=minim_absolut;i++)
        System.out.print(solutie[i]+" ");  //Solutie generata afisand indicele sirului de biti corespunzator
    }
    catch (FileNotFoundException fnf){
         System.out.print("Fisier inexistent");
    }
 }
}
