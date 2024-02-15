package PROBLEMAS;
import RED_NEURONAL_ADALINE.Adaline;
import RED_NEURONAL_PERCEPTRON.Perceptron;

public class Temperaturas{
	
	public static void main(String[] args) {
		
		// Patron 1
		double[][] entradas_1 = {{-40},{-20},{-10},{0},{10},{20}};
		double[][] salidas_1 = {{-40},{-4},{14},{32},{50},{68}};
		
		conversor(entradas_1,salidas_1);
				
	}
	
	public static void conversor(double[][] entradas, double[][] salidas) {
		
		System.out.println("PATRON 1...");
		Adaline adaline = new Adaline(entradas,salidas,0.2);
		adaline.train();
		adaline.probar();

	}
		
}