package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class ExamenMedico implements Serializable {
	
	private static final long serialVersionUID = 6937903695321206414L;
	
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

	public ExamenMedico(TipoExamen tipo, LocalDate fecha, Profesional medico, Paciente paciente, String nota) {
		super();
		this.id = UUID.randomUUID().toString();
		this.tipo = tipo;
		this.fecha = fecha;
		this.medico = medico;
		this.paciente = paciente;
		this.nota = nota;
	}

	public ExamenMedico() {
	}
}
