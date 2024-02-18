import Clases.Entradas;
import Clases.Simulacion;

public class Principal{
	
	public static Simulacion simulacionActual;
	public static final String RUTA_ENTORNO = ".\\files\\entornos.txt";
	public static final String RUTA_REDES = ".\\files\\redes.txt";
	public static final String RUTA_SIMULACIONES = ".\\files\\simulaciones.txt";
	
	public static void main(String[] args) {
		
		menuPrincipal();
		
	}
	
	public static void menuPrincipal() {
		
		do {
			
			System.out.println("--- SIMULACION COLREG IA ---");
			System.out.println("1 ---> CARGAR SIMULACION");
			System.out.println("2 ---> CREAR NUEVA SIMULACION");
			System.out.println("3 ---> SEGUIR ENTRENANDO SIMULACION");
			System.out.println("4 ---> PROBAR SIMULACION YA ENTRENADA");
			System.out.println("0 ---> SALIR");
			int opcion = Entradas.entero("SELECCIONE UNA OPCION [0-4]: ");
			
			if(opcion == 1) {
				
				cargarSimulacion();
				
			}else if(opcion == 2) {
				
				crearSimulacion();
				
			}else if(opcion == 3) {
				
				simulacionActual.entrenamiento();
				
			}else if(opcion == 4) {
				
				simulacionActual.probar();
				
			}else if(opcion == 0) {
				
				System.err.println("Saliendo del programa...");
				return;
				
			}else {
				
				System.err.println("Seleccione una opción entre [0-2]");
				
			}
			
		}while(true);
		
	}
	
	public static void cargarSimulacion() {
		
		// AQUI DEBE LISTAR LAS SIMULACIONES PREVIAS REALIZAS PARA SELECCIONAR UNA
		// UNA VEZ SELECCIONADA SE CARGA Y SE VUELVE AL MENU PRINCIPAL
		
		do {
			
			System.out.println("--- CARGAR SIMULACION ---");
			System.out.println("1 ---> CARGAR SIMULACION");
			System.out.println("2 ---> CREAR NUEVA SIMULACION");
			System.out.println("0 ---> SALIR");
			int opcion = Entradas.entero("SELECCIONE UNA OPCION [0-2]: ");
			
			if(opcion == 1) {
				
			}else if(opcion == 2) {
				
			}else if(opcion == 0) {
				
				System.err.println("Saliendo del programa...");
				return;
				
			}else {
				
				System.err.println("Seleccione una opción entre [0-2]");
				
			}
			
		}while(true);
		
	}
	
	public static void crearSimulacion() {
		
		simulacionActual= new Simulacion();
		//AHORA DEBE VER COMO GUARDAR TODA LA CONFIGURACION
		String respuesta = Entradas.texto("¿Desea guardar el entorno? S - SI ");
		
		if(respuesta.equalsIgnoreCase("S")) {
			
			simulacionActual.getEntorno().guardarEntorno(RUTA_ENTORNO);
			
		}
		
		respuesta = Entradas.texto("¿Desea guardar la red neuronal? S - SI ");
		
		if(respuesta.equalsIgnoreCase("S")) {
			
			simulacionActual.getRed().guardarRed(RUTA_REDES);
			
		}
		
		respuesta = Entradas.texto("¿Desea guardar la simulacion? S - SI ");
		
		if(respuesta.equalsIgnoreCase("S")) {
			
			simulacionActual.guardarSimulacion(RUTA_SIMULACIONES);
			
		}
		
	}
	
}