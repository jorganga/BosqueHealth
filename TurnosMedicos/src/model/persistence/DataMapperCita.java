package model.persistence;

import java.util.ArrayList;
import model.Cita;
import model.CitaDTO;
import model.Turno;

public class DataMapperCita {
	
	public static Cita CitaDTOToCita(CitaDTO dto) {
		Cita entity;
		entity = new Cita(dto.getTurnito(), dto.getPaciente(), dto.getEstado(), dto.isEnvioRecordatorio());
		entity.setId(dto.getId());
		return entity;
	}

	public static CitaDTO CitaToCitaDTO(Cita entity) {
		CitaDTO dto;
		dto = new CitaDTO(entity.getId(), entity.getTurnito(), entity.getPaciente(), entity.getEstado(), entity.isEnvioRecordatorio());
		return dto;
	}

	public static ArrayList<CitaDTO> listaCitaToListaCitaDTO
		(ArrayList<Cita> entityList) {
		ArrayList<CitaDTO> dtoList = new ArrayList<>();
		for (Cita m : entityList) {
			dtoList.add(new CitaDTO(m.getId(), m.getTurnito(), m.getPaciente(), m.getEstado(), m.isEnvioRecordatorio()));
		}
		return dtoList;
	}
	
	public static ArrayList<Cita> listaCitaDTOToListaCita
		(ArrayList<CitaDTO> dtoList){
		ArrayList<Cita> entityList = new ArrayList<>();
		for (CitaDTO d : dtoList) {
			entityList.add(new Cita(d.getTurnito(), d.getPaciente(), d.getEstado(), d.isEnvioRecordatorio()));
		}
		return entityList;
	}

}
