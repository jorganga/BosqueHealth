package model;

public class TipoExamenDTO {
	private String id;
	private String nombre;

	public TipoExamenDTO() {
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

	public TipoExamenDTO(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "TipoExamen [id=" + id + ", nombre=" + nombre + "]";
	}
}
