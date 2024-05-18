package clases;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import redv2.Capas;
import redv2.RedNeuronal;

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
		this.entorno= new Entorno(0.2,20,nombre);
		
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