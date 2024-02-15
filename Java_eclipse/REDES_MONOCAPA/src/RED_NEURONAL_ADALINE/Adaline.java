package RED_NEURONAL_ADALINE;

/*
 * PASO 1:
 * 
 * Asignar valores aleatorios de pesos y umbral/bias (se sugiere entre 1 y -1)
 * Asignamos valor al parametro de aprendizaje
 * Asignamos un valor de error minimo aceptado
 * 
 * PASO 2:
 * 
 * Mientras la condicion de parada sea false, se ejecutan pasos del 2 al 7
 * 
 * PASO 3:
 * 
 * Para cada patron de entrenamiento se ejecutan pasos del 4 al 5
 * 
 * PASO 4:
 * 
 * Calcular salida red adaline: salida = neta = sumatorio de w*x + bias
 * 
 * PASO 5:
 * 
 * Se calcula la funcion de perdida, en este caso, error cuadratico promedio:
 * esperada menos la salida
 * 
 * PASO 6: 
 * 
 * actualizar pesos:
 * 
 * w+1 = w + alfa*error*entrada
 * bias+1 = bias * error * alfa
 * 
 * PASO 7: 
 * 
 * Calcular el error o perdidad global de la red, si es menos que un valro minimo, 
 * detenemos algoritmo. En caso contrario se vuelve a paso 2.
 * 
 */

import java.util.Arrays;

public class Adaline {
	
	private int id;
	private double[][] entradas = new double[0][0];
	private double[][] salidaEsperada = new double[0][0];
	private double[] pesosSinapticos = new double[0];
	private double umbral_Bias;
	private final double alfa = 0.1;
	private double error_minimo;
	
	public Adaline(double[][] entradasD, double[][] salidaEsperadaD, double error_minimo) {
		
		this.error_minimo=error_minimo;
		this.entradas=entradasD;
		this.salidaEsperada=salidaEsperadaD;
		this.pesosSinapticos = new double[entradas[0].length];
		this.umbral_Bias = Math.random()* 2 - 1;

		for (int i = 0; i < entradas[0].length; i++) {
			pesosSinapticos[i] = Math.random()* 2 - 1;
		}
		
	}
	
	public Adaline(double[][] entradasD, double[][] salidaEsperadaD, int id, double error_minimo) {
		
		this.error_minimo=error_minimo;
		this.id=id;
		this.entradas=entradasD;
		this.salidaEsperada=salidaEsperadaD;
		this.pesosSinapticos = new double[entradas[0].length];
		this.umbral_Bias = Math.random()* 2 - 1;

		for (int i = 0; i < entradas[0].length; i++) {
			pesosSinapticos[i] = Math.random()* 2 - 1;
		}
		
	}

	
	public double calcularNeta(double[] entradas) {
		
		double neta = 0;
		
		for (int i = 0; i < entradas.length; i++) {
			
			neta += entradas[i] * pesosSinapticos[i];
			
		}
		System.out.println(neta);
		return neta + umbral_Bias;
	}

	public void train() {
		
		for (int i = 0; i < salidaEsperada.length; i++) { // ver salidaEsperada.length  /* OK */
			
			double[] entradaActual = entradas[i];
			
			do {
				
				double neta = calcularNeta(entradaActual);
				
				double error = salidaEsperada[i][id] - neta; // CAMBIO 0 POR ID
				System.out.println(error);
				if(error<=error_minimo) {//costo
					
					break;
					
				}else {
					
					for(int j = 0; j < pesosSinapticos.length; j++) {
						
						pesosSinapticos[j] = pesosSinapticos[j] +(alfa*error*entradaActual[j]); // Aplicando la regla de actualización
						
					}

					umbral_Bias = umbral_Bias + (alfa*error); // Actualización del umbral
					
				}	
				
			}while(true);
			
		}
		
		if(check()) {
			
			System.err.println("\n\tEntrenada correctamente: ");
			System.out.println("\t\t"+toString());
			
		}else {
			train();
		}
		
	}
	
	public boolean probar() {
		
		int cont=0;
		System.out.println(toString());
		
		for (int i = 0; i < salidaEsperada.length; i++) { // 4
			
			double[] entradaActual = entradas[i];
			double salida = calcularNeta(entradaActual);
			
			System.out.println("\nTanda "+(i+1)+"º.");
			
			System.out.print("Entradas: ");
			
			for (int j = 0; j < entradaActual.length; j++) {
				System.out.print(entradaActual[j]+", ");
			}
		
			
			if(salida==salidaEsperada[i][id]) { // 0 por id
				
				System.out.println("\nSalida esperada: "+salidaEsperada[i][id]+" ---> Salida real: "+salida); // 0 por id
				System.out.println();
				cont++;
				
			}else {
				
				System.err.println("Salida esperada: "+salidaEsperada[i][id]+" ---> Salida real: "+salida); // 0 por id
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
			
			double[] entradaActual = entradas[i];
			double salida = calcularNeta(entradaActual);
			
			if(salida==salidaEsperada[i][id]) { // 0 por id
				
				cont++;
				
			}
			
		}
		
		if(cont<salidaEsperada.length) {
			return false;
		}else {
			return true;
		}
		
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Adaline [id=" + id + ", pesosSinapticos=" + Arrays.toString(pesosSinapticos) + ", umbral_Bias="
				+ umbral_Bias + ", alfa=" + alfa + ", error_minimo=" + error_minimo + "]";
	}
	
	

}