package RED_NEURONAL_PERCEPTRON;

public class PuertasLogicas{
	
	public static void main(String[] args) {
		
		// Puerta AND
		double[][] entradas_AND = {{1,1,1},{1,1,0},{1,0,1},{1,0,0},{0,1,1},{0,1,0},{0,0,1},{0,0,0}};
		double[][] salidas_AND = {{1},{0},{0},{0},{0},{0},{0},{0}};
		
		// Puerta AND
		//int[][] entradas_AND = {{1,1},{1,0},{0,1},{0,0}};
		//int[][] salidas_AND = {{1},{0},{0},{0}};
		
		// Puerta AND
				double[][] entradas_OR = {{1,1,1},{1,1,0},{1,0,1},{1,0,0},{0,1,1},{0,1,0},{0,0,1},{0,0,0}};
				double[][] salidas_OR = {{1},{1},{1},{1},{1},{1},{1},{0}};
		
		// Puerta NOR
		double[][] entradas_NOT = {{0},{1}};
		double[][] salidas_NOT = {{1},{0}};
		
		// Puerta OR
		//int[][] entradas_OR = {{1,1},{1,0},{0,1},{0,0}};
		//int[][] salidas_OR = {{1},{1},{1},{0}};
		
		and(entradas_AND,salidas_AND);
		or(entradas_OR,salidas_OR);
		not(entradas_NOT,salidas_NOT);	
				
	}
	
	public static void and(double[][] entradas, double[][] salidas) {
		
		System.out.println("PUERTA AND...");
		Perceptron perceptron = new Perceptron(entradas,salidas);
		perceptron.train();
		perceptron.probar();

	}
	
	public static void or(double[][] entradas, double[][] salidas) {
		
		System.out.println("PUERTA OR...");
		Perceptron perceptron = new Perceptron(entradas,salidas);
		perceptron.train();
		perceptron.probar();
		
		
	}
	
	public static void not(double[][] entradas, double[][] salidas) {
		
		System.out.println("PUERTA NOT...");
		Perceptron perceptron = new Perceptron(entradas,salidas);
		perceptron.train();
		perceptron.probar();
		
	}
		
}
		
