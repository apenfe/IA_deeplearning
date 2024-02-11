package RED_NEURONAL_PERCEPTRON;

import java.util.Arrays;

public class Perceptron {

	private double[] pesosSinapticos;
	private double umbral;
	private double costo;

	public Perceptron(int entradas, double costo) {
		this.pesosSinapticos = new double[entradas];
		this.costo = costo;
		this.umbral = Math.random()* 2 - 1;

		for (int i = 0; i < entradas; i++) {
			pesosSinapticos[i] = Math.random()* 2 - 1;
		}
		
	}

	public int feedForward(double[] entradas) {
		
		double neta = 0;
		
		for (int i = 0; i < entradas.length; i++) {
			
			neta += entradas[i] * pesosSinapticos[i] + umbral;
			
		}
		
		return activate(neta);
	}

	private int activate(double neta) {
		
		return (neta >= 0) ? 1 : -1;
		
	}

	public void train(double[] inputs, int deseado) {
		
		int salida = feedForward(inputs);
		
		double error = deseado - salida;
		
		for (int i = 0; i < pesosSinapticos.length; i++) {
			
			pesosSinapticos[i] += error * inputs[i] * costo; // Aplicando la regla de actualización
			
		}

		umbral += error * costo; // Actualización del umbral
	}

	public void printWeights() {
		
		System.out.println(Arrays.toString(pesosSinapticos));
		System.out.println("Umbral: "+umbral);
		
	}

}