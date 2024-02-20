package redes;

import java.util.Arrays;

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

public class Perceptron {
	
	private double[] pesosSinapticos = new double[0];
	
	public Perceptron() { // se genera vacio
		
	}
	
	public double calcularNeta(double[] entradas) {
		
	/*	for (int i = 0; i < entradas.length; i++) {
			System.out.println(entradas[i]);

		}
		
		for (int i = 0; i < pesosSinapticos.length; i++) {
			System.out.println(pesosSinapticos[i]);

		}*/
		
		double neta = 0;
		
		for (int i = 0; i < entradas.length; i++) {
			
			neta += entradas[i] * pesosSinapticos[i];
			
		}
		
		return neta;
	}

	public int activate(double neta) {
		
		return (neta >= 0) ? 1 : -1;
		
	}
	
	public int probar(double[] entradas) {
	
		double neta = calcularNeta(entradas);
		return activate(neta);
		
	}

	public double[] getPesosSinapticos() {
		return pesosSinapticos;
	}

	public void setPesosSinapticos(double[] pesosSinapticos) {
		this.pesosSinapticos = pesosSinapticos;
	}

	@Override
	public String toString() {
		return "Perceptron [pesosSinapticos=" + Arrays.toString(pesosSinapticos) + "]";
	}
	
	
	
}