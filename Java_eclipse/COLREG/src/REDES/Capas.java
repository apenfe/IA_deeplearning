package REDES;

import Clases.Entradas;

public class Capas {

	private Perceptron[] perceptrones = new Perceptron[0];
	private double[] salidas = new double[0];

	public Capas() {
		
		int neuronas = Entradas.entero("¿Cuantas neuronas dese añadir?");

		this.perceptrones = new Perceptron[neuronas];
		this.salidas = new double[neuronas];

		for (int i = 0; i < neuronas; i++) {

			perceptrones[i] = new Perceptron(); // ver como le paso los pesos necesito hacer
																		// sensore* numero de neuronas
		}

	}

	public double[] probarCapa(double[] entradas) {

		for (int j = 0; j < perceptrones.length; j++) { // 4

			salidas[j] = perceptrones[j].probar(entradas);

		}

		return salidas;

	}

	public int getPerceptrones() {
		return perceptrones.length;
	}

}