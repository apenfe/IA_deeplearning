package Clases;

import java.util.ArrayList;

public class Barco{
	
	private int id;
	private double pasos;
	private final double horizonte = 10;
	private double x;
	private double y;
	private ArrayList<Double[]> camino = new ArrayList<Double[]>();
	private Entorno entorno;
	private double direccion;
	
	public Barco(int id, double x, double y, Entorno entorno) {
		
		this.id = id;
		this.entorno = entorno;
		this.x = entorno.getEntrada_x();
		this.y = entorno.getEntrada_y();
		this.direccion = 0; //expresado en grados
		Double[] posicion= new Double[2];
		posicion[0]=x;
		posicion[1]=y;
		camino.add(posicion);
		pasos++;
		
	}
	
	public void acciones(boolean[] movimientos) {
		
		if(movimientos[0]) { // avanzar
			
			if(!movimientos[1]&&!movimientos[2]) { // recto
				
			}else if(!movimientos[1]&&movimientos[2]) { // derecha
				
				girarDerecha();
				
			}else if(movimientos[1]&&!movimientos[2]) { // izquiera
				
				girarIzquierda();
				
			}else if(movimientos[1]&&movimientos[2]) { // recto
				
			}
			
			avanzar();
			
		}else { // NO vanza
			
			if(!movimientos[1]&&!movimientos[2]) { // nada
				
			}else if(!movimientos[1]&&movimientos[2]) { // gira derecha
				
				girarDerecha();
				
			}else if(movimientos[1]&&!movimientos[2]) { // gira izquiera
				
				girarIzquierda();
				
			}else if(movimientos[1]&&movimientos[2]) { // nada
				
			}
			
		}
		
		
	}
	
	private void avanzar() {

		// Convertir el ángulo a radianes
		double anguloRadianes = Math.toRadians(obtenerAngulo(0));

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
		
		direccion=obtenerAngulo((int)entorno.getPaso());

	}
	
	private void girarIzquierda() {
		
		direccion=obtenerAngulo((int)-entorno.getPaso());

	}

	public int[] sensores() {
		
		int[] sensor = new int[6];
		
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
	
	private double obtenerAngulo(int grados) {
		
		double angulo = direccion + grados;

	    // Ajustar el ángulo para que esté dentro del rango de 0 a 359 grados
		
	    if (angulo >= 360) {
	    	
	        angulo -= 360;
	        
	    } else if (angulo < 0) {
	    	
	        angulo += 360;
	        
	    }

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
	
	

}