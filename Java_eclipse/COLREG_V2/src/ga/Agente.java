package ga;

public class Agente {
	
	private int[] cromosomas;
	private double fitness = -1;

	public Agente(int[] chromosome) {
		// Create individual chromosome
		this.cromosomas = chromosome;
		
	}

	public Agente(int numeroCromosomas) {
		
		this.cromosomas = new int[numeroCromosomas];
		
		for (int gene = 0; gene < numeroCromosomas; gene++) {
			
			if (0.5 < Math.random()) {
				this.setGene(gene, 1);
			} else {
				this.setGene(gene, 0);
			}
			
		}
		
	}

	public int[] getChromosome() {
		
		return this.cromosomas;
		
	}

	public int getChromosomeLength() {
		
		return this.cromosomas.length;
		
	}

	public void setGene(int indice, int gen) {
		
		this.cromosomas[indice] = gen;
		
	}

	public int getGene(int indice) {
		
		return this.cromosomas[indice];
		
	}

	public void setFitness(double fitness) {
		
		this.fitness = fitness;
		
	}

	public double getFitness() {
		
		return this.fitness;
		
	}

	public String toString() {
		
		String exit = "";
		
		for (int gen = 0; gen < this.cromosomas.length; gen++) {
			
			exit += this.cromosomas[gen];
			
		}
		
		return exit;
		
	}
	
}