package supermerk;
import java.sql.*;

public class Administrador extends Usuario{
	private int id;
	private String nombre;
	private String apellido;
	
	
	public Administrador(String email,String password,int id,String nombre,String apellido) {
		super(id, email, password);
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
		permisosActivados();
	}


	
	
	
	// GETTER SETTER
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private String getApellido() {
		return apellido;
	}
	private void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	

}
