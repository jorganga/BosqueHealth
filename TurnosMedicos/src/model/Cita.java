package model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Representa una cita médica asociada a un paciente y un turno específico.
 * Contiene información sobre el turno asignado, el paciente, el estado de la
 * cita y si se ha enviado un recordatorio.
 */
public class Cita implements Serializable {

	private static final long serialVersionUID = 6937903695321206414L; // Solo los que tienen la misma version pueden

	private Turno turnito;
	private String id;
	private boolean envioRecordatorio;
	private Paciente paciente;
	private String estado;

	public Cita() {
		// TODO Auto-generated constructor stub
	}

	public boolean isEnvioRecordatorio() {
		return envioRecordatorio;
	}

	public void setEnvioRecordatorio(boolean envioRecordatorio) {
		this.envioRecordatorio = envioRecordatorio;
	}

	public Cita(Turno turnito, Paciente paciente, String estado, boolean envioRecordatorio) {
		super();
		this.turnito = turnito;
		this.paciente = paciente;
		this.estado = estado;
		this.envioRecordatorio = envioRecordatorio;
		this.id = UUID.randomUUID().toString();
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

	@Override
	public String toString() {
		return "Cita [turnito=" + turnito + ", id=" + id + ", paciente=" + paciente + ", estado=" + estado + "]";
	}

}
