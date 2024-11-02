package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoDTO {

	private String id;
	private Profesional doctor;
	private LocalDate fecha;
	private LocalTime hora;

	public TurnoDTO() {
		// TODO Auto-generated constructor stub
	}

	public TurnoDTO(String id, Profesional doctor, LocalDate fecha, LocalTime hora) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.fecha = fecha;
		this.hora = hora;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Profesional getDoctor() {
		return doctor;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
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

	@Override
	public String toString() {
		return "TurnoDTO [id=" + id + ", doctor=" + doctor + ", fecha=" + fecha + ", hora=" + hora + "]";
	}

}
