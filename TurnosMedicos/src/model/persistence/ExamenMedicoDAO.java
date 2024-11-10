package model.persistence;

import java.util.ArrayList;
import model.ExamenMedico;
import model.ExamenMedicoDTO;

/**
 * Esta clase maneja operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para
 * los exámenes médicos. Utiliza archivos CSV y binarios para la persistencia de
 * datos.
 */
public class ExamenMedicoDAO implements CRUDOperation<ExamenMedicoDTO, ExamenMedico> {

	private ArrayList<ExamenMedico> listaExamenMedico;
	private final String FILE_NAME = "examenMedico.csv"; // csv: excel txt: texto docx:word
	private final String SERIAL_NAME = "examenMedico.bin"; // .dat o .bin

	/**
	 * Constructor de la clase ExamenMedicoDAO. Verifica la existencia de la carpeta
	 * y carga los datos desde archivos CSV y binarios.
	 */
	public ExamenMedicoDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}

	/**
	 * Muestra todos los exámenes médicos en formato de texto.
	 */
	public String showAll() {
		String rta = "";
		if (listaExamenMedico.isEmpty()) {
			return "No hay exámenes médicos en la lista";
		} else {
			for (ExamenMedico examenMedico : listaExamenMedico) {
				rta += examenMedico;
			}
			return rta;
		}
	}

	/**
	 * Devuelve todos los exámenes médicos como una lista de ExamenMedicoDTO.
	 */
	@Override
	public ArrayList<ExamenMedicoDTO> getAll() {
		return DataMapperExamenMedico.listaExamenMedicoToListaExamenMedicoDTO(listaExamenMedico);
	}

	/**
	 * Añade un nuevo examen médico a la lista. Verifica que el examen médico no
	 * exista antes de agregarlo.
	 */
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

	/**
	 * Elimina un examen médico de la lista.
	 */
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

	/**
	 * Busca un examen médico en la lista.
	 */
	@Override
	public ExamenMedico find(ExamenMedico toFind) {
		// TODO Implementar búsqueda de examen médico
		return null;
	}

	/**
	 * Actualiza un examen médico existente con nuevos datos.
	 */
	@Override
	public boolean update(ExamenMedicoDTO previous, ExamenMedicoDTO newData) {
		return false;
	}

	/**
	 * Guarda los exámenes médicos en un archivo CSV.
	 */
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

	/**
	 * Lee los exámenes médicos desde un archivo CSV.
	 */
	public void readFile() {
		String content = FileHandler.readFile(FILE_NAME);
		if (content == "" || content == null) {
			this.listaExamenMedico = new ArrayList<>();
		} else {
			this.listaExamenMedico = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				String[] columns = row.split(";");
				// Aquí se deben agregar los datos al objeto ExamenMedico, si es necesario
			}
		}
	}

	/**
	 * Guarda los exámenes médicos en un archivo binario.
	 */
	public void writeSerializable() {
		FileHandler.writeSerializable(SERIAL_NAME, listaExamenMedico);
	}

	/**
	 * Lee los exámenes médicos desde un archivo binario.
	 */
	public void readSerializable() {
		Object content = FileHandler.readSerializable(SERIAL_NAME);
		if (content == null) {
			listaExamenMedico = new ArrayList<>();
		} else {
			listaExamenMedico = (ArrayList<ExamenMedico>) content;
		}
	}
}
