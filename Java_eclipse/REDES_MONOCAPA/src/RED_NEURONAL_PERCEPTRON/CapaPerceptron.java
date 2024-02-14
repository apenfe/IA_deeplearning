package RED_NEURONAL_PERCEPTRON;

public class CapaPerceptron {
	
	private Perceptron[] perceptrones = new Perceptron[0];
	private double[][] entradas = new double[0][0]; 
	private double[][] salidas = new double[0][0]; 
	
	public CapaPerceptron(double[][] entradas, double[][] salidas) {
		
		this.perceptrones = new Perceptron[salidas[0].length];
		this.entradas = entradas;
		this.salidas = salidas;
		
		for (int i = 0; i < salidas[0].length; i++) {
			
			perceptrones[i] = new Perceptron(entradas,salidas,i);
			
		}
		
	}
	
	public void trainCapa() {
		
		for (int i = 0; i < perceptrones.length; i++) {
			
			perceptrones[i].train();
			
		}
		
	}
	
	public void probarCapa() {
		
		// 1 paso todas las entradas y calculo para cada neurona su neta y su activacion
		// 2 comparo la salida con la esperada
		int[] salidasReales = new int[salidas[0].length] ;
		
		for (int i = 0; i < entradas.length; i++) {
			
			for (int j = 0; j < perceptrones.length; j++) {
				
				salidasReales[j] = perceptrones[j].activate(perceptrones[j].calcularNeta(entradas[i]));
				
				if(salidasReales[j]==salidas[i][j]) {
					System.out.println("BIEN");
				}else {
					System.err.println("Mal");
				}
				
			}
			
		}
		
		
	}

	

}