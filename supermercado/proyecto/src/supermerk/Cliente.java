package supermerk;
import java.util.LinkedList;
import java.util.Queue;

public class Cliente extends Usuario{
	private int id;
	private String nombre;
	private String apellido;
	private  int dni;
	private String domicilio;
	private int telefono;
	private  String pais;
	private String provincia;
	private int codigoPostal;
	private Queue<Producto> listaProducto;
	
	// CONSTRUCTOR 1
	public Cliente(String email,String password, int id,String nombre,String apellido,int dni,String domicilio,
			int telefono,String pais,String provincia,int codigoPostal) {
		super(id,email,password);
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.dni=dni;
		this.domicilio=domicilio;
		this.telefono=telefono;
		this.pais=pais;
		this.provincia=provincia;
		this.codigoPostal=codigoPostal;
		this.listaProducto=null;
		permisosDesactivados();
	}

	// CONSTRUCTOR 2
	public Cliente(String email,String password,int id,String nombre,String apellido,int dni,String domicilio,
			int telefono) {
		super(id, email, password);
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.dni=dni;
		this.domicilio=domicilio;
		this.telefono=telefono;
		this.pais="Argentina";
		this.provincia="Salta";
		this.codigoPostal=4400;
		this.listaProducto=null;
	}
	
	// CONSTRUCTOR 3
		public Cliente(String email,String password,int id,String nombre,String apellido,int dni) {
			super(id, email, password);
			this.id=id;
			this.nombre=nombre;
			this.apellido=apellido;
			this.dni=dni;
			this.domicilio="B° Solidaridad";
			this.telefono=155449922;
			this.pais="Argentina";
			this.provincia="Salta";
			this.codigoPostal=4400;
			this.listaProducto=null;
		}
	
		
	// 	Agregar un producto a la lista
		public void agregarProductoLista(Producto articulo) {
			this.listaProducto.add(articulo);
		}
		
	// MOSTRAR PRODUCTOS SELECCIONADOS
		public void mostrarLista() {
			int i=0;
			Queue<Producto> copia = new LinkedList<>();
			copia.addAll(this.listaProducto);
			while(copia.size()>0) {
				i++;
				System.out.println("Nro: " + i + " ");
				copia.peek().mostrarProducto();
				System.out.println("--------------------");
			}
		}
		
	// ELIMINAR UN PRODUCTO DE LA LISTA("COLA")
		public void eliminar_producto(Producto eliminar) {
			Queue<Producto> copia = new LinkedList<>();
			copia.addAll(this.listaProducto);
			boolean b=false;
			while((this.listaProducto.size()>0) && b==false) { // itera en la lista mientras haya algun objeto ó no lo haya encontrado
				if(this.listaProducto.equals(eliminar)) {  // pregunta si el objeto de la lista y el pasado por parametro son iguales al ser verdadero entra en el if
					this.listaProducto.remove(); // elimina el objeto en el que esta parado("Producto de la lista")
					b=true;
				}
				this.listaProducto.peek();  // Avanza al siguiente elemento
			}
			if(b==true) {
				System.out.println("Se elimino correctamente");
			}
			else {
				System.out.println("No se encontro en la lista");
			}
		}
		
	
	// GETTER SETTER
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
}
