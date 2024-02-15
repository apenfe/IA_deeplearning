package RED_NEURONAL_PERCEPTRON;

public class CapaPerceptron {
	
	private long time;
	private Perceptron[] perceptrones = new Perceptron[0];
	private double[][] entradas = new double[0][0]; 
	private double[][] salidas = new double[0][0]; 
	
	public CapaPerceptron(double[][] entradas, double[][] salidas) {
		
		this.time=System.currentTimeMillis();
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
		
		int[] salidasReales = new int[salidas[0].length] ;

		for (int i = 0; i < entradas.length; i++) { // 10
			
			System.out.println("\n\tEntrada " + (i)+": ");
			System.out.println();

			for (int j = 0; j < perceptrones.length; j++) { // 4

				salidasReales[j] = perceptrones[j].activate(perceptrones[j].calcularNeta(entradas[i]));

				if (salidasReales[j] == salidas[i][j]) {

					System.out.println("\tNúmero esperado: " +salidas[i][j]+" ---> "+salidasReales[j]+" salida obtenida.");		

				} else {
					
					System.out.println("\tNúmero esperado: " +salidas[i][j]+" ---> "+salidasReales[j]+" salida obtenida.");
				}

			}
			
		}
		
		this.time=System.currentTimeMillis()-time;
		
		System.err.println("\n\tDuración del entrenamiento: "+time+" ms ---> "+time/1000+" s.");

	}

}