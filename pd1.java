import java.util.*;

class Dinamic1 implements Comparable<Dinamic1>{
	
int l,c,indice,lmax;
static int x=1;	
Dinamic1(){
	Scanner sc=new Scanner(System.in);
	System.out.print("Lungimea este ");
	l=sc.nextInt();
	System.out.print("Culoarea este ");
	c=sc.nextInt();
	indice=x; //am folosit indice deoarece cuburile se amesteca in urma sortarii, asa pot tine o evidenta asupra lor
	x++;
}

public int compareTo(Dinamic1 o){
	if(this.l < o.l)
		return -1;
	if(this.l == o.l)
		return 0;
	return 1;
}

public String toString(){
	return "Cub "+x+" ";
}

static void afisare(int[]pred, int val)
{
	if(pred[val]==0)
		return;
	afisare(pred,pred[val]);
	System.out.println(pred[val]+" ");
}
	

public static void main(String[] args){
	
	int n,i,max,j,p,total=0;
	int []pred;
	int []plasat;//in plasat va retine in cate moduri poate fi pus un cub, ne ajuta la a determina nr total de solutii
    Scanner sc=new Scanner(System.in);
    System.out.print("Numarul de cuburi este ");
    n=sc.nextInt();
	System.out.print("Numarul de culori este ");
	p=sc.nextInt();
	pred=new int[n+1];
	plasat=new int[n+1];
    Dinamic1[] cub=new Dinamic1[n];
	System.out.println("Citim cuburile");
	for(i=0;i<n;i++)
		cub[i]=new Dinamic1();
	Arrays.sort(cub);
  
   int []h=new int[n];//retinem lungimea maxima obtinuta ptr cubul i
   h[0]=cub[0].l;
   max=h[0];
   plasat[0]=1;
   pred[cub[0].indice]=0;
   for(i=1;i<n;i++){
	   for(j=0;j<i;j++){
		   if(cub[i].l > cub[j].l && cub[i].c!=cub[j].c){
			   if(h[j]+cub[i].l > max)
			   {max=h[j]+cub[i].l;
		        pred[cub[i].indice]=cub[j].indice;
				plasat[i]=plasat[j];
			   }
		   }
			   else{
			   if(h[j]+cub[i].l==max && cub[j].c!=cub[i].c){
				   plasat[i]=plasat[i]+plasat[j];
			   }
			   
			   else
				   if(cub[i].l==cub[j].l)
					   plasat[i]++;
			   }
	   }			
	   			
		if(max==h[i])
			pred[cub[i].indice]=0;
		else
			h[i]=max;
		
		
		
   }
   
   max=0;
   int ind=0;
   for(i=0;i<n;i++)
	   if(h[i]>max)
	   {max=h[i];
        ind=i;
	   }
   for(i=0;i<n;i++)
	   if(max==h[i])
		   total=total+plasat[i];
	
	
	System.out.println("Inaltimea maxima este "+max);
	System.out.println("Turnul este:");
    afisare(pred,cub[ind].indice);
	System.out.println(cub[ind].indice);
	

	System.out.println("Solutii: "+total);
	
}
}