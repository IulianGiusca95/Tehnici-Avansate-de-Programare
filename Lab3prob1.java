import java.util.*;

class Complex{

 int re, im;                                            //variabile ptr partea reala/imaginara
 Complex(){}                                            //constructor fara parametri
 Complex(int a, int b) {this.re=a;this.im=b;}           //constructor parametrizat
 public String toString() {return ""+re+"+i*"+im;}      //rescriu toString
 public boolean equals(Object o) {                      //rescriu equals
  if(o==null)
       return false;
  if(o.getClass() != this.getClass())
       return false;
  Complex p = (Complex)o;
  return ((re == p.re) && (im== p.im));
 }
  public int hashCode() {                                //rescriu hashCode
    int rez=17;
    rez=rez*31 + re;
    rez=rez*31 + im;
    return rez;
 }
}

class ExpComplex{
 public static void main(String args[]) {
   Complex c1=new Complex(1,2);                           //imi creez 3 obiecte pe care testez operatiile
   Complex c2=new Complex(3,4); 
   Complex c3=new Complex(1,2); 
   Complex[] v={c1,c2,c3};                                //Colectia de obiecte
   for(int j=0;j<3;j++)
           System.out.println("c"+(j+1)+" este "+v[j].toString());
   System.out.println("c1=c2 "+v[0].equals(v[1]));
   System.out.println("c1=c3 "+v[0].equals(v[2]));
   int x,y,z;
   x=v[0].hashCode();
   y=v[1].hashCode();
   z=v[2].hashCode();
   System.out.println("Hashcode pentru c1 este "+x);
   System.out.println("Hashcode pentru c2 este "+y);
   System.out.println("Hashcode pentru c3 este "+z);
   
}
}
   