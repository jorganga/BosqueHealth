package model;

import java.time.LocalDate;

public class TratamientoMedico {
	private String id;
	private LocalDate fecha;
	private Paciente paciente;
	private Profesional medico;
	private String descripcion;
	
	public TratamientoMedico() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Profesional getMedico() {
		return medico;
	}

	public void setMedico(Profesional medico) {
		this.medico = medico;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TratamientoMedico(String id, LocalDate fecha, Paciente paciente, Profesional medico, String descripcion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.paciente = paciente;
		this.medico = medico;
		this.descripcion = descripcion;
	}	
}
