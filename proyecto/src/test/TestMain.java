package test;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import supermerk.*;

public class TestMain {

	public static void main(String[] args) {
		ListaClientes listaclientes = new ListaClientes();
		listaProducto Listaproducto = new listaProducto();
		
		Cliente usuario = null;
		
		Scanner teclado = new Scanner(System.in);
		
		int opcion;
		do {
			opcion=inicioSeccion();
			switch (opcion) {
			case 1: {
				listaclientes.agregarUno();
				break;
			}
			case 2:{
				if(listaclientes.iniciarSeccion()) {
					do {
						opcion=menuCliente();
						switch (opcion) {
						case 1: {  // 1)Seleccionar Productos
							Listaproducto.retornaProducto(); // DEBO REPARAR LA SELECCION DE PRODUCTO
							
							break;
						}
						
						case 2: {  // 2)Ver lista de productos
							break;
						}
						
						case 3: { //3)Autorizar compra
							break;
						}
						
						} // final switch menu clientes
						
					}while(opcion!=0);
					
					opcion=2;
				} // final del if (sobre inicio de seccion);
				else {
					System.out.println("Contraseña o usuario incorrectos");
				}
				break;
			}
			
			}// final switch Registrarse | Iniciar seccion 
		} while (opcion!=0);
		
	}

	public static int inicioSeccion() {
		int x;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("Ingrese una opcion:");
			System.out.println("1)Registrarse");
			System.out.println("2)Iniciar Seccion");
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
