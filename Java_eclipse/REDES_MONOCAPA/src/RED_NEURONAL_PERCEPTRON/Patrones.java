package RED_NEURONAL_PERCEPTRON;

public class Patrones{
	
	public static void main(String[] args) {
		
		// Patron 1
		double[][] entradas_1 = {{-0.5,-1},{1,1},{1,0.5},{-1,-0.5},{-1,-1},{0.5,1}};
		double[][] salidas_1 = {{0},{1},{1},{0},{0},{1}};
		
		// PatrON 2 
		double[][] entradas_2 = {{-1,-1},{-0.5,-0.5},{-1,0},{0,-1},{1,1},{0.5,0.5},{1,0},{0,1}};
		double[][] salidas_2 = {{0},{0},{0},{0},{1},{1},{1},{1}};
		
		and(entradas_1,salidas_1);
		or(entradas_2,salidas_2);	
				
	}
	
	public static void and(double[][] entradas, double[][] salidas) {
		
		System.out.println("PATRON 1...");
		Perceptron perceptron = new Perceptron(entradas,salidas);
		perceptron.train();
		perceptron.probar();

	}
	
	public static void or(double[][] entradas, double[][] salidas) {
		
		System.out.println("PATRON 2...");
		Perceptron perceptron = new Perceptron(entradas,salidas);
		perceptron.train();
		perceptron.probar();
		
		
	}
		
}
	