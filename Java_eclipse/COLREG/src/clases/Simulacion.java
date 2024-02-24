package clases;

import java.io.FileWriter;
import java.util.Arrays;

import processing.core.PApplet;
import redes.RedNeuronal;

public class Simulacion{
	
	private String nombre;
	private Entorno entorno;
	private Barco[] barcos = new Barco[0];
	private RedNeuronal red;
	// MEJOR ADN HASTA EL MOMENTO
	
	public Simulacion() {
		
		System.err.println("\nBienvenido al asistente de generacion de simulaciones...");
		this.nombre=Entradas.texto("¿Que nombre desea dar a esta simulacion? ");
		
		System.err.println("\tCreación de entorno...");
		this.entorno= new Entorno(0.2,0.4,nombre);
		
		System.err.println("\tCreación de entidades...");
		int cantidad = Entradas.entero("\n¿Cuantos barcos desea crear para el entrenamiento? ");
		
		barcos = new Barco[cantidad];
		
		System.err.println("\tCreación de red neuronal...");
		System.out.println("Recomendable entre 3 y 4 capas...");
		int capas = Entradas.entero("Cuantas capas desea en la Red Neuronal? ");
		this.red = new RedNeuronal(capas,nombre); 
		
		for (int i = 0; i < cantidad; i++) {
			barcos[i]= new Barco(i,entorno);
			barcos[i].setAdn(asignarPesos_0());
		}
		
	}
	
	public Simulacion(String nombre, String[] datos_entorno, String[] datos_red) {
		
		this.nombre=nombre;
		this.entorno= new Entorno(datos_entorno);
		int cantidad = Entradas.entero("Cuantos barcos desea crear? ");
		barcos = new Barco[cantidad];
		this.red = new RedNeuronal(datos_red); 
		
		for (int i = 0; i < cantidad; i++) {
			barcos[i]= new Barco(i,entorno);
			barcos[i].setAdn(asignarPesos_0());
		}
		
	}
	
	public double[] asignarPesos_0() {
		
		int cromosomas = red.getPesosTotales(barcos[0].sensores().length);
		double[] salida = new double[cromosomas];
		
		for (int i = 0; i < salida.length; i++) {
			
			salida[i]= Math.random() * 6 - 3; // Genera un número entre -1 y 1
			
		}
		
		return salida;	
		
	}
	
	public void entrenamiento() {
		
		int generaciones = Entradas.entero("Cuantas generaciones desea crear para el primer entrenamiento? ");
		
		int cont = -1;
		
		System.out.println("Asignando pesos Aleatorios iniciales...");

		for (int i = 0; i < barcos.length; i++) {
			
			barcos[i].setAdn(asignarPesos_0());
			
		}
		
		System.out.println("Comienzo del entrenamiento...");
		
		
		
		do {
			
			cont++;
			System.out.println("\tGeneracion nº "+cont+":");
			
			for (int j = 0; j < barcos.length; j++) {
				
				System.out.println("\t\tBarco nº "+(j+1)+".");
				red.asignarPesosSinapticosCapas(barcos[j].getAdn(),barcos[j].sensores().length); // es esto lo que falta?
				
				do {
					
					double[] entradas = barcos[j].sensores();
					int[] salidas = red.probarRed(entradas);
					barcos[j].acciones(salidas);
					
					if(barcos[j].fin()||barcos[j].getPasos()>10000) {
						System.out.println("\t\t\tFin simulación Barco nº "+(j+1)+", Resumen:");
						
						if(barcos[j].getPasos()>10000) {
							System.out.println("\t\t\tEliminado por cantidad excesiva de pasos.");
						}else {
							
							System.out.println("\t\t\tEliminado por llegada a meta o salida.");
	
						}
						System.out.println("\t\t\tPuntos: "+barcos[j].getPuntos());
						System.out.println("\t\t\tPasos: "+barcos[j].getPasos());
						break;
					}
					
				} while (true);
				
			}
			
			System.out.println("\n\tGeneracion nº "+cont+":");
			SeleccionMejorCormosoma();
			cruzarCormosoma();
			mutacionCormosoma();
			evaluacionCormosoma();
			
		}while(cont<generaciones);
		
	}
	
	public void SeleccionMejorCormosoma() {
		
		int contPuntosMax=Integer.MIN_VALUE;
	
		Barco mejor=null;
		
		for (int i = 0; i < barcos.length; i++) {
			
			int puntos = barcos[i].getPuntos();
			
			if(puntos>contPuntosMax) {
				mejor=barcos[i];
				contPuntosMax=puntos;
			}

		}
		
		System.out.println("\tEl mejor ejemplar es el barco "+mejor.getId()+".");
		System.out.println("\tADN ==> "+mejor.getAdn());
		System.out.println("\tPuntos ==> "+mejor.getPuntos());
		System.out.println("\tPasos ==> "+mejor.getPasos());
		
		try {
			Thread.sleep(8000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		
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
		
		/* linea de testeo */
		barco.setAdn(asignarPesos_0());
		/* linea de testeo */
		
		red.asignarPesosSinapticosCapas(barco.getAdn(),barco.sensores().length);

		System.out.println("Comienzo de la prueba...");

		do {

			double[] entradas = barco.sensores();
			int[] salidas = red.probarRed(entradas);
			barco.acciones(salidas);
			System.out.println(barco.getPasos());

			if (barco.fin() || barco.getPasos() > 100000) {
				System.out.println("\t\t\tFin simulación del Barco nº, Resumen:");

				if (barco.getPasos() > 100000) {
					System.out.println("\t\t\tEliminado por cantidad excesiva de pasos.");
				} else {
					System.out.println("\t\t\tEliminado por llegada a meta o salida.");
				}
				System.out.println("\t\t\tPuntos: " + barco.getPuntos());
				System.out.println("\t\t\tPasos: " + barco.getPasos());
				barco.camino();
			
				break;
			}

		} while (true);

	}
	
	public boolean guardarSimulacion(String ruta) {
		
		try {

			FileWriter escritor = new FileWriter(ruta,true);
			
			String simulator = ""+this.nombre;
				
			escritor.write("\n"+simulator);
			escritor.close();
			
			return true;
			
		} catch (Exception e) {

			System.err.println("ERROR AL GUARDAR ARCHIVO " + ruta);
			return false;

		}
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		String salida="Simulacion [nombre=" + nombre + ", entorno=" + entorno + ", barcos=" + Arrays.toString(barcos)
				+ ", red=" + red + "]";
		
		salida+=entorno.toString();
		salida+=red.toString();
		
		return salida;
		
	}
	
	
}