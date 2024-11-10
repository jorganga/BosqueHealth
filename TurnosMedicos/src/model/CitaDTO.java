package model;

import java.util.UUID;

/**
 * Representa un objeto de transferencia de datos (DTO) para la entidad Cita.
 */
public class CitaDTO {
	private Turno turnito;
	private String id;
	private boolean envioRecordatorio;
	private Paciente paciente;
	private String estado;

	public CitaDTO() {
	}

	public CitaDTO(String id, Turno turnito, Paciente paciente, String estado, boolean envioRecordatorio) {
		super();
		this.turnito = turnito;
		this.paciente = paciente;
		this.estado = estado;
		this.id = id;
		this.envioRecordatorio = envioRecordatorio;
	}

	public boolean isEnvioRecordatorio() {
		return envioRecordatorio;
	}

	public void setEnvioRecordatorio(boolean envioRecordatorio) {
		this.envioRecordatorio = envioRecordatorio;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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