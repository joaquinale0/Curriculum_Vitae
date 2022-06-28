package supermerk;

public abstract class Usuario {
	private int id;
	private String email;
	private String password;
	private boolean permisos;
	// CONSTRUCTOR
	public Usuario(int id,String email,String password, boolean permisos) {
		this.id=id;
		this.email=email;
		this.password=password;
		this.permisos=permisos;
	}
	
	// RETORNA PRIVILEGIO
	public abstract boolean retorna_privilegio();
	
	// INICIAR SESION
	public abstract boolean confirmacionIniciarSesion(String email, String password);
	
	// GETTER AND SETTER
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getPermisos() {
		return this.permisos;
	}
	
	// permitir modificar
	public void permisosActivados() {
		this.permisos=true;
	}
	public void permisosDesactivados() {
		this.permisos=false;
	}
}
