package model.persistence;

import java.util.ArrayList;
import model.ExamenMedico;
import model.ExamenMedicoDTO;

public class DataMapperExamenMedico {

	/**
	 * Convierte un ExamenMedicoDTO en un ExamenMedico.
	 */
	public static ExamenMedico ExamenMedicoDTOToExamenMedico(ExamenMedicoDTO dto) {
		ExamenMedico entity;
		entity = new ExamenMedico(dto.getTipo(), dto.getFecha(), dto.getMedico(), dto.getPaciente(), dto.getNota());
		entity.setId(dto.getId());
		return entity;
	}

	/**
	 * Convierte un ExamenMedico en un ExamenMedicoDTO.
	 */
	public static ExamenMedicoDTO ExamenMedicoToExamenMedicoDTO(ExamenMedico entity) {
		ExamenMedicoDTO dto;
		dto = new ExamenMedicoDTO(entity.getId(), entity.getTipo(), entity.getFecha(), entity.getMedico(),
				entity.getPaciente(), entity.getNota());
		return dto;
	}

	/**
	 * Convierte una lista de ExamenMedicos a una lista de ExamenMedicoDTOs.
	 */
	public static ArrayList<ExamenMedicoDTO> listaExamenMedicoToListaExamenMedicoDTO(
			ArrayList<ExamenMedico> entityList) {
		ArrayList<ExamenMedicoDTO> dtoList = new ArrayList<>();
		for (ExamenMedico m : entityList) {
			dtoList.add(new ExamenMedicoDTO(m.getId(), m.getTipo(), m.getFecha(), m.getMedico(), m.getPaciente(),
					m.getNota()));
		}
		return dtoList;
	}

	/**
	 * Convierte una lista de ExamenMedicoDTOs a una lista de ExamenMedicos.
	 */
	public static ArrayList<ExamenMedico> listaExamenMedicoDTOToListaExamenMedico(ArrayList<ExamenMedicoDTO> dtoList) {
		ArrayList<ExamenMedico> entityList = new ArrayList<>();
		for (ExamenMedicoDTO d : dtoList) {
			entityList.add(new ExamenMedico(d.getTipo(), d.getFecha(), d.getMedico(), d.getPaciente(), d.getNota()));
		}
		return entityList;
	}
}
