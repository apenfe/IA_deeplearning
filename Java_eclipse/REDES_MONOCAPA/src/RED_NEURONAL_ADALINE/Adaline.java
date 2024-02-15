package RED_NEURONAL_ADALINE;

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

public class Adaline {
	
	private int id;
	private double[][] entradas = new double[0][0];
	private double[][] salidaEsperada = new double[0][0];
	private double[] pesosSinapticos = new double[0];
	private double umbral_Bias;
	
	public Adaline(double[][] entradasD, double[][] salidaEsperadaD) {
		
		this.entradas=entradasD;
		this.salidaEsperada=salidaEsperadaD;
		this.pesosSinapticos = new double[entradas[0].length];
		this.umbral_Bias = Math.random()* 2 - 1;

		for (int i = 0; i < entradas[0].length; i++) {
			pesosSinapticos[i] = Math.random()* 2 - 1;
		}
		
	}
	
	public Adaline(double[][] entradasD, double[][] salidaEsperadaD, int id) {
		
		this.id=id;
		this.entradas=entradasD;
		this.salidaEsperada=salidaEsperadaD;
		this.pesosSinapticos = new double[entradas[0].length];
		this.umbral_Bias = Math.random()* 2 - 1;

		for (int i = 0; i < entradas[0].length; i++) {
			pesosSinapticos[i] = Math.random()* 2 - 1;
		}
		
	}

	
	public float calcularNeta(double[] entradas) {
		
		float neta = 0;
		
		for (int i = 0; i < entradas.length; i++) {
			
			neta += entradas[i] * pesosSinapticos[i];
			
		}
		
		return neta + (float)umbral_Bias;
	}

	public int activate(float neta) {
		
		return (neta >= 0) ? 1 : 0;
		
	}

	public void train() {
		
		for (int i = 0; i < salidaEsperada.length; i++) { // ver salidaEsperada.length  /* OK */
			
			double[] entradaActual = entradas[i];
			
			do {
				
				float neta = calcularNeta(entradaActual);
				int salida = activate(neta);
				
				double error = salidaEsperada[i][id] - salida; // CAMBIO 0 POR ID
				
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
			float neta = calcularNeta(entradaActual);
			int salida = activate(neta);
			
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
			float neta = calcularNeta(entradaActual);
			int salida = activate(neta);
			
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

	

	@Override
	public String toString() {
		return "Perceptron [id=" + id  + ", pesosSinapticos=" + Arrays.toString(pesosSinapticos)
				+ ", umbral_Bias=" + umbral_Bias + "]";
	}

	public int getId() {
		return id;
	}

}