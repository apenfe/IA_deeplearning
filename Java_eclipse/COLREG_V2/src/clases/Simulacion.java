package clases;

import entorno.Entorno;
import red.*;
import ga.*;

public class Simulacion{
	
	private double[] adn_red = new double[0];
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

	public void entrenarDesdeCero() {
		
		System.out.println("Preparación de agentes y entorno...");
		int numAgentes = Entradas.entero("¿Cuantos agentes desea añadir a la simulación? ");
		barcos = new Barco[numAgentes];
		
		for (int i = 0; i < barcos.length; i++) {
			
			barcos[i] = new Barco(i,this.entorno);
			double[] param = new double[this.red.getParametros().length];
			
			for (int j = 0; j < param.length; j++) {
				
				param[j]= -1 + 2 * Math.random();
				
			}
			
			barcos[i].setAdn(param);
			
		}

		System.out.println("Comienzo de la prueba...");
		
		for (int i = 0; i < barcos.length; i++) {
			
			red.setParametros(barcos[i].getAdn());
			
			do {

				double[] entradas = barcos[i].sensores();
				double[] salidas = red.probarRed(entradas);
				barcos[i].acciones(salidas);

				if (barcos[i].fin() || barcos[i].getPasos() > 10000) {
					System.out.println("\t\t\tFin simulación del Barco nº "+(i+1)+", Resumen:");

					if (barcos[i].getPasos() > 10000) {
						System.out.println("\t\t\tEliminado por cantidad excesiva de pasos.");
					} else {
						System.out.println("\t\t\tEliminado por llegada a meta o salida.");
					}
					System.out.println("\t\t\tPuntos: " + barcos[i].getPuntos());
					System.out.println("\t\t\tPasos: " + barcos[i].getPasos());
					barcos[i].printCamino();
					System.out.println();
					break;
				}

			} while (true);
			
		}
		
		

	}
	
	public void continuarEntrenamiento() {
		
		

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
		
		salida+=red.toString();
		
		return salida;
		
	}
	
	public boolean cargarEntorno(String nombre) {
		
		DAO db = new DAO();
		
		Entorno cargado =db.cargararEntorno(nombre);
		
		if(cargado!=null) {
			this.entorno=cargado;
			return true;
		}
		
		return false;
		
	}
	
	public boolean cargarRed(String nombre) {
		
		DAO db = new DAO();
		
		RedNeuronal cargado =db.cargarRedNeuronal(nombre);
		
		if(cargado!=null) {
			this.red=cargado;
			return true;
		}
		
		return false;

	}
	
	public boolean cargarADN(String nombreRed,String nombreADN) {
		
		DAO db = new DAO();
		
		double[] cargado =db.cargarGenes(nombreRed,nombreADN);
		
		if(cargado.length>0) {
			this.adn_red=cargado;
			return true;
		}
		
		return false;
		
	}
	
	public boolean guardarADN(String nombreADN) {
		
		DAO db = new DAO();
		
		
		
		if(db.guardarGenes(red.getNombre(),nombreADN, red.getParametros())) {
			
			return true;
		}
		
		return false;
		
	}

	public double[] getAdn_red() {
		return adn_red;
	}

	public void setAdn_red(double[] adn_red) {
		this.adn_red = adn_red;
	}
	
}