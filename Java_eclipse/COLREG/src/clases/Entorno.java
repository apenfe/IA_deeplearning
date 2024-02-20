package clases;

import java.io.FileWriter;

public class Entorno{
	
	private String nombreSimulacion;
	private String nombre;
	private final int[] origen = {0,0};
	private double alto;
	private double ancho;
	private double entradaX;
	private double entradaY;
	private double salidaX;
	private double salidaY;
	private double paso;
	private double areaAprox;
	
	public Entorno(double paso, double areaAprox, String nombreSimulacion) {
		
		this.nombre = Entradas.texto("Seleccione un nombre para el entorno: ");
		this.alto = Entradas.entero("\nSeleccione un alto para el entorno (eje y): ");
		this.ancho = Entradas.entero("Seleccione un ancho para el entorno (eje x): ");
		this.entradaX = Entradas.entero("\nSeleccione coordenada x de entrada (x): ");
		this.entradaY = Entradas.entero("Seleccione coordenada y de entrada (y): ");
		this.salidaX = Entradas.entero("\nSeleccione coordenada x de salida (x): ");
		this.salidaY = Entradas.entero("Seleccione coordenada y de salida (y): ");
		this.paso = paso;
		this.areaAprox = areaAprox;
		this.nombreSimulacion=nombreSimulacion;
		
	}
	
	public Entorno(String[] datos) {
		
		this.nombre = datos[0];
		this.alto = Double.parseDouble(datos[1]);
		this.ancho = Double.parseDouble(datos[2]);
		this.entradaX = Double.parseDouble(datos[3]);
		this.entradaY = Double.parseDouble(datos[4]);
		this.salidaX = Double.parseDouble(datos[5]);
		this.salidaY = Double.parseDouble(datos[6]);
		this.paso = Double.parseDouble(datos[7]);
		this.areaAprox = Double.parseDouble(datos[8]);
		this.nombreSimulacion=datos[9];
		
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
		return origen;
	}

	@Override
	public String toString() {
		
		return nombre+"#"+alto+"#"+ancho+"#"+entradaX+"#"+entradaY+"#"+salidaX+"#"+salidaY+"#"+paso+"#"+areaAprox+"#"+nombreSimulacion;
		
	}
	
	public boolean esSalida(double x, double y) {
		
		if(distanciaSalida(x,y)<=areaAprox) {
			System.err.println("Salida Alcanzada!!");
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
			
			System.err.println("Fuera de los limites.");
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
	
}