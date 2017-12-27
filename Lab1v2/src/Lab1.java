
import java.util.Random;

public class Lab1 {
	private static Random randomInteger = new Random();
	private static int[][] regularOper = {{50, 0},
										  {75, 1},
										  {65, 2},
										  {90, 3},
										  {100, 4},
										  {85, 5}};
	private static int[][] fileOper = {{100, 1, 6},
									   {150, 1, 7},
									   {175, 1, 8},
									   {125, 2, 9},
									   {250, 2, 10}};
	private static double[] n = new double [11];
	private static double[] V = new double [11];
	private static int cycleIterations = 9000000;

	private static void algo(){
		
		int i, j, x, n;
		boolean bf1, bf2, bf5;
		bf1=bf2=bf5=true;
		i=j=n=1;
		x=0;
		
		
		do{
			if(bf1==true){
				V[0]++;
			}
			if(bf2==true){
				V[1]++;
				i=randomInteger(0, 4);
				j=randomInteger(0, 3);
				if(i>0||j<3){
					bf1=bf2=true;
					bf5=false;
					V[2]++;
					V[3]++;
				}
				else{
					bf5=true;
				}
			}
			if(bf5==true){
				V[4]++;
				V[5]++;	
				n=randomInteger(0, 2);
			}
			x=randomInteger(-2, 3);
			if(n==2){
				V[6]++;
				V[7]++;
				V[8]++;
				if(x==0){
					bf1=bf2=false;
					bf5=true;
					V[9]++;
					V[10]++;
				}
			}
			else{
				bf1=false;
				bf2=bf5=true;
			}
		}while (x==0);
	    
	}
	
	public static void main(String... args){
		//Прогон алгоритма
		for(int i = 0; i < cycleIterations; i++){
			algo();
		}
		
		//Подсчёт n
		System.out.println("Подсчёт n:");
		for(int i = 0; i < n.length; i++){
			n[i] = V[i] / cycleIterations;
			System.out.println("n" + (i+1) + " = " + n[i]);
		}
		
		System.out.println("\n---------------------------------------------------------------\n");
		
		double N = 0; // Cреднее число операций при одном прогоне алгоритма
		double operNum = 0;
		for(int i = 0; i < regularOper.length; i++){
			operNum += (n[regularOper[i][1]] * regularOper[i][0]);
			N += n[regularOper[i][1]];
		}
		System.out.println("Cреднее число операций при одном прогоне алгоритма: " + operNum);

		double appealNum; // Среднее число обращений к каждому из файлов
		double infoNum; // Среднее число информации, передаваемое при одном обращении к файлу
		double fileNum; // Номер файла
		for(int i = 0; i < 2; i++){
			appealNum = 0;
			infoNum = 0;
			fileNum = i + 1;
			
			//Cреднее число обращений к каждому из файлов
			for(int j = 0; j < fileOper.length; j++){
				if(fileNum == fileOper[j][1])
					appealNum += n[fileOper[j][2]];
			}
			System.out.println("Cреднее число обращений к файлу " + (i + 1) + ": " + appealNum);
			
			//Cреднее число информации, передаваемое при одном обращении к файлу
			for(int j = 0; j < fileOper.length; j++){
				if(fileNum == fileOper[j][1])
					infoNum += (n[fileOper[j][2]] * fileOper[j][0]);
			}
			System.out.println("Cреднее число информации, передаваемое при одном обращении к файлу " + (i + 1) + ": " + (infoNum / appealNum));
		}
		System.out.println("Средняя трудоемкость этапа счёта: " + (operNum / N));
	}
	
	private static int randomInteger(int min, int max){
		return randomInteger.nextInt((max - min) + 1) + min;
	}
}

