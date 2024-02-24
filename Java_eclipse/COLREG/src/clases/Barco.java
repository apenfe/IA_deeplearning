package clases;

import java.util.ArrayList;

import processing.core.PApplet;
import visual.Plot;

public class Barco{
	
	// se cambian los valores de entradas de 0 a 1 a -1 1
	
	private int id;
	private double[] adn = new double[0];
	private int puntos;
	private double pasos;
	private final double horizonte = 20;
	private double x;
	private double y;
	private ArrayList<Double[]> camino = new ArrayList<Double[]>();
	private Entorno entorno;
	private double direccion;
	
	public Barco(int id, Entorno entorno) {
		
		this.id = id;
		this.entorno = entorno;
		this.x = entorno.getEntradaX();
		this.y = entorno.getEntradaX();
		this.direccion = Math.random()*360; //expresado en grados
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		camino.add(posicion);
		pasos++;
		
	}
	
	public void acciones(int[] movimientos) {
		
		if(movimientos[0]==1) { // avanzar
			
			if(movimientos[1]==-1&&movimientos[2]==-1) { // recto
				
				puntos+=2;
				this.pasos++;
				
			}else if(movimientos[1]==-1&&movimientos[2]==1) { // derecha
				
				girarDerecha();
				puntos+=2;
				
			}else if(movimientos[1]==1&&movimientos[2]==-1) { // izquiera
				
				girarIzquierda();
				puntos+=2;
				
			}else if(movimientos[1]==1&&movimientos[2]==1) { // recto
				
				puntos-=4;
				this.pasos++;
				
			}
			
			avanzar();
			
		}else { // NO vanza
			
			if(movimientos[1]==-1&&movimientos[2]==-1) { // nada
				
				puntos-=4;
				this.pasos++;
				
			}else if(movimientos[1]==-1&&movimientos[2]==1) { // gira derecha
				
				girarDerecha();
				puntos-=2;
				
			}else if(movimientos[1]==1&&movimientos[2]==-1) { // gira izquiera
				
				girarIzquierda();
				puntos-=2;
				
			}else if(movimientos[1]==1&&movimientos[2]==1) { // nada
				
				puntos-=8;
				pasos++;
				
			}
			
			avanzar(); // cuidado, luego quitar
			
		}
		
		if(fin()) {
			System.out.println("El barco ha terminado su intento.");
			return; // 
		}
		
		
	}
	
	private void avanzar() {

		// Convertir el ángulo a radianes
		double anguloRadianes = Math.toRadians(direccion);

		// Calcular las coordenadas del punto extremo
		x = x + entorno.getPaso() * Math.cos(anguloRadianes);
		y = y + entorno.getPaso() * Math.sin(anguloRadianes);
		pasos++;
		
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		
		camino.add(posicion);

	}
	
	private void girarDerecha() {
		
		this.direccion=obtenerAngulo(entorno.getPaso()*9);
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		
		camino.add(posicion);
		this.pasos++;
	}
	
	private void girarIzquierda() {
		
		this.direccion=obtenerAngulo(-9*(entorno.getPaso()));
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		
		camino.add(posicion);
		this.pasos++;
	}

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
		
		
		for (int i = 0; i < sensor.length; i++) {
			System.out.print("Salida "+(i+1)+": "+sensor[i]+" - ");
		}

		return sensor;

	}
	
	private int delante() {

		// Convertir el ángulo a radianes
		double anguloRadianes = Math.toRadians(obtenerAngulo(0));

		// Calcular las coordenadas del punto extremo
		double xExtremo = x + horizonte * Math.cos(anguloRadianes);
		double yExtremo = y + horizonte * Math.sin(anguloRadianes);
		
		if(entorno.fueraLimites(xExtremo, yExtremo)) {
			return 1;
		}else {
			return -1;
		}

	}

	private int costado_izquierdo() {

		// Convertir el ángulo a radianes
		double anguloRadianes = Math.toRadians(obtenerAngulo(-90));

		// Calcular las coordenadas del punto extremo
		double xExtremo = x + horizonte * Math.cos(anguloRadianes);
		double yExtremo = y + horizonte * Math.sin(anguloRadianes);
		
		if(entorno.fueraLimites(xExtremo, yExtremo)) {
			return 1;
		}else {
			return -1;
		}

	}

	private int costado_derecho() {

		// Convertir el ángulo a radianes
		double anguloRadianes = Math.toRadians(obtenerAngulo(90));

		// Calcular las coordenadas del punto extremo
		double xExtremo = x + horizonte * Math.cos(anguloRadianes);
		double yExtremo = y + horizonte * Math.sin(anguloRadianes);
		
		if(entorno.fueraLimites(xExtremo, yExtremo)) {
			return 1;
		}else {
			return -1;
		}

	}

	private int amura_izquierda() {

		// Convertir el ángulo a radianes
		double anguloRadianes = Math.toRadians(obtenerAngulo(-45));

		// Calcular las coordenadas del punto extremo
		double xExtremo = x + horizonte * Math.cos(anguloRadianes);
		double yExtremo = y + horizonte * Math.sin(anguloRadianes);
		
		if(entorno.fueraLimites(xExtremo, yExtremo)) {
			return 1;
		}else {
			return -1;
		}

	}

	private int amura_derecha() {

		// Convertir el ángulo a radianes
		double anguloRadianes = Math.toRadians(obtenerAngulo(45));

		// Calcular las coordenadas del punto extremo
		double xExtremo = x + horizonte * Math.cos(anguloRadianes);
		double yExtremo = y + horizonte * Math.sin(anguloRadianes);
		
		if(entorno.fueraLimites(xExtremo, yExtremo)) {
			return 1;
		}else {
			return -1;
		}

	}
	
	private int seccion_derecha() {

		if(amura_derecha()==1&&costado_derecho()==1) {
			return 1;
		}else {
			return -1;
		}

	}
	
	private int seccion_izquierda() {

		if(amura_izquierda()==1&&costado_izquierdo()==1) {
			return 1;
		}else {
			return -1;
		}

	}
	
	private int seccion_frontal() {

		if(amura_izquierda()==1&&amura_derecha()==1&&delante()==1) {
			return 1;
		}else {
			return -1;
		}

	}

	private double distanciaAsalida() {

		double distancia = entorno.distanciaSalida(x, y);
		
		return normalizar(distancia);

	}
	
	private double direccionActual() {
		
		return normalizar(direccion);

	}
	
//	private double direccionDeseada() {
		
		// ver como implementarlo

//	}
	
	private double distanciaAentrada() {

		double distancia = entorno.distanciaEntrada(x, y);
		
		return normalizar(distancia);

	}
	
	private double posX() {
		
		return normalizar(x);

	}
	
	private double posY() {
		
		return normalizar(y);

	}	
	
	private double normalizar(double xs) {
		
		// debo generalizar el metodo
		
		double xMax = Math.sqrt(Math.pow(entorno.getAlto(), 2)+Math.pow(entorno.getAncho(), 2));
		
		if(xs==direccion) {
			xMax=360;
		}else if(xs==x||xs==y) {
			xMax=entorno.getAlto();
		}
		
		return 2*((xs-0)/(xMax))-1;
		
		
	}
	
 	private double obtenerAngulo(double grados) {

		double angulo = direccion + grados;
	    
	    // Normalizar el ángulo para que esté dentro del rango de 0 a 359 grados
	    angulo = (angulo % 360 + 360) % 360;
	    
	    return angulo;
		
	}
	
	public boolean fin() {
		
		if(entorno.esSalida(x, y)) {
			puntos+=20000;
			return true;
		}
		
		if(entorno.fueraLimites(x, y)) {
			puntos-=10000;
			return true;
		}
		
		return false;
		
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public double[] getAdn() {
		return adn;
	}

	public void setAdn(double[] adn) {
		this.adn = adn;
	}
		
	public void camino() {
		
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

		Plot applet = new Plot();
		applet.setXY((int)entorno.getAncho(),(int)entorno.getAlto());
		applet.setPuntos(resultado);
		applet.setInOut(entorno.getEntradaX(),entorno.getEntradaY(),entorno.getSalidaX(),entorno.getSalidaY());
	    PApplet.runSketch(new String[]{"visual/Plot"}, applet);
	    
	}

	public double getPasos() {
		return pasos;
	}

	public void setPasos(double pasos) {
		this.pasos = pasos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}