package model;

import java.time.LocalDate;
import java.time.LocalTime;
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
	ArrayList<Turno> listaTurnos;
	TurnoDAO dao;
	
	private  Profesional userActivo;
	
	public Profesional getUserActivo() {
		return userActivo;
	}
	public void setUserActivo(Profesional userActivo) {
		this.userActivo = userActivo;
	}
	public AdminClinica(Profesional usuario) {
		// TODO Auto-generated constructor stub
		listaEspecialidades = new ArrayList<Especialidad>();
		listaTurnos = new ArrayList<Turno>();
		dao = new TurnoDAO();
		userActivo = usuario;
	}
	public ArrayList<Especialidad> getListaEspecialidades() {
		return listaEspecialidades;
	}
	public void setListaEspecialidades(ArrayList<Especialidad> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}
	
	public void cargarEspecialidades() {
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
	
	public String generarTurnos(LocalDate fechaMes, ArrayList<Profesional> listaDoctores) {
		Turnero turnero = new Turnero();
		List<Profesional> listaFiltrada;
		 
		ArrayList<Turno> turnosEspecialidad = new ArrayList<Turno>();
		
		if (turnosYaGenerados(fechaMes)) {
			return "Este periodo ya fue generado, seleccione otro!";
		}
		
		for(Especialidad especia:listaEspecialidades) {
			listaFiltrada =  listaDoctores.stream().filter(per -> per.getEspecialidad().getNombre().equals(especia.getNombre())).collect(Collectors.toList());
		    turnero.setListaDeDoctores((ArrayList<Profesional>)listaFiltrada); 
		    turnero.asignarTurnos(fechaMes);
		    turnosEspecialidad.addAll(turnero.getListaTurnos());
		}
		
		listaTurnos.addAll(turnosEspecialidad);
		dao.setListaTurno(listaTurnos);
	    dao.writeSerializable();
		
	    notificarTurnosGenerados(turnosEspecialidad);
	    
		return "Turnos Generados Exitosamente!";
	}
	
	private void notificarTurnosGenerados(ArrayList<Turno> turnosGenerados) {
		String mensaje = "Dr " + userActivo.getNombre()  + "\r\n Estos son los turnos del mes\r\n";
		List<Turno> listaTurnosDia = turnosGenerados.stream().filter(tur -> tur.getHora().equals(LocalTime.of(0, 0))).collect(Collectors.toList());
		
		LocalDate fechaActiva = null;
		boolean cambioFecha = false;
		for(Turno turnoDia:listaTurnosDia) {
			mensaje = mensaje + "Fecha: " + turnoDia.getFecha() + ", ";
			mensaje = mensaje + "Especialidad "+ turnoDia.getDoctor().getEspecialidad().getNombre() + ", ";
			mensaje = mensaje + "Profesional "+ turnoDia.getDoctor().getNombre() + "\r\n";		
		}
		
		Email email = new Email("Turnos Generados", userActivo.getEmail(), mensaje);
		email.EnviarMail();
		System.out.println("Email de turnos enviado a: " + email.getDestinatario());
	}
	
	private boolean turnosYaGenerados(LocalDate fechaMes) {
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
	
	public Periodo[] consultarPeriodos(){
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
