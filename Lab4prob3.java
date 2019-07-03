import java.util.*;

class C1 implements Comparable<C1>{           
     public int info;                           //fiecare obiect din C1 contine o valoare de tip int  
     C1(int i){this.info=i;}
     public int compareTo (C1 o){               //definirea metodei din interfata
        if(this.info<o.info)
                return -1;
        if(this.info==o.info)
                return 0;
        return 1;
        }
    public int getInfo(){return info;}                //metoda care returneaza valoarea variabilei declarate
}


class C2 extends C1 implements Comparable<C1>{
        int info2;                                    //contine o variabila in plus
        C2(int i, int j){
        super(i);
        this.info2=j;
        }
        public int compareTo(C2 o){                   //un mod de a rescrie functia din interfata
        if(this.info2<o.info2)
                return -1;
        if(this.info2==o.info2)
                return 0;
        return 1;
        }
}

class Binar<T extends Comparable<? super T>>{

   Binar(){}
   class Nod{
     T val;
     Nod st;
     Nod dr;
     Nod(){val=null;}
      Nod(T ob){
         val=ob;
      }
     }
   Nod rad;

   public void add(T ob){
   Nod p=new Nod(ob);
   if(rad == null)
         rad = p;
   else{
        Nod aux=rad;
        Nod q;
        while(true){
             q=aux;
             if(p.val.compareTo(aux.val)<0){
                   aux=aux.st;
                   if(aux==null){
                   q.st=p;
                   return;
                   }
              }
             else{
                   aux=aux.dr;
                   if(aux==null){
                   q.dr=p;
                   return;
                   }
                  }
               } 
          } 
  }

   public void cauta(T ob){
     Nod q=rad;
     while(true){
         if(q == null){
            System.out.println("Obiectul nu a fost gasit");
            return;
            }
         if(q.val.compareTo(ob)==0){
            System.out.println("Obiectul a fost gasita");
            return;
            }
         
         if(ob.compareTo(q.val)<0){
             q=q.st;
             continue;
             }
         if(ob.compareTo(q.val)>0){
             q=q.dr;
             continue;
             }
         }
   }


   public static void main(String[] args){
   System.out.println("Ne aflam in arborele C1");
   Binar<C1> Ob=new Binar<C1>();
   C1 o1=new C1(1);
   C1 o2=new C1(3);
   C1 o3=new C1(5);
   C1 o4=new C1(7);
   C1 o5=new C1(-21);
   Ob.add(o1);
   Ob.add(o2);
   Ob.add(o3);
   Ob.cauta(o1);
   Ob.cauta(o2);
   Ob.cauta(o3);
   Ob.cauta(o4);
   Ob.cauta(o5);
   System.out.println(" ");

   System.out.println("Ne aflam in arborele C2");
   Binar<C2> Ob2=new Binar<C2>();
   C2 p1=new C2(1,2);
   C2 p2=new C2(3,4);
   C2 p3=new C2(5,6);
   C2 p4=new C2(7,8);
   C2 p5=new C2(9,10);
   Ob2.add(p1);
   Ob2.add(p2);
   Ob2.add(p3);
   Ob2.cauta(p1);
   Ob2.cauta(p2);
   Ob2.cauta(p3);
   Ob2.cauta(p4);
   Ob2.cauta(p5);
   System.out.println(" ");

 }
}
                   
                            
                                



  

   