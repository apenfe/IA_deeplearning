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

	private int[][] entradas = new int[0][0];
	private int[][] salidaEsperada = new int[0][0];
	private double[] pesosSinapticos = new double[0];
	private double umbral_Bias;

	public Perceptron(int[][] entradas, int[][] salidaEsperada) {
		
		this.entradas=entradas;
		this.salidaEsperada=salidaEsperada;
		this.pesosSinapticos = new double[entradas[0].length];
		this.umbral_Bias = Math.random()* 2 - 1;

		for (int i = 0; i < entradas[0].length; i++) {
			pesosSinapticos[i] = Math.random()* 2 - 1;
		}
		
	}

	public double calcularNeta(int[] entradas) {
		
		double neta = 0;
		
		for (int i = 0; i < entradas.length; i++) {
			
			neta += entradas[i] * pesosSinapticos[i];
			
		}
		
		return neta + umbral_Bias;
	}

	private int activate(double neta) {
		
		return (neta >= 0) ? 1 : 0;
		
	}

	public void train() {
		
		for (int i = 0; i < salidaEsperada.length; i++) { // 4
			
			int[] entradaActual = entradas[i];
			
			do {
				
				double neta = calcularNeta(entradaActual);
				int salida = activate(neta);
				
				double error = salidaEsperada[i][0] - salida;
				
				if(error==0) {//costo
					
					break;
					
				}else {
					
					for(int j = 0; j < pesosSinapticos.length; j++) {
						
						pesosSinapticos[j] = pesosSinapticos[j] +(1*error*entradaActual[j]); // Aplicando la regla de actualización
						
					}

					umbral_Bias = umbral_Bias + (1*error); // Actualización del umbral
					
				}	
				
			}while(true);
			
		}
		
		if(check()) {
			
			System.err.println("Entrenada correctamente...");
			
		}else {
			train();
		}
		
	}
	
	public boolean probar() {
		
		int cont=0;
		printWeights();
		
		for (int i = 0; i < salidaEsperada.length; i++) { // 4
			
			int[] entradaActual = entradas[i];
			double neta = calcularNeta(entradaActual);
			int salida = activate(neta);
			
			System.out.println("\nTanda "+(i+1)+"º.");
			
			System.out.print("Entradas: ");
			
			for (int j = 0; j < entradaActual.length; j++) {
				System.out.print(entradaActual[j]+", ");
			}
		
			
			if(salida==salidaEsperada[i][0]) {
				
				System.out.println("\nSalida esperada: "+salidaEsperada[i][0]+" ---> Salida real: "+salida);
				System.out.println();
				cont++;
				
			}else {
				
				System.err.println("Salida esperada: "+salidaEsperada[i][0]+" ---> Salida real: "+salida);
				System.out.println();
				
			}
			
		}
		
		if(cont<salidaEsperada.length) {
			return false;
		}else {
			return true;
		}
		
	}
	
	public boolean check() {
		
		int cont=0;
		
		for (int i = 0; i < salidaEsperada.length; i++) { // 4
			
			int[] entradaActual = entradas[i];
			double neta = calcularNeta(entradaActual);
			int salida = activate(neta);
			
			if(salida==salidaEsperada[i][0]) {
				
				cont++;
				
			}
			
		}
		
		if(cont<salidaEsperada.length) {
			return false;
		}else {
			return true;
		}
		
	}

	public void printWeights() {
		
		System.out.println("Pesos sinapticos: "+Arrays.toString(pesosSinapticos));
		System.out.println("Umbral: "+umbral_Bias);
		
	}

}