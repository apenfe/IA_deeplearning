package redv2;

import java.util.Arrays;

/*
 * PASO 1: Asignar valores aleatorios de pesos y umbral/bias (se sugiere entre 1 y -1)
 * 
 * PASO 2: Presentar el vector de entradas y especificar vector salida deseada
 * 
 * PASO 3: Calcular la neta (peso*entrada)+bias
 * 
 * PASO 4: Aplicar funcion, para el caso, activacion binaria o de escalon
 * 
 * PASO 5: Actualizar pesos de la capa de salida new_peso = peso + (incremento) * entrada
 * 
 * PASO 6: Calculo de la funcion perdida
 * 
 * PASO 7: SI la funcion perdida es diferente de 0 volvemos a PASO 2
 * 
 */

public class Perceptron {
	
	private double alfa;
	private double bias;
	private int funcion;
	private double[] pesosSinapticos = new double[0];
	
	public Perceptron() {
		
		this.funcion = 0;
		this.bias = 0;
		this.alfa = 0;
		
	}
	
	public Perceptron(double alfa, double bias, int funcion, double[] pesosSinapticos) {
		
		this.funcion = funcion;
		this.bias = bias;
		this.alfa = alfa;
		this.pesosSinapticos=pesosSinapticos;
		
	}
	
	public double calcularNeta(double[] entradas) {
		
		double neta = 0;
		
		for (int i = 0; i < entradas.length; i++) {
			
			neta += entradas[i] * this.pesosSinapticos[i];
			
		}
		
		neta += bias;
		
		return neta;
		
	}

	public double activate(double neta) {
		
		if(this.funcion==0) {
			
			return (neta >= 0) ? 1.0 : -1.0; // Funcion signo {-1,1}

		}else if(this.funcion==1){
			
			return neta; // Funcion Adaline
			
		}else if(this.funcion==2){
			
			return (neta >= 0) ? 0 : 1; // Funcion escalon {0,1}
			
		}else if(this.funcion==3){
			
			return (1/(1+Math.pow(Math.E, -neta))); // Funcion Sigmoide
			
		}else if(this.funcion==4){
			
			return ((Math.pow(Math.E, neta)-Math.pow(Math.E, -neta))/(Math.pow(Math.E, neta)+Math.pow(Math.E, -neta))); // Funcion Tangente Hiperbolica
			
		}else if(this.funcion==5){ // FUNCION RELU
			
			if(neta<0) {
				return 0;
			}else {
				return neta;
			}
			
		}else if(this.funcion==6){ // FUNCION LEAKY RELU
			
			if(neta<0) {
				return 0.1*neta;
			}else {
				return neta;
			}
			
		}else if(this.funcion==7){ // FUNCION ELU
			
			if(neta<0) {
				return this.alfa*(Math.pow(Math.E, neta)-1);
			}else {
				return neta;
			}
			
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

	public int getFuncion() {
		return funcion;
	}

	public void setFuncion(int funcion) {
		this.funcion = funcion;
	}

	public double getBias() {
		return bias;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}

	public double getAlfa() {
		return alfa;
	}

	public void setAlfa(double alfa) {
		this.alfa = alfa;
	}

	@Override
	public String toString() {
		
		return "Perceptron [alfa=" + alfa + ", bias=" + bias + ", funcion=" + funcion + ", pesosSinapticos="
				+ Arrays.toString(pesosSinapticos) + "]";
	}
	
}