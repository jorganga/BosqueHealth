package model;

import java.util.Date;

public class Paciente extends Persona {
	private String tipoSangre;
	private int peso;
	private Date fechaNacimiento;
	private int cedula;

	public Paciente() {

	}

	public Paciente(String nombre, String id, String tipoSangre, int peso, Date fechaNacimiento, int cedula) {
		super(nombre, id);
		this.tipoSangre = tipoSangre;
		this.peso = peso;
		this.fechaNacimiento = fechaNacimiento;
		this.cedula = cedula;
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

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	@Override
	public String toString() {
		return getNombre() + "-" + getId() ;
	}

}