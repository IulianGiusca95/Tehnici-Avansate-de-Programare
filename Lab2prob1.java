import java.util.*;

class Propozitie
{
public static void main(String args[])
{
 Scanner sc= new Scanner(System.in);     //sc va fi folosit ptr citirea de la tastatura
 System.out.print("Propozitia este ");   
 String p=sc.nextLine();                 //citim de la tastatura
 System.out.println(p);
 String[] words = p.split("[ ,.]");      //in cate un camp din words punem fiecare cuvant delimitat de separatori
 for(int i=0;i<words.length-1;i++)
     for(int j=i+1;j<words.length;j++)
         if(words[i].compareTo(words[j]) >0)   //sortam lexicografic cuvintele
              {
                String aux = words[i];
                words[i] = words[j];
                words[j] = aux;
              }
 for(int i=0;i<words.length;i++)
     System.out.println(words[i]);     //printam cuvintele ordonate lexicografic
}
}