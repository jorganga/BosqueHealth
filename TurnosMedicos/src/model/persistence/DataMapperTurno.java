package model.persistence;


import java.util.ArrayList;

import model.Turno;
import model.TurnoDTO;

//import model.Turno;
//import model.TurnoDTO;

public class DataMapperTurno {
	
	public static Turno TurnoDTOToTurno(TurnoDTO dto) {
		Turno entity;
		entity = new Turno(dto.getDoctor(), dto.getFecha(), dto.getHora());
		return entity;
	}

	public static TurnoDTO TurnoToTurnoDTO(Turno entity) {
		TurnoDTO dto;
		dto = new TurnoDTO(null, entity.getDoctor(), entity.getFecha(), entity.getHora());
		return dto;
	}

	public static ArrayList<TurnoDTO> listaTurnoToListaTurnoDTO
		(ArrayList<Turno> entityList) {
		ArrayList<TurnoDTO> dtoList = new ArrayList<>();
		for (Turno m : entityList) {
			dtoList.add(new TurnoDTO(null, m.getDoctor(), m.getFecha(), m.getHora()));
		}
		return dtoList;
	}
	
	public static ArrayList<Turno> listaTurnoDTOToListaTurno
		(ArrayList<TurnoDTO> dtoList){
		ArrayList<Turno> entityList = new ArrayList<>();
		for (TurnoDTO d : dtoList) {
			entityList.add(new Turno(d.getDoctor(), d.getFecha(), d.getHora()));
		}
		return entityList;
	}
}
