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
	
	private double[] cromosomas = new double[0];
	private double fitness = -1;
	public Entorno entorno;

	public Agente(int numeroCromosomas, Entorno entorno) {
		
		this.cromosomas = new double[numeroCromosomas];
		this.entorno = entorno;
		
		for (int i = 0; i < cromosomas.length; i++) {
			
			cromosomas[i]= -1 + 2 * Math.random();
			
		}
		
		this.initShip();
		
	}

	public Agente(double[] chromosome, Entorno entorno) {
		
		this.cromosomas = chromosome;
		this.entorno = entorno;
		this.initShip();
		
	}

	public double[] getCromosomas() {
		return cromosomas;
	}

	public void setCromosomas(double[] cromosomas) {
		this.cromosomas = cromosomas;
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
		//this.direccion = Math.random()*270;
		//this.direccion = 81;
		
		this.addPaso(x,y);
		
	}
	
	private void addPaso(double x, double y) {
		
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		this.camino.add(posicion);
		this.pasos++;
		
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
			
			this.girarDerecha();
			this.avanzar();
			
		}else if(movimientos[2]==max){
			
			this.girarIzquierda();
			this.avanzar();
			
		}else {
			
			this.avanzar();
			
		}
		
		this.apuntaASalida(x,y);
		this.seAcercaASalida(x,y);
		
	}

	@Override
	public void avanzar() {
		
		double anguloRadianes = Math.toRadians(direccion);

		this.x = x + entorno.getPaso() * Math.cos(anguloRadianes);
		this.y = y + entorno.getPaso() * Math.sin(anguloRadianes);
		
		this.addPaso(x, y);
		
	}

	@Override
	public void girarDerecha() {
		
		this.direccion=obtenerAngulo(entorno.getPaso()*10);
		
	}

	@Override
	public void girarIzquierda() {
		
		this.direccion=obtenerAngulo(-10*(entorno.getPaso()));
		
	}

	@Override
	public double[] sensores() {
		
		double[] sensor = new double[14];
		
		sensor[0] = this.delante();
		sensor[1] = this.costado_izquierdo();
		sensor[2] = this.costado_derecho();
		sensor[3] = this.amura_izquierda();
		sensor[4] = this.amura_derecha();
		sensor[5] = this.distanciaAsalida();
		sensor[6] = this.distanciaAentrada();
		sensor[7] = this.direccionActual();
		sensor[8] = this.seccion_derecha();
		sensor[9] = this.seccion_izquierda();
		sensor[10] = this.seccion_frontal();
		sensor[11] = this.posY();
		sensor[12] = this.posX();
		sensor[13] = this.direccionCorrecta();

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
	
	public int direccionCorrecta() { //****************************************************************
		
		double distActual = entorno.distanciaSalida(x, y);
		// Convertir el ángulo a radianes
		double anguloRadianes = Math.toRadians(obtenerAngulo(0));

				// Calcular las coordenadas del punto extremo
		double xExtremo = x + distActual * Math.cos(anguloRadianes);
		double yExtremo = y + distActual * Math.sin(anguloRadianes);
						
		if(entorno.esSalida(xExtremo, yExtremo)) {
			
			return 1;
					
		}else {
					
			return -1;
					
		}
		
	}
	
	public int direccionCorrectaV2() {
	    // Obtener la distancia actual desde el entorno hasta la salida
	    double distActual = entorno.distanciaSalida(x, y);
	    
	    for (int i=0 ; i<11; i++) {
	        // Convertir el ángulo a radianes
	        double anguloRadianes = Math.toRadians(i);

	        // Calcular las coordenadas del punto extremo usando trigonometría
	        double xExtremo = x + distActual * Math.cos(anguloRadianes);
	        double yExtremo = y + distActual * Math.sin(anguloRadianes);

	        // Verificar si las coordenadas calculadas son una salida
	        if (entorno.esSalida(xExtremo, yExtremo)) {
	        	System.err.println(i+" grados");
	            return 1; // Indica que la dirección es correcta
	        }
	    }
	    
	    for (int i=350 ; i<360; i++) {
	        // Convertir el ángulo a radianes
	        double anguloRadianes = Math.toRadians(i);

	        // Calcular las coordenadas del punto extremo usando trigonometría
	        double xExtremo = x + distActual * Math.cos(anguloRadianes);
	        double yExtremo = y + distActual * Math.sin(anguloRadianes);

	        // Verificar si las coordenadas calculadas son una salida
	        if (entorno.esSalida(xExtremo, yExtremo)) {
	        	System.err.println(i+" grados");
	            return 1; // Indica que la dirección es correcta
	        }
	    }

	    return -1; // Indica que ninguna dirección en el rango es correcta
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
	
	private int revisarListaPasos(double x, double y) {
		
		for (int i = 0; i < this.camino.size(); i++) {
			
			// recorrer un arrary y ver si ya h sido visitada la casilla, asi evito bucles, tambien puedo hacerlo con un area de margen
			
		}
		
	}
	
	private void seAcercaASalida(double x, double y) {
		
		Double[] anterior = camino.get(camino.size()-1);
			
		double distAnterior = entorno.distanciaSalida(anterior[0], anterior[1]);
		double distActual = entorno.distanciaSalida(x, y);
		
		if(distAnterior>distActual) {
			this.fitness+=1;
		}else {
			this.fitness-=3;
		}		
		
	}
	
	private void apuntaASalida(double x, double y) {
		
		// saco la distancia a la salida
		// hago un sensor que compruebe si es la salida a esa distancia
		
		double distActual = entorno.distanciaSalida(x, y);
		// Convertir el ángulo a radianes
		double anguloRadianes = Math.toRadians(obtenerAngulo(0));

				// Calcular las coordenadas del punto extremo
		double xExtremo = x + distActual * Math.cos(anguloRadianes);
		double yExtremo = y + distActual * Math.sin(anguloRadianes);
						
		if(entorno.esSalida(xExtremo, yExtremo)) {
			
			//System.err.println("apunta");
			fitness+=10;
					
		}else {
					
			fitness-=2;
					
		}
	
	}

	@Override
	public double calculateFitness() {
		
		int nota =0;
		
		// recorrer un arrary y ver si ya h sido visitada la casilla, asi evito bucles, tambien puedo hacerlo con un area de margen
		// si apunta hacia el 0,2 mas
		// si uno de ellos  ha llegado a salida, contar los puntos extra y añadir penalizacion por pasos, para comprara con otros que si han llegado en menos pasos
		
		
		 //double distanciaSalida = entorno.distanciaSalida(x, y);
	   
	     double stepPenalty = pasos;
	     
	     double penalizacionChoque = sensorChoque*3;
	     fitness-= penalizacionChoque;
	        
	     if(entorno.esSalida(x, y)) {
	    	 
	    	 fitness+=100000;
	    	 
	     }
	     
	     if(lose()) {
	    	 fitness-=10000;
	     }

	     return fitness;
		
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
		if(entorno.esSalida(this.x, this.y)) {
			
			return true;
			
		}
		
		return false;
	}

	@Override
	public boolean lose() {
		if(entorno.fueraLimites(this.x, this.y)) {
			
			return true;
		}
		
		return false;
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
	
	public Entorno getEntorno() {
		return entorno;
	}

	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}
	
}