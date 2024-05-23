package agente;

import java.util.ArrayList;

public interface Ship{
	
	public ArrayList<Double[]> getCamino();
	public void setCamino(ArrayList<Double[]> camino);

	public void initShip();
	
	public void acciones(double[] movimientos);
	
	public void avanzar();
	
	public void girarDerecha();
	
	public void girarIzquierda();

	public double[] sensores();
	
	public int delante();

	public int costado_izquierdo();

	public int costado_derecho();

	public int amura_izquierda();

	public int amura_derecha();
	
	public int seccion_derecha();
	
	public int seccion_izquierda();
	
	public int seccion_frontal();

	public double distanciaAsalida();
	
	public double direccionActual();
	
	public double distanciaAentrada();
	
	public double posX();
	
	public double posY();
	
	public double normalizar(double value, double min, double max);
	
	public double calculateFitness();
	
	public double obtenerAngulo(double grados);
	
	public boolean fin();
	
	public boolean win();
	
	public boolean lose();

	public double[] getAdn();

	public void setAdn(double[] cromosomas);
		
	public float[][] caminoFloat();

	public double getPasos();

	public void setPasos(double pasos);

}