package model;

public class TipoExamen {
	private String id;
	private String nombre;

	public TipoExamen() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoExamen(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

}
