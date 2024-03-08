package redes;

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
		
		this.nombre_simulacion=data[data.length-1];
		this.capas = new Capas[Integer.parseInt(data[0])];
		
		for (int i = 1; i <= (Integer.parseInt(data[0])); i++) {
			
			String[] dataNeurona = data[i].split("&");
			capas[i-1]= new Capas(Integer.parseInt(dataNeurona[0]));
			
			for (int j = 0; j < capas[i-1].getPerceptrones().length; j++) {
				capas[i-1].getPerceptrones()[j].setFuncion(Integer.parseInt(dataNeurona[1]));
			}
			
		}
		
	}
	
	public double[] probarRed(double[] entradas) { // cambio salida a double
		
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

	public boolean guardarRed(String ruta) {

		try {

			FileWriter escritor = new FileWriter(ruta,true);
			
			String red = ""+capas.length+"#";
			
			for (int i = 0; i < capas.length; i++) {
				
				red+=capas[i].getPerceptrones().length+"&"+capas[i].getPerceptrones()[0].getFuncion()+"#";
				
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