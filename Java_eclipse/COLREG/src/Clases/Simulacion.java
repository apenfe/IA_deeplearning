package Clases;

import REDES.RedNeuronal;

public class Simulacion{
	
	private String nombre;
	private Entorno entorno;
	private Barco[] barcos = new Barco[0];
	private double[][] pesos = new double[0][0];
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
	
	// CREO ENTORNO Y 10 BARCOS
	
	// CREO RED NEURONAL
	
	// ASIGNO VALOR ALEATORIO DE PESOS PARA CADA BARCO
	
	// SIMULO CADA BARCO CON ESOS PESOS HASTA QUE UNO LLEGUE A META O SALGA DEL MAPA O ITERACIONES SEAN MUY GRANDES
	
	// CALCULO LOS PUNTOS DE CADA UNO Y VEO CUALES SON MEJORES
	
	// DEJO LOS 3 MEJORES
	
	/*
	 * while no se cumpla el criterio de terminación do
	      Selección de los cromosomas más aptos en la nueva 		población
	      Cruzamiento de los cromosomas
	      Mutación de los cromosomas
	      Evaluación de los cromosomas
  end while
  Devuelve la mejor solución (la más apta) en la población
	 */
	 // REPITO ESTO POR UNAS CUANTAS ITERACIONES, ME QUEDO CON EL MEJOR Y LO GUARDO EN LA GRAFICA PARA EXCEL


	
}