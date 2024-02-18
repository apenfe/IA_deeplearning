package REDES;

import Clases.Entradas;

public class Capas {

	private Perceptron[] perceptrones = new Perceptron[0];
	private double[] salidas = new double[0];

	public Capas() {
		
		System.out.println("Se recomeniendan:");
		System.out.println("1º capa igual que nº de entradas.");
		System.out.println("última capa igual que nº de salidas.");

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

	public Perceptron[] getPerceptrones() {
		return perceptrones;
	}

	public void setPerceptrones(Perceptron[] perceptrones) {
		this.perceptrones = perceptrones;
	}
	
	

}