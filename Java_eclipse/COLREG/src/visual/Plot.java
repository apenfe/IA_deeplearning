package visual;

import processing.core.PApplet;

// Clase que hereda de PApplet y muestra los puntos en una gráfica
public class Plot extends PApplet {
	
	float[][] puntos = new float[0][0];
	int x;
	int y;
	double xE;
	double yE;
	double xS;
	double yS;

	// Constructor que recibe el array de puntos
	
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
	
  
  // Método para configurar el lienzo
  public void settings() {
    size(x+20, y+20); // 400 400
  }
  
  // Método para dibujar en el lienzo
  public void draw() {
    background(200);
    
    fill(0,0,255);
    line(0+10,0+10,0+10,y+10);
    line(0+10,0+10,x+10,0+10);
    line(x+10,y+10,0+10,y+10);
    line(x+10,y+10,x+10,0+10);
    //stroke(0);
    // Dibujar los ejes
   // line(50, height-50, width-50, height-50);
   // line(50, height-50, 50, 50);
    fill(150, 0, 0);
    ellipse((float)xE+10, (float)yE+10, 20, 20);
    fill(255, 0, 0);
    ellipse((float)xE+10, (float)yE+10, 10, 10);
    fill(0, 150, 0);
    ellipse((float)xS+10, (float)yS+10, 20, 20);
    fill(0, 255, 0);
    ellipse((float)xS+10, (float)yS+10, 10, 10);
    // Dibujar los puntos
    fill(0, 0, 200);
    for (float[] punto : puntos) {
    	//fill(255, 0, 0);
    	
      //float xPos =  map(punto[0], 0, x, 0, height);
      //float yPos =  map(punto[1], 0, y, 0,width);
      
      //ellipse(xPos, yPos, 3, 3);
    	ellipse(punto[1]+10, punto[0]+10, 3, 3);
      
    }
  }
  
  
}