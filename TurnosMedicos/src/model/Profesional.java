package model;

import java.time.LocalDate;

public class Profesional {
	private String nombre;
	private String identificacion;
	private int turnosEnSemana;
	private LocalDate fechaUltimoTurno;
	private int numeroSemana;
	private Especialidad especialidad;
	
	public Profesional(String nombre, String identificacion, Especialidad especialidadDr) {
		super();
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.turnosEnSemana = 0;
		this.numeroSemana = 0;
		this.especialidad = especialidadDr;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
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
	

}
