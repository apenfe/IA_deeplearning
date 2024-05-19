package red;

import java.util.ArrayList;
import java.util.Arrays;

public class RedNeuronal{
	
	// crear desde datos anteriores
	// funciones para discriminar que hacer con la ultima capa
	
	private String nombre;
	private int entradas;
	private int salidas;
	private int numCapas;
	private int numNeuronas;
	private Capas[] capas = new Capas[0];
	
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
		
		this.numNeuronas = getNumNeuronas();
		
	}
	/*
	public RedNeuronal(String nombre_simulacion, int numeroCapas, int[] numeroNeuronas, double[][] bias, int[] funcion, double[][] pesosDeLaCapa) {
		
		this.entradas=numeroNeuronas[0];
		this.salidas=numeroNeuronas[numeroNeuronas.length-1];
		this.numCapas=numeroCapas;
		this.nombre=nombre_simulacion;
		this.capas = new Capas[numeroCapas];
		
		for (int i = 0; i < capas.length; i++) {
			
			capas[i]= new Capas(numeroNeuronas[i], bias[i], funcion[i], pesosDeLaCapa[i]); 
			
		}
		
		this.numNeuronas = getNumNeuronas();
		
	}*/
	
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
	
	// -----------------------------------  DISCRIMINACION ULTIMA CAPA -------------------------------------------------------------------------
	
	private int getNumNeuronas() {
		
		int neuronas = 0;
		
		for (int i = 0; i < capas.length; i++) {
			
			neuronas += capas[i].getNumNeuronas();
			
		}
		
		return neuronas;
		
	}
	
	// -----------------------------------  METODOS PARA METER Y SACR GENES --------------------------------------------------------------------
	
	public double[] getParametros() {
		
		ArrayList<Double> pesos = new ArrayList<Double>();
		ArrayList<Double> bias = new ArrayList<Double>();
		
		for (int i = 0; i < capas.length; i++) {
			
			double[] actualBias = capas[i].obtenerDatosCapa(false);
			double[] actualPesos = capas[i].obtenerDatosCapa(true);
			
			for (int j = 0; j < actualPesos.length; j++) {
				
				pesos.add(actualPesos[j]);
				
			}
			
			for (int j = 0; j < actualBias.length; j++) {
				
				bias.add(actualBias[j]);
				
			}
			
		}
		
		ArrayList<Double> total = new ArrayList<Double>();
		
		for (int j = 0; j < pesos.size(); j++) {
			
			total.add(pesos.get(j));
			
		}
		
		for (int j = 0; j < bias.size(); j++) {
			
			total.add(bias.get(j));
			
		}
		
		double[] salida = new double[total.size()];
		
		for (int i = 0; i < salida.length; i++) {
			
			salida[i]=total.get(i).doubleValue();
			
		}
		
		return salida;

	}
	
	public void setParametros(double[] parametros) {
		
		double[] pesosSinapticos = separarBiasPesos(true,parametros);
		double[] bias = separarBiasPesos(false,parametros);
		
		int item1 = -1;
		int item2 = -1;
		double[] pesosParaCapa;
		double[] biasParaCapa;
		
		for (int i = 0; i < capas.length; i++) {
			
			if(i==0) {
				
				pesosParaCapa = new double[this.entradas*capas[i].getNumNeuronas()];
				biasParaCapa = new double[capas[i].getNumNeuronas()];
				
			}else {
				
				pesosParaCapa = new double[capas[i-1].getNumNeuronas()*(capas[i].getNumNeuronas())];
				biasParaCapa = new double[capas[i].getNumNeuronas()];
				
			}
			
			//*****************ver*********************
			
			for (int j = 0; j < pesosParaCapa.length; j++) {
				
				item1++;
				pesosParaCapa[j]=pesosSinapticos[item1];
				
			}
			
			for (int j = 0; j < biasParaCapa.length; j++) {
				
				item2++;
				biasParaCapa[j]=bias[item2];
				
			}
			
			//*****************ver*********************
			
			capas[i].actualizarCapa(pesosParaCapa,biasParaCapa);
			
		}
		
	}
	
	private double[] separarBiasPesos(boolean pesos, double[] datos) { // revisar si esta bien   creo que si
		
		if(pesos) {
			
			double[] pesosSinapticos = new double[(datos.length)-this.numNeuronas];
			
			for (int i = 0; i < pesosSinapticos.length; i++) {
				
				pesosSinapticos[i]=datos[i];
				
			}
			
			return pesosSinapticos;
			
		}else {
			
			double[] bias = new double[this.numNeuronas];
			
			for (int i = 0; i < this.numNeuronas; i++) {
				
	            bias[i] = datos[datos.length - this.numNeuronas + i];
	            
	        }
			
			return bias;
			
		}
		
	}

	@Override
	public String toString() {
		return "RedNeuronal [nombre=" + nombre + ", entradas=" + entradas + ", salidas=" + salidas + ", numCapas="
				+ numCapas + ", numNeuronas=" + numNeuronas + ", capas=" + Arrays.toString(capas) + "]";
	}
	
	public boolean guardarRed() {
		
		DAO db = new DAO();
		
		if(db.guardarRed(this)) {
			return true;
		}
		
		return false;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEntradas() {
		return entradas;
	}

	public void setEntradas(int entradas) {
		this.entradas = entradas;
	}

	public int getSalidas() {
		return salidas;
	}

	public void setSalidas(int salidas) {
		this.salidas = salidas;
	}

	public int getNumCapas() {
		return numCapas;
	}

	public void setNumCapas(int numCapas) {
		this.numCapas = numCapas;
	}

	public Capas[] getCapas() {
		return capas;
	}

	public void setCapas(Capas[] capas) {
		this.capas = capas;
	}

	public void setNumNeuronas(int numNeuronas) {
		this.numNeuronas = numNeuronas;
	}
	
	
		
}