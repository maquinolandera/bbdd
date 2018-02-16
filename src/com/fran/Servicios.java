package com.fran;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Servicios {
	//En el método insertarRegistro meteremos los parámetros de entrada y crearemos 
	// una variable tipo Connection para guardar la conexión
	public void insertarRegistro(String d, String n, int e){
		try {
			Connection databaseConnection = getConnection();
			java.sql.Statement statement =  databaseConnection.createStatement();
			/*statement.executeUpdate("CREATE TABLE nueva_tabla (dni VARCHAR(9) NOT NULL PRIMARY KEY, nombre VARCHAR(30))");
			statement.executeUpdate("DROP TABLE persona");*/
			String registro = "INSERT INTO prueba (dni,edad,nombre)VALUES('"+d+"',"+e+",'"+n+"')";
			statement.execute(registro);
			statement.close();
			databaseConnection.close();
			System.out.println("se ha introducido el registro con nombre "+n);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}
	//creamos el método getConnection que nos devolverá un objeto de la clase connection

	Connection getConnection() {
		Connection databaseConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String sourceURL = "jdbc:mysql://localhost/personas";
			// Create a connection through the DriverManager
			databaseConnection = DriverManager.getConnection(sourceURL, "root", "");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Define the data source for the driver
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return databaseConnection;
	}
	public void eliminarRegistro(String dni)
	{
		Connection databaseConnection = getConnection();
		try {
			java.sql.Statement statement =  databaseConnection.createStatement();
			String borrar = "DELETE FROM prueba WHERE dni='"+dni+"'";
			statement.execute(borrar);
			statement.close();
			databaseConnection.close();
			System.out.println("Registro Eliminado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public ArrayList<Persona> consultaPersona()
	{
		Connection databaseConnection = getConnection();
		ArrayList<Persona> gente = new ArrayList <Persona>();
		
		try {
			java.sql.Statement statement =  databaseConnection.createStatement();
			ResultSet personas = statement.executeQuery("SELECT * FROM prueba");
			
			
			while(personas.next())
			{
				
				String persona = personas.getString("dni");
				String nombre = personas.getString("nombre");
				int edad = personas.getInt("edad");
				Persona consulta = new Persona(persona, nombre,edad);
				
				gente.add(consulta);
//				Iterator <Persona> it = gente.iterator();
//				while(it.hasNext())
//				{
//					Persona nuevo = it.next();
//					System.out.println(nuevo.getNombre());
//				}
				
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return gente;
	}
	public  void modificarDatos(String dni, String nombre,int edad)
	{
		Connection databaseConnection = getConnection();
		try {
			java.sql.Statement statement = databaseConnection.createStatement();
			statement.executeUpdate("UPDATE prueba SET nombre='"+nombre+"', edad = '"+edad+"' WHERE dni='"+dni+"'");
			System.out.println("**************************************************************");
			System.out.println("Modificado dni "+dni+" por nombre "+nombre+" Su edad modificada "+edad);
			System.out.println("**************************************************************");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("no había ningún registro en la base de datos");
		}
		
	}

}
