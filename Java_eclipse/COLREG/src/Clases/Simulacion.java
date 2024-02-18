package Clases;

import REDES.Perceptron;
import REDES.RedNeuronal;

public class Simulacion{
	
	private String nombre;
	private Entorno entorno;
	private Barco[] barcos = new Barco[0];
	private RedNeuronal red;
	
	public Simulacion() {
		
		this.nombre=Entradas.texto("Que nombre desea dar a esta simulacion? ");
		this.entorno= new Entorno(0.2,0.4);
		int cantidad = Entradas.entero("Cuantos barcos desea crear para el entrenamiento? ");
		
		barcos = new Barco[cantidad];
		
		for (int i = 0; i < cantidad; i++) {
			barcos[i]= new Barco(i,entorno);
			barcos[i].setAdn(asignarPesos_0());
		}
		
		System.out.println("Recomendable entre 3 y 4 capas...");
		int capas = Entradas.entero("Cuantas capas desea en la Red Neuronal? ");
		this.red = new RedNeuronal(capas); 
		
	}
	
	public double[] asignarPesos_0() {
		
		int cromosomas = red.getPesosTotales(barcos[0].sensores().length);
		double[] salida = new double[cromosomas];
		
		for (int i = 0; i < salida.length; i++) {
			
			salida[i]= Math.random() * 2 - 1; // Genera un número entre -1 y 1
			
		}
		
		return salida;	
		
	}
	
	// ver esta seccion, posiblemente lo tengo que mover a redneuronal.java
	// la idea es asignar los pesos a cada neurona dado el adn de la entidad
	
	public void asignarPesosSinapticos(double[] pesos) {
		
		Perceptron[] neuronas = red.obtnerPerceptrones();
		
		int genesPorNeurona = pesos.length/neuronas.length;
		
		for (int i = 0; i < neuronas.length; i++) {
			
			for (int j = 0; j < pesos.length; i++) {
				
				neuronas[i].setPesosSinapticos(pesos);
				
			}
			
		}
 		
	}
	
	public void entrenamiento() {
		
		int generaciones = Entradas.entero("Cuantas generaciones desea crear? ");
		
		int cont = -1;
		
		for (int i = 0; i < barcos.length; i++) {
			
			barcos[i].setAdn(asignarPesos_0());
			
		}
		
		System.out.println("Comienzo del entrenamiento...");
		
		do {
			
			cont++;
			System.out.println("\tGeneracion nº "+cont+":");
			
			for (int j = 0; j < barcos.length; j++) {
				
				System.out.println("\t\tBarco nº "+(j+1)+".");
				
				do {
					
					double[] entradas = barcos[j].sensores();
					int[] salidas = red.probarRed(entradas);
					barcos[j].acciones(salidas);
					
					if(barcos[j].fin()||barcos[j].getPasos()>30000) {
						System.out.println("\t\t\tFin simulación Barco nº "+(j+1)+", Resumen:");
						
						if(barcos[j].getPasos()>30000) {
							System.out.println("\t\t\tEliminado por cantidad excesiva de pasos.");
						}else {
							
							System.out.println("\t\t\tEliminado por llegada a meta o salida.");
	
						}
						System.out.println("\t\t\tPuntos: "+barcos[j].getPuntos());
						System.out.println("\t\t\tPuntos: "+barcos[j].getPasos());
						break;
					}
					
				} while (true);
				
			}
			
			SeleccionMejorCormosoma();
			cruzarCormosoma();
			mutacionCormosoma();
			evaluacionCormosoma();
			
		}while(cont<generaciones);
		
	}
	
	public void SeleccionMejorCormosoma() {
		
	}
	
	public void cruzarCormosoma() {

	}

	public void mutacionCormosoma() {

	}

	public void evaluacionCormosoma() {

	}

	// CREO ENTORNO Y 10 BARCOS

	// CREO RED NEURONAL

	// ASIGNO VALOR ALEATORIO DE PESOS PARA CADA BARCO

	// SIMULO CADA BARCO CON ESOS PESOS HASTA QUE UNO LLEGUE A META O SALGA DEL MAPA
	// O ITERACIONES SEAN MUY GRANDES

	// CALCULO LOS PUNTOS DE CADA UNO Y VEO CUALES SON MEJORES

	// DEJO LOS 3 MEJORES

	/*
	 * while no se cumpla el criterio de terminación do Selección de los cromosomas
	 * más aptos en la nueva población Cruzamiento de los cromosomas Mutación de los
	 * cromosomas Evaluación de los cromosomas end while Devuelve la mejor solución
	 * (la más apta) en la población
	 */
	// REPITO ESTO POR UNAS CUANTAS ITERACIONES, ME QUEDO CON EL MEJOR Y LO GUARDO
	// EN LA GRAFICA PARA EXCEL

	public void probar() {

		// 1º establezco el mejor adn en la red asignado pesos sinapticos
		Barco barco = new Barco(0,entorno);

		System.out.println("Comienzo de la prueba...");

		do {

			double[] entradas = barco.sensores();
			int[] salidas = red.probarRed(entradas);
			barco.acciones(salidas);

			if (barco.fin() || barco.getPasos() > 30000) {
				System.out.println("\t\t\tFin simulación del Barco nº, Resumen:");

				if (barco.getPasos() > 30000) {
					System.out.println("\t\t\tEliminado por cantidad excesiva de pasos.");
				} else {
					System.out.println("\t\t\tEliminado por llegada a meta o salida.");
				}
				System.out.println("\t\t\tPuntos: " + barco.getPuntos());
				System.out.println("\t\t\tPuntos: " + barco.getPasos());
				break;
			}

		} while (true);

	}

}