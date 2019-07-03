import java.util.*;

class Dinamic4{
	
	
	public static int optim(char[]a,int i,char[]b,int j,int [][]s, int[][]op)
	{
		int p=0,min1,min2,t;
		
		if(i==-1)
		{
			if(j!=-1){
				for(t=0;t<=j;t++)
				{p=p+2;
				}
			    return p;
			}
			return 0;
		}
		
		if(j==-1)
			{
			if(i!=-1){
				for(t=0;t<=i;t++){
					p=p+2;
				    
				}
			    return p;
			}
			return 0;
		}
		
		if(s[i][j]!=-1)
			return s[i][j];
		
		if(a[i]==b[j])
			p=0;
		else{
		if((a[i]=='A' && b[j]=='C') ||(a[i]=='C' && b[j]=='A') ||(a[i]=='G' && b[j]=='T') ||(a[i]=='T' && b[j]=='G'))
			p=1;
		else
			p=3;
		}
		int x,y,z,w;
		x=optim(a,i-1,b,j-1,s,op);
		p=p+x;
		y=optim(a,i,b,j-1,s,op);
		min1=2+y;
		z=optim(a,i-1,b,j,s,op);
		min2=2+z;
		w=x;
		if(min1<p){
			p=min1;
			w=y;
			
		}
		if(min2<p){
			p=min2;
			w=z;
			
		}

		op[i][j]=w;
		s[i][j]=p;
		return p;
	}
	
	static void solutie(char[]a, char[]b, int [][]s,int[][]op, int i, int j,int oldi,int oldj)
	{
		int ok=0,k,valoare=0;
		if(i==-1 || j==-1)
		{
			if(i==-1 && j!=-1)
				for(k=0;k<=j;k++)
					System.out.println("- "+b[k]);
			if(j==-1 && i!=-1)
				for(k=0;k<=i;k++)
					System.out.println(a[k]+" -");
			return;
		}
		int i1, j1;
		int operatie=s[i][j]-op[i][j];
		if(operatie==1 || operatie==3 || operatie==0)
		{
			//System.out.println(a[i]+" "+b[j]);
			valoare=1;
			i1=i-1;
			j1=j-1;
			solutie(a,b,s,op,i1,j1,i,j);
		}
		else
		{
			if(i-1>=0 && (s[i-1][j]==op[i][j]) || (op[i][j]==0))
			{
				//System.out.println(a[i]+" "+"-");
				valoare=2;
				i1=i-1;
				j1=j;
				ok=1;
				solutie(a,b,s,op,i1,j1,i,j);
			}
			if(j-1>=0 && (s[i][j-1]==op[i][j]) || (op[i][j]==0))
			{
				//System.out.println("- "+b[j]);
				valoare=3;
				i1=i;
				j1=j-1;
				ok=1;
				solutie(a,b,s,op,i1,j1,i,j);
			}
			if(ok==0)
			{
				if(i==oldi)
					//System.out.println("- "+b[j]);
				      valoare=3;
				if(j==oldj)
					//System.out.println(a[i]+" "+"-");
				      valoare=2;
					
					
			}
		}
		if(valoare==1)
			System.out.println(a[i]+" "+b[j]);
		if(valoare==2)
			System.out.println(a[i]+" "+"-");
		if(valoare==3)
			System.out.println("- "+b[j]);
			
		
	}
	
	
	
	public static void main(String[] args){
		
		Scanner sc=new Scanner(System.in);
		int i,j;
		System.out.print("Introduceti primul cuvant: ");
		char[] a;
		a=sc.next().toCharArray();
		System.out.print("Introduceti al doilea cuvant: ");
		char[] b;
		b=sc.next().toCharArray();
		int [][]s= new int[a.length][b.length];
		int [][]op= new int[a.length][b.length];

		for(i=0;i<a.length;i++)
			for(j=0;j<b.length;j++)
				s[i][j]=-1;
		
		System.out.println("Penalizarea e "+optim(a,a.length-1,b,b.length-1,s,op));
		
		System.out.println("");
		System.out.println("Matricea solutiilor subproblemelor este:");
		for(i=0;i<a.length;i++){
			for(j=0;j<b.length;j++)
				System.out.print(s[i][j]+" ");
			System.out.println("");
		}
		System.out.println("");
		System.out.println("Matricea operatiilor este:");
		for(i=0;i<a.length;i++){
			for(j=0;j<b.length;j++)
				System.out.print(op[i][j]+" ");
			System.out.println("");
		}
        System.out.println(" ");
		solutie(a,b,s,op,a.length-1,b.length-1,a.length-1,b.length-1);
	}
}