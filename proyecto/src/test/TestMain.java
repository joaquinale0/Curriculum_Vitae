package test;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import supermerk.*;

public class TestMain {

	public static void main(String[] args) {
		//ListaClientes listaclientes = new ListaClientes();
		Cliente usuario = null;
		
		/// ---- datos cliente para registrarse
		String nombreUsuario;
		String password;
		int id=0;
		String nombre;
		String apellido;
		int dni;
		
		listaProducto Listaproducto = new listaProducto();
		
		//Cliente usuario = null;
		
		Scanner teclado = new Scanner(System.in);
		
		int opcion;
		do {
			opcion=inicioSesion();
			switch (opcion) {
			case 1: {
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
				
				teclado.nextLine(); //LIMPIAMOS EL BUFFERA (para evitar que el enter quede como caracter y poder igresar el siguiente String)
				
				// llamamos al constructor
				usuario = new Cliente (nombreUsuario,password,id,nombre,apellido,dni);
				
				usuario.mostrarUsuario();
				break;
			}
			case 2:{				
				usuario.mostrarUsuario();
				
				System.out.println("Ingrese los siguientes datos");
				System.out.println("Nombre de Usuario: ");
				nombreUsuario=teclado.nextLine();
				System.out.println("Password: ");
				password=teclado.nextLine();
				if(usuario.confirmacionIniciarSesion(nombreUsuario, password)) {
					if(usuario.retorna_privilegio()){ // MENU DEL ADMINISTRADOR
						System.out.println("ERES ADMIN");
					}
					else { // MENU DEL CLIENTE
						System.out.println("ERES CLIENTE");
					}
				}
				else {
					System.out.println("Su Nombre de Usuario o contraseña es incorrecto");
				}
				break;
			}
			
			}// final switch Registrarse | Iniciar seccion 
		} while (opcion!=0);
		
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
