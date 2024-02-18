package REDES;

import java.util.ArrayList;

public class RedNeuronal{
	
	private Capas[] capas = new Capas[0];
	
	public RedNeuronal(int numeroCapas) {
		
		this.capas = new Capas[numeroCapas];
		
		for (int i = 0; i < capas.length; i++) {
			
			System.err.println("Capa nº "+(i+1)+":");
			
			capas[i]= new Capas(); 
			
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
	
	public Perceptron[] obtnerPerceptrones() {
		
		ArrayList<Perceptron> perceptrones = new ArrayList<Perceptron>();
		
		for (int i = 0; i < capas.length; i++) {
			
			Perceptron[] neuronasCapa = capas[i].getPerceptrones();
			
			for (int j = 0; j < neuronasCapa.length; j++) {
				
				perceptrones.add(neuronasCapa[i]);
				
			}
		}
		
		return perceptrones.toArray(new Perceptron[0]);
		
	}
	
	
	
	
	
}