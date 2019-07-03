import java.util.*;

class Tabel{

int [][]t;
int n,nr,lg,cg;
int piesa=1;

void citire()
{
Scanner sc=new Scanner(System.in);
System.out.print("N ia valoarea: ");
n=sc.nextInt();
double nr_linii=Math.pow(2,n);        
nr=(int)nr_linii;
t=new int[nr+1][nr+1];               //am creat matrice patratica cu  2^n linii si coloane
System.out.println("Coordonatele spatiului gol: ");
System.out.print("Linia:");
lg=sc.nextInt();
System.out.print("Coloana:");
cg=sc.nextInt();
t[lg][cg]=0;
}

void completeaza(int ls,int ld, int cs, int cd, int lg, int cg)
{
 int lm,cm;
 lm=(ls+ld)/2;
 cm=(cs+cd)/2;
 if(lg <= lm)
 {
 if(cg <= cm)
      {
       t[lm][cm+1]=piesa;
       t[lm+1][cm]=piesa;
       t[lm+1][cm+1]=piesa;
       piesa=piesa+1;
       if(lm==ls || cm==cs)
              return;
       completeaza(ls,lm,cs,cm,lg,cg);
       completeaza(ls,lm,cm+1,cd,lm,cm+1);
       completeaza(lm+1,ld,cs,cm,lm+1,cm);
       completeaza(lm+1,ld,cm+1,cd,lm+1,cm+1);
      }
 else
     {
       t[lm][cm]=piesa;
       t[lm+1][cm]=piesa;
       t[lm+1][cm+1]=piesa;
       piesa=piesa+1;
       if(lm==ls || cm==cs)
               return;
       completeaza(ls,lm,cs,cm,lm,cm);
       completeaza(ls,lm,cm+1,cd,lg,cg);
       completeaza(lm+1,ld,cs,cm,lm+1,cm);
       completeaza(lm+1,ld,cm+1,cd,lm+1,cm+1);
     }
 }
 else
     {
 if(cg <= cm)
      {
       t[lm+1][cm+1]=piesa;
       t[lm][cm]=piesa;
       t[lm][cm+1]=piesa;
       piesa=piesa+1;
       if(lm==ls || cm==cs)
              return;
       completeaza(ls,lm,cs,cm,lm,cm);
       completeaza(ls,lm,cm+1,cd,lm,cm+1);
       completeaza(lm+1,ld,cs,cm,lg,cg);
       completeaza(lm+1,ld,cm+1,cd,lm+1,cm+1);
      }
 else
     {
       t[lm+1][cm]=piesa;
       t[lm][cm]=piesa;
       t[lm][cm+1]=piesa;
       piesa=piesa+1;
       if(lm==ls || cm==cs)
             return;
       completeaza(ls,lm,cs,cm,lm,cm);
       completeaza(ls,lm,cm+1,cd,lm,cm+1);
       completeaza(lm+1,ld,cs,cm,lm+1,cm);
       completeaza(lm+1,ld,cm+1,cd,lg,cg);
     }
 }
 } 
void afiseaza()
{
  for(int i=1;i<=nr;i++){
  for(int j=1;j<=nr;j++)
      System.out.print(t[i][j]+" ");
  System.out.println("");
  }
}
   

public static void main(String []args){
Tabel ob=new Tabel();
ob.citire();
ob.completeaza(1,ob.nr,1,ob.nr,ob.lg,ob.cg);
ob.afiseaza();
  }
}