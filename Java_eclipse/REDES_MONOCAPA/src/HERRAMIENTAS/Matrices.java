package HERRAMIENTAS;

public class Matrices{
	
	public static int productoEscalar(int[] a, int[] b) {
		
		int producto = 0;
		
		if(a.length==b.length) {
			
			for (int i = 0; i < b.length; i++) {
				
				producto += a[i]*b[i];
				
			}
			
			return producto;
			
		}else {
			
			 throw new IllegalArgumentException("Los vectores deben ser del mismo tamaño");
			
		}
		
	}
	
	public static int[][] transponer(int[][] a) {
		
		int[][] transpuesta = new int[a[0].length][a.length];
		
		for (int i = 0; i < a.length; i++) {
			
			for (int j = 0; j < a[0].length; j++) {
				
				transpuesta[j][i] = a[i][j];
				
			}
			
		}
		
		return transpuesta;
		
	}
	
	public static boolean esDiagonal(int[][] a) {
		
		if(a.length!=a[0].length) {
			
			return false;
			
		}
		
		for (int i = 0; i < a.length; i++) {
			
			for (int j = 0; j < a.length; j++) {
				
				if(i!=j && a[i][j] !=0) {
					
					return false;
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
	public static boolean esIdentidad(int[][] a) {
		
		if(!esDiagonal(a)) {
			
			return false;
			
		}
		
		for (int i = 0; i < a.length; i++) {
			
			for (int j = 0; j < a.length; j++) {
				
				if(i==j && a[i][j] !=1) {
					
					return false;
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
	public static int[][] suma(int[][] a, int[][] b) {
		
		if(a.length!=b.length||a[0].length!=b[0].length) {
			
			 throw new IllegalArgumentException("Las matrices deben tener la misma dimension");
			
		}
		
		int[][] suma = new int[a.length][a[0].length];
		
		for (int i = 0; i < a.length; i++) {
			
			for (int j = 0; j < a[0].length; j++) {
				
				suma[i][j]=a[i][j]+b[i][j];
				
			}
			
		}
		
		return suma;
		
	}
	
	public static int[][] producto(int[][] a, int[][] b) {
		
		if(a[0].length!=b.length) {
			
			 throw new IllegalArgumentException("Las matrices deben tener la misma dimension");
			
		}
		
		int[][] salida = new int[a.length][b[0].length];
		
		for (int i = 0; i < salida.length; i++) {
			
			for (int j = 0; j < salida[i].length; j++) {
				
				int sum = 0;
				
	            for (int k = 0; k < a[i].length; k++) {
	            	
	                sum += a[i][k] * b[k][j];
	            }
	            
	            salida[i][j] = sum;
				
			}
			
		}
		
		return salida;	
		
	}
	
	
	
}