package REDES;

import java.io.FileWriter;
import java.util.ArrayList;

public class RedNeuronal{
	
	private String nombre_simulacion;
	private Capas[] capas = new Capas[0];
	
	public RedNeuronal(int numeroCapas, String nombre_simulacion) {
		
		this.nombre_simulacion=nombre_simulacion;
		this.capas = new Capas[numeroCapas];
		
		for (int i = 0; i < capas.length; i++) {
			
			System.err.println("Capa nº "+(i+1)+":");
			
			capas[i]= new Capas(); 
			
		}
		
	}
	
	public RedNeuronal(String[] data) {
		
		//1 numero capas data[0]
		//2 numeros de neuronas por capas data[1] en adelante
		this.nombre_simulacion=data[data.length-1];
		this.capas = new Capas[Integer.parseInt(data[0])];
		
		for (int i = 0; i < Integer.parseInt(data[0]); i++) {
			
			capas[i]= new Capas(Integer.parseInt(data[i+1])); 
			
		}
		
	}
	
	// aqui pasa algo raro, revisarlo bien
	
	public int[] probarRed(double[] entradas) {
		
		// debo vigilar el cambio de tamaño de las entradas
		
		double[] anterioresentradas = entradas;
		
		double[] nuevasentradas = capas[0].probarCapa(entradas);
		anterioresentradas=nuevasentradas;

		for (int i = 1; i < capas.length; i++) {
			System.err.println("DB CAPA: "+(i+1));
			
			nuevasentradas = capas[i].probarCapa(anterioresentradas);
			anterioresentradas=nuevasentradas;
		}
		
		int[] salidas = new int[anterioresentradas.length];
		
		for (int i = 0; i < salidas.length; i++) {
			salidas[i]=(int)anterioresentradas[i];
		}

		return salidas;

	}

	public Capas[] getCapas() {
		return capas;
	}

	public int getPesosTotales(int entradas) {
		
		int pesos=(capas[0].getPerceptrones().length)*entradas;
		int neuronas_anteriores=capas[0].getPerceptrones().length;
		int neuronas;
		
		for (int i = 1; i < capas.length; i++) {
			
			neuronas = capas[i].getPerceptrones().length;
			pesos += neuronas_anteriores*neuronas;
			neuronas_anteriores = neuronas;
			
		}
		
		return pesos;
		
	}
	
	public Perceptron[] obtenerPerceptrones() {
		
		ArrayList<Perceptron> perceptrones = new ArrayList<Perceptron>();
		
		for (int i = 0; i < capas.length; i++) {
			
			Perceptron[] neuronasCapa = capas[i].getPerceptrones();
			
			for (int j = 0; j < neuronasCapa.length; j++) {
				
				perceptrones.add(neuronasCapa[i]);
				
			}
		}

		return perceptrones.toArray(new Perceptron[0]);

	}

	// la idea es asignar los pesos a cada neurona dado el adn de la entidad

	// Para simplificar voy a poner las ismas neuronas por capa, en este caso, tres
	// por cada una

	// tres pesos por cada neurona

	// SE ESTAN ASIGNANDO MAL, LA PRIMERA CAPA TIENE POR CADA NEURONA 6 PESOS, EL
	// RESTO 3

	public void asignarPesosSinapticos(double[] pesos,int entradas) {
		
		int item = -1;
		double[] pesosSinapticos = new double[0];
		
		for (int i = 0; i < capas.length; i++) {
			
			if(i==0) {
				pesosSinapticos = new double[entradas];
			}else {
				pesosSinapticos = new double[capas[i].getPerceptrones().length];
			}
			
			int cont=0;
			
			for (int j = 0; j < capas[i].getPerceptrones().length; j++) {
				
				item++;
				pesosSinapticos[cont]=pesos[item];
				cont++;
				
			}
			
		}

		
	}

	public boolean guardarRed(String ruta) {

		try {

			FileWriter escritor = new FileWriter(ruta,true);
			
			String red = ""+capas.length+"#";
			
			//1 numero capas data[0]
			//2 numeros de neuronas por capas data[1] en adelante
			
			for (int i = 0; i < capas.length; i++) {
				
				red+=capas[i].getPerceptrones().length+"#";
				
			}
			
			red+=this.nombre_simulacion;
				
			escritor.write("\n"+red);
			escritor.close();
			
			return true;
			
		} catch (Exception e) {

			System.err.println("ERROR AL GUARDAR ARCHIVO " + ruta);
			return false;

		}
		
	}
	
	
	
	
	
}