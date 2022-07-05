package test;
import java.lang.System.Logger;
import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import supermerk.*;

public class TestMain {
	
		//Registrar la JDBC Driver
		//JDBC nombre del driver y URL de la BDD
		static final String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
		//static final String DB_URL="jdbc:mysql://localhost:3306/supermarkBaseDatos";
		static final String DB_URL="jdbc:mysql://localhost:3306/supermercadoUno";
		
		// Credenciales de la BDD
		static final String USER = "root";
		static final String PASS= "jag40515989";
		
		
		//variables globales
		static int id_cliente;
		static int id_usuario;

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
		
		int opcion, opcionCliente;
		int seleccion;
		
		
		/*  CONEXIONES Y REGISTROS MYSQL */
		// creamos variable String sql para escribir en codigo sql
		//dentro de java el objeto 'ResultSet = SELECT'
		String sql;
		ResultSet rs = null;
		
		//registrar el driver
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
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
									
					//dentro de java el objeto 'ResultSet = insert'
					//CARGANDO EL  USUARIO
					sql = "insert into Usuario (nombreUsuario, password, permisos)values(?,?,?)";
					
					/*"('" + nombreUsuario + "','" + password + "',"+ 0 + ")";
					rs = stmt.executeQuery(sql);*/
					
					ps = conn.prepareStatement(sql); 
					ps.setString(1, nombreUsuario);
					ps.setString(2, password);
					ps.setInt(3, 0);
					ps.execute();
					
					// BUSCAR ID USUARIO
					sql = "SELECT * from Usuario";
					rs = stmt.executeQuery(sql);
					
					//PASO 5: Extraer datos ResultSet
					if(rs.isBeforeFirst()) {
						while(rs.next()) {
							// Recibir por tipo de columna
							String nombreUser = rs.getString("nombreUsuario");
							if(nombreUsuario.equals(nombreUser)) {
								id_usuario = rs.getInt("idUsuairo");
							}
						}
					}
					else
						id_usuario = 1;
					
					
					//cargamos cliente
					sql = "insert into Cliente (nombre, apellido, dni, cliente_es_usuario)values(?,?,?,?)";
					ps = conn.prepareStatement(sql); 
					ps.setString(1, nombre);
					ps.setString(2, apellido);
					ps.setInt(3, dni);
					ps.setInt(4, id_usuario);
					ps.execute();
							
					/*"('" + nombre + "','" + apellido  + "'," + dni + ")";
					rs = stmt.executeQuery(sql); */
					
					
					/* FALTA AGREGAR LOS DATOS DE LA TABLA Usuario*/
					
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
					rs = stmt.executeQuery(sql);
					
					//PASO 5: Extraer datos ResultSet
					if(rs.isBeforeFirst()) {
						while(rs.next()) {
							// Recibir por tipo de columna
							String nombreUser = rs.getString("nombreUsuario");
							String contrasena = rs.getString("password");
							int permisos = rs.getInt("permisos");
							
							if(nombreUser.equals(nombreUsuario) && contrasena.equals(password)) {
								if(permisos==0){ // MENU DEL CLIENTE
									do {
										System.out.println("ERES CLIENTE");
										opcionCliente = menuCliente();
										switch(opcionCliente) {
										case 1: // 		SELECCIONAR PRODUCTOS DE LA LISTA	
											// Consulta SQL
											sql = "SELECT * from Producto";
											rs = stmt.executeQuery(sql);
											
											//PASO 5: Extraer datos ResultSet
											if(rs.isBeforeFirst()) {
												
												while(rs.next()) {
													// Recibir por tipo de columna
													int idProducto = rs.getInt("idProducto");
													String nombreProducto = rs.getString("nombre");
													int cantidad = rs.getInt("cantidad");
													float preciUnitario = rs.getFloat("precio");
													String marca = rs.getString("marca");
													String descripcion = rs.getString("descripcion");
													
													System.out.println("---------------------------------");
													System.out.println(idProducto + ")" + "Producto: " + nombreProducto + "( " + marca + ")" + "\n" + 
																	   "Precio: " + preciUnitario + "\n" + 
																	   "Cantidad: " + cantidad + "\n" +
																	   "Descripcion: " + descripcion + "\n" ); 
													System.out.println("---------------------------------");
												}
												
												while(opcionCliente == 1) {
													System.out.println("Ingrese el numero del producto que quiere comprar");
													seleccion = teclado.nextInt();
													while(rs.next()) {
														int idProducto = rs.getInt("idProducto");
														if(idProducto == seleccion) {
															sql = "insert into Ticket (Cliente_idCliente, Cliente_Usuairo_idUsuairo, Producto_idProducto)values("+
														1 + "," + id + idProducto; // FALTA TERMINAR (debo retornar el id del cliente para poder saber a que cliente agregar el producto)
														}
													}
												}
												
											}
											else {
												System.out.println("No hay Productos en Venta");
											}
											
											
											
											
											
											break;
										case 2:
											break;
										case 3:
											break;
										}
									}while(opcionCliente != 0);
								}
								else { // MENU DEL ADMINISTRADOR
									System.out.println("ERES ADMIN");
								}
							}
							else {
								System.out.println("Su Nombre de Usuario o contraseña es incorrecto");
							}
							
						}
					}
					else {
						System.out.println("No Hay Usuarios Creados");
					}
				
					break;
				}
				
				}// final switch Registrarse | Iniciar sesion 
				
			} while (opcion!=0);
			
			//PASO 6: Entorno de limpieza
			rs.close();
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


