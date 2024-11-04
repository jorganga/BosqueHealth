package model;

import java.io.Serializable;

public class Persona implements Serializable {
	private static final long serialVersionUID = 6937903695321206414L; // Solo los que tienen la misma version pueden
	private String nombre;
	private String identificacion;
	private String email;
	
	
	public Persona(String nombre, String identificacion, String email) {
		super();
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdentificacion(String identificacion) {
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