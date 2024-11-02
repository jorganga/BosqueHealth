package model.persistence;

import java.util.ArrayList;

import model.TipoExamen;
import model.TipoExamenDTO;

public class DataMapperTipoExamen {
	
	public static TipoExamen TipoExamenDTOToTipoExamen(TipoExamenDTO dto) {
		TipoExamen entity;
		entity = new TipoExamen(dto.getId(), dto.getNombre());
		return entity;
	}

	public static TipoExamenDTO TipoExamenToTipoExamenDTO(TipoExamen entity) {
		TipoExamenDTO dto;
		dto = new TipoExamenDTO(entity.getId(), entity.getNombre());
		return dto;
	}

	public static ArrayList<TipoExamenDTO> listaTipoExamenToListaTipoExamenDTO
		(ArrayList<TipoExamen> entityList) {
		ArrayList<TipoExamenDTO> dtoList = new ArrayList<>();
		for (TipoExamen m : entityList) {
			dtoList.add(new TipoExamenDTO(m.getId(), m.getNombre()));
		}
		return dtoList;
	}
	
	public static ArrayList<TipoExamen> listaTipoExamenDTOToListaTipoExamen
		(ArrayList<TipoExamenDTO> dtoList){
		ArrayList<TipoExamen> entityList = new ArrayList<>();
		for (TipoExamenDTO d : dtoList) {
			entityList.add(new TipoExamen(d.getId(), d.getNombre()));
		}
		return entityList;
	}

}
