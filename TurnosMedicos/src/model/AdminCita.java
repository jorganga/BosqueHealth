package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import model.persistence.*;
import java.util.Comparator;

public class AdminCita {
	private CitaDAO dao;
	private ArrayList<Cita> listaCitas;
	Properties properties;
	private boolean citasDiaSiguientesNotificadas;

	public AdminCita() {
		// TODO Auto-generated constructor stub
		listaCitas = new ArrayList<Cita>();
		dao = new CitaDAO();
		properties = FileHandler.loadProperties("config.properties");
	}

	public ArrayList<Turno> listarTurnosEspecialidad(int idEspecialidad) {

		Turnero turnerito = new Turnero();
		ArrayList<Turno> listaTurnoClinica = turnerito.getListaTurnos();
		List<Turno> listaEspecialidad;

		listaEspecialidad = listaTurnoClinica.stream()
				.filter(turno -> turno.getDoctor().getEspecialidad().getId() == idEspecialidad)
				.collect(Collectors.toList());

		return (ArrayList<Turno>) listaEspecialidad;
	}

	public ArrayList<CitaDTO> listarCitas() {
		CitaDAO citaDao = new CitaDAO();
		return citaDao.getAllActiva();
	}

	private ArrayList<CitaDTO> listarCitasRecordatorio() {
		CitaDAO citaDao = new CitaDAO();
		ArrayList<CitaDTO> listaCitasAll = citaDao.getAll();

		int diasRecordatorio = Integer.parseInt(properties.getProperty("cita.diasRecordatorio"));
		LocalDate fechaMaximaCitaRecordar = LocalDate.now();

		ArrayList<CitaDTO> listaCitasRecordar = listaCitasAll.stream()
				.filter(cita -> cita.getTurnito().getFecha().isAfter(LocalDate.now())
						&& cita.getTurnito().getFecha().isBefore(fechaMaximaCitaRecordar.plusDays(diasRecordatorio))
						&& cita.isEnvioRecordatorio() == false)
				.collect(Collectors.toCollection(ArrayList::new));
		return listaCitasRecordar;

	}

	public void enviarEmailsCitasDiaSiguiente(ArrayList<Profesional> listaDoctores) {
		if (!isEnviarCitasDiaSiguiente()) {
			return;
		}

		notificarCitasDiaSiguiente(listaDoctores);

		System.out.println("Email de citas dia siguiente ");
		citasDiaSiguientesNotificadas = true;
	}

	private boolean isEnviarCitasDiaSiguiente() {
		int hora = Integer.parseInt(properties.getProperty("cita.horaNotificarCitasDiaSiguiente.hora"));
		int minuto = Integer.parseInt(properties.getProperty("cita.horaNotificarCitasDiaSiguiente.minuto"));
		LocalTime horaActual = LocalTime.now();

		if (citasDiaSiguientesNotificadas) {
			return false;
		}

		if (horaActual.getHour() != hora) {
			return false;
		}

		if (horaActual.getMinute() != minuto) {
			return false;
		}

		return true;
	}

	private void notificarCitasDiaSiguiente(ArrayList<Profesional> listaProfesionales) {
		CitaDAO citaDao = new CitaDAO();
		ArrayList<CitaDTO> listaCitasAll = citaDao.getAllActiva();
		ArrayList<CitaDTO> listaCitasRecordar;

		for (Profesional medico : listaProfesionales) {
			listaCitasRecordar = listaCitasAll.stream()
					.filter(cita -> cita.getTurnito().getFecha().equals(LocalDate.now().plusDays(1))
							&& cita.getTurnito().getDoctor().getIdentificacion().equals(medico.getIdentificacion()))
					.collect(Collectors.toCollection(ArrayList::new));
			if (listaCitasRecordar.size() > 0) {

				// ordenar por hora
				listaCitasRecordar.sort(Comparator.comparing((CitaDTO cit) -> cit.getTurnito().getHora()));
				notificarCitasDiaSiguienteProfesional(listaCitasRecordar, medico);
			}
		}

	}

	private void notificarCitasDiaSiguienteProfesional(ArrayList<CitaDTO> listaCitasProfesional, Profesional medico) {
		String mensaje = "Dr(ra) " + medico.getNombre() + "\r\n\n";
		LocalDate fecha = listaCitasProfesional.getFirst().getTurnito().getFecha();
		mensaje = mensaje + "Estas son sus citas para la especialidad " + medico.getEspecialidad().getNombre()
				+ ", para el día " + fecha + "\r\n\n";

		for (CitaDTO cita : listaCitasProfesional) {
			mensaje = mensaje + "Paciente: " + cita.getPaciente().getIdentificacion() + "---"
					+ cita.getPaciente().getNombre() + "\r\n";
			mensaje = mensaje + "Hora: " + cita.getTurnito().getHora() + "\r\n\n";
		}

		Email email = new Email("Bosque Health - Citas " + fecha, medico.getEmail(), mensaje);
		email.EnviarMail();
		System.out.println("Email Citas Especialista dia Siguiente - enviado a: " + email.getDestinatario());
	}

	public void enviarEmailsRecordarCitas() {
		ArrayList<CitaDTO> citasNotificar = listarCitasRecordatorio();

		for (CitaDTO citaRecordar : citasNotificar) {
			notificarCitaRecordar(citaRecordar);
		}
	}

	public ArrayList<Cita> listarCitasEspecialista(String idMedico) {

		List<Cita> listaCitasEspecialista;
		listaCitasEspecialista = listaCitas.stream()
				.filter(cita -> cita.getTurnito().getDoctor().getIdentificacion().equals(idMedico))
				.collect(Collectors.toList());

		listaCitasEspecialista = listaCitasEspecialista.stream().filter(cita -> cita.getEstado().equals("activa"))
				.collect(Collectors.toList());

		return (ArrayList<Cita>) listaCitasEspecialista;
	}

	public void crearCita(String idTurno, Paciente miPaciente) {

		TurnoDAO daoTurno = new TurnoDAO();
		TurnoDTO turnoModificado = new TurnoDTO();

		Turno turno = new Turno();
		turno.setId(idTurno);

		Turno turnoSeleccionado = daoTurno.find(turno);
		TurnoDTO turnoOriginal = DataMapperTurno.TurnoToTurnoDTO(turnoSeleccionado);

		Cita nuevaCita = new Cita(turnoSeleccionado, miPaciente, "activo", false);

		CitaDTO laCita = DataMapperCita.CitaToCitaDTO(nuevaCita);
		dao.add(laCita);

		turnoModificado = DataMapperTurno.TurnoToTurnoDTO(turnoSeleccionado);
		turnoModificado.setLibre(false);

		daoTurno.update(turnoOriginal, turnoModificado);

		notificarCitaCreada(laCita);

	}

	private void notificarCitaCreada(CitaDTO citaCreada) {
		String mensaje = "Sr(ra) " + citaCreada.getPaciente().getNombre() + "\r\n\n";
		mensaje = mensaje + "Se ha asignado una cita médica. Estos son los datos de su cita:\r\n\n";
		mensaje = mensaje + "Especialidad: " + citaCreada.getTurnito().getDoctor().getEspecialidad() + "\r\n";
		mensaje = mensaje + "Fecha: " + citaCreada.getTurnito().getFecha() + "\r\n";
		mensaje = mensaje + "Hora: " + citaCreada.getTurnito().getHora() + "\r\n";
		mensaje = mensaje + "Especialista: " + citaCreada.getTurnito().getDoctor().getNombre() + "\r\n";

		Email email = new Email("Bosque Health - Cita", citaCreada.getPaciente().getEmail(), mensaje);
		email.EnviarMail();
		System.out.println("Email de cita enviado a: " + email.getDestinatario());
	}

	private void notificarCitaRecordar(CitaDTO citaRecordar) {
		String mensaje = "Sr(ra) " + citaRecordar.getPaciente().getNombre() + "\r\n\n";
		mensaje = mensaje + "Recuerde asistir a su cita.\r\n\n";
		mensaje = mensaje + "Estos son los datos de su cita:\r\n\n";
		mensaje = mensaje + "Especialidad: " + citaRecordar.getTurnito().getDoctor().getEspecialidad() + "\r\n";
		mensaje = mensaje + "Fecha: " + citaRecordar.getTurnito().getFecha() + "\r\n";
		mensaje = mensaje + "Hora: " + citaRecordar.getTurnito().getHora() + "\r\n";
		mensaje = mensaje + "Especialista: " + citaRecordar.getTurnito().getDoctor().getNombre() + "\r\n";

		Email email = new Email("Bosque Health - Recordatorio de Cita", citaRecordar.getPaciente().getEmail(), mensaje);
		if (email.EnviarMail()) {
			System.out.println("Email de Recordatorio de cita enviado a: " + email.getDestinatario());

			CitaDTO citaActualizar = citaRecordar;
			citaActualizar.setEnvioRecordatorio(true);
			dao.update(citaRecordar, citaActualizar);
		}

	}

	// Funcion para cancelar cita

	public boolean cancelarCita(CitaDTO citaCancelar) {

		citaCancelar.setEstado("cancelada");
		Cita citaCancelOriginal = dao.find(DataMapperCita.CitaDTOToCita(citaCancelar));
		Cita citaCancelNuevo = citaCancelOriginal;
		citaCancelNuevo.setEstado(citaCancelar.getEstado());
		citaCancelar = DataMapperCita.CitaToCitaDTO(citaCancelNuevo);
		return dao.update(DataMapperCita.CitaToCitaDTO(citaCancelOriginal), citaCancelar);

	}

	public ArrayList<Cita> getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(ArrayList<Cita> listaCitas) {
		this.listaCitas = listaCitas;
	}

}
