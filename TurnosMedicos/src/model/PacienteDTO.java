package model;

import java.util.Date;

public class PacienteDTO extends Persona {
	private String tipoSangre;
	private int peso;
	private Date fechaNacimiento;
	private String identificacion;
	private String nombre;
	public PacienteDTO() {

	}

	public PacienteDTO(String identificacion, String nombre, String tipoSangre, int peso, Date fechaNacimiento) {
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
	
	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "identificacion=" + identificacion + ", nombre=" + nombre + ", tipoSangre=" +  tipoSangre + 
				", peso=" + peso + ", fechaNacimiento=" + fechaNacimiento;
	}

}
