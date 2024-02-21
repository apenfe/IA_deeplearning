//Clase que representa un punto en la gráfica

import processing.core.PApplet;

public class Punto {
	public static void main(String[] args) {
		 float[][] puntos = {
			      {0.1f, 0.2f},
			      {0.3f, 0.4f},
			      {0.5f, 0.6f},
			      {0.7f, 0.8f},
			      {0.9f, 0.1f}
			    };
			    Main applet = new Main(puntos);
			    PApplet.runSketch(new String[]{"Main"}, applet);
	}
}