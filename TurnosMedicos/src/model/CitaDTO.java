package model;

import java.util.UUID;

public class CitaDTO {
	private Turno turnito;
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private Paciente paciente;
	private String estado;

	public CitaDTO() {
		// TODO Auto-generated constructor stub
	}

	public CitaDTO(Turno turnito, Paciente paciente, String estado) {
		super();
		this.turnito = turnito;
		this.paciente = paciente;
		this.estado = estado;
		this.id = UUID.randomUUID().toString();

	}

	public Turno getTurnito() {
		return turnito;
	}

	public void setTurnito(Turno turnito) {
		this.turnito = turnito;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}