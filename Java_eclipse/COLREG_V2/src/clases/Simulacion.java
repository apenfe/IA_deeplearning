package clases;

import agente.Agente;
import entorno.Entorno;
import red.*;
import visual.EstablecerCasillas;
import visual.Plot5Agent;
import visual.Plot6Agent;
import ga.*;
import processing.core.PApplet;

public class Simulacion{
	
	private double[] adn_red = new double[0];
	private Entorno entorno;
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
	
	public void probarRandom() {
		
		EstablecerCasillas applet = new EstablecerCasillas();
		applet.setXY((int)entorno.getAncho(),(int)entorno.getAlto());
		 PApplet.runSketch(new String[]{"visual/EstablecerCasillas"}, applet);
		double[] meter = new double[4];
	    
	    do {
	    	int count=0;
	    	
	    	meter = applet.getInOut();
	    	
	    	for (int i = 0; i < meter.length; i++) {
	    		
	    		System.out.println(meter[i]);
				if(meter[i]!=0) {
					count++;
				}
			}
	    	
	    	if(count==4) {
	    		break;
	    	}
	    	
	    }while(true);
	    
	    this.entorno.setAreaAprox(20);
		this.entorno.setSalidaX(meter[3]); // y
		this.entorno.setSalidaY(meter[2]); // x
		
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

					if(agentes[i].win()) {
						System.err.println("Agente: "+i);
					}else {
						System.out.println("Agente: "+i);
					}
				
					break;
				}

			} while (true);
			
		}
		
		Plot6Agent plot = new Plot6Agent();
		plot.setXY((int)entorno.getAncho(),(int)entorno.getAlto());
		plot.setBarcos(agentes);
		plot.setInOut(entorno.getEntradaX(),entorno.getEntradaY(),entorno.getSalidaX(),entorno.getSalidaY());
	    PApplet.runSketch(new String[]{"visual/Plot6Agent"}, plot);

	}
	
	public void probarADN() {
		

		String nombreADN = Entradas.texto("Inserte el nombre del ADN a cargar: ");
			
		if(cargarADN(this.red.getNombre(),nombreADN)) {
				
			System.out.println("ADN cargado correctamente");
				
		}else {
				
			System.err.println("Error al cargar el ADN");
			return;
				
		}
		
		this.entorno.setAreaAprox(20);
		this.entorno.setSalidaX(500); // y
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
			
			agentes[i].setCromosomas(this.adn_red);
			
		}

		System.out.println("Comienzo de la prueba...");
		
		for (int i = 0; i < agentes.length; i++) {
			
			red.setParametros(agentes[i].getCromosomas());
			
			do {

				double[] entradas = agentes[i].sensores();
				double[] salidas = red.probarRed(entradas);
				agentes[i].acciones(salidas);

				if (agentes[i].fin() || agentes[i].getPasos() > 17000) {
					
					if(agentes[i].win()) {
						System.err.println("Agente: "+i);
					}else {
						System.out.println("Agente: "+i);
					}
					
					break;
				}

			} while (true);
			
		}
		
		Plot6Agent applet = new Plot6Agent();
		applet.setXY((int)entorno.getAncho(),(int)entorno.getAlto());
		applet.setBarcos(agentes);
		applet.setInOut(entorno.getEntradaX(),entorno.getEntradaY(),entorno.getSalidaX(),entorno.getSalidaY());
	    PApplet.runSketch(new String[]{"visual/Plot6Agent"}, applet);

	}
	
	public void entrenarDesdeCeroAlgoritmogenetico() {
		
		this.establecerEntradaSalida();
		
		System.out.println("Preparación de agentes y entorno...");
		
		//int tamanoPoblacion, double ratioMutacion, double ratioDeCruce, int elite
		int numAgentes = Entradas.entero("¿Cuantos agentes desea añadir a la simulación? ");
		int numGeneraciones = Entradas.entero("¿Cuantas generaciones desea simular? ");
		
		this.ga = new GeneticAlgorithm(numAgentes, 0.02, 0.90, 12,this.entorno);

		Poblacion poblacion = ga.iniciarPoblacion(this.red.getParametros().length); // numero de cromosomas

		ga.calculoFitnessPoblacion(poblacion);
		
		int generacion = 1;
		
		while (ga.condicionTerminacion(generacion,numGeneraciones) == false) {
			
			System.out.println("Generacion: "+generacion+" entorno: "+poblacion.getFittest(0).getEntorno().getNombre());

			poblacion = ga.cruzarPoblacion(poblacion,entorno);
			
			poblacion = ga.mutarPoblacion(poblacion);
			
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
			
			generacion++;
			
		}
		
		System.out.println("Solucion encontrada en " + generacion + "generaciones.");
		System.out.println("Mejor solucion: " + poblacion.getFittest(0).toString());
		
		Plot6Agent applet = new Plot6Agent();
		applet.setXY((int)entorno.getAncho(),(int)entorno.getAlto());
		applet.setBarcos(poblacion.getIndividuals());
		applet.setInOut(entorno.getEntradaX(),entorno.getEntradaY(),entorno.getSalidaX(),entorno.getSalidaY());
	    PApplet.runSketch(new String[]{"visual/Plot6Agent"}, applet);
	    
	    boolean adn=false;
		
		String respuesta = Entradas.texto("¿Desea guardar el ADN? S - SI ");
		
		if(respuesta.equalsIgnoreCase("S")) {
			
			adn=true;
			
		}
		
		if(adn) {
			
			String nombreADN = Entradas.texto("Inserte el nombre del ADN a guadar: ");
			
			if(guardarADN(nombreADN,poblacion.getFittest(0))) {
				
				System.out.println("ADN guardado correctamente");
				
			}else {
				
				System.err.println("Error al guardar el ADN");
				
			}
			
		}

	}
	
	private void establecerEntradaSalida() {
		
		EstablecerCasillas applet = new EstablecerCasillas();
		applet.setXY((int)entorno.getAncho(),(int)entorno.getAlto());
		 PApplet.runSketch(new String[]{"visual/EstablecerCasillas"}, applet);
		double[] meter = new double[4];
	    
	    do {
	    	int count=0;
	    	
	    	meter = applet.getInOut();
	    	
	    	for (int i = 0; i < meter.length; i++) {
	    		
	    		System.out.println(meter[i]);
				if(meter[i]!=0) {
					count++;
				}
			}
	    	
	    	if(count==4) {
	    		break;
	    	}
	    	
	    }while(true);
	    
	    this.entorno.setAreaAprox(20);
		this.entorno.setSalidaX(meter[3]); // y
		this.entorno.setSalidaY(meter[2]); // x
		
	}
	
	public void continuarEntrenamiento() {
		
		String nombreADN = Entradas.texto("Inserte el nombre del ADN a cargar: ");
		
		if(cargarADN(this.red.getNombre(),nombreADN)) {
				
			System.out.println("ADN cargado correctamente");
				
		}else {
				
			System.err.println("Error al cargar el ADN");
			return;
				
		}
		
		this.establecerEntradaSalida();
		
		System.out.println("Preparación de agentes y entorno...");
		
		int numAgentes = Entradas.entero("¿Cuantos agentes desea añadir a la simulación? ");
		int numGeneraciones = Entradas.entero("¿Cuantas generaciones desea simular? ");
		
		this.ga = new GeneticAlgorithm(numAgentes, 0.02, 0.90, 12,this.entorno);

		Poblacion poblacion = ga.iniciarPoblacion(this.red.getParametros().length);

		ga.calculoFitnessPoblacion(poblacion);
		
		for (int i = 0; i < poblacion.size(); i++) {
			
			poblacion.getIndividual(i).setCromosomas(adn_red);
			
		}
		
		int generacion = 1;
		
		while (ga.condicionTerminacion(generacion,numGeneraciones) == false) {
			
			System.out.print("Generacion: "+generacion+" ");

			poblacion = ga.cruzarPoblacion(poblacion,entorno);
			
			poblacion = ga.mutarPoblacion(poblacion);
			
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
			
			generacion++;
			System.out.println();
		}
		
		System.out.println("Solucion encontrada en " + generacion + "generaciones.");
		System.out.println("Mejor solucion: " + poblacion.getFittest(0).toString());
		
		Plot6Agent plot = new Plot6Agent();
		plot.setXY((int)entorno.getAncho(),(int)entorno.getAlto());
		plot.setBarcos(poblacion.getIndividuals());
		plot.setInOut(entorno.getEntradaX(),entorno.getEntradaY(),entorno.getSalidaX(),entorno.getSalidaY());
	    PApplet.runSketch(new String[]{"visual/Plot6Agent"}, plot);
	    
	    boolean adn=false;
		
		String respuesta = Entradas.texto("¿Desea guardar el ADN? S - SI ");
		
		if(respuesta.equalsIgnoreCase("S")) {
			
			adn=true;
			
		}
		
		if(adn) {
			
			nombreADN = Entradas.texto("Inserte el nombre del ADN a guadar: ");
			
			if(guardarADN(nombreADN,poblacion.getFittest(0))) {
				
				System.out.println("ADN guardado correctamente");
				
			}else {
				
				System.err.println("Error al guardar el ADN");
				
			}
			
		}

	}

	public Entorno getEntorno() {
		return entorno;
	}

	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}

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
	
	public boolean guardarADN(String nombreADN, Agente mejor) {
		
		DAO db = new DAO();
		
		
		
		if(db.guardarGenes(red.getNombre(),nombreADN, mejor.getCromosomas())) {
			
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