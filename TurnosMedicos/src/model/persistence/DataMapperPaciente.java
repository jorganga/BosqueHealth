package model.persistence;

import java.util.ArrayList;

import model.Paciente;
import model.PacienteDTO;

//import model.Paciente;
//import model.PacienteDTO;

public class DataMapperPaciente {
	
	public static Paciente PacienteDTOToPaciente(PacienteDTO dto) {
		Paciente entity;
		entity = new Paciente(dto.getNombre(), dto.getIdentificacion(), dto.getEmail(), dto.getTipoSangre(), dto.getPeso(), dto.getFechaNacimiento());
		return entity;
	}

	public static PacienteDTO PacienteToPacienteDTO(Paciente entity) {
		PacienteDTO dto;
		dto = new PacienteDTO(entity.getNombre(), entity.getIdentificacion(), entity.getEmail(), entity.getTipoSangre(), entity.getPeso(), entity.getFechaNacimiento());
		return dto;
	}

	public static ArrayList<PacienteDTO> listaPacienteToListaPacienteDTO
		(ArrayList<Paciente> entityList) {
		ArrayList<PacienteDTO> dtoList = new ArrayList<>();
		for (Paciente m : entityList) {
			dtoList.add(new PacienteDTO(m.getNombre(), m.getIdentificacion(), m.getEmail(), m.getTipoSangre(), m.getPeso(), m.getFechaNacimiento()));
		}
		return dtoList;
	}
	
	public static ArrayList<Paciente> listaPacienteDTOToListaPaciente
		(ArrayList<PacienteDTO> dtoList){
		ArrayList<Paciente> entityList = new ArrayList<>();
		for (PacienteDTO d : dtoList) {
			entityList.add(new Paciente(d.getNombre(), d.getIdentificacion(), d.getEmail(), d.getTipoSangre(), d.getPeso(), d.getFechaNacimiento()));
		}
		return entityList;
	}
}
