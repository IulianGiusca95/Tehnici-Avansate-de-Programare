import java.util.*;
import java.lang.*;

class Dinamic6{

int x,y;
static int[] sol=new int[999];     //aici retin punctele pe care le folosesc pentru a forma diagonale
static int contor=0;
static double perimetru_minim=9999;
Dinamic6(int a, int b){
 x=a;
 y=b;
}

public String toString(){
   return "Punct "+x+" "+y;
}

public static double lungime(Dinamic6 p1, Dinamic6 p2){
    return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
}

public static double triangulare(Dinamic6[] p,int i, int j,double[][] T ){          //i < j. pentru fiecare k dintre i si j aflu care e perimetrul cel mai mic pe care il obtin
     if(T[i][j]!=0)
		 return T[i][j];
	 double min=9999,ok=0,perimetru;
     for(int k=i+1;k<j;k++){
             perimetru=lungime(p[i],p[j]);
             perimetru=perimetru+triangulare(p,i,k,T)+triangulare(p,k,j,T);
             if(perimetru < min)
                {min=perimetru;
                 ok=1;
				 T[i][j]=min;
                }
             }
     if(ok==1)      //cu ok determin daca am intrat in for sau nu. Daca nu, inseamna ca punctele i si j erau adiacente, si returnez lungimea laturii
        return min;
     return lungime(p[i],p[j]);
     
}

public static void solutie(Dinamic6[] p,int a, int b,double[][]T){     //cu aceasta functie aflu prin ce puncte trasez diagonalele
double perimetru,perimetru_minim=9999;
int vf_minim=-1,i;
for(i=a+1;i<b;i++){
perimetru=lungime(p[a],p[b]);
perimetru=perimetru + triangulare(p,a,i,T) + triangulare(p,i,b,T);
if(perimetru < perimetru_minim){
     perimetru_minim=perimetru;
     vf_minim=i; 
    }
   }
if(vf_minim ==-1)    //dupa ce am trecut de for, cu siguranta raman cu varful care determina cea mai mica pondere dintre varfurile a,b, si vf_minim
   return;           //daca vf_minim a ramas -1, inseamna ca nu a existat un vf intre a si b (a<b)
sol[contor++]=vf_minim;   //daca a existat, il includ in solutie
if(vf_minim-a>1)     //verific daca varful e adiacent cu a, daca este inseamna ca e latura a poligonului
   System.out.println("Diagonala "+a+" "+vf_minim);
if(b-vf_minim>1)     //verific daca varful e adiacent cu b, daca este inseamna ca e latura a poligonului
   System.out.println("Diagonala "+vf_minim+" "+b);
solutie(p,a,vf_minim,T);
solutie(p,vf_minim,b,T);
}

public static void main(String[] args){

int n,i,j,nr1,nr2;
double perimetru;
Scanner sc=new Scanner(System.in);
System.out.print("Numarul de puncte: ");
n=sc.nextInt();
double[][] T=new double[n][n];
Dinamic6[] p=new Dinamic6[n];
System.out.println("Dati coordonatele punctelor");
for(i=0;i<n;i++){
    System.out.print("x= ");
    nr1=sc.nextInt();
    System.out.print("y= ");
    nr2=sc.nextInt();
    p[i]=new Dinamic6(nr1,nr2);
}
for(i=1;i<n-1;i++){      //pornesc triangularea de la vf 0 si vf n-1. Ele trebuie sa apartina unui triunghi, asa ca folosesc latura aceasta ca punct de plecare
perimetru=lungime(p[0],p[n-1]);
perimetru=perimetru + triangulare(p,0,i,T) + triangulare(p,i,n-1,T);//cele 2 triangulari returneaza in parte cea mai mica pondere
if(perimetru < perimetru_minim){
     perimetru_minim=perimetru;
     T[0][n-1]=perimetru;	 
}
}

System.out.println("Perimetrul minim este " + perimetru_minim);
solutie(p,0,n-1,T); //afisez punctele care formeaza diagonale dupa indici

 }
}