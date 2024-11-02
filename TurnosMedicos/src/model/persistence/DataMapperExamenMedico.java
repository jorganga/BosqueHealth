package model.persistence;

import java.util.ArrayList;
import model.ExamenMedico;
import model.ExamenMedicoDTO;

public class DataMapperExamenMedico {
	
	public static ExamenMedico ExamenMedicoDTOToExamenMedico(ExamenMedicoDTO dto) {
		ExamenMedico entity;
		entity = new ExamenMedico(dto.getId(), dto.getTipo(), dto.getFecha(), dto.getMedico(), dto.getPaciente(), dto.getNota());
		return entity;
	}

	public static ExamenMedicoDTO ExamenMedicoToExamenMedicoDTO(ExamenMedico entity) {
		ExamenMedicoDTO dto;
		dto = new ExamenMedicoDTO(entity.getId(), entity.getTipo(), entity.getFecha(), entity.getMedico(), entity.getPaciente(), entity.getNota());
		return dto;
	}

	public static ArrayList<ExamenMedicoDTO> listaExamenMedicoToListaExamenMedicoDTO
		(ArrayList<ExamenMedico> entityList) {
		ArrayList<ExamenMedicoDTO> dtoList = new ArrayList<>();
		for (ExamenMedico m : entityList) {
			dtoList.add(new ExamenMedicoDTO(m.getId(), m.getTipo(), m.getFecha(), m.getMedico(), m.getPaciente(), m.getNota()));
		}
		return dtoList;
	}
	
	public static ArrayList<ExamenMedico> listaExamenMedicoDTOToListaExamenMedico
		(ArrayList<ExamenMedicoDTO> dtoList){
		ArrayList<ExamenMedico> entityList = new ArrayList<>();
		for (ExamenMedicoDTO d : dtoList) {
			entityList.add(new ExamenMedico(d.getId(), d.getTipo(), d.getFecha(), d.getMedico(), d.getPaciente(), d.getNota()));
		}
		return entityList;
	}

}
