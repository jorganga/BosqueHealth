package model.persistence;

import java.util.ArrayList;

import model.Paciente;
import model.PacienteDTO;

/**
 * Clase encargada de realizar las operaciones CRUD (Crear, Leer, Actualizar,
 * Eliminar) para los pacientes. Utiliza archivos CSV y binarios para la
 * persistencia de los datos.
 */
public class PacienteDAO implements CRUDOperation<PacienteDTO, Paciente> {

	private ArrayList<Paciente> listaPaciente;
	private final String FILE_NAME = "paciente.csv"; // csv: excel, txt: texto, docx: word
	private final String SERIAL_NAME = "paciente.bin"; // .dat o .bin

	/**
	 * Constructor que verifica la existencia de la carpeta y carga los datos desde
	 * archivos CSV y binarios.
	 */
	public PacienteDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}

	/**
	 * Muestra todos los pacientes en formato de texto. Si la lista está vacía, se
	 * indica que no hay pacientes. Si hay pacientes, los devuelve en formato de
	 * texto concatenado.
	 */
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

	/**
	 * Obtiene la lista de pacientes. Devuelve la lista de pacientes almacenada en
	 * la clase.
	 */
	public ArrayList<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	/**
	 * Establece la lista de pacientes con una nueva lista proporcionada.
	 */
	public void setListaPaciente(ArrayList<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	/**
	 * Devuelve todos los pacientes como una lista de objetos PacienteDTO. Convierte
	 * la lista de objetos Paciente en una lista de objetos PacienteDTO.
	 */
	@Override
	public ArrayList<PacienteDTO> getAll() {
		return DataMapperPaciente.listaPacienteToListaPacienteDTO(listaPaciente);
	}

	/**
	 * Añade un nuevo paciente a la lista de pacientes. Verifica que el paciente no
	 * exista en la lista antes de agregarlo. Si el paciente no está en la lista, se
	 * agrega y se guarda el estado.
	 */
	@Override
	public boolean add(PacienteDTO newData) {
		if (find(DataMapperPaciente.PacienteDTOToPaciente(newData)) == null) {
			listaPaciente.add(DataMapperPaciente.PacienteDTOToPaciente(newData));
			// writeFile();
			writeSerializable();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Elimina un paciente de la lista. Busca el paciente por su identificación y lo
	 * elimina si está presente.
	 */
	@Override
	public boolean delete(PacienteDTO toDelete) {
		Paciente found = find(DataMapperPaciente.PacienteDTOToPaciente(toDelete));
		if (found != null) {
			// writeFile();
			writeSerializable();
			return listaPaciente.remove(found);
		} else {
			return false;
		}
	}

	/**
	 * Busca un paciente por su identificación en la lista de pacientes. Si
	 * encuentra el paciente, lo devuelve, de lo contrario retorna null.
	 */
	@Override
	public Paciente find(Paciente toFind) {
		Paciente found = null;
		if (!listaPaciente.isEmpty()) {
			for (Paciente paciente : listaPaciente) {
				if (paciente.getIdentificacion().equals(toFind.getIdentificacion())) {
					found = paciente;
					// writeFile();
					return found;
				} else {
					continue; // las siguientes líneas después de continue no se ejecutan, se salta a la
								// siguiente iteración
				}
			}
		} else {
			return null;
		}
		return null;
	}

	/**
	 * Actualiza los datos de un paciente existente en la lista. Busca el paciente
	 * y, si lo encuentra, lo reemplaza por los nuevos datos proporcionados.
	 */
	@Override
	public boolean update(PacienteDTO previous, PacienteDTO newData) {
		Paciente found = find(DataMapperPaciente.PacienteDTOToPaciente(previous));
		if (found != null) {
			listaPaciente.remove(found);
			listaPaciente.add(DataMapperPaciente.PacienteDTOToPaciente(newData));
			// writeFile();
			writeSerializable();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Guarda la lista de pacientes en un archivo CSV. Los datos se escriben en
	 * formato de texto con campos separados por punto y coma.
	 */
	public void writeFile() {
		String content = "";
		for (Paciente m : listaPaciente) {
			content += m.getIdentificacion() + ";";
			content += m.getNombre() + ";";
			content += m.getTipoSangre() + ";";
			content += m.getPeso() + ";";
			content += m.getFechaNacimiento() + ";";
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	/**
	 * Lee los datos de los pacientes desde un archivo CSV. Los datos se separan por
	 * líneas y columnas para formar la lista de pacientes.
	 */
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

	/**
	 * Guarda la lista de pacientes en un archivo binario serializado. Los datos se
	 * escriben de forma serializada para permitir su recuperación posterior.
	 */
	public void writeSerializable() {
		FileHandler.writeSerializable(SERIAL_NAME, listaPaciente);
	}

	/**
	 * Lee los datos de los pacientes desde un archivo binario serializado. Si el
	 * archivo no contiene datos, se inicializa la lista de pacientes vacía.
	 */
	public void readSerializable() {
		Object content = FileHandler.readSerializable(SERIAL_NAME);
		if (content == null) {
			listaPaciente = new ArrayList<>();
		} else {
			listaPaciente = (ArrayList<Paciente>) content;
		}
	}
}
