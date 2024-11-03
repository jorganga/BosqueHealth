package model;

import java.io.Serializable;

public class Persona implements Serializable {
	private static final long serialVersionUID = 6937903695321206414L; // Solo los que tienen la misma version pueden
	private String nombre;
	private String identificacion;
	
	public Persona(String nombre, String identificacion) {
		super();
		this.nombre = nombre;
		this.identificacion = identificacion;
	}
	
	public Persona() {
		super();
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setId(String identificacion) {
		this.identificacion = identificacion;
	}
		
}