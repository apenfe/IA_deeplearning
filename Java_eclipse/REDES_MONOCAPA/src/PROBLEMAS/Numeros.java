package PROBLEMAS;

import RED_NEURONAL_PERCEPTRON.CapaPerceptron;

public class Numeros{
	
	public static void main(String[] args) {
		
		// Numeros
		double[][] entradas = {{1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
				{0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1},
				{1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
				{1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1},
				{1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1},
				{1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1},
				{1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
				{1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1},
				{1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
				{1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}};
		
		double[][] salidas = {{0, 0, 0, 0},
				{0, 0, 0, 1},
				{0, 0, 1, 0},
				{0, 0, 1, 1},
				{0, 1, 0, 0},
				{0, 1, 0, 1},
				{0, 1, 1, 0},
				{0, 1, 1, 1},
				{1, 0, 0, 0},
				{1, 0, 0, 1}};
		
		CapaPerceptron capa = new CapaPerceptron(entradas,salidas);
		capa.trainCapa();
		capa.probarCapa();
				
	}
		
}
		