package supermerk;


public class Administrador extends Usuario{
	private int id;
	private String nombre;
	private String apellido;
	
	public Administrador(String email,String password,int id,String nombre,String apellido) {
		super(id, email, password, true);
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
		permisosActivados();
	}


	// RETORNA PRIVILEGIO
	@Override
	public boolean retorna_privilegio() {
		return getPermisos();
	}
	
	// INICIAR SESION
			public boolean confirmacionIniciarSesion(String email, String password) {
				if(email==super.getEmail() && password==super.getPassword()) {
					return true;
				}
				else {
					return false;
				}
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
