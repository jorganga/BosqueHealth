package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
/**
 * Clase que representa un seguimiento médico de un paciente.
 * Contiene la información del seguimiento, incluyendo el ID único,
 * el ID del paciente, la descripción y la fecha en la que se realiza.
 */
public class Seguimiento implements Serializable {
	
	private static final long serialVersionUID = 6937903695321206414L; 
	
	private String id;
	private String idPaciente;
	private String descripcion;
	private LocalDate fecha;
	
	public Seguimiento(String idPaciente, String descripcion, LocalDate fecha) {
		super();
		this.idPaciente = idPaciente;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
