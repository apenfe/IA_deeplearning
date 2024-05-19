package red;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Entradas;
import entorno.Entorno;

public class DAO{

	private static final String SERVER = "127.0.0.1";
	private static final String PORT = "3306";
	private static final String DATABASE = "redes_neuronales";
	private static final String URL = "jdbc:mysql://"+SERVER+":"+PORT+"/"+DATABASE;
	private static final String USER = "root";
	private static final String PASS = "1234";
	
	public boolean guardarRed(RedNeuronal red) {

		try {
			
			String descripcion = Entradas.texto("Inserte una descricion para la RED NEURONAL: ");

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String nn = "INSERT INTO red(nombre,numCapas,descripcion) VALUES ('"+red.getNombre()+"','"+red.getNumCapas()+"','"+descripcion+"')";
			stmt.executeUpdate(nn);
			
			for (int i = 0; i < red.getNumCapas(); i++) {
				
				String layer = "INSERT INTO capa(red_nombre,ordenCapa,numNeuronas,numNeuronasCapaAnterior,funcion) VALUES ('"+red.getNombre()+"','"+(i+1)+"','"+red.getCapas()[i].getNumNeuronas()+"','"+red.getCapas()[i].getNumNeuronasCapaAnterior()+"','"+red.getCapas()[i].getFuncion()+"')";
				stmt.executeUpdate(layer);
				
			}
			
			stmt.close();
			conn.close();
			
			return true;

		} catch (Exception e) {
			
			return false;
			
		}
		
	}
	
	public boolean guardarGenes(double[] genes) {
		
	
		
	}
	
	public double[] cargarGenes(User u, String password) {
			
	
		
	}
	
	public RedNeuronal cargarRedNeuronal(String username, String password) {

		
		
	}
	
	public boolean guardarEntorno(Entorno en) {

		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
				
			String entorno = "INSERT INTO capa(nombre,alto,ancho,entradaX,entradaY,salidaX,salidaY,paso,areaAprox) "
			+ "VALUES ('"+en.getNombre()+"','"+en.getAlto()+"','"+en.getAncho()+"','"+en.getEntradaX()+"','"+en.getEntradaY()+"','"+en.getSalidaX()+"','"+en.getSalidaY()+"','"+en.getPaso()+"','"+en.getAreaAprox()+"')";
			stmt.executeUpdate(entorno);
			
			stmt.close();
			conn.close();
			
			return true;

		} catch (Exception e) {
			
			return false;
			
		}
		
	}
	
	public Entorno cargararEntorno(String nombre) {

		Entorno cargado = null;
		
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String entorno = "SELECT * FROM entorno WHHERE nombre = '"+nombre+"';";
			
			ResultSet rs = stmt.executeQuery(entorno);
			
			while(rs.next()) {
				cargado = new Entorno(rs.getString("nombre"),rs.getString("alto"),rs.getString("ancho"),rs.getString("entradaX"),rs.getString("entradaY"),rs.getString("salidaX"),rs.getString("salidaY"),rs.getString("paso"),rs.getString("areaAprox"));
			}
			
			rs.close();
			stmt.close();
			conn.close();
			return cargado;

		} catch (Exception e) {
			
			return cargado;
			
		}
		
	}
	
}