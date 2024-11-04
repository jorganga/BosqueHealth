package model;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Turnero {
	ArrayList<Profesional> listaDeDoctores;
	ArrayList<Turno> listaTurnos;

	public Turnero() {
		// TODO Auto-generated constructor stub
		listaDeDoctores = new ArrayList<Profesional>();
		listaTurnos = new ArrayList<Turno>();
	}

	public ArrayList<Profesional> getListaDeDoctores() {
		return listaDeDoctores;
	}

	public void setListaDeDoctores(ArrayList<Profesional> listaDeDoctores) {
		this.listaDeDoctores = listaDeDoctores;
	}

	public ArrayList<Turno> getListaTurnos() {
		return listaTurnos;
	}

	public void setListaTurnos(ArrayList<Turno> listaTurnos) {
		this.listaTurnos = listaTurnos;
	}

	public void asignarTurnos(LocalDate fecha) {

		Profesional doctorTurno = null;
		LocalDate fechaIncremento = fecha;
		// Último día del mes
		LocalDate ultimoDia = fecha.with(TemporalAdjusters.lastDayOfMonth());
		Turno turno = null;

		// cantidad de doctores disponibles
		int numDoctores = listaDeDoctores.size();
		int indiceDoctor = 0;

		boolean doctorValido = false;
		int drNoDisponible = 0;

		for (int i = 1; i <= ultimoDia.getDayOfMonth(); i++) {
			doctorValido = false;
			while (!doctorValido) {
				// determina el doctor que se debe asignar al turno
				if (indiceDoctor >= numDoctores) {
					// reinicia el indice a 0 para empezar de nuevo con el primer doctor
					indiceDoctor = 0;
				}
				// si no hay más dr disponible
				if (drNoDisponible >= numDoctores) {
					fechaIncremento = fecha.plusDays(i);
					drNoDisponible = 0;
					doctorValido = true;
				} else {
					doctorTurno = listaDeDoctores.get(indiceDoctor);
					if (doctorDisponible(doctorTurno, fechaIncremento)) {
						crearTurno(doctorTurno, fechaIncremento);
						fechaIncremento = fecha.plusDays(i);
						doctorValido = true;
						drNoDisponible = 0;
						indiceDoctor++;
					} else {
						indiceDoctor++;
						drNoDisponible++;
					}
				}
			}

		}
	}

	private boolean doctorDisponible(Profesional doctor, LocalDate fechaTurno) {
		boolean disponible = false;
		long dias = 0;
		int numeroSemanaDoctor = 0;

		Date fechaDoctor = null;
		Date fechaActual = TransformarFecha(fechaTurno);

		int numeroSemanaActual = obtenerSemanaFecha(fechaActual);

		if (doctor.getFechaUltimoTurno() != null) {
			fechaDoctor = TransformarFecha(doctor.getFechaUltimoTurno());
			dias = DAYS.between(doctor.getFechaUltimoTurno(), fechaTurno);
			// valida si tiene dos turnos consecutivos
			if (dias > 1) {
				// valida si ya tiene mas de dos turnos en la misma semana

				numeroSemanaDoctor = obtenerSemanaFecha(fechaDoctor);

				if (numeroSemanaActual == numeroSemanaDoctor) {
					if (doctor.getTurnosEnSemana() < 2) {
						disponible = true;
					}
				} else {
					doctor.setTurnosEnSemana(0);
					disponible = true;
				}
			}
		} else {
			disponible = true;
		}

		return disponible;
	}

	private Date TransformarFecha(LocalDate fecha) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private int obtenerSemanaFecha(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);

		calendar.setTime(fecha);

		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	private int obtenerSemanaFecha(LocalDate fecha) {

		ZoneId defaultZoneId = ZoneId.systemDefault();
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);

		Date fechaCasteada = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());

		calendar.setTime(fechaCasteada);

		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	private void crearTurno(Profesional doctor, LocalDate fechaTurno) {
		int numeroSemanaDoctor = 0;

		int numeroSemanaTurno = obtenerSemanaFecha(fechaTurno);

		if (doctor.getFechaUltimoTurno() != null) {

			numeroSemanaDoctor = obtenerSemanaFecha(doctor.getFechaUltimoTurno());
			// calendar.setTime(doctor.getFechaUltimoTurno());
		}
		if (numeroSemanaDoctor == numeroSemanaTurno) {
			// se debe verificar la cantidad de turnos asignados en la semana
			doctor.setTurnosEnSemana(doctor.getTurnosEnSemana() + 1);
		} else {
			doctor.setTurnosEnSemana(1);
		}
		doctor.setFechaUltimoTurno(fechaTurno);

		Turno nuevoTurno;
		
		LocalTime hora = LocalTime.of(0, 0, 0); 

		for (int i = 0; i < 24; i++) {
			hora = LocalTime.of(i, 0, 0);
			nuevoTurno = new Turno(doctor, fechaTurno, hora);
			
			listaTurnos.add(nuevoTurno);
		}

		// Turno nuevoTurno = new Turno(doctor, fechaTurno);

		// System.out.println("Turno: " + nuevoTurno.getDoctor().getNombre() + ", " +
		// nuevoTurno.getFecha().toString());

	}

	public boolean MesValido(LocalDate fecha) {
		List<Turno> listaValidacionMes;
		listaValidacionMes = listaTurnos.stream().filter(turn -> turn.getFecha().equals(fecha))
				.collect(Collectors.toList());
		if (listaValidacionMes.size() == 0) {
			return true;
		}
		return false;

	}

}
