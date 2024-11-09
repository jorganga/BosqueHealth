package model.persistence;

import java.util.ArrayList;
import model.SeguimientoDTO;
import model.Seguimiento;

public class DataMapperSeguimiento {
	
	public static Seguimiento SeguimientoDTOToSeguimiento(SeguimientoDTO dto) {
		Seguimiento entity;
		entity = new Seguimiento(dto.getIdPaciente(), dto.getDescripcion(), dto.getFecha());
		entity.setId(dto.getId());
		return entity;
	}

	public static SeguimientoDTO SeguimientoToSeguimientoDTO(Seguimiento entity) {
		SeguimientoDTO dto;
		dto = new SeguimientoDTO(entity.getId(), entity.getIdPaciente(), entity.getDescripcion(), entity.getFecha());
		return dto;
	}

	public static ArrayList<SeguimientoDTO> listaSeguimientoToListaSeguimientoDTO
		(ArrayList<Seguimiento> entityList) {
		ArrayList<SeguimientoDTO> dtoList = new ArrayList<>();
		for (Seguimiento m : entityList) {
			dtoList.add(new SeguimientoDTO(m.getId(), m.getIdPaciente(), m.getDescripcion(), m.getFecha()));
		}
		return dtoList;
	}
	
	public static ArrayList<Seguimiento> listaSeguimientoDTOToListaSeguimiento
		(ArrayList<SeguimientoDTO> dtoList){
		ArrayList<Seguimiento> entityList = new ArrayList<>();
		for (SeguimientoDTO d : dtoList) {
			entityList.add(new Seguimiento(d.getIdPaciente(), d.getDescripcion(), d.getFecha()));
		}
		return entityList;
	}

}
