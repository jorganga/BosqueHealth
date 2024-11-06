package model.persistence;

import java.util.ArrayList;

import model.Cita;
import model.CitaDTO;
import model.Cita;
import model.CitaDTO;
import model.Turno;

public class CitaDAO implements CRUDOperation<CitaDTO, Cita>{

	private ArrayList<Cita> listaCitas;
	private final String FILE_NAME = "cita.csv"; // csv: excel txt: texto docx:word
	private final String SERIAL_NAME = "cita.bin"; // .dat o .bin
	
	
	
	public CitaDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}

	@Override
	public String showAll() {
		String rta = "";
		if (listaCitas.isEmpty()) {
			return "No hay Citas en la lista";
		} else {
			for (Cita Cita : listaCitas) {
				rta += Cita;
			}
			return rta;
		}
	}

	@Override
	public ArrayList<CitaDTO> getAll() {
		return DataMapperCita.listaCitaToListaCitaDTO(listaCitas);

	}

	@Override
	public boolean add(CitaDTO newData) {
		if (find(DataMapperCita.CitaDTOToCita(newData)) == null) {
			listaCitas.add(DataMapperCita.CitaDTOToCita(newData));
			//writeFile();
			writeSerializable();
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean delete(CitaDTO toDelete) {
		Cita found = find(DataMapperCita.CitaDTOToCita(toDelete));
		if (found != null) {
			writeFile();
			writeSerializable();
			return listaCitas.remove(found);
		} else {
			return false;
		}
	}

	@Override
	public Cita find(Cita toFind) {

		Cita found = null;
		if (!listaCitas.isEmpty()) {
			for (Cita Cita : listaCitas) {
				if (Cita.getId().equals(toFind.getId())) {
					found = Cita;
					writeFile();
					return found;
				} else {
					continue; // las sig lineas desps de continue no se ejecutan, saltan a la sig iteracion
				}
			}
		} else {
			return null;
		}
		return null;
	}

	@Override
	public boolean update(CitaDTO previous, CitaDTO newData) {
		Cita found = find(DataMapperCita.CitaDTOToCita(previous));
		if (found != null) {
			listaCitas.remove(found);
			listaCitas.add(DataMapperCita.CitaDTOToCita(newData));
			//writeFile();
			writeSerializable();
			return true;
		} else {
			return false;
		}
	}

	public void writeFile() {
		String content = "";
		for (Cita m : listaCitas) {
			content += m.getTurnito() + ";";
			content += m.getId() + ";";
			content += m.getEstado() + ";";			
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	public void readFile() {
		String content = FileHandler.readFile(FILE_NAME);
		if (content == "" || content == null) {
			this.listaCitas = new ArrayList<>();
		} else {
			this.listaCitas = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				String[] columns = row.split(";");
			}
		}
	}

	public void writeSerializable() {
		FileHandler.writeSerializable(SERIAL_NAME, listaCitas);
	}

	public void readSerializable() {
		Object content = FileHandler.readSerializable(SERIAL_NAME);
		if (content == null) {
			listaCitas = new ArrayList<>();
		} else {
			listaCitas = (ArrayList<Cita>) content;
		}
	}

}
