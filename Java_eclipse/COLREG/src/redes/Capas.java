package redes;

import java.util.Arrays;

import clases.Entradas;

public class Capas {

	private Perceptron[] perceptrones = new Perceptron[0];

	public Capas() {
		
		System.out.println("Se recomeniendan:");
		System.out.println("1º capa igual que nº de entradas.");
		System.out.println("última capa igual que nº de salidas.");

		int neuronas = Entradas.entero("¿Cuantas neuronas dese añadir?");
		int funcion = Entradas.entero("¿Que funcion desea establecer para esta capa? ");


		this.perceptrones = new Perceptron[neuronas];

		for (int i = 0; i < neuronas; i++) {

			perceptrones[i] = new Perceptron(); 
			perceptrones[i].setFuncion(funcion); // revisar esta parte														
		}

	}
	
	public Capas(int numero) {

		this.perceptrones = new Perceptron[numero];

		for (int i = 0; i < numero; i++) {

			perceptrones[i] = new Perceptron(); 
																		
		}

	}

	public double[] probarCapa(double[] entradas) {
		
		double[] exit = new double[perceptrones.length];

		for (int j = 0; j < perceptrones.length; j++) {

			exit[j] = perceptrones[j].probar(entradas);

		}

		return exit;

	}
	
	// comprobar si este metodo va a funcionar
	
	public void establecerPesosNeuronas(double[] pesosDeLaCapa) {
		
		int pesosPorNeurona = pesosDeLaCapa.length/perceptrones.length; //3
		
		//System.out.println("pesos por neurona: "+pesosPorNeurona);
		
		for (int i = 0; i < perceptrones.length; i++) {
			
			int inicio = i * pesosPorNeurona;
	        int fin = (i + 1) * pesosPorNeurona;
			perceptrones[i].setPesosSinapticos(Arrays.copyOfRange(pesosDeLaCapa,inicio,fin));
			
		}
		
	}

	public Perceptron[] getPerceptrones() {
		return perceptrones;
	}

	public void setPerceptrones(Perceptron[] perceptrones) {
		this.perceptrones = perceptrones;
	}
	
}