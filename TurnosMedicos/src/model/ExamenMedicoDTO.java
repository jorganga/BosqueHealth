package model;

import java.time.LocalDate;
/**
 * Clase que representa un DTO (Data Transfer Object) para un examen médico.
 */
public class ExamenMedicoDTO {
	private String id;
	private TipoExamen tipo;
	private LocalDate fecha;
	private Profesional medico;
	private Paciente paciente;
	private String nota;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoExamen getTipo() {
		return tipo;
	}

	public void setTipo(TipoExamen tipo) {
		this.tipo = tipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Profesional getMedico() {
		return medico;
	}

	public void setMedico(Profesional medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public ExamenMedicoDTO(String id, TipoExamen tipo, LocalDate fecha, Profesional medico, Paciente paciente,
			String nota) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.fecha = fecha;
		this.medico = medico;
		this.paciente = paciente;
		this.nota = nota;
	}

	public ExamenMedicoDTO() {
		super();
	}

	@Override
	public String toString() {
		return "ExamenMedico [id=" + id + ", tipo=" + tipo + ", fecha=" + fecha + ", medico=" + medico + ", paciente="
				+ paciente + ", nota=" + nota + "]";
	}

}
