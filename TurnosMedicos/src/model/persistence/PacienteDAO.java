package model.persistence;

import java.util.ArrayList;
import model.Paciente;
import model.PacienteDTO;

public class PacienteDAO implements CRUDOperation<PacienteDTO, Paciente> {

	private ArrayList<Paciente> listaPaciente;
	private final String FILE_NAME = "paciente.csv"; // csv: excel txt: texto docx:word
	private final String SERIAL_NAME = "paciente.bin"; // .dat o .bin

	public PacienteDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}

	public String showAll() {
		String rta = "";
		if (listaPaciente.isEmpty()) {
			return "No hay Pacientes en la lista";
		} else {
			for (Paciente paciente : listaPaciente) {
				rta += paciente;
			}
			return rta;
		}
	}

	public ArrayList<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(ArrayList<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	@Override
	public ArrayList<PacienteDTO> getAll() {
		return DataMapperPaciente.listaPacienteToListaPacienteDTO(listaPaciente);
	}

	@Override
	public boolean add(PacienteDTO newData) {
		if (find(DataMapperPaciente.PacienteDTOToPaciente(newData)) == null) {
			listaPaciente.add(DataMapperPaciente.PacienteDTOToPaciente(newData));
			writeFile();
			writeSerializable();
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean delete(PacienteDTO toDelete) {
		Paciente found = find(DataMapperPaciente.PacienteDTOToPaciente(toDelete));
		if (found != null) {
			writeFile();
			writeSerializable();
			return listaPaciente.remove(found);
		} else {
			return false;
		}
	}

	@Override
	public Paciente find(Paciente toFind) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PacienteDTO previous, PacienteDTO newData) {
		// TODO Auto-generated method stub
		return false;
	}

	public void writeFile() {
		String content = "";
		for (Paciente m : listaPaciente) {
			content += m.getNombre() + ";";
			content += m.getId() + ";";
			content += m.getTipoSangre() + ";";
			content += m.getPeso() + ";";
			content += m.getFechaNacimiento() + ";";
			content += m.getCedula() + ";";
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	public void readFile() {
		String content = FileHandler.readFile(FILE_NAME);
		if (content == "" || content == null) {
			this.listaPaciente = new ArrayList<>();
		} else {
			this.listaPaciente = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				String[] columns = row.split(";");
			}
		}
	}

	public void writeSerializable() {
		FileHandler.writeSerializable(SERIAL_NAME, listaPaciente);
	}

	public void readSerializable() {
		Object content = FileHandler.readSerializable(SERIAL_NAME);
		if (content == null) {
			listaPaciente = new ArrayList<>();
		} else {
			listaPaciente = (ArrayList<Paciente>) content;
		}
	}

}
