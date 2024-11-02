package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminCita {

	private ArrayList<Cita> listaCitas;

	public AdminCita() {
		// TODO Auto-generated constructor stub
		listaCitas = new ArrayList<Cita>();
	}

	public ArrayList<Turno> ListarTurnosEspecialidad(int idEspecialidad) {

		Turnero turnerito = new Turnero();
		ArrayList<Turno> listaTurnoClinica = turnerito.getListaTurnos();
		List<Turno> listaEspecialidad;

		listaEspecialidad = listaTurnoClinica.stream().filter(turno -> turno.getDoctor().getEspecialidad().getId() == idEspecialidad).collect(Collectors.toList());
		
		return (ArrayList<Turno>) listaEspecialidad;
	}
	
	public ArrayList<Cita> ListarCitasEspecialista(String idMedico) {

		List<Cita> listaCitasEspecialista;
		listaCitasEspecialista = listaCitas.stream().filter(cita -> cita.getTurnito().getDoctor().getIdentificacion().equals(idMedico)).collect(Collectors.toList());
		
		listaCitasEspecialista = listaCitasEspecialista.stream().filter(cita -> cita.getEstado().equals("activa")).collect(Collectors.toList());
		
		return (ArrayList<Cita>)listaCitasEspecialista;
	}
	
	public void CrearCita(Turno miTurno, Paciente miPaciente) {

		Cita laCita = new Cita(miTurno, miPaciente, "activo");
		listaCitas.add(laCita);

	}

	public ArrayList<Cita> getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(ArrayList<Cita> listaCitas) {
		this.listaCitas = listaCitas;
	}

}
