import Clases.Entradas;

public class Principal{
	
	public static void main(String[] args) {
		
		menuPrincipal();
		
	}
	
	public static void menuPrincipal() {
		
		do {
			
			System.out.println("--- SIMULACION COLREG IA ---");
			System.out.println("1 ---> CARGAR SIMULACION");
			System.out.println("2 ---> CREAR NUEVA SIMULACION");
			System.out.println("0 ---> SALIR");
			int opcion = Entradas.entero("SELECCIONE UNA OPCION [0-2]: ");
			
			if(opcion == 1) {
				
				cargarSimulacion();
				
			}else if(opcion == 2) {
				
				crearSimulacion();
				
			}else if(opcion == 0) {
				
				System.err.println("Saliendo del programa...");
				return;
				
			}else {
				
				System.err.println("Seleccione una opción entre [0-2]");
				
			}
			
		}while(true);
		
	}
	
	public static void cargarSimulacion() {
		
		do {
			
			System.out.println("--- SIMULACION COLREG IA ---");
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
		
		do {
			
			System.out.println("--- SIMULACION COLREG IA ---");
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
	
}