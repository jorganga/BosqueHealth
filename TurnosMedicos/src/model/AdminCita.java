package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import model.persistence.*;

public class AdminCita {
	private CitaDAO dao;
	private ArrayList<Cita> listaCitas;
	Properties properties;
	
	public AdminCita() {
		// TODO Auto-generated constructor stub
		listaCitas = new ArrayList<Cita>();
		dao = new CitaDAO();
	}


	public ArrayList<Turno> ListarTurnosEspecialidad(int idEspecialidad) {

		Turnero turnerito = new Turnero();
		ArrayList<Turno> listaTurnoClinica = turnerito.getListaTurnos();
		List<Turno> listaEspecialidad;

		listaEspecialidad = listaTurnoClinica.stream().filter(turno -> turno.getDoctor().getEspecialidad().getId() == idEspecialidad).collect(Collectors.toList());
		
		return (ArrayList<Turno>) listaEspecialidad;
	}
	
	public ArrayList<CitaDTO> ListarCitas(){
		CitaDAO citaDao = new CitaDAO();
		return citaDao.getAll();
	}
	
	private ArrayList<CitaDTO> ListarCitasRecordatorio(){
		CitaDAO citaDao = new CitaDAO();
		ArrayList<CitaDTO> listaCitasAll = citaDao.getAll();
		 
		properties = FileHandler.loadProperties("config.properties");
        
        int diasRecordatorio = Integer.parseInt(properties.getProperty("cita.diasRecordatorio"));
        LocalDate fechaMaximaCitaRecordar = LocalDate.now();
        
        ArrayList<CitaDTO> listaCitasRecordar = listaCitasAll.stream().
        		filter(cita -> cita.getTurnito().getFecha().isAfter(LocalDate.now()) &&
        			cita.getTurnito().getFecha().isBefore(fechaMaximaCitaRecordar.plusDays(diasRecordatorio)) &&
        			cita.isEnvioRecordatorio() == false)	
        			.collect(Collectors.toCollection(ArrayList::new));
        return listaCitasRecordar;
        
	}
	
	public void enviarEmailsRecordarCitas(){
		ArrayList<CitaDTO> citasNotificar = ListarCitasRecordatorio();
		
		for(CitaDTO citaRecordar:citasNotificar) {
			notificarCitaRecordar(citaRecordar);
		}
	}
	
	public ArrayList<Cita> ListarCitasEspecialista(String idMedico) {

		List<Cita> listaCitasEspecialista; 
		listaCitasEspecialista = listaCitas.stream().filter(cita -> cita.getTurnito().getDoctor().getIdentificacion().equals(idMedico)).collect(Collectors.toList());
		
		listaCitasEspecialista = listaCitasEspecialista.stream().filter(cita -> cita.getEstado().equals("activa")).collect(Collectors.toList());
		
		return (ArrayList<Cita>)listaCitasEspecialista;
	}
	
	public void CrearCita(String idTurno, Paciente miPaciente) {

		TurnoDAO daoTurno = new TurnoDAO();
		TurnoDTO turnoModificado = new TurnoDTO(); 
		
		Turno turno = new Turno();
		turno.setId(idTurno);
		
		Turno turnoSeleccionado = daoTurno.find(turno);
		TurnoDTO turnoOriginal = DataMapperTurno.TurnoToTurnoDTO(turnoSeleccionado); 
		
		CitaDTO laCita = new CitaDTO("", turnoSeleccionado, miPaciente, "activo", false);
		dao.add(laCita);
		
		turnoModificado = DataMapperTurno.TurnoToTurnoDTO(turnoSeleccionado);
		turnoModificado.setLibre(false);
		
		daoTurno.update(turnoOriginal, turnoModificado);
		
		notificarCitaCreada(laCita);
		
	}
	
	private void notificarCitaCreada(CitaDTO citaCreada) {
		String mensaje = "Sr(ra) " +  citaCreada.getPaciente().getNombre()  + "\r\n\n";
		mensaje = mensaje + "Se ha asignado una cita médica. Estos son los datos de su cita:\r\n\n";
		mensaje = mensaje + "Especialidad: " + citaCreada.getTurnito().getDoctor().getEspecialidad()  + "\r\n";
		mensaje = mensaje + "Fecha: "+ citaCreada.getTurnito().getFecha() + "\r\n";
		mensaje = mensaje + "Hora: "+ citaCreada.getTurnito().getHora() + "\r\n";
		mensaje = mensaje + "Especialista: "+ citaCreada.getTurnito().getDoctor().getNombre() + "\r\n";
		
		Email email = new Email("Bosque Health - Cita", citaCreada.getPaciente().getEmail(), mensaje);
		email.EnviarMail();
		System.out.println("Email de cita enviado a: " + email.getDestinatario());
	}
	
	private void notificarCitaRecordar(CitaDTO citaRecordar) {
		String mensaje = "Sr(ra) " +  citaRecordar.getPaciente().getNombre()  + "\r\n\n";
		mensaje = mensaje + "Recuerde asistir a su cita.\r\n\n";
		mensaje = mensaje + "Estos son los datos de su cita:\r\n\n";
		mensaje = mensaje + "Especialidad: " + citaRecordar.getTurnito().getDoctor().getEspecialidad()  + "\r\n";
		mensaje = mensaje + "Fecha: "+ citaRecordar.getTurnito().getFecha() + "\r\n";
		mensaje = mensaje + "Hora: "+ citaRecordar.getTurnito().getHora() + "\r\n";
		mensaje = mensaje + "Especialista: "+ citaRecordar.getTurnito().getDoctor().getNombre() + "\r\n";
		
		Email email = new Email("Bosque Health - Recordatorio de Cita", citaRecordar.getPaciente().getEmail(), mensaje);
		if (email.EnviarMail())
		{
			System.out.println("Email de Recordatorio de cita enviado a: " + email.getDestinatario());
			
			CitaDTO citaActualizar = citaRecordar;
			citaActualizar.setEnvioRecordatorio(true);
			dao.update(citaRecordar, citaActualizar);
		}
		
	}

	public ArrayList<Cita> getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(ArrayList<Cita> listaCitas) {
		this.listaCitas = listaCitas;
	}

}
