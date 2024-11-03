package model.persistence;
import java.util.ArrayList;
import model.Turno;
import model.TurnoDTO;

public class DataMapperTurno {
	
	public static Turno TurnoDTOToTurno(TurnoDTO dto) {
		Turno entity;
		entity = new Turno();
		entity.setId(dto.getId());
		entity.setDoctor(dto.getDoctor());
		entity.setFecha(dto.getFecha());
		entity.setHora(dto.getHora());
		return entity;
	}

	public static TurnoDTO TurnoToTurnoDTO(Turno entity) {
		TurnoDTO dto;
		dto = new TurnoDTO(entity.getId(), entity.getDoctor(), entity.getFecha(), entity.getHora());
		return dto;
	}

	public static ArrayList<TurnoDTO> listaTurnoToListaTurnoDTO
		(ArrayList<Turno> entityList) {
		ArrayList<TurnoDTO> dtoList = new ArrayList<>();
		for (Turno m : entityList) {
			dtoList.add(new TurnoDTO(m.getId(), m.getDoctor(), m.getFecha(), m.getHora()));
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
