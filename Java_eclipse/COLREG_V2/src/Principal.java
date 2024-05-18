import clases.Entradas;
import clases.Ficheros;
import clases.Simulacion;

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
			
			System.out.println("\n--- SIMULACION COLREG IA ---");
			System.out.println("1 ---> CARGAR SIMULACION");
			System.out.println("2 ---> CREAR NUEVA SIMULACION");
			System.out.println("3 ---> PROBAR SIMULACION YA ENTRENADA");
			System.out.println("4 ---> VER DATOS SIMULACION ACTUAL");
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
					simulacionActual.probar();
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
		
		String[] simulaciones = Ficheros.leerTxt(RUTA_SIMULACIONES);
		
		do {
			
			
			System.out.println("\n--- CARGAR SIMULACION ---");
			System.out.println("0 --> SALIR");
			
			for (int i = 0; i < simulaciones.length; i++) {
				System.out.println((i+1)+" --> "+simulaciones[i]);
			}
			
			int opcion = Entradas.entero("SELECCIONE UNA OPCION [0-"+(simulaciones.length)+"]: ");
			
			if(opcion == 0) {
				
				System.err.println("\nSaliendo del menu de carga...");
				return;
				
			}else if(opcion >=1 && opcion <=simulaciones.length) {
				
				String nombre_simulacion = simulaciones[opcion-1];
				
				String[] datos_entorno = Ficheros.leerTxt(RUTA_ENTORNO,nombre_simulacion);
				String[] datos_red = Ficheros.leerTxt(RUTA_REDES,nombre_simulacion);
				
				simulacionActual= new Simulacion(nombre_simulacion,datos_entorno,datos_red);
				break;
			}else {
				
				System.err.println("SELECCIONE UNA OPCION [0-"+(simulaciones.length)+"]: ");
				
			}
			
		}while(true);
		
	}
	
	public static void crearSimulacion() {
		
		simulacionActual= new Simulacion();
		String respuesta = Entradas.texto("¿Desea guardar el entorno? S - SI ");
		
		if(respuesta.equalsIgnoreCase("S")) {
			
			simulacionActual.getEntorno().guardarEntorno(RUTA_ENTORNO);
			
		}
		
		respuesta = Entradas.texto("¿Desea guardar la red neuronal? S - SI ");
		
		if(respuesta.equalsIgnoreCase("S")) {
			
			//simulacionActual.getRed().guardarRed(RUTA_REDES);
			
		}
		
		respuesta = Entradas.texto("¿Desea guardar la simulacion? S - SI ");
		
		if(respuesta.equalsIgnoreCase("S")) {
			
			simulacionActual.guardarSimulacion(RUTA_SIMULACIONES);
			
		}
		
	}
	
}