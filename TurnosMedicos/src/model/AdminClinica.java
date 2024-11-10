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

/**
 * Clase que administra los turnos y especialidades en la clínica. Permite
 * cargar especialidades, generar turnos, consultar periodos, y gestionar la
 * información relacionada con los turnos médicos.
 */
public class AdminClinica {
	ArrayList<Especialidad> listaEspecialidades;
	ArrayList<Turno> listaTurnos;
	TurnoDAO dao;
	private Profesional userActivo;

	/**
	 * Obtiene el profesional activo.
	 */
	public Profesional getUserActivo() {
		return userActivo;
	}

	/**
	 * Establece el profesional activo.
	 */
	public void setUserActivo(Profesional userActivo) {
		this.userActivo = userActivo;
	}

	/**
	 * Constructor de la clase, inicializa las listas de especialidades y turnos y
	 * asigna el profesional activo.
	 */
	public AdminClinica(Profesional usuario) {
		// TODO Auto-generated constructor stub
		listaEspecialidades = new ArrayList<Especialidad>();
		listaTurnos = new ArrayList<Turno>();
		dao = new TurnoDAO();
		userActivo = usuario;
	}

	/**
	 * Obtiene la lista de especialidades disponibles.
	 */
	public ArrayList<Especialidad> getListaEspecialidades() {
		return listaEspecialidades;
	}

	/**
	 * Establece una nueva lista de especialidades.
	 */
	public void setListaEspecialidades(ArrayList<Especialidad> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	/**
	 * Carga las especialidades predefinidas en el sistema.
	 */
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

	/**
	 * Genera los turnos para un mes específico, asignando turnos a los doctores
	 * según sus especialidades. Si los turnos ya fueron generados para ese mes,
	 * muestra un mensaje indicando que ya fueron generados. Si no, asigna turnos a
	 * los doctores disponibles según sus especialidades.
	 */
	public String generarTurnos(LocalDate fechaMes, ArrayList<Profesional> listaDoctores) {
		Turnero turnero = new Turnero();
		List<Profesional> listaFiltrada;

		ArrayList<Turno> turnosEspecialidad = new ArrayList<Turno>();

		if (turnosYaGenerados(fechaMes)) {
			return "Este periodo ya fue generado, seleccione otro!";
		}

		for (Especialidad especia : listaEspecialidades) {
			listaFiltrada = listaDoctores.stream()
					.filter(per -> per.getEspecialidad().getNombre().equals(especia.getNombre()))
					.collect(Collectors.toList());
			if (listaFiltrada.size() > 0) {
				turnero = new Turnero();
				turnero.setListaDeDoctores((ArrayList<Profesional>) listaFiltrada);
				turnero.asignarTurnos(fechaMes);
				turnosEspecialidad.addAll(turnero.getListaTurnos());
			}
		}

		listaTurnos.addAll(turnosEspecialidad);
		dao.setListaTurno(listaTurnos);
		dao.writeSerializable();

		notificarTurnosGenerados(turnosEspecialidad);

		return "Turnos Generados Exitosamente!";
	}

	/**
	 * Envía un correo electrónico con la notificación de los turnos generados. La
	 * notificación incluye la fecha, especialidad y profesional correspondiente a
	 * cada turno.
	 */
	private void notificarTurnosGenerados(ArrayList<Turno> turnosGenerados) {
		String mensaje = "Dr " + userActivo.getNombre() + "\r\n Estos son los turnos del mes\r\n";
		List<Turno> listaTurnosDia = turnosGenerados.stream().filter(tur -> tur.getHora().equals(LocalTime.of(0, 0)))
				.collect(Collectors.toList());

		LocalDate fechaActiva = null;
		boolean cambioFecha = false;
		for (Turno turnoDia : listaTurnosDia) {
			mensaje = mensaje + "Fecha: " + turnoDia.getFecha() + ", ";
			mensaje = mensaje + "Especialidad " + turnoDia.getDoctor().getEspecialidad().getNombre() + ", ";
			mensaje = mensaje + "Profesional " + turnoDia.getDoctor().getNombre() + "\r\n";
		}

		Email email = new Email("Turnos Generados", userActivo.getEmail(), mensaje);
		email.EnviarMail();
		System.out.println("Email de turnos enviado a: " + email.getDestinatario());
	}

	/**
	 * Verifica si los turnos ya han sido generados para un mes específico. Filtra
	 * la lista de turnos por la fecha y verifica si ya existen turnos asignados
	 * para ese mes.
	 */
	private boolean turnosYaGenerados(LocalDate fechaMes) {
		dao.readSerializable();
		listaTurnos = dao.getListaTurno();
		if (listaTurnos != null && listaTurnos.size() > 0) {
			List<Turno> listaFiltrada = listaTurnos.stream().filter(turn -> turn.getFecha().equals(fechaMes))
					.collect(Collectors.toList());
			if (listaFiltrada.size() > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtiene la lista de turnos generados en el sistema.
	 */
	public ArrayList<Turno> getListaTurnos() {
		return listaTurnos;
	}

	/**
	 * Establece una nueva lista de turnos generados.
	 */
	public void setListaTurnos(ArrayList<Turno> listaTurnos) {
		this.listaTurnos = listaTurnos;
	}

	/**
	 * Consulta los periodos actuales, el siguiente mes y el mes posterior. Devuelve
	 * un arreglo con tres periodos: el actual, el siguiente mes y el mes posterior.
	 */
	public Periodo[] consultarPeriodos() {
		Periodo[] periodos = new Periodo[3];

		LocalDate fecha = LocalDate.now();

		Month mes = fecha.getMonth();
		String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		int mesNumero = fecha.getMonthValue();
		int year = fecha.getYear();

		Periodo periodo = new Periodo(nombre, mesNumero, year);

		periodos[0] = periodo;

		fecha = LocalDate.now().plusMonths(1);
		mes = fecha.getMonth();
		nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		mesNumero = fecha.getMonthValue();
		year = fecha.getYear();

		periodo = new Periodo(nombre, mesNumero, year);
		periodos[1] = periodo;

		fecha = LocalDate.now().plusMonths(2);
		mes = fecha.getMonth();
		nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
		mesNumero = fecha.getMonthValue();
		year = fecha.getYear();

		periodo = new Periodo(nombre, mesNumero, year);
		periodos[2] = periodo;

		return periodos;
	}

}
