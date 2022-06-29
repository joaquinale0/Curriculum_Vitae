package baseDatos;
import java.sql.*;

public class TeoriaDataBases {
	
	//Registrar la JDBC Driver
	//JDBC nombre del driver y URL de la BDD
	static final String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/universidad";
	// Credenciales de la BDD
	static final String USER = "root";
	static final String PASS= "1234";
	
	public static void main(String[] args) {
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
			
			// creamos variable String sql para escribir en codigo sql
			//dentro de java el objeto 'ResultSet = SELECT'
			String sql;
			sql = "SELECT id,nombre from persona";
			ResultSet rs = stmt.executeQuery(sql);
			
			//PASO 5: Extraer datos ResultSet
			while(rs.next()) {
				// Recibir por tipo de columna
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				
				//Mostrar valores
				System.out.print("ID: "+ id);
				System.out.print(", Nombre: "+ nombre);
				System.out.println();
			}
			
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
		
		System.out.println("Adios!");
	} //cierra main
}
