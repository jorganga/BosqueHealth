package model.persistence;

import java.util.ArrayList;

import model.ExamenMedico;
import model.ExamenMedicoDTO;

public class ExamenMedicoDAO implements CRUDOperation<ExamenMedicoDTO, ExamenMedico>{
	
	private ArrayList<ExamenMedico> listaExamenMedico;
	private final String FILE_NAME = "examenMedico.csv"; // csv: excel txt: texto docx:word
	private final String SERIAL_NAME = "examenMedico.bin"; // .dat o .bin

	public ExamenMedicoDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}

	public String showAll() {
		String rta = "";
		if (listaExamenMedico.isEmpty()) {
			return "No hay examenes medicos en la lista";
		} else {
			for (ExamenMedico ExamenMedico : listaExamenMedico) {
				rta += ExamenMedico;
			}
			return rta;
		}
	}

	public ArrayList<ExamenMedico> getListaExamenMedico() {
		return listaExamenMedico;
	}

	public void setListaExamenMedico(ArrayList<ExamenMedico> listaExamenMedico) {
		this.listaExamenMedico = listaExamenMedico;
	}

	@Override
	public ArrayList<ExamenMedicoDTO> getAll() {
		return DataMapperExamenMedico.listaExamenMedicoToListaExamenMedicoDTO(listaExamenMedico);
	}

	@Override
	public boolean add(ExamenMedicoDTO newData) {
		if (find(DataMapperExamenMedico.ExamenMedicoDTOToExamenMedico(newData)) == null) {
			listaExamenMedico.add(DataMapperExamenMedico.ExamenMedicoDTOToExamenMedico(newData));
			writeFile();
			writeSerializable();
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean delete(ExamenMedicoDTO toDelete) {
		ExamenMedico found = find(DataMapperExamenMedico.ExamenMedicoDTOToExamenMedico(toDelete));
		if (found != null) {
			writeFile();
			writeSerializable();
			return listaExamenMedico.remove(found);
		} else {
			return false;
		}
	}

	@Override
	public ExamenMedico find(ExamenMedico toFind) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ExamenMedicoDTO previous, ExamenMedicoDTO newData) {
		return false;
	}

	public void writeFile() {
		String content = "";
		for (ExamenMedico m : listaExamenMedico) {
			content += m.getId() + ";";
			content += m.getTipo() + ";";
			content += m.getFecha() + ";";
			content += m.getMedico() + ";";
			content += m.getPaciente() + ";";
			content += m.getNota() + ";";
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	public void readFile() {
		String content = FileHandler.readFile(FILE_NAME);
		if (content == "" || content == null) {
			this.listaExamenMedico = new ArrayList<>();
		} else {
			this.listaExamenMedico = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				String[] columns = row.split(";");
			}
		}
	}

	public void writeSerializable() {
		FileHandler.writeSerializable(SERIAL_NAME, listaExamenMedico);
	}

	public void readSerializable() {
		Object content = FileHandler.readSerializable(SERIAL_NAME);
		if (content == null) {
			listaExamenMedico = new ArrayList<>();
		} else {
			listaExamenMedico = (ArrayList<ExamenMedico>) content;
		}
	}

}
