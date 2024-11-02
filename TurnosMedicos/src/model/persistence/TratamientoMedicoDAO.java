package model.persistence;

import java.util.ArrayList;

import model.TratamientoMedico;
import model.TratamientoMedicoDTO;

public class TratamientoMedicoDAO implements CRUDOperation<TratamientoMedicoDTO, TratamientoMedico>{
	
	private ArrayList<TratamientoMedico> listaTratamientoMedico;
	private final String FILE_NAME = "TratamientoMedico.csv"; // csv: excel txt: texto docx:word
	private final String SERIAL_NAME = "TratamientoMedico.bin"; // .dat o .bin

	public TratamientoMedicoDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}

	public String showAll() {
		String rta = "";
		if (listaTratamientoMedico.isEmpty()) {
			return "No hay tipo examen en la lista";
		} else {
			for (TratamientoMedico TratamientoMedico : listaTratamientoMedico) {
				rta += TratamientoMedico;
			}
			return rta;
		}
	}

	public ArrayList<TratamientoMedico> getListaTratamientoMedico() {
		return listaTratamientoMedico;
	}

	public void setListaTratamientoMedico(ArrayList<TratamientoMedico> listaTratamientoMedico) {
		this.listaTratamientoMedico = listaTratamientoMedico;
	}

	@Override
	public ArrayList<TratamientoMedicoDTO> getAll() {
		return DataMapperTratamientoMedico.listaTratamientoMedicoToListaTratamientoMedicoDTO(listaTratamientoMedico);
	}

	@Override
	public boolean add(TratamientoMedicoDTO newData) {
		if (find(DataMapperTratamientoMedico.TratamientoMedicoDTOToTratamientoMedico(newData)) == null) {
			listaTratamientoMedico.add(DataMapperTratamientoMedico.TratamientoMedicoDTOToTratamientoMedico(newData));
			writeFile();
			writeSerializable();
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean delete(TratamientoMedicoDTO toDelete) {
		TratamientoMedico found = find(DataMapperTratamientoMedico.TratamientoMedicoDTOToTratamientoMedico(toDelete));
		if (found != null) {
			writeFile();
			writeSerializable();
			return listaTratamientoMedico.remove(found);
		} else {
			return false;
		}
	}

	@Override
	public TratamientoMedico find(TratamientoMedico toFind) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(TratamientoMedicoDTO previous, TratamientoMedicoDTO newData) {
		// TODO Auto-generated method stub
		return false;
	}

	public void writeFile() {
		String content = "";
		for (TratamientoMedico m : listaTratamientoMedico) {
			content += m.getId() + ";";
			content += m.getFecha() + ";";
			content += m.getPaciente() + ";";
			content += m.getMedico() + ";";
			content += m.getDescripcion() + ";";
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	public void readFile() {
		String content = FileHandler.readFile(FILE_NAME);
		if (content == "" || content == null) {
			this.listaTratamientoMedico = new ArrayList<>();
		} else {
			this.listaTratamientoMedico = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				String[] columns = row.split(";");
			}
		}
	}

	public void writeSerializable() {
		FileHandler.writeSerializable(SERIAL_NAME, listaTratamientoMedico);
	}

	public void readSerializable() {
		Object content = FileHandler.readSerializable(SERIAL_NAME);
		if (content == null) {
			listaTratamientoMedico = new ArrayList<>();
		} else {
			listaTratamientoMedico = (ArrayList<TratamientoMedico>) content;
		}
	}

}
