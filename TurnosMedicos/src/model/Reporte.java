package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

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
