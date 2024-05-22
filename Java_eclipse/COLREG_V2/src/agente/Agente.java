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
		initShip();
		
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

	public void setGene(int indice, double gen) {
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
double max = Double.MIN_VALUE;
		
		for (int i = 0; i < movimientos.length; i++) {
			
			if(movimientos[i]>max) {
				max=movimientos[i];
			}
			
		}
		
		if(movimientos[0]==max) {
			
			girarDerecha();
			avanzar();
			
		}else if(movimientos[2]==max){
			
			girarIzquierda();
			avanzar();
			
		}else {
			
			avanzar();
			
		}
		
		fin();
		
	}

	@Override
	public void avanzar() {
		double anguloRadianes = Math.toRadians(direccion);

		x = x + entorno.getPaso() * Math.cos(anguloRadianes);
		y = y + entorno.getPaso() * Math.sin(anguloRadianes);
		pasos++;
		
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		
		camino.add(posicion);
		
	}

	@Override
	public void girarDerecha() {
		this.direccion=obtenerAngulo(entorno.getPaso()*9);
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		
		camino.add(posicion);
		this.pasos++;
		
	}

	@Override
	public void girarIzquierda() {
		this.direccion=obtenerAngulo(-9*(entorno.getPaso()));
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		
		camino.add(posicion);
		this.pasos++;
		
	}

	@Override
	public double[] sensores() {
	double[] sensor = new double[13];
		
		sensor[0] = delante();
		sensor[1] = costado_izquierdo();
		sensor[2] = costado_derecho();
		sensor[3] = amura_izquierda();
		sensor[4] = amura_derecha();
		sensor[5] = distanciaAsalida();
		sensor[6] = distanciaAentrada();
		sensor[7] = direccionActual();
		sensor[8] = seccion_derecha();
		sensor[9] = seccion_izquierda();
		sensor[10] = seccion_frontal();
		sensor[11] = posY();
		sensor[12] = posX();

		return sensor;
	}

	@Override
	public int delante() {
		// Convertir el ángulo a radianes
				double anguloRadianes = Math.toRadians(obtenerAngulo(0));

				// Calcular las coordenadas del punto extremo
				double xExtremo = x + HORIZONTE * Math.cos(anguloRadianes);
				double yExtremo = y + HORIZONTE * Math.sin(anguloRadianes);
				
				if(entorno.fueraLimites(xExtremo, yExtremo)) {
					this.sensorChoque++;
					return 1;
				}else {
					return -1;
				}
	}

	@Override
	public int costado_izquierdo() {
		// Convertir el ángulo a radianes
				double anguloRadianes = Math.toRadians(obtenerAngulo(-90));

				// Calcular las coordenadas del punto extremo
				double xExtremo = x + HORIZONTE * Math.cos(anguloRadianes);
				double yExtremo = y + HORIZONTE * Math.sin(anguloRadianes);
				
				if(entorno.fueraLimites(xExtremo, yExtremo)) {
					this.sensorChoque++;
					return 1;
					
				}else {
					return -1;
				}
	}

	@Override
	public int costado_derecho() {
		// Convertir el ángulo a radianes
				double anguloRadianes = Math.toRadians(obtenerAngulo(90));

				// Calcular las coordenadas del punto extremo
				double xExtremo = x + HORIZONTE * Math.cos(anguloRadianes);
				double yExtremo = y + HORIZONTE * Math.sin(anguloRadianes);
				
				if(entorno.fueraLimites(xExtremo, yExtremo)) {
					this.sensorChoque++;
					return 1;
				}else {
					return -1;
				}
	}

	@Override
	public int amura_izquierda() {
		// Convertir el ángulo a radianes
				double anguloRadianes = Math.toRadians(obtenerAngulo(-45));

				// Calcular las coordenadas del punto extremo
				double xExtremo = x + HORIZONTE * Math.cos(anguloRadianes);
				double yExtremo = y + HORIZONTE * Math.sin(anguloRadianes);
				
				if(entorno.fueraLimites(xExtremo, yExtremo)) {
					this.sensorChoque++;
					return 1;
				}else {
					return -1;
				}
	}

	@Override
	public int amura_derecha() {
		// Convertir el ángulo a radianes
				double anguloRadianes = Math.toRadians(obtenerAngulo(45));

				// Calcular las coordenadas del punto extremo
				double xExtremo = x + HORIZONTE * Math.cos(anguloRadianes);
				double yExtremo = y + HORIZONTE * Math.sin(anguloRadianes);
				
				if(entorno.fueraLimites(xExtremo, yExtremo)) {
					this.sensorChoque++;
					return 1;
				}else {
					return -1;
				}
	}

	@Override
	public int seccion_derecha() {
		if(amura_derecha()==1&&costado_derecho()==1) {
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public int seccion_izquierda() {
		if(amura_izquierda()==1&&costado_izquierdo()==1) {
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public int seccion_frontal() {
		if(amura_izquierda()==1&&amura_derecha()==1&&delante()==1) {
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public double distanciaAsalida() {
		double distMin=0;
		double distMax = Math.sqrt(Math.pow(x,2)+Math.pow(y, 2));
		double distancia = entorno.distanciaSalida(x, y);
		
		return normalizar(distancia,distMin,distMax);
	}

	@Override
	public double direccionActual() {
		return normalizar(direccion,0,360);
	}

	@Override
	public double distanciaAentrada() {
		double distMin=0;
		double distMax = Math.sqrt(Math.pow(x,2)+Math.pow(y, 2));
		double distancia = entorno.distanciaEntrada(x, y);
		
		return normalizar(distancia,distMin,distMax);
	}

	@Override
	public double posX() {
		return normalizar(x,0,entorno.getAncho());
	}

	@Override
	public double posY() {
		return normalizar(y,0,entorno.getAlto());
	}

	@Override
	public double normalizar(double value, double min, double max) {
		 // Normalizar el valor dentro del rango [0, 1]
        double normalizedValue = (value - min) / (max - min);
        
        // Normalizar el valor dentro del rango [-1, 1]
        normalizedValue = normalizedValue * 2 - 1;
        
        return normalizedValue;
	}

	@Override
	public void calculateFitness() {
		 double distanciaSalida = entorno.distanciaSalida(x, y)*12;
	        double penalizacionChoque = sensorChoque*2;
	        double stepPenalty = pasos;

	        double premioLlegada = 0;
	      
	        if (entorno.esSalida(x, y)) {
	            premioLlegada = 1000000;
	        }

	        fitness = distanciaSalida - penalizacionChoque - stepPenalty + premioLlegada;
		
	}

	@Override
	public double obtenerAngulo(double grados) {
double angulo = direccion + grados;
	    
	    angulo = (angulo % 360 + 360) % 360;
	    
	    return angulo;
	}

	@Override
	public boolean fin() {
		if(entorno.esSalida(x, y)) {
			//puntos+=20000;
			return true;
		}
		
		if(entorno.fueraLimites(x, y)) {
			//puntos-=10000;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean win() {
		if(entorno.esSalida(x, y)) {
			//puntos+=20000;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean lose() {
		if(entorno.fueraLimites(x, y)) {
			//puntos-=10000;
			return true;
		}
		
		return false;
	}

	@Override
	public double[] getAdn() {
		return cromosomas;
	}

	@Override
	public void setAdn(double[] cromosomas) {
		this.cromosomas = cromosomas;
		
	}

	@Override
	public float[][] caminoFloat() {
		int numRows = camino.size();
        float[][] resultado = new float[numRows][];

        for (int i = 0; i < numRows; i++) {
            Double[] fila = camino.get(i);
            int numCols = fila.length;
            resultado[i] = new float[numCols];

            for (int j = 0; j < numCols; j++) {
                resultado[i][j] = fila[j].floatValue();
            }
        }
        
        return resultado;
	}

	@Override
	public double getPasos() {
		return pasos;
	}

	@Override
	public void setPasos(double pasos) {
		this.pasos = pasos;
		
	}
	
}