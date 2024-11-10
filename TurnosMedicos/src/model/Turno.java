
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
/**
 * Representa un turno asignado a un doctor en una fecha y hora específicas.
 * La clase implementa Serializable para permitir la persistencia de objetos de tipo Turno.
 */
public class Turno  implements Serializable {
	
	private static final long serialVersionUID = 6937903695321206414L; // Solo los que tienen la misma version pueden
	
	private String id;
	private Profesional doctor;
	private LocalDate fecha;
	private LocalTime hora;
	private boolean libre;
	
	public Turno() {
		// TODO Auto-generated constructor stub
	}
	
	
	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getId() {
		return id;
	}
	
	public Turno(Profesional doctor, LocalDate fecha, LocalTime hora) {
		super();
		this.doctor = doctor;
		this.fecha = fecha;
		this.id = UUID.randomUUID().toString();
		this.hora =  hora;
		this.libre = true;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public Profesional getDoctor() {
		return doctor;
	}
	public void setDoctor(Profesional doctor) {
		this.doctor = doctor;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean isLibre() {
		return libre;
	}

	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	
}
