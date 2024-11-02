package model.persistence;

import java.util.ArrayList;

import model.TratamientoMedico;
import model.TratamientoMedicoDTO;


public class DataMapperTratamientoMedico {
	
	public static TratamientoMedico TratamientoMedicoDTOToTratamientoMedico(TratamientoMedicoDTO dto) {
		TratamientoMedico entity;
		entity = new TratamientoMedico(dto.getId(), dto.getFecha(), dto.getPaciente(), dto.getMedico(), dto.getDescripcion());
		return entity;
	}

	public static TratamientoMedicoDTO TratamientoMedicoToTratamientoMedicoDTO(TratamientoMedico entity) {
		TratamientoMedicoDTO dto;
		dto = new TratamientoMedicoDTO(entity.getId(), entity.getFecha(), entity.getPaciente(), entity.getMedico(), entity.getDescripcion());
		return dto;
	}

	public static ArrayList<TratamientoMedicoDTO> listaTratamientoMedicoToListaTratamientoMedicoDTO
		(ArrayList<TratamientoMedico> entityList) {
		ArrayList<TratamientoMedicoDTO> dtoList = new ArrayList<>();
		for (TratamientoMedico m : entityList) {
			dtoList.add(new TratamientoMedicoDTO(m.getId(), m.getFecha(), m.getPaciente(), m.getMedico(), m.getDescripcion()));
		}
		return dtoList;
	}
	
	public static ArrayList<TratamientoMedico> listaTratamientoMedicoDTOToListaTratamientoMedico
		(ArrayList<TratamientoMedicoDTO> dtoList){
		ArrayList<TratamientoMedico> entityList = new ArrayList<>();
		for (TratamientoMedicoDTO d : dtoList) {
			entityList.add(new TratamientoMedico(d.getId(), d.getFecha(), d.getPaciente(), d.getMedico(), d.getDescripcion()));
		}
		return entityList;
	}

}
