package redv2;

import java.io.FileWriter;
import java.util.ArrayList;

public class RedNeuronal{
	
	// debo poder crear la red rapidamente (en modo iniical random)
	// poder hacer actualizaciones de bias y pesos
	// obtener la informacion completa de cada capa
	// crear desde datos anteriores
	// funciones para discriminar que hacer con la ultima capa
	
	private String nombre;
	private int entradas;
	private int salidas;
	private int numCapas;
	private int numNeuronas;
	private Capas[] capas = new Capas[0];
	private Perceptron[][] neuronas = new Perceptron[0][0];
	
	public RedNeuronal(String nombre_simulacion, int numeroCapas, int[] numeroNeuronas, int[] funcion) { // minimo modo
		
		this.entradas=numeroNeuronas[0];
		this.salidas=numeroNeuronas[numeroNeuronas.length-1];
		this.numCapas=numeroCapas;
		this.nombre=nombre_simulacion;
		this.capas = new Capas[numeroCapas];
		
		for (int i = 0; i < capas.length; i++) {
			
			if(i==0) {
				capas[i]= new Capas(numeroNeuronas[i], funcion[i], this.entradas); 
			}else {
				capas[i]= new Capas(numeroNeuronas[i], funcion[i], capas[i-1].getNumNeuronas()); 
			}
			
		}
		
	}
	
	public RedNeuronal(String nombre_simulacion, int numeroCapas, int[] numeroNeuronas, double[][] bias, int[] funcion, double[][] pesosDeLaCapa) {
		
		this.entradas=numeroNeuronas[0];
		this.salidas=numeroNeuronas[numeroNeuronas.length-1];
		this.numCapas=numeroCapas;
		this.nombre=nombre_simulacion;
		this.capas = new Capas[numeroCapas];
		
		for (int i = 0; i < capas.length; i++) {
			
			capas[i]= new Capas(numeroNeuronas[i], bias[i], funcion[i], pesosDeLaCapa[i]); 
			
		}
		
	}
	
	public RedNeuronal(String[] data) {
		
		this.nombre=data[data.length-1];
		this.capas = new Capas[Integer.parseInt(data[0])];
		
		for (int i = 1; i <= (Integer.parseInt(data[0])); i++) {
			
			String[] dataNeurona = data[i].split("&");
			capas[i-1]= new Capas(Integer.parseInt(dataNeurona[0]));
			
			for (int j = 0; j < capas[i-1].getPerceptrones().length; j++) {
				capas[i-1].getPerceptrones()[j].setFuncion(Integer.parseInt(dataNeurona[1]));
			}
			
		}
		
	}
	
	public double[] probarRed(double[] entradas) {
		
		double[] anterioresentradas = entradas;
		
		double[] nuevasentradas = capas[0].probarCapa(entradas);
		anterioresentradas=nuevasentradas;

		for (int i = 1; i < capas.length; i++) {
		
			nuevasentradas = capas[i].probarCapa(anterioresentradas);
			anterioresentradas=nuevasentradas;
			
		}
		
		double[] salidas = new double[anterioresentradas.length];
		
		for (int i = 0; i < salidas.length; i++) {
			
			salidas[i]=anterioresentradas[i];
			
		}

		return salidas;

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
		
		ArrayList<Perceptron> perceptrones = new ArrayList<>();
		
		for (int i = 0; i < capas.length; i++) {
			
			Perceptron[] neuronasCapa = capas[i].getPerceptrones();
			
			for (int j = 0; j < neuronasCapa.length; j++) {
				
				perceptrones.add(neuronasCapa[i]);
				
			}
		}

		return perceptrones.toArray(new Perceptron[0]);

	}
	
	public void asignarPesosSinapticosCapas(double[] pesos,int entradas) {
		
		int item = -1;
		double[] pesosSinapticos;
		
		for (int i = 0; i < capas.length; i++) {
			
			if(i==0) {
				pesosSinapticos = new double[entradas*capas[i].getPerceptrones().length];
			}else {
				pesosSinapticos = new double[capas[i-1].getPerceptrones().length*(capas[i].getPerceptrones().length)];
			}
			
			for (int j = 0; j < pesosSinapticos.length; j++) {
				
				item++;
				pesosSinapticos[j]=pesos[item];
				
			}
			
			for (int j = 0; j < capas[i].getPerceptrones().length; j++) {
				
				capas[i].establecerPesosNeuronas(pesosSinapticos);
				
			}
			
		}
		
	}
		
}