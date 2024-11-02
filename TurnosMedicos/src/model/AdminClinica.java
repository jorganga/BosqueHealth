package model;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import model.persistence.DataMapperTurno;
import model.persistence.TurnoDAO;

public class AdminClinica {
	ArrayList<Especialidad> listaEspecialidades;
	ArrayList<Profesional> listaEspecialistas;
	ArrayList<Turno> listaTurnos;
	TurnoDAO dao;
	
	public AdminClinica() {
		// TODO Auto-generated constructor stub
		listaEspecialidades = new ArrayList<Especialidad>();
		listaEspecialistas = new ArrayList<Profesional>();
		listaTurnos = new ArrayList<Turno>();
		dao = new TurnoDAO();
	}
	public ArrayList<Especialidad> getListaEspecialidades() {
		return listaEspecialidades;
	}
	public void setListaEspecialidades(ArrayList<Especialidad> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}
	public ArrayList<Profesional> getListaEspecialistas() {
		return listaEspecialistas;
	}
	public void setListaEspecialistas(ArrayList<Profesional> listaEspecialistas) {
		this.listaEspecialistas = listaEspecialistas;
	}
	
	public void CargarEspecialidades() {
		Especialidad especialidad = new Especialidad("Medicina Interna", 0);
		listaEspecialidades.add(especialidad);
		
		especialidad = new Especialidad("Cirugía", 1);
		listaEspecialidades.add(especialidad);
		
		especialidad = new Especialidad("Oncología", 2);
		listaEspecialidades.add(especialidad);
		
		especialidad = new Especialidad("Neumología", 3);
		listaEspecialidades.add(especialidad);
		
		especialidad = new Especialidad("Dermatología", 4);
		listaEspecialidades.add(especialidad);
		
		especialidad = new Especialidad("Cardiología", 5);
		listaEspecialidades.add(especialidad);
	}
	
	public void CargarEspecialistas() {
		//cargar especialistas por cada especialidad
		int idEspecialidad = 0; //medicina interna
		Especialidad especialidad = listaEspecialidades.get(idEspecialidad);
		
		listaEspecialistas.add(new Profesional("Sonia Jimenez", "50224887", especialidad));
        listaEspecialistas.add(new Profesional("Carlos Pérez", "50224888", especialidad));
        listaEspecialistas.add(new Profesional("Ana Rodríguez", "50224889", especialidad));
        listaEspecialistas.add(new Profesional("Juan Martínez", "50224890", especialidad));
        listaEspecialistas.add(new Profesional("Laura Gómez", "50224891", especialidad));
        listaEspecialistas.add(new Profesional("Pedro Ramírez", "50224892", especialidad));
        listaEspecialistas.add(new Profesional("Clara Morales", "50224893", especialidad));
        
        idEspecialidad++; //cirugía
		especialidad = listaEspecialidades.get(idEspecialidad);
        listaEspecialistas.add(new Profesional("Javier Ortega", "50224894", especialidad));
        listaEspecialistas.add(new Profesional("Marta Castillo", "50224895", especialidad));
        listaEspecialistas.add(new Profesional("Diego Herrera", "50224896", especialidad));
        listaEspecialistas.add(new Profesional("Elena Torres", "50224897", especialidad));
        listaEspecialistas.add(new Profesional("Ricardo Ruiz", "50224898", especialidad));
        listaEspecialistas.add(new Profesional("Patricia Sánchez", "50224899", especialidad));
        listaEspecialistas.add(new Profesional("Alberto Fernández", "50224900", especialidad));
        
        idEspecialidad++; //oncología
		especialidad = listaEspecialidades.get(idEspecialidad);
        listaEspecialistas.add(new Profesional("Silvia Ramírez", "50224901", especialidad));
        listaEspecialistas.add(new Profesional("Andrés Gutiérrez", "50224902", especialidad));
        listaEspecialistas.add(new Profesional("Isabel López", "50224903", especialidad));
        listaEspecialistas.add(new Profesional("Tomás Vega", "50224904", especialidad));
        listaEspecialistas.add(new Profesional("Gabriela Muñoz", "50224905", especialidad));
        listaEspecialistas.add(new Profesional("Santiago Castro", "50224906", especialidad));
        listaEspecialistas.add(new Profesional("Diana Vázquez", "50224907", especialidad));

        idEspecialidad++; //neumología
		especialidad = listaEspecialidades.get(idEspecialidad);
        listaEspecialistas.add(new Profesional("María Acosta", "50224908", especialidad));
        listaEspecialistas.add(new Profesional("Luis Benítez", "50224909", especialidad));
        listaEspecialistas.add(new Profesional("Carmen Núñez", "50224910", especialidad));
        listaEspecialistas.add(new Profesional("José Castillo", "50224911", especialidad));
        listaEspecialistas.add(new Profesional("Lucía Mendoza", "50224912", especialidad));
        listaEspecialistas.add(new Profesional("Miguel Campos", "50224913", especialidad));
        listaEspecialistas.add(new Profesional("Paula Paredes", "50224914", especialidad));
        
        idEspecialidad++; //dermatología
		especialidad = listaEspecialidades.get(idEspecialidad);
        listaEspecialistas.add(new Profesional("Rafael Durán", "50224915", especialidad));
        listaEspecialistas.add(new Profesional("Teresa Escobar", "50224916", especialidad));
        listaEspecialistas.add(new Profesional("Daniel Salinas", "50224917", especialidad));
        listaEspecialistas.add(new Profesional("Rosa Vargas", "50224918", especialidad));
        listaEspecialistas.add(new Profesional("Fernando Navarro", "50224919", especialidad));
        listaEspecialistas.add(new Profesional("Sara Luna", "50224920", especialidad));
        listaEspecialistas.add(new Profesional("Adrián Ortiz", "50224921", especialidad));
        
        idEspecialidad++; //cardiología
		especialidad = listaEspecialidades.get(idEspecialidad);
        listaEspecialistas.add(new Profesional("Natalia Peña", "50224922", especialidad));
        listaEspecialistas.add(new Profesional("Víctor Rojas", "50224923", especialidad));
        listaEspecialistas.add(new Profesional("Verónica Guzmán", "50224924", especialidad));
        listaEspecialistas.add(new Profesional("Roberto Cárdenas", "50224925", especialidad));
        listaEspecialistas.add(new Profesional("Eva Gallardo", "50224926", especialidad));
        listaEspecialistas.add(new Profesional("David Arias", "50224927", especialidad));
        listaEspecialistas.add(new Profesional("Laura Romero", "50224928", especialidad));		
	}
	
	

	
	public String GenerarTurnos(LocalDate fechaMes) {
		Turnero turnero = new Turnero();
		List<Profesional> listaFiltrada;
		
		ArrayList<Turno> turnosEspecialidad = new ArrayList<Turno>();
		
		if (TurnosYaGenerados(fechaMes)) {
			return "Este periodo ya fue generado, seleccione otro!";
		}
		
		for(Especialidad especia:listaEspecialidades) {
			listaFiltrada =  listaEspecialistas.stream().filter(per -> per.getEspecialidad().getNombre().equals(especia.getNombre())).collect(Collectors.toList());
		    turnero.setListaDeDoctores((ArrayList<Profesional>)listaFiltrada); 
		    turnero.AsignarTurnos(fechaMes);
		    turnosEspecialidad.addAll(turnero.getListaTurnos());
		}
		
		dao.setListaTurno(turnosEspecialidad);
	    dao.writeSerializable();
		
		return "Turnos Generados Exitosamente!";
	}
	
	private boolean TurnosYaGenerados(LocalDate fechaMes) {
		dao.readSerializable();
		listaTurnos = dao.getListaTurno();
		if (listaTurnos != null && listaTurnos.size() > 0) {
			List<Turno> listaFiltrada =  
					listaTurnos.stream()
					.filter(turn -> turn.getFecha().equals(fechaMes)).collect(Collectors.toList());
			if(listaFiltrada.size() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Turno> getListaTurnos() {
		return listaTurnos;
	}
	public void setListaTurnos(ArrayList<Turno> listaTurnos) {
		this.listaTurnos = listaTurnos;
	}
	
	public Periodo[] ConsultarPeriodos(){
		Periodo[] periodos = new Periodo[3];
		
		LocalDate fecha = LocalDate.now();
		
		Month mes = fecha.getMonth();
		String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es","ES"));
		int mesNumero = fecha.getMonthValue();
		int year = fecha.getYear();
		
		
		Periodo periodo = new Periodo(nombre, mesNumero, year);
		
		periodos[0] = periodo;
		
		fecha = LocalDate.now().plusMonths(1);
		mes = fecha.getMonth();
		nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es","ES"));
		mesNumero = fecha.getMonthValue();
		year = fecha.getYear();
		
		periodo = new Periodo(nombre, mesNumero, year);
		periodos[1] = periodo;
		
		fecha = LocalDate.now().plusMonths(2);
		mes = fecha.getMonth();
		nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es","ES"));
		mesNumero = fecha.getMonthValue();
		year = fecha.getYear();
		
		periodo = new Periodo(nombre, mesNumero, year);
		periodos[2] = periodo;
		
		return periodos;
	}
	
	

}
