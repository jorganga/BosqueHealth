package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Paciente extends Persona implements Serializable {
	private static final long serialVersionUID = 69379036953212014L;
	private String tipoSangre;
	private int peso;
	private LocalDate fechaNacimiento;

	public Paciente() {

	}
	
	public Paciente(String nombre, String identificacion, String email, String tipoSangre, int peso,
			LocalDate fechaNacimiento) {
		super(nombre, identificacion, email);
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return getNombre() + "-" + getIdentificacion() ;
	}

}