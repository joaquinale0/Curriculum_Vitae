package supermerk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Producto {
	private int id;
	private String nombre;
	private int cantidad;
	private float precioUnitario;
	private String marca;
	private String descripcion;
	
	// CONSTRUCTOR 1
	public Producto(int id,String nombre,int cantidad,
			float precioUnitario,
			String marca,String descripcion) {
		this.id=id;
		this.nombre=nombre;
		this.cantidad=cantidad;
		this.precioUnitario=precioUnitario;
		this.marca=marca;
		this.descripcion=descripcion;
	}
	
	// CONSTRUCTOR 2
		public Producto(int id,String nombre,int cantidad,
				float precioUnitario) {
			this.id=id;
			this.nombre=nombre;
			this.cantidad=cantidad;
			this.precioUnitario=precioUnitario;
			this.marca="No tiene";
			this.descripcion="No tiene";
		}
		// CONSTRUCTOR 3
		public Producto() {
			
		}
	// metodos PRODUCTO
	//       MOSTRAR UN PRODUCTO	
	public void mostrarProducto() {
		System.out.println(getId());
		System.out.println(getNombre());
		System.out.println(getMarca());
		System.out.println(getPrecioUnitario());
		System.out.println(getCantidad());
		System.out.println(getDescripcion());
	}
//////////////////  GUSTAVO ////////////////////////////////////////////
	public void capturaDatosProducto() {
		try {
			
		
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		System.out.println("Ingresar nombre  del producto: ");
		this.nombre=teclado.nextLine();
		System.out.println("Ingresar marca  del producto: ");
		this.marca=teclado.nextLine();
		System.out.println("Ingresar precio unitario  del producto: ");
		this.precioUnitario=teclado.nextFloat();
		teclado.nextLine(); //LIMPIAMOS EL BUFFER 
		System.out.println("Ingresar descripcion  del producto: ");
		this.descripcion=teclado.nextLine();
		System.out.println("Ingresar cantidad  del producto: ");
		this.cantidad=teclado.nextInt();
		teclado.nextLine(); //LIMPIAMOS EL BUFFER
		}
		catch(Exception e) {
			System.out.println("Error capturando datos del nuevo producto"+ e.getMessage()); 
		}
		
	}
/////////////////////////////GUSTAVO/////////////////////////////////////
	// EDITAR UN PRODUCTO
	public void modificarPrecio() {
		float precioNuevo;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Ingresar precio nuevo del producto: "+
				getNombre());
		precioNuevo=teclado.nextFloat();
		setPrecioUnitario(precioNuevo);
		System.out.println("Se ha modificado el precio correctamente");
	}
//////////////////GUSTAVO ////////////////////////////////////////////
	public void listarProductos(ResultSet rs) {
	
		
		//PASO 5: Extraer datos ResultSet
		try {
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
				
					// Recibir por tipo de columna
					String nombre = rs.getString("nombre");
					String precio = rs.getString("precioUnitario");
					String marca = rs.getString("marca");
					System.out.println("Nombre: " + nombre + ", Precio: " + precio + ", Marca: " + marca );
				}
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("hay errroirororororororororo");
			e.printStackTrace();
		}
	
	}
/////////////////////////////GUSTAVO/////////////////////////////////////
	//     GETTER SETTER
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
