package visual;

import processing.core.PApplet;
import processing.core.PImage;

public class EstablecerCasillas extends PApplet {
	
	int leftX = 0, leftY = 0; // Coordenadas para entrada
	int rightX = 0, rightY = 0; // Coordenadas para salida
	PImage fondo;
	int x, y;
	
	public EstablecerCasillas() {

	}
	
	public void setup() {
		
		this.fondo = loadImage("\\mapas\\mapa_1.png");
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double[] getInOut() {
		
		double[] inout = new double[4];
		
		inout[0]=leftX;
		inout[1]=leftY;
		inout[2]=rightX;
		inout[3]=rightY;
		
		return inout;
	}

	public void settings() {

		size(x,y); // offset of 20 per axis
	}

	public void draw() {
		
		background(fondo);

		 // Dibujar las marcas si hay coordenadas válidas
		  if (leftX != -1 && leftY != -1) {
		    fill(0, 0, 255);
		    ellipse(leftX, leftY, 10, 10); // Marca azul para el clic izquierdo
		  }
		  
		  if (rightX != -1 && rightY != -1) {
		    fill(255, 0, 0);
		    ellipse(rightX, rightY, 10, 10); // Marca roja para el clic derecho
		  }
	
	}
	
	public void mousePressed() {
		  if (mouseButton == LEFT) {
		    // Capturar coordenadas del clic izquierdo
		    leftX = mouseX;
		    leftY = mouseY;
		  } else if (mouseButton == RIGHT) {
		    // Capturar coordenadas del clic derecho
		    rightX = mouseX;
		    rightY = mouseY;
		  }
		}
	
	@Override
	public void exit() {
		
		noLoop();

	}
}