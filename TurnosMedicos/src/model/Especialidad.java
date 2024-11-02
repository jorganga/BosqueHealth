package model;

import java.io.Serializable;

public class Especialidad implements Serializable {
	
	private static final long serialVersionUID = 6937903695321206414L; // Solo los que tienen la misma version pueden
	
	private String nombre;
	private int id;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Especialidad(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
