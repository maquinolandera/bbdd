package com.fran;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class Main {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Scanner entrada = new Scanner(System.in);
		Scanner entrada2 = new Scanner(System.in);
		Servicios objMain = new Servicios();
		boolean seguir = false;
		
		while(!seguir)
		{
			System.out.println("1.-Insertar Registro");
			System.out.println("2-Eliminar Registro");
			System.out.println("3.-Consultar Registro");
			System.out.println("4.-Modificación de Registro");
			String op = entrada2.next();
			if(op.equals("1"))
			{
				ArrayList<Persona> per = new ArrayList <Persona>();
				System.out.println("Introduce el DNI usuario");
				String dni = entrada.next();
				System.out.println(" Introduce en nombre usuario");
				String nombre = entrada.next();
				System.out.println("Introduce la edad");
				int edad = entrada2.nextInt();
				Persona objPer = new Persona(dni,nombre,edad);
				per.add(objPer);
				Iterator <Persona> it = per.iterator();
				Persona persona = null;
				while(it.hasNext())
				{
					 persona = it.next();
					 objMain.insertarRegistro(persona.getDni(), persona.getNombre(), persona.getEdad());
				}
				
				seguir = false;
			}else if(op.equals("2"))
			{

				System.out.println("Introduce dni a borrar");
				String dni = entrada.next();
				objMain.eliminarRegistro(dni);
				seguir = false;
				
			}else if(op.equals("3"))
			{	
			
				int cont = 0;
				ArrayList <Persona> consulta  = objMain.consultaPersona();
				
				Iterator <Persona>it = consulta.iterator();
				System.out.println("******************CONSULTA BBDD *************************");
				System.out.println("*********************************************************");
				
				while(it.hasNext())
				{
					System.out.println("");
					
					Persona penyi = it.next();
					System.out.println("Dni "+penyi.getDni()+" Nombre "+penyi.getNombre()+" Edad "+penyi.getEdad());
					cont++;
				}
				System.out.println("********************************************");
				System.out.println("Registros "+cont+"\n");
				System.out.println("********************************************");
				seguir = true;
			}else if(op.equals("4"))
			{
				System.out.println(" Introduce el DNI");
				String dni = entrada.next();
				System.out.println("Modificar el nombre ");
				String nombre = entrada.next();
				System.out.println("Modifica la edad");
				int edad = entrada2.nextInt();
				objMain.modificarDatos(dni,nombre,edad);
			}




		}










	}
	
}
