package model;

import java.time.LocalDate;
import java.util.Date;

public class PacienteDTO extends Persona {
	private String tipoSangre;
	private int peso;
	private LocalDate fechaNacimiento;
	public PacienteDTO() {

	}

	public PacienteDTO(String nombre, String identificacion, String email, String tipoSangre, int peso,
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
		return "identificacion=" + super.getIdentificacion() + ", nombre=" + super.getNombre() + ", tipoSangre=" +  tipoSangre + 
				", peso=" + peso + ", fechaNacimiento=" + fechaNacimiento;
	}

}
