package visual;

import processing.core.PApplet;

// Clase que hereda de PApplet y muestra los puntos en una gráfica
public class Plot extends PApplet {
	float[][] puntos = new float[0][0];

	// Constructor que recibe el array de puntos
	
	public Plot() {
	
	}
	
	public void setPuntos(float[][] puntos) {
		
		
        this.puntos = puntos;
    }
	
  
  // Método para configurar el lienzo
  public void settings() {
    size(400, 400);
  }
  
  // Método para dibujar en el lienzo
  public void draw() {
    background(255);
    stroke(0);
    // Dibujar los ejes
   // line(50, height-50, width-50, height-50);
   // line(50, height-50, 50, 50);
    
    // Dibujar los puntos
    fill(100, 100, 100);
    for (float[] punto : puntos) {
     //float xPos =  map(punto[0], 0, 50, 0, width);
     // float yPos =  map(punto[1], 0, 50, height, 0);
      float xPos =  map(punto[0], 0, 50, 50, width-50);
      float yPos =  map(punto[1], 0, 50, height-50, 50);
      ellipse(xPos, yPos, 8, 8);
    }
  }
  
  
}