package supermerk;

import java.util.Queue;
import java.util.Scanner;

public class listaProducto {
	private Queue<Producto> listaProducto=null;
	private int id;
	public listaProducto() {
		this.id=0;
		listaProducto=null;
	}
	
	public void agregarUno() {
		Scanner teclado = new Scanner(System.in);
		
		// datos registrarse
		String email;
		String password;
		String nombre;
		float precio;
		int cantidad;
		
		// registrarse
		System.out.println("Ingrese los siguientes datos");
		System.out.println("Nombre: ");
		nombre=teclado.nextLine();
		System.out.println("Precio: ");
		precio=teclado.nextFloat();
		System.out.println("DNI: ");
		cantidad=teclado.nextInt();
		
		//agregando los datos
		this.id++;
		Producto usuario = new Producto(this.id,nombre,cantidad,precio);
		
		//agregando en la lista
		this.listaProducto.add(usuario);
		System.out.println("Se ha registrado exitosamente");
	}
	
	public Producto retornaProducto() {
		Queue<Producto> listaProductoCopia=null;
		listaProductoCopia.addAll(this.listaProducto);
		int seleccionID;
		boolean bandera=false;
		mostrarListaProducto();
		
		Scanner teclado = new Scanner(System.in);
		
		do {
			System.out.println("Ingrese el id: ");
			seleccionID=teclado.nextInt();
		}while(seleccionID>listaProductoCopia.size() || seleccionID<=0);
		
		while(listaProductoCopia.size()>seleccionID) {
			listaProductoCopia.peek();
		}
		return listaProductoCopia.element();
	}
	
	private void mostrarListaProducto() {
		Queue<Producto> listaProductoCopia=null;
		listaProductoCopia.addAll(this.listaProducto);
		while(listaProductoCopia.size()>0) {
			listaProductoCopia.peek().mostrarProducto();
			System.out.println("------------------------");
		}
	}

}
