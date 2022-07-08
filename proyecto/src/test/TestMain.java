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

		// variable local main gustavo (administrador menu)
		int opcionProducto;

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
					sql = "SELECT * from Usuario";
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

									/*		CONSEGUIMOS EL ID CLIENTE Y USUARIO QUE HAYA INICIADO SESION 	*/
									id_usuario = rs.getInt("idUsuairo");			//
									sql = "SELECT * from Cliente";					//
									rs = stmt.executeQuery(sql);					//						
									//PASO 5: Extraer datos ResultSet				//
									if(rs.isBeforeFirst()) {						//
										while(rs.next()) {							//
											id = rs.getInt("cliente_es_usuario");	//
											if(id == id_usuario)					//
												id_cliente = rs.getInt("idCliente");//
										}
									}

									/* 		CONTINUAMOS CON EL MENU CLIENTE 		*/
									do {
										System.out.println("ERES CLIENTE");
										opcionCliente = menuCliente();
										switch(opcionCliente) {
										case 1: // 		SELECCIONAR PRODUCTOS DE LA LISTA	
											// Consulta SQL
											sql = "SELECT * from Producto";
											rs = stmt.executeQuery(sql);


											int contadorDeProductos = 0;

											//PASO 5: Extraer datos ResultSet
											if(rs.isBeforeFirst()) {
												while(rs.next()) {


													// Recibir por tipo de columna
													int idProducto = rs.getInt("idProducto");
													String nombreProducto = rs.getString("nombre");
													int cantidad = rs.getInt("cantidad");
													float preciUnitario = rs.getFloat("precioUnitario");
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
													teclado.nextLine(); //LIMPIAMOS EL BUFFER

													//sql = "select count(p.idProducto) from producto p";
													sql = "select * from Producto";
													rs = stmt.executeQuery(sql);

													while(rs.next()) {
														contadorDeProductos = rs.getInt("idProducto");	
													}
												
													if(seleccion <= contadorDeProductos && seleccion >= 1) {
														sql = "insert into Ticket (Cliente_idCliente, Cliente_idUsuario, Producto_idProducto)values(?,?,?)";
														ps = conn.prepareStatement(sql); 
														ps.setInt(1, id_cliente);
														ps.setInt(2, id_usuario);
														ps.setInt(3, seleccion);
														ps.execute();

														System.out.println("Agregado al carrito");
													}
													else {
														System.out.println("No existe el producto elegido");
													}
													System.out.println("1)Continuar seleccionando. \n2)Salir.");
													opcionCliente = teclado.nextInt();
													teclado.nextLine(); //LIMPIAMOS EL BUFFER
												}

											}
											else {
												System.out.println("No hay Productos en Venta");
											}
											break;
										case 2:	//2)Ver lista de productos
											break;
										case 3:	//3)Autorizar compra

											break;
										}
									}while(opcionCliente != 0);
								}


								else { // MENU DEL ADMINISTRADOR
									System.out.println("ERES ADMIN");
									opcionCliente = menuAdministrador();
									switch (opcionCliente) {
									case 1: //1)Cargar productos
										///////////////////////////////////////////// GUSTAVO ///////////////////////////////////////////////////////////////

										do {
											opcionProducto = menuProducto();
											Producto prod = new Producto();
											switch(opcionProducto) {

											case 1:

												sql = "select * from producto";

												rs = stmt.executeQuery(sql);
												prod.listarProductos(rs);

												break;
											case 2:

												prod.capturaDatosProducto();

												sql = "INSERT INTO producto (nombre,cantidad,precioUnitario,marca,descripcion) VALUES (?,?,?,?,?)";
												ps = conn.prepareStatement(sql); 
												ps.setString(1, prod.getNombre());
												ps.setInt(2, prod.getCantidad());
												ps.setFloat(3,prod.getPrecioUnitario());
												ps.setString(4, prod.getMarca());
												ps.setString(5, prod.getDescripcion());
												ps.execute();
												break;
											case 3:
												System.out.println("Ingresar codigo  del producto: ");
												int idP=teclado.nextInt();
												sql = "select * from producto WHERE idProducto="+idP;

												rs = stmt.executeQuery(sql);
												while(rs.next()){
													prod.setId(rs.getInt("idProducto"));
													prod.setNombre(rs.getString("nombre"));
													prod.setCantidad(rs.getInt("cantidad"));
													prod.setPrecioUnitario(rs.getFloat("precioUnitario"));
													prod.setMarca(rs.getString("marca"));
													prod.setDescripcion(rs.getString("descripcion"));
												}
												rs.close();
												String opcionMod = "NO";


												prod.mostrarProducto();
												System.out.println("Desea modificar el Producto SI/NO: ");
												opcionMod=teclado.next();

												if(opcionMod.equals("SI")) {
													prod.capturaDatosProducto();
													sql = "UPDATE producto SET nombre = ?, cantidad = ?,precioUnitario =?, marca =?,descripcion=? WHERE idProducto=?";
													ps = conn.prepareStatement(sql); 
													ps.setString(1, prod.getNombre());
													ps.setInt(2, prod.getCantidad());
													ps.setFloat(3,prod.getPrecioUnitario());
													ps.setString(4, prod.getMarca());
													ps.setString(5, prod.getDescripcion());
													ps.setInt(6, prod.getId());
													ps.executeUpdate();
												}

												break;
											case 4:
												System.out.println("Ingresar codigo  del producto: ");
												int idPD=teclado.nextInt();
												teclado.nextLine(); //LIMPIAMOS EL BUFFER
												sql = "select * from producto WHERE idProducto="+idPD;

												rs = stmt.executeQuery(sql);
												while(rs.next()){
													prod.setId(rs.getInt("idProducto"));
													prod.setNombre(rs.getString("nombre"));
													prod.setCantidad(rs.getInt("cantidad"));
													prod.setPrecioUnitario(rs.getFloat("precioUnitario"));
													prod.setMarca(rs.getString("marca"));
													prod.setDescripcion(rs.getString("descripcion"));
												}
												//rs.close();
												String opcionDel = "NO";
												prod.mostrarProducto();
												System.out.println("Desea eliminar el Producto SI/NO: ");
												opcionDel=teclado.next();

												if(opcionDel.equals("SI")) {
													try {
														sql = "DELETE FROM Producto WHERE idProducto ="+idPD;
														stmt.executeUpdate(sql);
														sql = "select * from producto";
														rs = stmt.executeQuery(sql);
														prod.listarProductos(rs);
													}catch (Exception e) {
														System.out.println("No se pudo eliminar el producto esta uso en un carrito");
													}
												}
												break;
											}

										}while(opcionProducto != 0);

										////////////////////////////////////// GUSTAVO ///////////////////////////////////////
										break;
									case 2:	//2)Ver todos los usuarios que realizaron una compra
										sql = "select u.idUsuairo, u.nombreUsuario from usuario u inner join ProductoComprado p on u.idUsuairo = p.Usuario_idUsuario";
										rs = stmt.executeQuery(sql);
										//PASO 5: Extraer datos ResultSet
										if(rs.isBeforeFirst()) {
											while(rs.next()) {
												// Recibir por tipo de columna
												int idUsuario = rs.getInt("u.idUsuairo");
												String nombreCliente = rs.getString("u.nombreUsuario");
												mostrar_un_Usuario(idUsuario, nombreCliente);
											}
										}
										else {
											System.out.println("Aun no se ha realizado ninguna compra.");
										}
										break;
									case 3: //3)Ver listado de productos seleccionados por el usuario
										sql = "select u.idUsuairo, u.nombreUsuario from usuario u inner join Cliente c on u.idUsuairo = c.Usuario_idUsuario";
										rs = stmt.executeQuery(sql);
										int contadorDeUsuario = 0;
										//PASO 5: Extraer datos ResultSet
										if(rs.isBeforeFirst()) {
											while(rs.next()) {
												// Recibir por tipo de columna
												int idUsuario = rs.getInt("u.idUsuairo");
												String nombreCliente = rs.getString("u.nombreUsuario");
												mostrar_un_Usuario(idUsuario, nombreCliente);
												contadorDeUsuario++;
											}
											id_usuario = elegirUsuario(contadorDeUsuario);
											sql = "select u.idUsuairo, u.nombreUsuario from usuario u inner join Cliente c on u.idUsuairo = c.Usuario_idUsuario";
											rs = stmt.executeQuery(sql);
										}
										else {
											System.out.println("Aun no se ha realizado ninguna compra.");
										}
										break;
									}
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

	public static int menuAdministrador() {
		int x;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("Ingrese una opcion:");
			System.out.println("1)Cargar productos");
			System.out.println("2)Ver todos los usuarios que realizaron una compra");
			System.out.println("3)Ver listado de productos seleccionados por el usuario");
			System.out.println("0)Menu Principal");
			x=teclado.nextInt();
		}while(x<0 || x>3);
		return x;
	}


	/// 	MOSTRAR CADA USUARIO
	public static void mostrar_un_Usuario(int id, String nombre) {
		System.out.println("Nro: " + id + " Nombre: " + nombre);
	}
	// SELECCIONAR UN USUARIO
	public static int elegirUsuario(int n) {
		int opcion;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("Ingresar el numero del cliente");
			opcion = teclado.nextInt();
		}while(opcion > n || opcion < 1);
		return opcion;
	}


	// GUSTAVO
	public static int menuProducto() {
		int x;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("Ingrese una opcion:");
			System.out.println("1)Listar Productos");
			System.out.println("2)Agregar Producto");
			System.out.println("3)Modificar Producto");
			System.out.println("4)Eliminar Producto");
			System.out.println("0)Menu Principal");
			x=teclado.nextInt();
		}while(x<0 || x>4);
		return x;
	}
}


