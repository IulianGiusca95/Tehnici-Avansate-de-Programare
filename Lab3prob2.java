class Clasa1{                     
static int a, b, c;
Clasa1(int x, int y, int z) {          //constructor parametrizat
 this.a=x;
 this.b=y;
 this.c=z;
}
 Clasa1(){}                            //constructor fara parametri
static void crescNr(){                 
a++;
b++;
c++;
}
void afisare(){                        //afiseaza elementele statice
 System.out.println(a+" "+b+" "+c);   
}
void pozitie(){System.out.println("Ne aflam in clasa 1");}        //metoda ce verifica in ce clasa ne aflam
public int suma(){                        //efectueaza suma variabilelor statice
   return this.a + this.b + this.c; 
}
public int produs(){                      //efectueaza produsul
   return this.a * this.b * this.c;
}

}

class Clasa2 extends Clasa1{
static int d;                             //am adaugat variabila d
Clasa2(int x, int y, int z, int w){
 super(x,y,z);
 this.d=w;
}
Clasa2(){}
static void crescNr(){
a=a+2;
b=b+2;
c=c+2;
d=d+2;
}
void afisare(){
 System.out.println(a+" "+b+" "+c+" "+d);
}
void pozitie(){System.out.println("Ne aflam in clasa 2");}

public int suma(){
   return this.a + this.b + this.c + this.d;
}

}

class OpClase{
public static void main(String args[])
{
 Clasa1 m= new Clasa1(1,2,3);
 Clasa2 n= new Clasa2(2,4,6,8);
 m.pozitie();
 n.pozitie();
 m.afisare();
 n.afisare();
 System.out.println("Suma1:"+m.suma());
 System.out.println("Suma2:"+n.suma());
 System.out.println("Produs1:"+m.produs());
 System.out.println("Produs2:"+n.produs());//nu am rescris metoda pentru produs 2, o apeleaza pe cea din Clasa1
 m.crescNr();
 m.afisare();
 n.afisare();
 n.crescNr();
 m.afisare();
 n.afisare();
 Clasa1 p=new Clasa2();
 p.pozitie();
 p=n;
 System.out.println("Suma3:"+p.suma());
}
}
 