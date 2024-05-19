package clases;

import entorno.Entorno;
import red.*;
import ga.*;

public class Simulacion{
	
	private Entorno entorno;
	private Barco[] barcos = new Barco[0];
	private RedNeuronal red;
	private GeneticAlgorithm ga;
	
	public Simulacion(boolean entorno) { // crear simulacion desde 0
		
		System.err.println("\nBienvenido al asistente de generacion de simulaciones...");
		
		if(entorno) {
			
			System.err.println("\tCreación de entorno...");
			this.entorno= new Entorno(0.2,20);
			
		}
		
		System.err.println("\tCreación de red neuronal...");
		System.out.println("Recomendable entre 3 y 4 capas...");
		int capas = Entradas.entero("Cuantas capas desea en la Red Neuronal? ");
		String nombreRed = Entradas.texto("¿Que nombre desea dar a esta red? ");
		
		int[] numeroNeuronas = new int[capas];
		int[] funciones = new int[capas];
		
		for (int i = 0; i < capas; i++) {
			
			numeroNeuronas[i] = Entradas.entero("Cuantas neuronas desea en la capa "+(i+1)+"? ");
			funciones[i] = Entradas.entero("Que funcion de activacion desea en la capa "+(i+1)+"? ");

		}

		this.red = new RedNeuronal(nombreRed, capas,numeroNeuronas, funciones); 
		
	}
	
	public Simulacion() { // crear simulacion desde 0
		
	}

	public void probar() {

		// 1º establezco el mejor adn en la red asignado pesos sinapticos
		Barco barco = new Barco(0,entorno);
		
		/* linea de testeo */
		//barco.setAdn(asignarPesos_0());
		/* linea de testeo */
		
		//red.asignarPesosSinapticosCapas(barco.getAdn(),barco.sensores().length);

		System.out.println("Comienzo de la prueba...");

		do {

			double[] entradas = barco.sensores();
			double[] salidas = red.probarRed(entradas);
			barco.acciones(salidas);

			if (barco.fin() || barco.getPasos() > 100000) {
				System.out.println("\t\t\tFin simulación del Barco nº, Resumen:");

				if (barco.getPasos() > 100000) {
					System.out.println("\t\t\tEliminado por cantidad excesiva de pasos.");
				} else {
					System.out.println("\t\t\tEliminado por llegada a meta o salida.");
				}
				System.out.println("\t\t\tPuntos: " + barco.getPuntos());
				System.out.println("\t\t\tPasos: " + barco.getPasos());
				barco.printCamino();
			
				break;
			}

		} while (true);

	}

	public Entorno getEntorno() {
		return entorno;
	}

	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}

	public Barco[] getBarcos() {
		return barcos;
	}

	public void setBarcos(Barco[] barcos) {
		this.barcos = barcos;
	}

	public RedNeuronal getRed() {
		return red;
	}

	public void setRed(RedNeuronal red) {
		this.red = red;
	}

	@Override
	public String toString() {
		
		double[] parametros = red.getParametros();
		
		System.out.println("genes de la red: ");
		for (int i = 0; i < parametros.length; i++) {
			
			System.out.print(parametros[i]+", ");
			
		}
		
		System.out.println();
		
		String salida="";
		
		//salida+=entorno.toString();
		salida+=red.toString();
		
		return salida;
		
	}
	
	public boolean cargarEntorno() {
		return true;
	}
	
	public boolean cargarRed() {
		return true;
	}
	
}