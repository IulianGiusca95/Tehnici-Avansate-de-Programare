import java.util.*;
import java.io.*;

class Cuvinte {
 public static void main(String args[]) {
  try{
    Scanner sf= new Scanner(new File("cuvinte.txt"));
    System.out.println("Se citesc cuvintele din fisier: ");
    String p= sf.nextLine();                               //in p se retine informatia din fisier
    String [] words= p.split("[ ,]");                      //separ cuvintele dupa spatiu sau virgula
    HashMap hm = new HashMap();              
    int nr;
    for(int i=0;i<words.length;i++){                       
        if(words[i].compareTo("")==0)                      //sare peste cuvantul vid
           continue;
        nr=1;                                              //va retine numarul de aparitii
        for(int j=0;j<i;j++)
            if(words[i].compareTo(words[j])==0)            //compara fiecare cuvant cu cele dinaintea lui, si incrementeaza nr de cate ori l-a gasit pana in punctul respectiv
              nr++;
        hm.put(words[i],nr);                               //asociaza cuvantului curent cheia nr; valoarea cheii se modifica pe parcurs daca e cazul
    }
    Set set = hm.entrySet();
    Iterator iterator = set.iterator();
    while(iterator.hasNext()){
       Map.Entry map= (Map.Entry)iterator.next();
       System.out.println("Cuvantul: "+map.getKey() + " apare de "+map.getValue()+" ori");
    }
  }
  catch(FileNotFoundException fnf){
        System.out.println("Fisierul e inexistent");
  }
  }
}