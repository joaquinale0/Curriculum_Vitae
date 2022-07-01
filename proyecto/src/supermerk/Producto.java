package supermerk;
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
	
	// EDITAR UN PRODUCTO
	public void modificarPrecio() {
		float precioNuevo;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Ingresar precio nuevo del producto: "+
				getNombre());
		precioNuevo=teclado.nextFloat();
		setPrecioUnitario(precioNuevo);
		System.out.println("Se a modificado el precio correctamente");
	}
	
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
