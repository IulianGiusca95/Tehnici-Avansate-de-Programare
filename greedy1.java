import java.util.*;

class Cub implements Comparable<Cub>{
   int culoare;
   int lungime;
   int indice;
   static int x=0;
   Cub(){
      culoare=0;
      lungime=0;
      indice=0;
     }
   Cub(int a, int b){
      x++;
      lungime=a;
      culoare=b;
      indice=x;
     }
   public String toString(){
       return "Cub "+indice+" ";
     }
   public int compareTo(Cub o){
       if(this.lungime < o.lungime)
               return -1;
       if(this.lungime == o.lungime)
               return 0;
       return 1;
       }
    }
class Greedy1{

 public static void main(String[] arg){

 Scanner sc=new Scanner(System.in);
 System.out.print("Numarul de cuburi este ");
 int n,l,color,i,p;
 n=sc.nextInt();
 System.out.print("Numarul de culori este ");
 p=sc.nextInt();
 Cub []c=new Cub[n];
 for(i=0;i<n;i++){
     System.out.println("Cub "+(i+1)+":");
     System.out.print("Lungimea este ");
     l=sc.nextInt();
     System.out.print("Culoarea este ");
     color=sc.nextInt();
     c[i]=new Cub(l,color);
     }
  Arrays.sort(c);
  System.out.print(c[n-1]);
  color=c[i-1].culoare;
  for(i=n-2;i>=0;i--){
      if(c[i].culoare==color)
          continue;
      color=c[i].culoare;
      System.out.print(c[i]);
      }
}
 
}
  
  