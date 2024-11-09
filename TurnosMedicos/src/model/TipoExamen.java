package model;

import java.io.Serializable;

public class TipoExamen  implements Serializable {
	
	private static final long serialVersionUID = 6937903695321206414L; // Solo los que tienen la misma version pueden
	
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
	
	@Override
	public String toString() {
		return nombre;
	}

}
