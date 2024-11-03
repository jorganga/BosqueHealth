package model.persistence;

import java.util.ArrayList;
import java.util.stream.Collectors;

import model.persistence.FileHandler;
import model.Turno;
import model.TurnoDTO;

public class TurnoDAO implements CRUDOperation<TurnoDTO, Turno>{
	
	private ArrayList<Turno> listaTurno;
	private ArrayList<Turno> listaTurnoActivos;
	
	
	private final String FILE_NAME = "turno.csv"; // csv: excel txt: texto docx:word
	private final String SERIAL_NAME = "turno.bin"; // .dat o .bin
	
	public TurnoDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}
	
	@Override
	public String showAll() {
		String rta = "";
		if (listaTurno.isEmpty()) {
			return "No hay Turnos en la lista";
		} else {
			for (Turno Turno : listaTurno) {
				rta += Turno;
			}
			return rta;
		}
	}

	public ArrayList<Turno> getListaTurno() {
		return listaTurno;
	}
	
	public ArrayList<Turno> getListaTurnoActiva() {
		
		listaTurnoActivos =  listaTurno.stream().filter(tur -> tur.isLibre()).collect(Collectors.toCollection(ArrayList::new));
		return listaTurnoActivos;
	}
	

	public void setListaTurno(ArrayList<Turno> listaTurno) {
		this.listaTurno = listaTurno;
	}

	@Override
	public ArrayList<TurnoDTO> getAll() {
		return DataMapperTurno.listaTurnoToListaTurnoDTO(listaTurno);
	}
	
	public ArrayList<TurnoDTO> getAllActivos() {
		return DataMapperTurno.listaTurnoToListaTurnoDTO(listaTurnoActivos);
	}
	
	

	@Override
	public boolean add(TurnoDTO newData) {
		if (find(DataMapperTurno.TurnoDTOToTurno(newData)) == null) {
			listaTurno.add(DataMapperTurno.TurnoDTOToTurno(newData));
			writeFile();
			writeSerializable();
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean delete(TurnoDTO toDelete) {
		Turno found = find(DataMapperTurno.TurnoDTOToTurno(toDelete));
		if (found != null) {
			writeFile();
			writeSerializable();
			return listaTurno.remove(found);
		} else {
			return false;
		}
	}
	

	@Override
	public Turno find(Turno toFind) {
		if (listaTurno != null) {
			return listaTurno.stream()
		            .filter(p -> p.getId().equals(toFind.getId()))
		            .findFirst()                        
		            .orElse(null);	
		}
		else {
			return null;
		}
	}

	@Override
	public boolean update(TurnoDTO previous, TurnoDTO newData) {
		if (find(DataMapperTurno.TurnoDTOToTurno(previous)) != null) {
			listaTurno.remove(find(DataMapperTurno.TurnoDTOToTurno(previous)));
			listaTurno.add(DataMapperTurno.TurnoDTOToTurno(newData));
			
			//writeFile();
			writeSerializable();
			return true;
		} else {
			return false;
		}
	}
	
	public void writeFile() {
		String content = "";
		for (Turno m : listaTurno) {
			content += m.getId() + ";";
			content += m.getDoctor() + ";";
			content += m.getFecha() + ";";
			content += m.getHora();
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}
	
	public void readFile() {
		String content = FileHandler.readFile(FILE_NAME);
		if (content == "" || content == null) {
			this.listaTurno = new ArrayList<>();
		} else {
			this.listaTurno = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				String[] columns = row.split(";");
			}
		}
	}
	public void writeSerializable() {
		FileHandler.writeSerializable(SERIAL_NAME, listaTurno);
	}
	
	public void readSerializable() {
		Object content = FileHandler.readSerializable(SERIAL_NAME);
		if (content == null) {
			listaTurno = new ArrayList<>();
			listaTurnoActivos = new ArrayList<Turno>();
		} else {
			listaTurno = (ArrayList<Turno>)content;
			listaTurnoActivos =  listaTurno.stream().filter(tur -> tur.isLibre()).collect(Collectors.toCollection(ArrayList::new));
		}
	}
	

}
