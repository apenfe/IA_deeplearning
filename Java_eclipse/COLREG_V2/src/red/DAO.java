package red;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Entradas;

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
		
		boolean check = false;
		
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String consulta = "SELECT * FROM user WHERE username = " + user.username + " OR nif = '" + user.nif + "' OR email = '"+user.email+"';";
			ResultSet rs = stmt.executeQuery(consulta);

			int i = 0;
			
			while (rs.next()) {

				i++;

			}
			
			if(i>0) {
				check = true;
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			check = false;

		}
		
		return check;
		
	}
	
	public double[] cargarGenes(User u, String password) {
			
		String pass = Utils.encryptMd5(password);
			
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String query = "INSERT INTO user (username, password, name, nif, email, address, birthdate, role) values ('"+u.username+"','"+pass+"','"+u.name+"','"+u.nif+"','"+u.email+"','"+u.addres+"','"+u.birthdate+"','user');";
				
			stmt.executeUpdate(query);
				
			stmt.close();
			conn.close();
			return true;

		} catch (Exception e) {
			
			return false;
				
		}
		
	}
	
	public RedNeuronal cargarRedNeuronal(String username, String password) {

		boolean exit = false;
		
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String consulta = "SELECT username FROM user WHERE username = '" + username + "' AND password = '" + password + "';";
			ResultSet rs = stmt.executeQuery(consulta);

			while (rs.next()) {
					
				if(rs.getString("username").equals(username)) {
						
					exit = true;
						
				}
					
			}

			rs.close();
			stmt.close();
			conn.close();
			
			return exit;

		} catch (Exception e) {

			return exit;
				
		}
		
	}
	
	public boolean guardarEntorno(RedNeuronal red) {

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
	
	public boolean cargararEntorno(RedNeuronal red) {

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
	
}