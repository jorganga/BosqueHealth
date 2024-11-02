package model;

import java.time.LocalDate;

public class ExamenMedico {
	private String id;
	private TipoExamen tipo;
	private LocalDate fecha;
	private Profesional medico;
	private Paciente paciente;
	private String nota;
}
