package model;
/**
 * Representa un periodo con nombre, mes y año.
 */
public class Periodo {
	private String nombre;
	private int mes;
	private int year;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getMes() {
		return mes;
	}
	
	public void setMes(int mes) {
		this.mes = mes;
	}
	
	public int getYear() {
		return year;
	}
	
	
	@Override
    public String toString() {
        return nombre + "-" + year;
    }
	public Periodo(String nombre, int mes, int year) {
		super();
		this.nombre = nombre;
		this.mes = mes;
		this.year = year;
	}
	
}
