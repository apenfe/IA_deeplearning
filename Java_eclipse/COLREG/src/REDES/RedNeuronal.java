package REDES;

import java.io.FileWriter;
import java.util.ArrayList;

public class RedNeuronal{
	
	private int id;
	private Capas[] capas = new Capas[0];
	
	public RedNeuronal(int numeroCapas, int id) {
		
		this.id=id;
		this.capas = new Capas[numeroCapas];
		
		for (int i = 0; i < capas.length; i++) {
			
			System.err.println("Capa nº "+(i+1)+":");
			
			capas[i]= new Capas(); 
			
		}
		
	}
	
	public RedNeuronal(String[] data) {
		
		//1 numero capas data[0]
		//2 numeros de neuronas por capas data[1] en adelante
		this.id=Integer.parseInt(data[data.length-1]);
		this.capas = new Capas[Integer.parseInt(data[0])];
		
		for (int i = 1; i < data.length-1; i++) {
			
			capas[i]= new Capas(Integer.parseInt(data[i])); 
			
		}
		
	}
	
	public int[] probarRed(double[] entradas) {

		for (int j = 0; j < capas.length; j++) {

			entradas = capas[j].probarCapa(entradas);

		}
		
		int[] salidas = new int[entradas.length];
		
		for (int i = 0; i < salidas.length; i++) {
			salidas[i]=(int)entradas[i];
		}

		return salidas;

	}

	public Capas[] getCapas() {
		return capas;
	}

	public int getPesosTotales(int entradas) {
		
		int pesos=capas[0].getPerceptrones().length*entradas;
		int neuronas_anteriores=capas[0].getPerceptrones().length;
		int neuronas;
		
		for (int i = 1; i < capas.length; i++) {
			
			neuronas=capas[i].getPerceptrones().length;
			pesos+=neuronas_anteriores*neuronas;
			neuronas_anteriores=neuronas;
			
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

	public boolean guardarRed(String ruta) {
		
		try {

			FileWriter escritor = new FileWriter(ruta,true);
			
			String red = ""+capas.length+"#";
			
			//1 numero capas data[0]
			//2 numeros de neuronas por capas data[1] en adelante
			
			for (int i = 0; i < capas.length; i++) {
				
				red+=capas[i].getPerceptrones().length+"#";
				
			}
			
			red+=this.id;
				
			escritor.write("\n"+red);
			escritor.close();
			
			return true;
			
		} catch (Exception e) {

			System.err.println("ERROR AL GUARDAR ARCHIVO " + ruta);
			return false;

		}
		
	}
	
	
	
	
	
}