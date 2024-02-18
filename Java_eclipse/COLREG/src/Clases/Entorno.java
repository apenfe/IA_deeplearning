package Clases;

import java.io.FileWriter;

public class Entorno{
	
	private String nombre;
	private final int[] origen = {0,0};
	private double alto;
	private double ancho;
	private double entrada_x;
	private double entrada_y;
	private double salida_x;
	private double salida_y;
	private double paso;
	private double area_aprox;
	
	public Entorno(double paso, double area_aprox) {
		
		this.nombre = Entradas.texto("Seleccione un nombre para el entorno: ");
		this.alto = Entradas.entero("Seleccione un alto para el entorno (eje y): ");
		this.ancho = Entradas.entero("Seleccione un ancho para el entorno (eje x): ");
		this.entrada_x = Entradas.entero("Seleccione coordenada x de entrada (x): ");
		this.entrada_y = Entradas.entero("Seleccione coordenada y de entrada (y): ");
		this.salida_x = Entradas.entero("Seleccione coordenada x de salida (x): ");
		this.salida_y = Entradas.entero("Seleccione coordenada y de salida (y): ");
		this.paso = paso;
		this.area_aprox = area_aprox;
		
	}
	
	public Entorno(String[] datos) {
		
		this.nombre = datos[0];
		this.alto = Double.parseDouble(datos[1]);
		this.ancho = Double.parseDouble(datos[2]);
		this.entrada_x = Double.parseDouble(datos[3]);
		this.entrada_y = Double.parseDouble(datos[4]);
		this.salida_x = Double.parseDouble(datos[5]);
		this.salida_y = Double.parseDouble(datos[6]);
		this.paso = Double.parseDouble(datos[7]);
		this.area_aprox = Double.parseDouble(datos[8]);
		
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

	public double getEntrada_x() {
		return entrada_x;
	}

	public void setEntrada_x(double entrada_x) {
		this.entrada_x = entrada_x;
	}

	public double getEntrada_y() {
		return entrada_y;
	}

	public void setEntrada_y(double entrada_y) {
		this.entrada_y = entrada_y;
	}

	public double getSalida_x() {
		return salida_x;
	}

	public void setSalida_x(double salida_x) {
		this.salida_x = salida_x;
	}

	public double getSalida_y() {
		return salida_y;
	}

	public void setSalida_y(double salida_y) {
		this.salida_y = salida_y;
	}

	public double getPaso() {
		return paso;
	}

	public void setPaso(double paso) {
		this.paso = paso;
	}

	public double getArea_aprox() {
		return area_aprox;
	}

	public void setArea_aprox(double area_aprox) {
		this.area_aprox = area_aprox;
	}

	public int[] getOrigen() {
		return origen;
	}

	@Override
	public String toString() {
		
		return nombre+"#"+alto+"#"+ancho+"#"+entrada_x+"#"+entrada_y+"#"+salida_x+"#"+salida_y+"#"+paso+"#"+area_aprox;
		
	}
	
	public boolean esSalida(double x, double y) {
		
		if(distanciaSalida(x,y)<=area_aprox) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
	}
	
	public double distanciaSalida(double x, double y) {
		
		double diferenciaX = x - salida_x;
        double diferenciaY = y - salida_y;
        double distancia = Math.sqrt(diferenciaX * diferenciaX + diferenciaY * diferenciaY);
			
        return distancia;
		
	}
	
	public boolean fueraLimites(double x, double y) {
		
		if(x>alto||x<0||y>ancho||y<0) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
	}
	
	public boolean guardarEntorno(String ruta) {
		
		String nombre = ""+this.nombre;
		
		try {

			FileWriter escritor = new FileWriter(ruta + nombre + ".txt",true);
			
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