package model;

import java.util.Date;

public class Paciente extends Persona {
	private String tipoSangre;
	private int peso;
	private Date fechaNacimiento;

	public Paciente() {

	}

	public Paciente(String identificacion, String nombre, String tipoSangre, int peso, Date fechaNacimiento) {
		super(nombre, identificacion);
		this.tipoSangre = tipoSangre;
		this.peso = peso;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return getNombre() + "-" + getIdentificacion() ;
	}

}