import java.util.*;

class Activitate implements Comparable<Activitate>{
int profit;
int deadline;
int indice;
static int ind=1;

Activitate(){}

Activitate(int i, int j){
profit=i;
deadline=j;
indice=ind++;
}

public String toString(){
 return "A"+indice;
}

public int compareTo(Activitate o){
 if(this.profit < o.profit)
    return -1;
 if(this.profit == o.profit)
    return 0;
 return 1;
}

public int getDeadline(){
   return deadline;
}

public int getIndice(){
   return indice;
}

}


class Greedy3{

public static void MakeSet(int i, int[] p)
{
p[i]=0;
}

public static int FindSet(int i, int[]p)
{
if(p[i]==0)
   return i;
p[i]=FindSet(p[i],p);
return p[i];
}

public static void Union(int i, int j, int[]p)
{
i=FindSet(i,p);
j=FindSet(j,p);
if(i<j)
   p[j]=i;
else
   p[i]=j;
}

public static void main(String[] arg){
Scanner sc=new Scanner(System.in);
int nr_a,p,d,i,j,maxdeadline,ok=1;
System.out.print("Numarul de activitati e ");
nr_a=sc.nextInt();
Activitate[] a=new Activitate[nr_a];          //vectorul de activitati
Activitate[] b=new Activitate[nr_a];          //vectorul unde sunt plasate activitatile

for(i=0;i<nr_a;i++){
   System.out.print("Profitul activitatii "+(i+1)+" este ");
   p=sc.nextInt();
   System.out.print("Deadline-ul activitatii "+(i+1)+" este ");
   d=sc.nextInt();
   a[i]=new Activitate(p,d);
  }
Arrays.sort(a);

int []pr= new int[nr_a];
for(i=0;i<nr_a;i++)
    MakeSet(i,pr);
for(i=nr_a-1;i>=0;i--)
{
 int x=FindSet(a[i].getDeadline(),pr);
 System.out.println(x);
 if(x-1<0)
   continue;
 if(x-1==0 && ok==1){
 b[x-1]=a[i];
 ok=0;
 }
 if(x-1!=0)
 {b[x-1]=a[i];
 }
 Union(x,x-1,pr);
}
System.out.println("Solutia generata este: ");
for(i=0;i<nr_a;i++)
  System.out.print(b[i]+" ");

}
}
