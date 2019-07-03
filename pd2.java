import java.util.*;

class Dinamic2{
	
	static void afisare(int []pred,int val){
		if(pred[val]==0)
			return;
		afisare(pred,pred[val]);
		System.out.print(pred[val]+" ");
	}
	public static void main(String[] args){
		
	int[][] a,b,drum;
	int n,m,i,j;
	int []pred;
	Scanner sc=new Scanner(System.in);
	System.out.print("Linii: ");
	n=sc.nextInt();
	System.out.print("Cloane: ");
	m=sc.nextInt();
	a=new int[n][m];
	b=new int[n][m];
	drum=new int[n][m];
	pred=new int[n*m+1];
	
	for(i=0;i<n;i++)
		for(j=0;j<m;j++)
			a[i][j]=sc.nextInt();
	int marcat=1;
	for(i=n-1;i>=0;i--)
		for(j=0;j<m;j++){
			drum[i][j]=marcat;
		    marcat++;
		}
	
	b[n-1][0]=a[n-1][0];
	pred[drum[n-1][0]]=0;
	for(i=1;i<m;i++)
	{b[n-1][i]=b[n-1][i-1]+a[n-1][i];
     pred[drum[n-1][i]]=drum[n-1][i-1];
	}
	for(i=n-2;i>=0;i--)
	{b[i][0]=b[i+1][0]+a[i][0];
    pred[drum[i][0]]=drum[i+1][0];
	}
	
	
	for(i=n-2;i>=0;i--)
		for(j=1;j<m;j++)
			if(b[i][j-1] > b[i+1][j])
			{b[i][j]=a[i][j]+b[i][j-1];
		     pred[drum[i][j]]=drum[i][j-1];
			}
			else
			{b[i][j]=a[i][j]+b[i+1][j];
		     pred[drum[i][j]]=drum[i+1][j];
			}
	System.out.println("Aceasta e matricea sumelor maxime obtinuta ptr fiecare unitate din matrice");
	for(i=0;i<n;i++){
		for(j=0;j<m;j++)
			System.out.print(b[i][j]+" ");
		System.out.println("");
	}
	System.out.println("");
	System.out.println("Considerand matricea numerotata de forma");
	for(i=0;i<n;i++){
		for(j=0;j<m;j++)
			System.out.print(drum[i][j]+" ");
		System.out.println("");
		}
	System.out.print("Drumul optim este ");
	afisare(pred,drum[0][m-1]);
	System.out.print(drum[0][m-1]);
	
	}
}