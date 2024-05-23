package ga;

import agente.Agente;
import entorno.Entorno;

public class GeneticAlgorithm {

	private int tamanoPoblacion;
	private double ratioMutacion;
	private double ratioDeCruce;
	private int elite;
	private Entorno entorno;

	public GeneticAlgorithm(int tamanoPoblacion, double ratioMutacion, double ratioDeCruce, int elite, Entorno entorno) {

		this.tamanoPoblacion = tamanoPoblacion;
		this.ratioMutacion = ratioMutacion;
		this.ratioDeCruce = ratioDeCruce;
		this.elite = elite;
		this.entorno=entorno;

	}

	public Poblacion iniciarPoblacion(int numeroCromosomas) {

		Poblacion poblacion = new Poblacion(this.tamanoPoblacion, numeroCromosomas,this.entorno);
		return poblacion;

	}

	public double calculoFitnessAgente(Agente agente) {
		
		double fitness = agente.calculateFitness();
		
		//agente.setFitness(fitness);
		return fitness;
		
	}

	public void calculoFitnessPoblacion(Poblacion poblacion) {
		
		double fitnessPoblacion = 0;
		
		for (Agente agente : poblacion.getIndividuals()) {
			
			fitnessPoblacion += calculoFitnessAgente(agente);
			
		}
		
		poblacion.setPopulationFitness(fitnessPoblacion);
		
	}

	public boolean condicionTerminacion(int generacion, int genMax) { // ver
			
		if (generacion>=genMax) {
				
			return true;
				
		}
		
		return false;
	}

	public Agente seleccionarPadre(Poblacion poblacion) {
		
		Agente agentes[] = poblacion.getIndividuals();
		
		double fitnessPoblacion = poblacion.getPopulationFitness();
		double posicionRuleta = Math.random() * fitnessPoblacion;
		
		double spinWheel = 0;
		
		for (Agente agente : agentes) {
			
			spinWheel += agente.getFitness();
			
			if (spinWheel >= posicionRuleta) {
				
				return agente;
				
			}
			
		}
		
		return agentes[poblacion.size() - 1];
		
	}

	public Poblacion cruzarPoblacion(Poblacion poblacion,Entorno entorno) {
		
		Poblacion nuevaPoblacion = new Poblacion(poblacion.size(),entorno);
		
		for (int i = 0; i < poblacion.size(); i++) {
			
			Agente padre = poblacion.getFittest(i);  // porque es una i??
			
			if (this.ratioDeCruce > Math.random() && i > this.elite) {
				
				Agente hijo = new Agente(padre.getChromosomeLength(),entorno);
				
				Agente madre = seleccionarPadre(poblacion);
				
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

	public Poblacion mutarPoblacion(Poblacion poblacion,Entorno entorno) {
		
		Poblacion nuevaPoblacion = new Poblacion(this.tamanoPoblacion,entorno);
		
		for (int i = 0; i < poblacion.size(); i++) {
			
			Agente agente = poblacion.getFittest(i);
			
			for (int j = 0; j < agente.getChromosomeLength(); j++) {
				
				if (i >= this.elite) {
					
					if (this.ratioMutacion > Math.random()) {
						// Get new gene
						double gen = agente.getGene(j);
						double nuevoGen=0;
						
						if (0.5 > Math.random()) {
							nuevoGen += 0.3;
						} else {
							nuevoGen -= 0.3;
						}

						
						// Mutate gene
						agente.setGene(j, gen+nuevoGen);
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