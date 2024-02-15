package PROBLEMAS;
import RED_NEURONAL_ADALINE.Adaline;
import RED_NEURONAL_PERCEPTRON.Perceptron;

public class Temperaturas{
	
	public static void main(String[] args) {

		
		double[][] entradas_1 = {{0.5,0.8},{0.2,0.4},{0.7,0.3},{0.9,0.6},{0.3,0.1}};
		double[][] salidas_1 = {{1},{0},{1},{1},{0}};
		
		conversor(entradas_1,salidas_1);
				
	}
	
	public static void conversor(double[][] entradas, double[][] salidas) {
		
		System.out.println("PATRON 1...");
		Adaline adaline = new Adaline(entradas,salidas,0.2);
		adaline.train();
		adaline.probar();

	}
		
}