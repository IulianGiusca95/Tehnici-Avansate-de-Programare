import java.util.*;

class Text implements Comparable<Text>{
static int ind=0;
int lungime;                          //retine lungimea textului          
int frecventa;                        //retine de cate ori apare
int total;                            //in total retinem numarul de caractere parcurse
int indice;
Text(){
 lungime=0;
 frecventa=0;
 total=0;
 indice=0;
}
Text(int i, int j){
 lungime=i;
 frecventa=j;
 total = lungime * frecventa;
 ind=ind+1;
 indice=ind;
}

public String toString(){
   return "Textul "+indice;
}

public int compareTo(Text o){          //efectueaza o sortare dupa total
 if(this.total < o.total)
    return -1;
 if(this.total == o.total)
    return 0;
 return 1;
}
}
class Texte{

public static void main(String[] argv){

Scanner sc= new Scanner(System.in);
System.out.println("Numarul de texte este: ");
int nr_text=sc.nextInt();
Text[] t=new Text[nr_text];
int i,l,f;
for(i=0;i<nr_text;i++){
   System.out.print("Lungimea textului "+(i+1)+" este ");
   l=sc.nextInt();
   System.out.print("Frecventa textului "+(i+1)+" este ");
   f=sc.nextInt();
   t[i]=new Text(l,f);
}
Arrays.sort(t);                            
for(i=0;i<nr_text;i++)
   System.out.println(t[i]);
}
}
   