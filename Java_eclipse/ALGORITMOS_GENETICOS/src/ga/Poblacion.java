package ga;

import java.util.Arrays;
import java.util.Comparator;
//import java.util.Random;

public class Poblacion {
	
	private Agente poblacion[];
	private double fitnessPoblacion = -1;

	public Poblacion(int numeroIndividuos) {
		
		this.poblacion = new Agente[numeroIndividuos];
		
	}

	public Poblacion(int tamanoPoblacion, int numeroCromosomas) {
		
		this.poblacion = new Agente[tamanoPoblacion];
		
		for (int i = 0; i < tamanoPoblacion; i++) {
			
			Agente agente = new Agente(numeroCromosomas);
			this.poblacion[i] = agente;
			
		}
		
	}

	public Agente[] getIndividuals() {
		
		return this.poblacion;
		
	}

	public Agente getFittest(int indice) {
		
		Arrays.sort(this.poblacion, new Comparator<Agente>() {
			
			@Override
			public int compare(Agente agente1, Agente agente2) {
				if (agente1.getFitness() > agente2.getFitness()) {
					return -1;
				} else if (agente1.getFitness() < agente2.getFitness()) {
					return 1;
				}
				return 0;
				
			}
			
		});
		
		return this.poblacion[indice];
		
	}

	public void setPopulationFitness(double fitness) {
		
		this.fitnessPoblacion = fitness;
		
	}

	public double getPopulationFitness() {
		
		int max = poblacion.length;
		
		return (this.fitnessPoblacion/max)*100;
		
	}

	public int size() {
		
		return this.poblacion.length;
		
	}

	public Agente setIndividual(int indice, Agente agente) {
		
		return poblacion[indice] = agente;
		
	}

	public Agente getIndividual(int indice) {
		
		return poblacion[indice];
		
	}

	/*public void shuffle() {
		
		Random rnd = new Random();
		
		for (int i = poblacion.length - 1; i > 0; i--) {
			
			int index = rnd.nextInt(i + 1);
			Agente a = poblacion[index];
			poblacion[index] = poblacion[i];
			poblacion[i] = a;
			
		}
		
	}*/
	
}