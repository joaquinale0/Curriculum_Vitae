package test;
import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import supermerk.*;

public class TestMain {
	
		//Registrar la JDBC Driver
		//JDBC nombre del driver y URL de la BDD
		static final String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
		static final String DB_URL="jdbc:mysql://localhost:3306/supermarkbasedatos";
		// Credenciales de la BDD
		static final String USER = "root";
		static final String PASS= "jag40515989";

	public static void main(String[] args) {
		
		
		/*  VARIABLES PARA JAVA  */
		Cliente usuario = null;
		
		/// ---- datos cliente para registrarse
		String nombreUsuario;
		String password;
		int id=0;
		String nombre;
		String apellido;
		int dni;
		// datos que retornaremos del objeto
		String domicilio;
		int telefono;
		String pais;
		String provincia;
		int codigoPostal;
		
			
		Scanner teclado = new Scanner(System.in);
		
		int opcion;
		
		
		
		/*  CONEXIONES Y REGISTROS MYSQL */
		// creamos variable String sql para escribir en codigo sql
		//dentro de java el objeto 'ResultSet = SELECT'
		String sql;
		
		//registrar el driver
		Connection conn=null;
		Statement stmt = null;
		
		try {
			//PASO2: Regristramos JDBC Driver
			Class.forName(JDBC_DRIVER);
			
			//PASO 3: Abrir una Conexion
			System.out.println("Conectando a la Base de Datos...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			//PASO 4: Ejecutar un consultor SQL con el objeto Statement
			System.out.println("Creando Statement...");
			stmt = conn.createStatement();
			
					/* MENU DE LA APLICACION*/
			do {
				opcion=inicioSesion();
				switch (opcion) {
				case 1: {  // REGISTRARSE
					id++;
					System.out.println("Ingrese los siguientes datos");
					System.out.println("Nombre de Usuario: ");
					nombreUsuario=teclado.nextLine();
					System.out.println("Password: ");
					password=teclado.nextLine();
					System.out.println("Nombre: ");
					nombre=teclado.nextLine();
					System.out.println("Apellido: ");
					apellido=teclado.nextLine();
					System.out.println("DNI: ");
					dni=teclado.nextInt();
					
					teclado.nextLine(); //LIMPIAMOS EL BUFFER (para evitar que el enter quede como caracter y poder igresar el siguiente String)
					
					// llamamos al constructor
					usuario = new Cliente (nombreUsuario,password,id,nombre,apellido,dni);
					
					// retornamos los datos del objeto Cliente
					domicilio=usuario.getDomicilio();
					telefono=usuario.getTelefono();
					pais=usuario.getPais();
					provincia=usuario.getProvincia();
					codigoPostal=usuario.getCodigoPostal();
					
					//dentro de java el objeto 'ResultSet = insert'
					sql = "insert into Cliente (nombre, apellido, dni, domicilio, "+
					"telefono, pais, provincia, codigoPostal)values"+
					"('" + nombre + "','" + apellido  + "'," + dni  + ",'" + domicilio  + "'," + telefono +
					",'" + pais  + "','" + provincia  + "'," + codigoPostal + ")";
					ResultSet rs = stmt.executeQuery(sql);
					
					/* FALTA AGREGAR LOS DATOS DE LA TABLA Usuario*/
					
					
					rs.close(); // cerramos las consulta SQL
					break;
				}
				case 2:{ 	// INICIAR SESION
					
					System.out.println("Ingrese los siguientes datos");
					System.out.println("Nombre de Usuario: ");
					nombreUsuario=teclado.nextLine();
					System.out.println("Password: ");
					password=teclado.nextLine();
					
					// Consulta SQL
					sql = "SELECT nombreUsuario, password, permisos from Usuario";
					ResultSet rs = stmt.executeQuery(sql);
					
					//PASO 5: Extraer datos ResultSet
					while(rs.next()) {
						// Recibir por tipo de columna
						String nombreUser = rs.getString("nombreUsuario");
						String contrasena = rs.getString("password");
						boolean permisos = rs.getBoolean("permisos");
						
						if(nombreUser.equals(nombreUsuario) && contrasena.equals(password)) {
							if(permisos){ // MENU DEL ADMINISTRADOR
								System.out.println("ERES ADMIN");
							}
							else { // MENU DEL CLIENTE
								System.out.println("ERES CLIENTE");
							}
						}
						else {
							System.out.println("Su Nombre de Usuario o contraseña es incorrecto");
						}
						
					}
					rs.close();
					break;
				}
				
				}// final switch Registrarse | Iniciar sesion 
				
			} while (opcion!=0);
			
			//PASO 6: Entorno de limpieza
			
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			// Resolver errores del JDBC
			se.printStackTrace();
		}
		catch (Exception e) {
			// Resolver errores para Class.forName
			e.printStackTrace();
		}
		finally {
			// Bloque final utilizado para cerrrar recursos
			try {
				if(stmt!=null)
					stmt.close();
			}
			catch (SQLException se2) {
				// Nada que podamos hacer
			}
			try {
				if (conn!=null) {
					conn.close();
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}// cierra finally try
		} // cierra try
		
	}

	public static int inicioSesion() {
		int x;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("Ingrese una opcion:");
			System.out.println("1)Registrarse");
			System.out.println("2)Iniciar Sesion");
			System.out.println("0)Salir");
			x=teclado.nextInt();
		}while(x<0 || x>2);
		return x;
	}
	
	public static int menuCliente() {
		int x;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("Ingrese una opcion:");
			System.out.println("1)Seleccionar Productos");
			System.out.println("2)Ver lista de productos");
			System.out.println("3)Autorizar compra");
			System.out.println("0)Menu Principal");
			x=teclado.nextInt();
		}while(x<0 || x>3);
		return x;
	}
	
	
}
