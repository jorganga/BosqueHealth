package model;

import java.time.LocalDate;

public class SeguimientoDTO {
	private String id;
	private String idPaciente;
	private String descripcion;
	private LocalDate fecha;
	
	public SeguimientoDTO(String id, String idPaciente, String descripcion, LocalDate fecha) {
		super();
		this.idPaciente = idPaciente;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.id = id;
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
