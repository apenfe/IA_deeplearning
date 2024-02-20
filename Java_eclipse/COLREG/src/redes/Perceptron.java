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
	
	private int funcion;
	private double[] pesosSinapticos = new double[0];
	
	public Perceptron() {
		
		this.funcion=0;
		
	}
	
	public double calcularNeta(double[] entradas) {
		
		double neta = 0;
		
		for (int i = 0; i < entradas.length; i++) {
			
			neta += entradas[i] * pesosSinapticos[i];
			
		}
		
		return neta;
	}

	public double activate(double neta) {
		
		if(funcion==0) {
			
			return (neta >= 0) ? 1.0 : -1.0; // Funcion signo {-1,1}

		}else if(funcion==1){
			
			return neta; // Funcion Adaline
			
		}else if(funcion==2){
			
			return (neta >= 0) ? 0 : 1; // Funcion escalon {0,1}
			
		}else if(funcion==3){
			
			return (1/(1+Math.pow(Math.E, -neta))); // Funcion Sigmoide
			
		}else if(funcion==4){
			
			return ((Math.pow(Math.E, neta)-Math.pow(Math.E, -neta))/(Math.pow(Math.E, neta)+Math.pow(Math.E, -neta))); // Funcion Tangente Hiperbolica
			
		}else {
			
			return 0.0;
			
		}
		
	}
	
	public double probar(double[] entradas) {
	
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

	public int getFuncion() {
		return funcion;
	}

	public void setFuncion(int funcion) {
		this.funcion = funcion;
	}
	
}