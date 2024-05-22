package agente;

import java.util.ArrayList;

import entorno.Entorno;

public class Agente implements Ship{
	
	public int sensorChoque;
	public double pasos;
	public final double HORIZONTE = 15;
	public double x;
	public double y;
	public  ArrayList<Double[]> camino = new ArrayList<>();
	public double direccion;
	
	///////////////////////////
	
	private double[] cromosomas = new double[0];
	private double fitness = -1;
	private int id;
	private Entorno entorno;
	
	public Agente(int id, Entorno entorno) {
		
		this.id = id;
		this.entorno = entorno;
		
	}

	public Agente(double[] chromosome) {
		
		this.cromosomas = chromosome;
		
	}

	public Agente(int numeroCromosomas) {
		
		this.cromosomas = new double[numeroCromosomas];
		
		for (int gene = 0; gene < numeroCromosomas; gene++) {
			
			if (0.5 < Math.random()) {
				this.setGene(gene, 1);
			} else {
				this.setGene(gene, 0);
			}
			
		}
		
	}

	public double[] getCromosomas() {
		return cromosomas;
	}

	public void setCromosomas(double[] cromosomas) {
		this.cromosomas = cromosomas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double[] getChromosome() {
		return this.cromosomas;	
	}

	public int getChromosomeLength() {
		return this.cromosomas.length;
	}

	public void setGene(int indice, int gen) {
		this.cromosomas[indice] = gen;
	}

	public double getGene(int indice) {
		return this.cromosomas[indice];
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		return this.fitness;
	}

	public String toString() {
		
		String exit = "";
		
		for (int gen = 0; gen < this.cromosomas.length; gen++) {
			
			exit += this.cromosomas[gen];
			
		}
		
		return exit;
		
	}

	@Override
	public ArrayList<Double[]> getCamino() {
		
		return camino;
	}

	@Override
	public void setCamino(ArrayList<Double[]> camino) {
		
		this.camino = camino;
		
	}

	@Override
	public void initShip() {
		
		this.x = entorno.getEntradaX();
		this.y = entorno.getEntradaX();
		this.direccion = Math.random()*360;
		//this.direccion = 42;
		
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		camino.add(posicion);
		pasos++;
		
	}

	@Override
	public void acciones(double[] movimientos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avanzar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void girarDerecha() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void girarIzquierda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double[] sensores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delante() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int costado_izquierdo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int costado_derecho() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int amura_izquierda() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int amura_derecha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int seccion_derecha() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int seccion_izquierda() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int seccion_frontal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double distanciaAsalida() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double direccionActual() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double distanciaAentrada() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double posX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double posY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double normalizar(double value, double min, double max) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void calculateFitness() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double obtenerAngulo(double grados) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean fin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean win() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lose() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getAdn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAdn(double[] cromosomas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float[][] caminoFloat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPasos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPasos(double pasos) {
		// TODO Auto-generated method stub
		
	}
	
}