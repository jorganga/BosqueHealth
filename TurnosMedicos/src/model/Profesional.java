package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Profesional extends Persona implements Serializable {
	
	private static final long serialVersionUID = 6937903695321206414L; // Solo los que tienen la misma version pueden
	
	private int turnosEnSemana;
	private LocalDate fechaUltimoTurno;
	private int numeroSemana;
	private Especialidad especialidad;
	private boolean esDirector;
	private String clave;
	
	
		
	public Profesional(String nombre, String identificacion, String email, int turnosEnSemana,
			LocalDate fechaUltimoTurno, int numeroSemana, Especialidad especialidad, boolean esDirector, String clave) {
		super(nombre, identificacion, email);
		this.turnosEnSemana = turnosEnSemana;
		this.fechaUltimoTurno = fechaUltimoTurno;
		this.numeroSemana = numeroSemana;
		this.especialidad = especialidad;
		this.esDirector = esDirector;
		this.clave = clave;
	}

	public int getTurnosEnSemana() {
		return turnosEnSemana;
	}
	
	public void setTurnosEnSemana(int turnosEnSemana) {
		this.turnosEnSemana = turnosEnSemana;
	}
	public LocalDate getFechaUltimoTurno() {
		return fechaUltimoTurno;
	}
	public void setFechaUltimoTurno(LocalDate fechaUltimoTurno) {
		this.fechaUltimoTurno = fechaUltimoTurno;
	}
	public int getNumeroSemana() {
		return numeroSemana;
	}
	public void setNumeroSemana(int numeroSemana) {
		this.numeroSemana = numeroSemana;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	public boolean isEsDirector() {
		return esDirector;
	}
	public void setEsDirector(boolean esDirector) {
		this.esDirector = esDirector;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
}
