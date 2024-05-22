package entorno;

import java.io.FileWriter;

import clases.DAO;
import clases.Entradas;

public class Entorno{
	
	private String nombre;
	private final int[] ORIGEN = {0,0};
	private double alto;
	private double ancho;
	private double entradaX;
	private double entradaY;
	private double salidaX;
	private double salidaY;
	private double paso;
	private double areaAprox;
	
	public Entorno(double paso, double areaAprox) {
		
		this.nombre = Entradas.texto("Seleccione un nombre para el entorno: ");
		this.alto = Entradas.entero("\nSeleccione un alto para el entorno (eje y): ");
		this.ancho = Entradas.entero("Seleccione un ancho para el entorno (eje x): ");
		this.entradaX = Entradas.entero("\nSeleccione coordenada x de entrada (x): ");
		this.entradaY = Entradas.entero("Seleccione coordenada y de entrada (y): ");
		this.salidaX = Entradas.entero("\nSeleccione coordenada x de salida (x): ");
		this.salidaY = Entradas.entero("Seleccione coordenada y de salida (y): ");
		this.paso = paso;
		this.areaAprox = areaAprox;
		
	}
	
	public Entorno(String nombre, String alto, String ancho, String entradaX, String entradaY, String salidaX, String salidaY, String paso, String areaAprox) {
		
		this.nombre = nombre;
		this.alto = Double.parseDouble(alto);
		this.ancho =  Double.parseDouble(ancho);
		this.entradaX =  Double.parseDouble(entradaX);
		this.entradaY =  Double.parseDouble(entradaY);
		this.salidaX =  Double.parseDouble(salidaX);
		this.salidaY =  Double.parseDouble(salidaY);
		this.paso =  Double.parseDouble(paso);
		this.areaAprox =  Double.parseDouble(areaAprox);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getEntradaX() {
		return entradaX;
	}

	public void setEntradaX(double entradaX) {
		this.entradaX = entradaX;
	}

	public double getEntradaY() {
		return entradaY;
	}

	public void setEntradaY(double entradaY) {
		this.entradaY = entradaY;
	}

	public double getSalidaX() {
		return salidaX;
	}

	public void setSalidaX(double salidaX) {
		this.salidaX = salidaX;
	}

	public double getSalidaY() {
		return salidaY;
	}

	public void setSalidaY(double salidaY) {
		this.salidaY = salidaY;
	}

	public double getPaso() {
		return paso;
	}

	public void setPaso(double paso) {
		this.paso = paso;
	}

	public double getAreaAprox() {
		return areaAprox;
	}

	public void setAreaAprox(double areaAprox) {
		this.areaAprox = areaAprox;
	}

	public int[] getOrigen() {
		return ORIGEN;
	}

	@Override
	public String toString() {
		
		return nombre+"#"+alto+"#"+ancho+"#"+entradaX+"#"+entradaY+"#"+salidaX+"#"+salidaY+"#"+paso+"#"+areaAprox;
		
	}
	
	public boolean esSalida(double x, double y) {
		
		if(distanciaSalida(x,y)<=areaAprox) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
	}
	
	public double distanciaSalida(double x, double y) {
		
		double diferenciaX = x - salidaX;
        double diferenciaY = y - salidaY;
        return Math.sqrt(diferenciaX * diferenciaX + diferenciaY * diferenciaY);
		
	}
	
	public double distanciaEntrada(double x, double y) {
		
		double diferenciaX = x - entradaX;
        double diferenciaY = y - entradaY;
        return Math.sqrt(diferenciaX * diferenciaX + diferenciaY * diferenciaY);
		
	}
	
	public boolean fueraLimites(double x, double y) {
		
		if(x>alto||x<0||y>ancho||y<0) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
	}
	
	public boolean guardarEntorno(String ruta) {
				
		try {

			FileWriter escritor = new FileWriter(ruta,true);
			
			String entorno = toString();
				
			escritor.write("\n" + entorno);
			escritor.close();
			return true;
			
		} catch (Exception e) {

			System.err.println("ERROR AL GUARDAR ARCHIVO " + ruta);
			return false;

		}
		
	}
	
	public boolean guardarEntorno() {
		
		DAO db = new DAO();
		
		if(db.guardarEntorno(this)) {
			
			return true;
			
		}
	
		return false;
		
	}
	
}