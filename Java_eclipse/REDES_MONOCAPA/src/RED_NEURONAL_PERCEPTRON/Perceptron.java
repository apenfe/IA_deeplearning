package RED_NEURONAL_PERCEPTRON;

/*
 * PASO 1:
 * 
 * Asignar valores aleatorios de pesos y umbral/bias (se sugiere entre 1 y -1)
 * 
 * PASO 2:
 * 
 * Presentar el vector de entradas y especificar vector salida deseada
 * 
 * PASO 3:
 * 
 * Calcular la neta (peso*entrada)+bias
 * 
 * PASO 4:
 * 
 * Aplicar funcion, para el caso, activacion binaria o de escalon
 * 
 * PASO 5:
 * 
 * Actualizar pesos de la capa de salida new_peso = peso + (incremento) * entrada
 * 
 * PASO 6: 
 * 
 * Calculo de la funcion perdida
 * 
 * PASO 7: 
 * 
 * SI la funcion perdida es diferente de 0 volvemos a PASO 2
 * 
 */

import java.util.Arrays;

public class Perceptron {

	private double[] entradas = new double[0];
	private double[] salidaEsperada = new double[0];
	private double[] pesosSinapticos = new double[0];
	private double umbral_Bias;
	private double costo;

	public Perceptron(int[] entradas, double[] salidaEsperada, double costo) {
		
		this.salidaEsperada=salidaEsperada;
		this.pesosSinapticos = new double[entradas.length];
		this.costo = costo;
		this.umbral_Bias = Math.random()* 2 - 1;

		for (int i = 0; i < entradas.length; i++) {
			pesosSinapticos[i] = Math.random()* 2 - 1;
		}
		
	}

	public double calcularNeta(double[] entradas) {
		
		double neta = 0;
		
		for (int i = 0; i < entradas.length; i++) {
			
			neta += entradas[i] * pesosSinapticos[i] + umbral_Bias;
			
		}
		
		return neta;
	}

	private int activate(double neta) {
		
		return (neta >= 0) ? 1 : -1;
		
	}

	public void train(double[] inputs, int deseado) {
		
		double neta = calcularNeta(inputs);
		int salida = activate(neta);
		
		double error = deseado - salida;
		
		for (int i = 0; i < pesosSinapticos.length; i++) {
			
			pesosSinapticos[i] += error * inputs[i] * costo; // Aplicando la regla de actualización
			
		}

		umbral_Bias += error * costo; // Actualización del umbral
	}

	public void printWeights() {
		
		System.out.println(Arrays.toString(pesosSinapticos));
		System.out.println("Umbral: "+umbral_Bias);
		
	}

}