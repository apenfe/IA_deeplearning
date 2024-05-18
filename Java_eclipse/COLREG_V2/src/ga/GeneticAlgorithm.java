package ga;

public class GeneticAlgorithm {

	private int tamanoPoblacion;
	private double ratioMutacion;
	private double ratioDeCruce;
	private int elite;

	public GeneticAlgorithm(int tamanoPoblacion, double ratioMutacion, double ratioDeCruce, int elite) {

		this.tamanoPoblacion = tamanoPoblacion;
		this.ratioMutacion = ratioMutacion;
		this.ratioDeCruce = ratioDeCruce;
		this.elite = elite;

	}

	public Poblacion iniciarPoblacion(int numeroCromosomas) {

		Poblacion poblacion = new Poblacion(this.tamanoPoblacion, numeroCromosomas);
		return poblacion;

	}

	public double calculoFitnessAgente(Agente agente) {
		// Track number of correct genes
		int genesCorrectos = 0;
		// Loop over individual's genes
		for (int i = 0; i < agente.getChromosomeLength(); i++) {
			// Add one fitness point for each "1" found
			if (agente.getGene(i) == 1) {
				genesCorrectos += 1;
			}
		}
		// Calculate fitness
		double fitness = (double) genesCorrectos / agente.getChromosomeLength();
		// Store fitness
		agente.setFitness(fitness);
		return fitness;
		
	}

	public void calculoFitnessPoblacion(Poblacion poblacion) {
		
		double fitnessPoblacion = 0;
		
		for (Agente agente : poblacion.getIndividuals()) {
			
			fitnessPoblacion += calculoFitnessAgente(agente);
			
		}
		
		poblacion.setPopulationFitness(fitnessPoblacion);
		
	}

	public boolean condicionTerminacion(Poblacion poblacion) {
		
		for (Agente agente : poblacion.getIndividuals()) {
			
			if (agente.getFitness() == 1) {
				
				return true;
				
			}
			
		}
		
		return false;
	}

	public Agente seleccionarPadre(Poblacion poblacion) {
		
		// Get individuals
		Agente agentes[] = poblacion.getIndividuals();
		// Spin roulette wheel
		double fitnessPoblacion = poblacion.getPopulationFitness();
		double posicionRuleta = Math.random() * fitnessPoblacion;
		// Find parent
		double spinWheel = 0;
		
		for (Agente agente : agentes) {
			
			spinWheel += agente.getFitness();
			
			if (spinWheel >= posicionRuleta) {
				
				return agente;
				
			}
			
		}
		
		return agentes[poblacion.size() - 1];
		
	}

	public Poblacion cruzarPoblacion(Poblacion poblacion) {
		
		// Create new population
		Poblacion nuevaPoblacion = new Poblacion(poblacion.size());
		
		// Loop over current population by fitness
		for (int i = 0; i < poblacion.size(); i++) {
			
			Agente padre = poblacion.getFittest(i);  // porque es una i??
			
			// Apply crossover to this individual?
			if (this.ratioDeCruce > Math.random() && i > this.elite) {
				// Initialize offspring
				Agente hijo = new Agente(padre.getChromosomeLength());
				// Find second parent
				Agente madre = seleccionarPadre(poblacion);
				// Loop over genome
				for (int gen = 0; gen < padre.getChromosomeLength(); gen++) {
					// Use half of parent1's genes and half of parent2's genes
					if (0.5 > Math.random()) {
						hijo.setGene(gen, padre.getGene(gen));
					} else {
						hijo.setGene(gen, madre.getGene(gen));
					}
				}
				// Add offspring to new population
				nuevaPoblacion.setIndividual(i, hijo);
				
			} else {
				// Add individual to new population without applying crossover
				nuevaPoblacion.setIndividual(i, padre);
			}
			
		}
		
		return nuevaPoblacion;
		
	}

	public Poblacion mutarPoblacion(Poblacion poblacion) {
		
		// Initialize new population
		Poblacion nuevaPoblacion = new Poblacion(this.tamanoPoblacion);
		
		// Loop over current population by fitness
		for (int i = 0; i < poblacion.size(); i++) {
			
			Agente agente = poblacion.getFittest(i);
			
			// Loop over individual's genes
			for (int j = 0; j < agente.getChromosomeLength(); j++) {
				
				// Skip mutation if this is an elite individual
				if (i >= this.elite) {
					
					// Does this gene need mutation?
					if (this.ratioMutacion > Math.random()) {
						// Get new gene
						int nuevoGen = 1;

						if (agente.getGene(j) == 1) {
							nuevoGen = 0;
						}
						// Mutate gene
						agente.setGene(j, nuevoGen);
					}
				}
			}
			// Add individual to population
			nuevaPoblacion.setIndividual(i, agente);
		}
		// Return mutated population
		return nuevaPoblacion;
	}

}