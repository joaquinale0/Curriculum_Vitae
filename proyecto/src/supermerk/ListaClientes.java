package supermerk;

import java.util.Queue;
import java.util.Scanner;

public class ListaClientes {
	Queue<Cliente> listaCliente = null;
	private int id;
	
	// CONSTRUCTOR
	public ListaClientes() {
		this.listaCliente=null;
		this.id=0;
	}
	
	public void agregarUno() {
		Scanner teclado = new Scanner(System.in);
		
		// datos registrarse
		String email;
		String password;
		String nombre;
		String apellido;
		int dni;
		
		// registrarse
		System.out.println("Ingrese los siguientes datos");
		System.out.println("E-mail: ");
		email=teclado.nextLine();
		System.out.println("Password: ");
		password=teclado.nextLine();
		System.out.println("Nombre: ");
		nombre=teclado.nextLine();
		System.out.println("Apellido: ");
		apellido=teclado.nextLine();
		System.out.println("DNI: ");
		dni=teclado.nextInt();
		
		//agregando los datos
		id++;
		Cliente usuario = new Cliente(email, password, id, nombre, apellido, dni);
		
		//agregando en la lista
		this.listaCliente.add(usuario);
		System.out.println("Se ha registrado exitosamente");
	}
	
	public boolean iniciarSeccion() {
		String email,password;
		Scanner teclado = new Scanner(System.in);
		Queue<Cliente> listaClienteCopia = null;
		listaClienteCopia.addAll(this.listaCliente);
		boolean bandera=false;
	
		
			System.out.println("Ingrese los siguientes datos");
			System.out.println("E-mail: ");
			email=teclado.nextLine();
			System.out.println("Password: ");
			password=teclado.nextLine();
			while(listaClienteCopia.size()>0 && bandera==false) {
				if(listaClienteCopia.peek().confirmacionIniciarSeccion(email, password))
					bandera=true;
				
			}
			if(bandera==false) {
				System.out.println("No se encontro E-mail o contraseña");
				return false;
			}
			else {
				System.out.println("Se inicio correctamente");
				return true;
			}
	}

}
