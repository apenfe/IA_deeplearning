package ga;

public class AllOnesGA {

	public static void main(String[] args) {
		
		GeneticAlgorithm algoritmo = new GeneticAlgorithm(100, 0.01, 0.95, 15);

		Poblacion poblacion = algoritmo.iniciarPoblacion(200);

		algoritmo.calculoFitnessPoblacion(poblacion);
		
		int generacion = 1;
		
		while (algoritmo.condicionTerminacion(poblacion) == false) {
			
			System.out.println("Fitness de la poblacion: "+poblacion.getPopulationFitness()+"%, Best solution: " + poblacion.getFittest(0).toString());

			poblacion = algoritmo.cruzarPoblacion(poblacion);
			
			poblacion = algoritmo.mutarPoblacion(poblacion);
			
			algoritmo.calculoFitnessPoblacion(poblacion);
			
			generacion++;
			
		}
		
		System.out.println("Solucion encontrada en " + generacion + "generaciones.");
		System.out.println("Mejor solucion: " + poblacion.getFittest(0).toString());

	}

}