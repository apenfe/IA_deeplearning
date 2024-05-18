package clases;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ficheros{

	public static String[] leerTxt(String ruta) {
		
		ArrayList<String> lineas = new ArrayList<>();

		try {

			File archivo = new File(ruta);
			Scanner lector = new Scanner(archivo);
			
			while(lector.hasNextLine()) {
				
				lineas.add(lector.nextLine());
				
			}

			lector.close();
			
		} catch (Exception e) {

			System.err.println("ERROR AL LEER ARCHIVO " + ruta);

		}
		
		return lineas.toArray(new String[0]);
		
	}
	
	public static String[] leerTxt(String ruta, String clave) {
		
		String[] linea = new String[0];
		
		try {

			File archivo = new File(ruta);
			Scanner lector = new Scanner(archivo);
			
			while(lector.hasNextLine()) {
				
				linea = lector.nextLine().split("#");
				
				if(linea[linea.length-1].equals(clave)) {
					break;
				}
				
			}

			lector.close();
			
		} catch (Exception e) {

			System.err.println("ERROR AL LEER ARCHIVO " + ruta);

		}
		
		return linea;
		
	}
	
	public static boolean guardarTxt(String ruta, String nombre, String[] lineas) { 

		try {

			FileWriter escritor = new FileWriter(ruta+nombre+".txt",true);
			
			for (int i = 0; i < lineas.length; i++) {
				
				escritor.write("\n"+lineas[i]);
				
			}

			escritor.close();
			return true;
			
		} catch (Exception e) {

			System.err.println("ERROR AL GUARDAR ARCHIVO " + ruta);
			return false;

		}
		
	}	
	
}