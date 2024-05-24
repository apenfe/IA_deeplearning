package clases;

import agente.Agente;
import agente.Barco;
import entorno.Entorno;
import red.*;
import visual.Plot4;
import visual.Plot4Agent;
import visual.Plot5Agent;
import ga.*;
import processing.core.PApplet;

public class Simulacion{
	
	private double[] adn_red = new double[0];
	private Entorno entorno;
	//private Barco[] barcos = new Barco[0];
	private Agente[] agentes = new Agente[0];
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
/*
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
					//barcos[i].printCamino();
					System.out.println();
					break;
				}

			} while (true);
			
		}
		
		Plot4 applet = new Plot4();
		applet.setXY((int)entorno.getAncho(),(int)entorno.getAlto());
		applet.setBarcos(barcos);
		applet.setInOut(entorno.getEntradaX(),entorno.getEntradaY(),entorno.getSalidaX(),entorno.getSalidaY());
	    PApplet.runSketch(new String[]{"visual/Plot4"}, applet);

	}
	*/
	public void entrenarDesdeCero() {
		
		this.entorno.setAreaAprox(10);
		this.entorno.setSalidaX(800); // y
		this.entorno.setSalidaY(1700); // x
		System.out.println("Preparación de agentes y entorno...");
		int numAgentes = Entradas.entero("¿Cuantos agentes desea añadir a la simulación? ");
		agentes = new Agente[numAgentes];
		
		for (int i = 0; i < agentes.length; i++) {
			
			agentes[i] = new Agente(i,this.entorno);
			double[] param = new double[this.red.getParametros().length];
			
			for (int j = 0; j < param.length; j++) {
				
				param[j]= -1 + 2 * Math.random();
				
			}
			
			agentes[i].setCromosomas(param);
			
		}

		System.out.println("Comienzo de la prueba...");
		
		for (int i = 0; i < agentes.length; i++) {
			
			red.setParametros(agentes[i].getCromosomas());
			
			do {

				double[] entradas = agentes[i].sensores();
				double[] salidas = red.probarRed(entradas);
				agentes[i].acciones(salidas);

				if (agentes[i].fin() || agentes[i].getPasos() > 17000) {
					System.out.println("\t\t\tFin simulación del Barco nº "+(i+1)+", Resumen:");

					if (agentes[i].getPasos() > 17000) {
						System.out.println("\t\t\tEliminado por cantidad excesiva de pasos.");
					} else {
						System.out.println("\t\t\tEliminado por llegada a meta o salida.");
					}
					
					if(agentes[i].win()) {
						System.err.println("\t\t\tPuntos: " + agentes[i].getFitness());
						System.err.println("\t\t\tPasos: " + agentes[i].getPasos());
						System.out.println();
					}else {
						System.out.println("\t\t\tPuntos: " + agentes[i].getFitness());
						System.out.println("\t\t\tPasos: " + agentes[i].getPasos());
						System.out.println();
						
					}
					
					break;
				}

			} while (true);
			
		}
		
		Plot5Agent applet = new Plot5Agent();
		applet.setXY((int)entorno.getAncho(),(int)entorno.getAlto());
		applet.setBarcos(agentes);
		applet.setInOut(entorno.getEntradaX(),entorno.getEntradaY(),entorno.getSalidaX(),entorno.getSalidaY());
	    PApplet.runSketch(new String[]{"visual/Plot5Agent"}, applet);

	}
	
	public void entrenarDesdeCeroAlgoritmogenetico() {
		
		this.entorno.setAreaAprox(10);
		this.entorno.setSalidaX(800); // y
		this.entorno.setSalidaY(1700); // x
		System.out.println("Preparación de agentes y entorno...");
		
		//int tamanoPoblacion, double ratioMutacion, double ratioDeCruce, int elite
		int numAgentes = Entradas.entero("¿Cuantos agentes desea añadir a la simulación? ");
		int numGeneraciones = Entradas.entero("¿Cuantas generaciones desea simular? ");
		
		this.ga = new GeneticAlgorithm(numAgentes, 0.01, 0.95, 12,this.entorno);

		Poblacion poblacion = ga.iniciarPoblacion(this.red.getParametros().length); // numero de cromosomas

		ga.calculoFitnessPoblacion(poblacion);
		
		///////
		System.err.println(entorno.getAreaAprox());
		///
		int generacion = 1;
		
		while (ga.condicionTerminacion(generacion,numGeneraciones) == false) {
			
			System.out.println("Generacion: "+generacion+" entorno: "+poblacion.getFittest(0).getEntorno().getNombre());

			//System.out.println("Fitness de la poblacion: "+poblacion.getPopulationFitness()+"%, Best solution: " + poblacion.getFittest(0).toString());

			poblacion = ga.cruzarPoblacion(poblacion,entorno);
			
			poblacion = ga.mutarPoblacion(poblacion,entorno);
			
			ga.calculoFitnessPoblacion(poblacion);
			
			for (int i = 0; i < numAgentes; i++) {
				
				red.setParametros(poblacion.getIndividual(i).getCromosomas());
				
				do {

					double[] entradas = poblacion.getIndividual(i).sensores();
					double[] salidas = red.probarRed(entradas);
					poblacion.getIndividual(i).acciones(salidas);

					if (poblacion.getIndividual(i).win() || poblacion.getIndividual(i).lose() || poblacion.getIndividual(i).getPasos() > 17000) {
						if(poblacion.getIndividual(i).win()) {
							System.err.print("-");
						}
						break;
					}

				} while (true);
				
			}
			//System.out.println();
			generacion++;
			
		}
		
		System.out.println("Solucion encontrada en " + generacion + "generaciones.");
		System.out.println("Mejor solucion: " + poblacion.getFittest(0).toString());
		
		//****************************************************************************************
		
		Plot5Agent applet = new Plot5Agent();
		applet.setXY((int)entorno.getAncho(),(int)entorno.getAlto());
		applet.setBarcos(poblacion.getIndividuals());
		applet.setInOut(entorno.getEntradaX(),entorno.getEntradaY(),entorno.getSalidaX(),entorno.getSalidaY());
	    PApplet.runSketch(new String[]{"visual/Plot5Agent"}, applet);

	}
	
	public void continuarEntrenamiento() {
		
		

	}

	public Entorno getEntorno() {
		return entorno;
	}

	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}
/*
	public Barco[] getBarcos() {
		return barcos;
	}

	public void setBarcos(Barco[] barcos) {
		this.barcos = barcos;
	}
	*/
	public Agente[] getAgentes() {
		return agentes;
	}

	public void setAgentes(Agente[] agentes) {
		this.agentes = agentes;
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