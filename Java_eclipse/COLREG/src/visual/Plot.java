package visual;

import processing.core.PApplet;

public class Plot extends PApplet {

	float[][] puntos = new float[0][0];
	int x, y;
	double xE, yE, xS, yS;

	public Plot() {

	}

	public void setPuntos(float[][] puntos) {

		this.puntos = puntos;
	}

	public void setXY(int x, int y) {

		this.x = x;
		this.y = y;

	}

	public void setInOut(double xE, double yE, double xS, double yS) {

		this.xE = xE;
		this.yE = yE;
		this.xS = xS;
		this.yS = yS;

	}

	// Método para configurar la ventana
	
	public void settings() {
		
		size(x + 20, y + 20); // offset de 20 por eje
		
	}

	// Método para dibujar en el lienzo
	public void draw() {
		
		background(200);

		// Pintar bordes
		fill(0, 0, 255);
		line(0 + 10, 0 + 10, 0 + 10, y + 10);
		line(0 + 10, 0 + 10, x + 10, 0 + 10);
		line(x + 10, y + 10, 0 + 10, y + 10);
		line(x + 10, y + 10, x + 10, 0 + 10);
		
		// Pintar entrada y salida
		fill(150, 0, 0);
		ellipse((float) xE + 10, (float) yE + 10, 20, 20);
		fill(255, 0, 0);
		ellipse((float) xE + 10, (float) yE + 10, 10, 10);
		fill(0, 150, 0);
		ellipse((float) xS + 10, (float) yS + 10, 20, 20);
		fill(0, 255, 0);
		ellipse((float) xS + 10, (float) yS + 10, 10, 10);
		
		// Dibujar los puntos
		fill(0, 0, 200);
		
		for (float[] punto : puntos) {
	
			ellipse(punto[1] + 10, punto[0] + 10, 3, 3);

		}
		
	}

}