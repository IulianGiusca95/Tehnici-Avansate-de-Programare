import java.util.*;

class Divide1
{

public static int DivImp(int st, int dr, int[] v)
{
    int m,rez;
    if(st==dr)
       {if(st==v[st])
          rez=1;
        else
          rez=0;
    }
    else
    {
    if((st+dr)%2==1)
        m=(st+dr)/2;
    else
        m=(st+dr)/2-1;
    if(m<=v[m])
    rez=DivImp(st,m,v);
    else
    rez=DivImp(m+1,dr,v);
    }
return rez;
}

public static void main(String [] args){

int n,i,x;
double mid;
Scanner sc=new Scanner(System.in);
System.out.print("Numarul de elemente din vector este ");
n=sc.nextInt();
int []v=new int[n];
for(i=0;i<n;i++){
    System.out.print("Elementul de pe pozitia "+i+" este ");
    v[i]=sc.nextInt();   
  }
if(DivImp(0,n-1,v)==1)
   System.out.print("Indice gasit");
else
   System.out.print("Indicele nu a fost gasit");
}
}