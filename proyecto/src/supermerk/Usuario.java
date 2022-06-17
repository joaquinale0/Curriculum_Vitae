package supermerk;

public class Usuario {
	private int id;
	private String email;
	private String password;
	private boolean permisos;
	// CONSTRUCTOR
	public Usuario(int id,String email,String password) {
		this.id=id;
		this.email=email;
		this.password=password;
		this.permisos=false;
	}
	
	
	// INICIAR SECCION
	public boolean confirmacionIniciarSeccion(String email, String password) {
		if(email==getEmail() && password==getPassword()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// GETTER AND SETTER
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// permitir modificar
	public void permisosActivados() {
		this.permisos=true;
	}
	public void permisosDesactivados() {
		this.permisos=false;
	}
}
