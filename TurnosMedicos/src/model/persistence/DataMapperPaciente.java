package model.persistence;

import java.util.ArrayList;

import model.Paciente;
import model.PacienteDTO;

//import model.Paciente;
//import model.PacienteDTO;

public class DataMapperPaciente {
	
	public static Paciente PacienteDTOToPaciente(PacienteDTO dto) {
		Paciente entity;
		entity = new Paciente(dto.getNombre(), dto.getId(), dto.getTipoSangre(), dto.getPeso(), dto.getFechaNacimiento(), dto.getCedula());
		return entity;
	}

	public static PacienteDTO PacienteToPacienteDTO(Paciente entity) {
		PacienteDTO dto;
		dto = new PacienteDTO(entity.getNombre(), entity.getId(), entity.getTipoSangre(), entity.getPeso(), entity.getFechaNacimiento(), entity.getCedula());
		return dto;
	}

	public static ArrayList<PacienteDTO> listaPacienteToListaPacienteDTO
		(ArrayList<Paciente> entityList) {
		ArrayList<PacienteDTO> dtoList = new ArrayList<>();
		for (Paciente m : entityList) {
			dtoList.add(new PacienteDTO(m.getNombre(), m.getId(), m.getTipoSangre(), m.getPeso(), m.getFechaNacimiento(), m.getCedula()));
		}
		return dtoList;
	}
	
	public static ArrayList<Paciente> listaPacienteDTOToListaPaciente
		(ArrayList<PacienteDTO> dtoList){
		ArrayList<Paciente> entityList = new ArrayList<>();
		for (PacienteDTO d : dtoList) {
			entityList.add(new Paciente(d.getNombre(), d.getId(), d.getTipoSangre(), d.getPeso(), d.getFechaNacimiento(), d.getCedula()));
		}
		return entityList;
	}
}
