package Clases;

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
	
	public Entorno(String nombre, double alto, double ancho, double entrada_x, double entrada_y, double salida_x,
			double salida_y, double paso, double area_aprox) {
		
		this.nombre = nombre;
		this.alto = alto;
		this.ancho = ancho;
		this.entrada_x = entrada_x;
		this.entrada_y = entrada_y;
		this.salida_x = salida_x;
		this.salida_y = salida_y;
		this.paso = paso;
		this.area_aprox = area_aprox;
		
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
		
		double diferenciaX = x - salida_x;
        double diferenciaY = y - salida_y;
        double distancia = Math.sqrt(diferenciaX * diferenciaX + diferenciaY * diferenciaY);
		
		if(distancia<=area_aprox) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
	}
	
	public boolean fueraLimites(double x, double y) {
		
		if(x>alto||x<0||y>ancho||y<0) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
	}
	
	
	
	
	
}