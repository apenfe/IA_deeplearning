package clases;

import java.util.ArrayList;

public class Barco{
	
	private int id;
	private double[] adn = new double[0];
	private int puntos;
	private double pasos;
	private final double horizonte = 15;
	private double x;
	private double y;
	private ArrayList<Double[]> camino = new ArrayList<Double[]>();
	private Entorno entorno;
	private double direccion;
	
	public Barco(int id, Entorno entorno) {
		
		this.id = id;
		this.entorno = entorno;
		this.x = entorno.getEntrada_x();
		this.y = entorno.getEntrada_y();
		this.direccion = 5; //expresado en grados
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		camino.add(posicion);
		pasos++;
		
	}
	
	public void acciones(int[] movimientos) {
		
		if(movimientos[0]==1) { // avanzar
			
			if(movimientos[1]==0&&movimientos[2]==0) { // recto
				
				puntos+=2;
				
			}else if(movimientos[1]==0&&movimientos[2]==1) { // derecha
				
				girarDerecha();
				puntos+=2;
				
			}else if(movimientos[1]==1&&movimientos[2]==0) { // izquiera
				
				girarIzquierda();
				puntos+=2;
				
			}else if(movimientos[1]==1&&movimientos[2]==1) { // recto
				
				puntos-=2;
				
			}
			
			avanzar();
			
		}else { // NO vanza
			
			if(movimientos[1]==0&&movimientos[2]==0) { // nada
				
				puntos-=2;
				
			}else if(movimientos[1]==0&&movimientos[2]==1) { // gira derecha
				
				girarDerecha();
				puntos++;
				
			}else if(movimientos[1]==1&&movimientos[2]==0) { // gira izquiera
				
				girarIzquierda();
				puntos++;
				
			}else if(movimientos[1]==1&&movimientos[2]==1) { // nada
				
				puntos-=2;
				
			}
			
		}
		
		if(fin()) {
			System.out.println("El barco ha terminado su intento.");
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
		
		this.direccion=obtenerAngulo(entorno.getPaso()*5);
		this.pasos++;
	}
	
	private void girarIzquierda() {
		
		this.direccion=obtenerAngulo(-5*(entorno.getPaso()));
		this.pasos++;
	}

	public double[] sensores() {
		
		double[] sensor = new double[6];
		
		sensor[0] = delante();
		sensor[1] = costado_izquierdo();
		sensor[2] = costado_derecho();
		sensor[3] = amura_izquierda();
		sensor[4] = amura_derecha();
		sensor[5] = distanciaAsalida();

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
			return 0;
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
			return 0;
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
			return 0;
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
			return 0;
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
			return 0;
		}

	}

	private int distanciaAsalida() {

		return (int) entorno.distanciaSalida(x, y);

	}
	
	private double obtenerAngulo(double grados) {

		double angulo = direccion + grados;
	    
	    // Normalizar el ángulo para que esté dentro del rango de 0 a 359 grados
	    angulo = (angulo % 360 + 360) % 360;
	    
	    return angulo;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPasos() {
		return pasos;
	}

	public void setPasos(double pasos) {
		this.pasos = pasos;
	}
	
	public boolean fin() {
		
		if(entorno.esSalida(x, y)) {
			puntos+=10000;
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
		
		for (int i = 0; i < camino.size(); i++) {
			
			System.out.println("X: "+camino.get(i)[0]+", Y: "+camino.get(i)[1]);
			
		}
	}
	
	

}