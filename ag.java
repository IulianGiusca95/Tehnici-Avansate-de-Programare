import java.util.*;
import java.io.*;

class Genetic
{
	static double[] fitness=new double[200];
	static double[] fitness_mediu=new double[200];
	static int c=0;
	public static void evolutie(int[][]cromozom,int dimensiune_cromozom,double[]x,double[]f,int n, int a, int b,double prob, double mutatie, int etape,int c)
	{
		if(etape==0)
			return;
		
	int i,j,k;
	double F=0;
	for(i=0;i<n;i++)
		F=F+f[i];
	
	double[] probabilitate_selectie=new double[n];
	for(i=0;i<n;i++)
		probabilitate_selectie[i]=f[i]/F;
		
	double[]interval=new double[n+1];
	interval[0]=0;
	for(i=1;i<n+1;i++)
		interval[i]=interval[i-1]+probabilitate_selectie[i-1];
	
	int [][]cromozom_aux=new int[n][dimensiune_cromozom];
	double []x_aux=new double[n];
	double []f_aux=new double[n];
	double xr;
	for(i=0;i<n;i++)
			{
				xr=Math.random();
				for(j=0;j<n;j++)
					if(interval[j]<=xr && interval[j+1]>=xr)
					{
						for(k=0;k<dimensiune_cromozom;k++)
							cromozom_aux[i][k]=cromozom[j][k];
						x_aux[i]=x[j];
						f_aux[i]=f[j];
						break;
					}
			}
	int[] cromozomi_participanti=new int[n];
	int contor=0;
	for(i=0;i<n;i++){
		xr=Math.random();
		if(xr<prob){
		cromozomi_participanti[contor++]=i;
		}
	}
	
	int punct_taiere,aux,X10_1,X10_2;
			
			while(contor>=2){
			X10_1=0;
			X10_2=0;
			punct_taiere=(int)(Math.random()*20);
			if(punct_taiere==0 || punct_taiere==dimensiune_cromozom-1)
			{
				contor=contor-2;
				continue;
			}
				for(i=0;i<punct_taiere;i++)
				{
					aux=cromozom_aux[cromozomi_participanti[contor-1]][i];
					cromozom_aux[cromozomi_participanti[contor-1]][i]=cromozom_aux[cromozomi_participanti[contor-2]][i];
					cromozom_aux[cromozomi_participanti[contor-2]][i]=aux;
				}
				for(i=0;i<dimensiune_cromozom;i++){
					X10_1=X10_1+(int)(cromozom[cromozomi_participanti[contor-1]][i]*Math.pow(2,dimensiune_cromozom-1-i));
					X10_2=X10_2+(int)(cromozom[cromozomi_participanti[contor-2]][i]*Math.pow(2,dimensiune_cromozom-1-i));
				}
				x_aux[cromozomi_participanti[contor-1]]=((b-a)/(Math.pow(2,dimensiune_cromozom)-1))*X10_1+a;
				x_aux[cromozomi_participanti[contor-2]]=((b-a)/(Math.pow(2,dimensiune_cromozom)-1))*X10_2+a;
				f_aux[cromozomi_participanti[contor-1]]= Math.pow(x_aux[cromozomi_participanti[contor-1]],2)*(-1) + x_aux[cromozomi_participanti[contor-1]] + 2;
				f_aux[cromozomi_participanti[contor-2]]= Math.pow(x_aux[cromozomi_participanti[contor-2]],2)*(-1) + x_aux[cromozomi_participanti[contor-2]] + 2;
				contor=contor-2;
			}
	int ok;
			for(i=0;i<n;i++){
				ok=0;
				for(j=0;j<dimensiune_cromozom;j++)
				{
					if(Math.random() < mutatie )
					{
						ok=1;
						if(cromozom_aux[i][j]==0)
							cromozom_aux[i][j]=1;
						else
							cromozom_aux[i][j]=0;
					}
				}
				if(ok==1)
				{
					ok=0;
					X10_1=0;
					for(j=0;j<dimensiune_cromozom;j++)
						X10_1=X10_1+(int)(cromozom[i][j]*Math.pow(2,dimensiune_cromozom-1-j));
					x_aux[i]=((b-a)/(Math.pow(2,dimensiune_cromozom)-1))*X10_1+a;
					f_aux[i]= Math.pow(x_aux[i],2)*(-1) + x_aux[i] + 2;
				}
			}
		
	double max_fitness=0,f_mediu=0;
			for(i=0;i<n;i++){
				if(max_fitness<f_aux[i])
					max_fitness=f_aux[i];
				f_mediu=f_mediu+f_aux[i];
			}
			fitness[c]=max_fitness;
			fitness_mediu[c]=f_mediu/n;
	
		evolutie(cromozom_aux,dimensiune_cromozom,x_aux,f_aux,n,a,b,prob,mutatie,etape-1,c+1);
	}
	
	public static void main(String[] args)
	{
			try{
			File fout=new File("Evolutie.txt");                    //fisierul de scriere
			FileOutputStream fos=new FileOutputStream(fout);
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));

			Scanner sc=new Scanner(System.in);
			System.out.print("Populatia este ");
			int n=sc.nextInt();
			System.out.println("Domeniul de definitie al functiei: ["+"a"+","+"b"+"]");
			int a=sc.nextInt();
			int b=sc.nextInt();
			System.out.print("Precizia este ");
			int p=sc.nextInt();
			System.out.print("Probabilitatea de recombinare este ");
			double prob=sc.nextDouble();
			System.out.print("Probabilitatea de mutatie este ");
			double mutatie=sc.nextDouble();
			System.out.print("Numarul de etape al algoritmului este ");
			int etape=sc.nextInt();
			
			int dimensiune_cromozom=(int)(Math.ceil((Math.log((b-a)*Math.pow(10,6)))/(Math.log(2)))); //aflam lungimea secventei de cromozomi
			int[][] cromozom=new int[n][dimensiune_cromozom];                    //aici retinem parintii
			int i,j;
			double xr;
			for(i=0;i<n;i++){                          //generarea aleatoare a populatiei initiale
				for(j=0;j<dimensiune_cromozom;j++)
				{
					xr=Math.random();
					if(xr <0.5)
						cromozom[i][j]=0;
					else
						cromozom[i][j]=1;
				}
			}
			bw.write("Populatia initiala");
            bw.newLine();			
				
			
			int []X10=new int[n];             //transformam din baza 2 in baza 10 secventa de cifre 0 1
			for(i=0;i<n;i++){  
				for(j=0;j<dimensiune_cromozom;j++)
					X10[i]=X10[i]+ (int)(cromozom[i][j]*Math.pow(2,dimensiune_cromozom-1-j));
			}
			
			double x[]=new double[n];         //aflam valoarea lui x
			for(i=0;i<n;i++)
			x[i]=((b-a)/(Math.pow(2,dimensiune_cromozom)-1))*X10[i]+a;
		     
			
			double f[]=new double[n];        //aflam fitness
			for(i=0;i<n;i++)
				f[i]= Math.pow(x[i],2)*(-1) + x[i] + 2;
			
			for(i=0;i<n;i++)
			{
				bw.write(i+": ");
				for(j=0;j<dimensiune_cromozom;j++)
					bw.write(""+cromozom[i][j]);
				bw.write(" x= "+ x[i]+" f= "+f[i]);
				bw.newLine();
			}
			
			bw.write("Probabilitatea de selectie");
			bw.newLine();

            double[] probabilitate_selectie=new double[n];      //calculam probabilitatea de selectie 
            double F=0;
            for(i=0;i<n;i++)
                F=F+f[i];            //suma totala de fitness
            for(i=0;i<n;i++){
				probabilitate_selectie[i]=f[i]/F;      //probabilitatea = f[i]/suma totala
				bw.write("cromozom "+i+" probabilitate "+ probabilitate_selectie[i]);
				bw.newLine();
			}
			bw.write("Intervale de selectie");
			bw.newLine();
			double[] interval=new double[n+1]; //intervalul pe care executa "ruleta"
			interval[0]=0;
			for(i=1;i<n+1;i++)
				interval[i]=interval[i-1]+probabilitate_selectie[i-1];
			for(i=0;i<n+1;i++)
			{
				bw.write(""+interval[i]);
				bw.newLine();
			}
			
			int [][]cromozom_aux=new int[n][dimensiune_cromozom]; //aici vor fi memorati cromozomii in urma selectiei
			double []x_aux=new double[n];
			double []f_aux=new double[n];
			int k;
			for(i=0;i<n;i++)
			{
				xr=Math.random();
				bw.write("u= "+xr);
				for(j=0;j<n;j++)
					if(interval[j]<=xr && interval[j+1]>=xr)
					{
						bw.write(" selectam cromozomul "+j);
						for(k=0;k<dimensiune_cromozom;k++)
							cromozom_aux[i][k]=cromozom[j][k];
						x_aux[i]=x[j];
						f_aux[i]=f[j];
						break;
					}
				bw.newLine();
			}
			bw.write("Dupa selectie");
			bw.newLine();
			for(i=0;i<n;i++)
			{
				bw.write(""+i+": "); 
				for(j=0;j<dimensiune_cromozom;j++)
					bw.write(""+cromozom_aux[i][j]);
				bw.write(" x= "+x_aux[i]);
				bw.write(" f= "+f_aux[i]);
				bw.newLine();
			}
			bw.write("Probabilitatea de incrucisare "+prob); //determina ce cromozomi participa la incrucisare, indicii lor sunt memorati in  cromozomi_participanti
			bw.newLine();
			int[] cromozomi_participanti=new int[n];
			int contor=0;
			for(i=0;i<n;i++){
				xr=Math.random();
				for(j=0;j<dimensiune_cromozom;j++)
					bw.write(""+cromozom_aux[i][j]);
				bw.write(" u= "+xr);
				if(xr<prob){
				bw.write("<"+prob);
				cromozomi_participanti[contor++]=i;
				}
				bw.newLine();
			}
			
			int punct_taiere,aux,X10_1,X10_2;
			
			while(contor>=2){
			X10_1=0;
			X10_2=0;
			bw.write("Recombinare dintre cromozomul "+cromozomi_participanti[contor-1]+" si cromozomul "+ cromozomi_participanti[contor-2]);
			bw.newLine();
			for(i=0;i<dimensiune_cromozom;i++)
			     bw.write(""+cromozom_aux[cromozomi_participanti[contor-1]][i]);
			bw.write(" ");
		    for(i=0;i<dimensiune_cromozom;i++)
			     bw.write(""+cromozom_aux[cromozomi_participanti[contor-2]][i]);
			punct_taiere=(int)(Math.random()*20);
			bw.write(" punct "+punct_taiere);
			bw.newLine();
			bw.write("Rezultat: ");
			if(punct_taiere==0 || punct_taiere==dimensiune_cromozom-1)
			{
				for(i=0;i<dimensiune_cromozom;i++)
			     bw.write(""+cromozom_aux[cromozomi_participanti[contor-1]][i]);
			    bw.write(" ");
			    for(i=0;i<dimensiune_cromozom;i++)
			     bw.write(""+cromozom_aux[cromozomi_participanti[contor-2]][i]);
			    bw.newLine();
				contor=contor-2;
				continue;
			}
				for(i=0;i<punct_taiere;i++)
				{
					aux=cromozom_aux[cromozomi_participanti[contor-1]][i];
					cromozom_aux[cromozomi_participanti[contor-1]][i]=cromozom_aux[cromozomi_participanti[contor-2]][i];
					cromozom_aux[cromozomi_participanti[contor-2]][i]=aux;
				}
				for(i=0;i<dimensiune_cromozom;i++){
					X10_1=X10_1+(int)(cromozom[cromozomi_participanti[contor-1]][i]*Math.pow(2,dimensiune_cromozom-1-i));
					X10_2=X10_2+(int)(cromozom[cromozomi_participanti[contor-2]][i]*Math.pow(2,dimensiune_cromozom-1-i));
				}
				x_aux[cromozomi_participanti[contor-1]]=((b-a)/(Math.pow(2,dimensiune_cromozom)-1))*X10_1+a;
				x_aux[cromozomi_participanti[contor-2]]=((b-a)/(Math.pow(2,dimensiune_cromozom)-1))*X10_2+a;
				f_aux[cromozomi_participanti[contor-1]]= Math.pow(x_aux[cromozomi_participanti[contor-1]],2)*(-1) + x_aux[cromozomi_participanti[contor-1]] + 2;
				f_aux[cromozomi_participanti[contor-2]]= Math.pow(x_aux[cromozomi_participanti[contor-2]],2)*(-1) + x_aux[cromozomi_participanti[contor-2]] + 2;
				for(i=0;i<dimensiune_cromozom;i++)
			     bw.write(""+cromozom_aux[cromozomi_participanti[contor-1]][i]);
			    bw.write(" ");
			    for(i=0;i<dimensiune_cromozom;i++)
			     bw.write(""+cromozom_aux[cromozomi_participanti[contor-2]][i]);
			    bw.newLine();
				contor=contor-2;
			}
			
			bw.write("Dupa recombinare");
			bw.newLine();
			for(i=0;i<n;i++)
			{
				bw.write(""+i+": "); 
				for(j=0;j<dimensiune_cromozom;j++)
					bw.write(""+cromozom_aux[i][j]);
				bw.write(" x= "+x_aux[i]);
				bw.write(" f= "+f_aux[i]);
				bw.newLine();
			}
			
			bw.write("Probabilitatea de mutatie pentru fiecare gena este "+mutatie);
			bw.newLine();
			int ok;
			for(i=0;i<n;i++){                 //aici executa partea de mutatie
				ok=0;  
				for(j=0;j<dimensiune_cromozom;j++)
				{
					if(Math.random() < mutatie )  //pentru fiecare gena, determina daca intervine o mutatie sau nu
					{
						bw.write("A fost modificat cromozomul "+i+" pe pozitia "+j);
						bw.newLine();
						ok=1;
						if(cromozom_aux[i][j]==0)
							cromozom_aux[i][j]=1;
						else
							cromozom_aux[i][j]=0;
					}
				}
				if(ok==1)
				{
					ok=0;
					X10_1=0;
					for(j=0;j<dimensiune_cromozom;j++)
						X10_1=X10_1+(int)(cromozom[i][j]*Math.pow(2,dimensiune_cromozom-1-j));
					x_aux[i]=((b-a)/(Math.pow(2,dimensiune_cromozom)-1))*X10_1+a;
					f_aux[i]= Math.pow(x_aux[i],2)*(-1) + x_aux[i] + 2;
				}
			}
			
			bw.write("Dupa mutatie: ");
			bw.newLine();
			for(i=0;i<n;i++)
			{
				bw.write(""+i+": "); 
				for(j=0;j<dimensiune_cromozom;j++)
					bw.write(""+cromozom_aux[i][j]);
				bw.write(" x= "+x_aux[i]);
				bw.write(" f= "+f_aux[i]);
				bw.newLine();
			}
			bw.write("Evolutia maximului: ");
			bw.newLine();
			    double max_fitness=0,f_mediu=0;
			for(i=0;i<n;i++){
				if(max_fitness<f_aux[i])
					max_fitness=f_aux[i];
				f_mediu=f_mediu+f_aux[i];
			}
			fitness_mediu[0]=f_mediu/n;   //pentru a calcula fitness-ul mediu, am calculat suma tuturor valorilor din f dupa toate modificarile si am impartit la n
			fitness[0]=max_fitness;
			bw.write(""+max_fitness);
			bw.newLine();
			evolutie(cromozom_aux,dimensiune_cromozom,x_aux,f_aux,n,a,b,prob,mutatie,etape-1,c+1);
			for(i=1;i<etape;i++){
				bw.write(""+fitness[i]);
				bw.newLine();
			}
			bw.newLine();
			bw.write("Evolutia mediului");
			bw.newLine();
			for(i=0;i<etape;i++)
			{
				bw.write(""+fitness_mediu[i]);
				bw.newLine();
			}
			bw.close();
			}
        
            			
		catch(IOException ex)
			{
				System.out.println(ex.toString());
				System.out.println("Nu a gasit fisier");
			}
		}
		
}
	
