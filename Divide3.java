import java.util.*;

class Divide3 {

int[] a,b;
int n,m,k;

Divide3(){
Scanner sc=new Scanner(System.in);
System.out.print("Nr de elemente din a: ");
n=sc.nextInt();
a=new int[n+1];
System.out.println("Introduceti elementele");
for(int i=1;i<=n;i++)
   a[i]=sc.nextInt();
System.out.print("Nr de elemente din b: ");
m=sc.nextInt();
b=new int[m+1];
System.out.println("Introduceti elementele");
for(int i=1;i<=m;i++)
   b[i]=sc.nextInt();
System.out.print("K este:");
k=sc.nextInt();
}

int pozitie(int []x, int xs, int xd, double val){//functia returneaza al catalea element e val(care provine din vectorul x) in vectorul y    
if(xs==xd){
   if(val<=x[xs])
     return xs;
   else
     return xs+1;
   }
int xm=x[(xs+xd)/2];
if(val<=xm)
  return pozitie(x,xs,(xs+xd)/2,val);
else
  return pozitie(x,(xs+xd)/2+1,xd,val);
}

void cauta(int []u, int[]v, int us, int ud, int vs, int vd, int k){
double um;
if(us==ud)
um=u[us];
else
um=u[(us+ud)/2];
int poz=pozitie(v,vs,vd,um);
int maimici=poz-1;//aflu cate elemente din al doilea vector sunt mai mici decat mediana primului
if((ud+us)/2+maimici==k)
     System.out.print("Elementul cu statistica de ordine "+k+" este "+u[(us+ud)/2]); 
else
{
if((us+ud)/2+maimici>k)
    cauta(v,u,vs,poz,us,(us+ud)/2,k);//la fiecare pas injumatateste primul vector dat ca parametru, iar ordinea vectorilor dati ca parametri se interschimba
else
{
    cauta(v,u,poz,vd,(us+ud)/2,ud,k);
}
}
}

void afisare(int []x, int nr){
for(int i=1;i<=nr;i++)
   System.out.print(x[i]+" ");
}

 public static void main(String []args){
 Divide3 ob=new Divide3();
 if(ob.k>ob.n+ob.m){
    System.out.print("Nu exista elementul in vectori");
    return;
 }
 ob.cauta(ob.a,ob.b,1,ob.n,1,ob.m,ob.k);
 
 
}
}