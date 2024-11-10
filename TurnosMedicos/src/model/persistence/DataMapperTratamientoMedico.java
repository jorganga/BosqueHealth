package model.persistence;

import java.util.ArrayList;

import model.TratamientoMedico;
import model.TratamientoMedicoDTO;

public class DataMapperTratamientoMedico {

	/**
	 * Convierte un TratamientoMedicoDTO en un TratamientoMedico.
	 */
	public static TratamientoMedico TratamientoMedicoDTOToTratamientoMedico(TratamientoMedicoDTO dto) {
		TratamientoMedico entity;
		entity = new TratamientoMedico(dto.getFecha(), dto.getPaciente(), dto.getMedico(), dto.getDescripcion());
		entity.setId(dto.getId());
		return entity;
	}

	/**
	 * Convierte un TratamientoMedico en un TratamientoMedicoDTO.
	 */
	public static TratamientoMedicoDTO TratamientoMedicoToTratamientoMedicoDTO(TratamientoMedico entity) {
		TratamientoMedicoDTO dto;
		dto = new TratamientoMedicoDTO(entity.getId(), entity.getFecha(), entity.getPaciente(), entity.getMedico(),
				entity.getDescripcion());
		return dto;
	}

	/**
	 * Convierte una lista de TratamientoMedico a una lista de
	 * TratamientoMedicoDTOs.
	 */
	public static ArrayList<TratamientoMedicoDTO> listaTratamientoMedicoToListaTratamientoMedicoDTO(
			ArrayList<TratamientoMedico> entityList) {
		ArrayList<TratamientoMedicoDTO> dtoList = new ArrayList<>();
		for (TratamientoMedico m : entityList) {
			dtoList.add(new TratamientoMedicoDTO(m.getId(), m.getFecha(), m.getPaciente(), m.getMedico(),
					m.getDescripcion()));
		}
		return dtoList;
	}

	/**
	 * Convierte una lista de TratamientoMedicoDTOs a una lista de
	 * TratamientoMedico.
	 */
	public static ArrayList<TratamientoMedico> listaTratamientoMedicoDTOToListaTratamientoMedico(
			ArrayList<TratamientoMedicoDTO> dtoList) {
		ArrayList<TratamientoMedico> entityList = new ArrayList<>();
		for (TratamientoMedicoDTO d : dtoList) {
			entityList.add(new TratamientoMedico(d.getFecha(), d.getPaciente(), d.getMedico(), d.getDescripcion()));
		}
		return entityList;
	}
}
