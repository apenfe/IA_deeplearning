import clases.Entradas;
import clases.Simulacion;

public class Principal{
	
	/*
	 * ENTRENAR RED
	 * CREAR ALGORITMO
	 * GUARDAR Y CARGAR ADN
	 * PODER PROBAR UN ADN
	 * PODER CONTINUAR ENTRENAMIENTO CON ADN, ENTORNO Y RED ALMACENADA
	 * ENTORNO GRAFICO
	 * AÑADIR GRAFICAS DE ENTRENAMIENTO Y VISUALIZACION DE AGENTES
	 * AÑADIR INTERFACES Y MEJORAS AL ENTORNO
	 */
	
	public static Simulacion simulacionActual;
	
	public static void main(String[] args) {
		
		menuPrincipal();
		
	}
	
	public static void menuPrincipal() {
		
		do {
			
			System.out.println("\n--- SIMULACION COLREG IA ---");
			System.out.println("1 ---> CARGAR RED");
			System.out.println("2 ---> CREAR NUEVA RED");
			System.out.println("3 ---> ENTRENAR_RED");
			System.out.println("4 ---> VER DATOS RED ACTUAL");
			System.out.println("0 ---> SALIR");
			int opcion = Entradas.entero("SELECCIONE UNA OPCION [0-4]: ");
			
			if(opcion == 1) {
				
				cargarSimulacion();
				
			}else if(opcion == 2) {
				
				crearSimulacion();
				
			}else if(opcion == 3) {
				
				if(simulacionActual==null) {
					System.err.println("\nDebe cargar o crear una simulacion");
				}else {
					entrenamiento();
				}
				
			}else if(opcion == 4) {
				
				if(simulacionActual==null) {
					System.err.println("\nDebe cargar o crear una simulacion");
				}else {
					
					verSimulacion();
				}
				
			}else if(opcion == 0) {
				
				System.err.println("\nSaliendo del programa...");
				return;
				
			}else {
				
				System.err.println("\nSeleccione una opción entre [0-4]");
				
			}
			
		}while(true);
		
	}
	
	public static void verSimulacion() {
		
		System.out.println(simulacionActual.toString());
		
	}
	
	public static void cargarSimulacion() {
		
		simulacionActual= new Simulacion();
		
		String nombreRed = Entradas.texto("Inserte el nombre de la red a cargar: ");
		
		if(simulacionActual.cargarRed(nombreRed)) {
			System.out.println("Red cargada correctamente");
		}else {
			System.out.println("Error al cargar la red");
		}
		
		String nombreEntorno = Entradas.texto("Inserte el nombre del entorno a cargar: ");
		
		if(simulacionActual.cargarEntorno(nombreEntorno)) {
			System.out.println("Entorno cargado correctamente");
		}else {
			System.out.println("Error al cargar el entorno");
		}
		
	}
	
	public static void crearSimulacion() {
		
		boolean entorno=false;
		
		String respuesta = Entradas.texto("¿Desea crear un entorno? S - SI ");
		
		if(respuesta.equalsIgnoreCase("S")) {
			
			entorno=true;
			
		}
		
		simulacionActual= new Simulacion(entorno);
		
		if(entorno) {
			
			respuesta = Entradas.texto("¿Desea guardar el entorno? S - SI ");
			
			if(respuesta.equalsIgnoreCase("S")) {
				
				if(simulacionActual.getEntorno().guardarEntorno()) {
					System.out.println("ENTORNO GUARDADO");
				}else {
					System.err.println("ERROR AL GUARDAR ENTORNO");
				}	
				
			}
			
		}
		
		respuesta = Entradas.texto("¿Desea guardar la red neuronal? S - SI ");
		
		if(respuesta.equalsIgnoreCase("S")) {
			
			if(simulacionActual.getRed().guardarRed()) {
				System.out.println("RED GUARDADA");
			}else {
				System.err.println("ERROR AL GUARDAR RED");
			}	
			
		}
		
	}
	
	public static void entrenamiento() {
		
		
	}
	
}