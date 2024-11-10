package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;
/**
 * Clase encargada de generar reportes en formato de tabla para los turnos de
 * los especialistas y las citas médicas.
 */
public class Reporte {
	
	public DefaultTableModel GenerarReporteTurnosPorEspecialista(ArrayList<TurnoDTO> lista) {
		
		String idTurno = "";
		String especialidad = "";
		String especialista = "";
		LocalDate fechaTurno;
		LocalTime horaTurno;
		
		String[] columnNames = {"ID","ESPECIALIDAD", "ESPECIALISTA","FECHA", "HORA"}; //Nombre de la cabecera del reporte
		
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		for (TurnoDTO model : lista) {
			idTurno = model.getId();
			especialidad = model.getDoctor().getEspecialidad().getNombre();
			especialista = model.getDoctor().getNombre();
			fechaTurno = model.getFecha();
			horaTurno = model.getHora();
			
			
			Object[] data = {idTurno, especialidad, especialista, fechaTurno, horaTurno};
			tableModel.addRow(data);
		};
		
		return tableModel;
	}
	
	/**
	 * Genera un reporte de las citas médicas, con los detalles de cada cita.
	 */
	public DefaultTableModel GenerarReporteTurnosMes(ArrayList<TurnoDTO> lista) {
		
		String especialidad = "";
		String especialista = "";
		LocalDate fechaTurno;
		int numeroTurnos = 0;
		
		String[] columnNames = {"ESPECIALIDAD", "ESPECIALISTA", "TURNOS"}; //Nombre de la cabecera del reporte
		
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		//lista de turnos de una sola hora (1 turno por dia)
		List<TurnoDTO> turnosUnicos = lista.stream()
				.filter(turn -> turn.getHora().equals(LocalTime.of(0,0)))
				.collect(Collectors.toList());
		
		List<TurnoDTO> turnosEspecialista;
		
		for (TurnoDTO turno : turnosUnicos) {
			turnosEspecialista = turnosUnicos.stream()
						.filter(turnito -> turnito.getDoctor().getIdentificacion().equals(turno.getDoctor().getIdentificacion()))
						.collect(Collectors.toList());
			
			especialista = turno.getDoctor().getNombre();
			especialidad = turno.getDoctor().getEspecialidad().getNombre();
			numeroTurnos = turnosEspecialista.size();
			
			Object[] data = {especialidad, especialista, numeroTurnos};
			tableModel.addRow(data);
		}
		return tableModel;
	}
	
	public DefaultTableModel GenerarReporteCitas(ArrayList<CitaDTO> lista) {
		
		String paciente = "";
		String idCita = "";
		String especialidad = "";
		String especialista = "";
		LocalDate fechaCita;
		LocalTime horaCita;
		
		String[] columnNames = {"ID","PACIENTE","ESPECIALIDAD", "ESPECIALISTA","FECHA", "HORA"}; //Nombre de la cabecera del reporte
		
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		for (CitaDTO model : lista) {
			idCita = model.getId();
			paciente = model.getPaciente().getNombre();
			especialidad = model.getTurnito().getDoctor().getEspecialidad().getNombre();
			especialista = model.getTurnito().getDoctor().getNombre();
			fechaCita = model.getTurnito().getFecha();
			horaCita = model.getTurnito().getHora();
			
			
			Object[] data = {idCita, paciente, especialidad, especialista, fechaCita, horaCita};
			tableModel.addRow(data);
		};
		
		return tableModel;
	}
	
	
	

}
