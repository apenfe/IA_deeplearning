package REDES;

public class RedNeuronal{
	
	private Capas[] capas = new Capas[0];
	
	public RedNeuronal(int numeroCapas) {
		
		this.capas = new Capas[numeroCapas];
		
		for (int i = 0; i < capas.length; i++) {
			
			System.err.println("Capa nº "+(i+1)+":");
			
			capas[i]= new Capas(); 
			
		}
		
	}
	
	// tarnspaso de salidas de capa a capa
	
	public int[] probarRed(double[] entradas) {

		for (int j = 0; j < capas.length; j++) {
			
			double[] salidas = new double[capas[j].getPerceptrones()];

			salidas[j] = capas[j].probarCapa(entradas);

		}

		return salidas;

	}
	
	
	
}