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
	

}
