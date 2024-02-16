package REDES;

public class CapaPerceptron {
	
	private Perceptron[] perceptrones = new Perceptron[0];
	private double[]salidas = new double[0]; 
	
	public CapaPerceptron(double[] entradas, int salidas, double[][] pesos) {
		
		this.perceptrones = new Perceptron[salidas];
		this.salidas= new double[salidas];
		
		for (int i = 0; i < salidas; i++) {
			
			perceptrones[i] = new Perceptron(entradas,pesos[i],i); // ver como le paso los pesos necesito hacer sensore* numero de neuronas
			
		}
		
	}
	
	public double[] probarCapa() {

			for (int j = 0; j < perceptrones.length; j++) { // 4

				salidas[j] = perceptrones[j].probar();

			}
			
			return salidas;
		

	}

}