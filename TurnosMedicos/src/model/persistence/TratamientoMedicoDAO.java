package model.persistence;

import java.util.ArrayList;

import model.TratamientoMedico;
import model.TratamientoMedicoDTO;

/**
 * Clase encargada de realizar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para los tratamientos médicos.
 * Utiliza archivos CSV y binarios para la persistencia de los datos.
 */
public class TratamientoMedicoDAO implements CRUDOperation<TratamientoMedicoDTO, TratamientoMedico> {
	
	private ArrayList<TratamientoMedico> listaTratamientoMedico;
	private final String FILE_NAME = "TratamientoMedico.csv"; // csv: excel, txt: texto, docx: word
	private final String SERIAL_NAME = "TratamientoMedico.bin"; // .dat o .bin

	/**
	 * Constructor que verifica la existencia de la carpeta y carga los datos desde archivos CSV y binarios.
	 */
	public TratamientoMedicoDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}

	/**
	 * Muestra todos los tratamientos médicos en formato de texto.
	 * Si la lista está vacía, se indica que no hay tratamientos médicos.
	 * Si hay tratamientos médicos, los devuelve en formato de texto concatenado.
	 */
	public String showAll() {
		String rta = "";
		if (listaTratamientoMedico.isEmpty()) {
			return "No hay tipo examen en la lista";
		} else {
			for (TratamientoMedico tratamientoMedico : listaTratamientoMedico) {
				rta += tratamientoMedico;
			}
			return rta;
		}
	}

	/**
	 * Obtiene la lista de tratamientos médicos.
	 * Devuelve la lista de tratamientos médicos almacenada en la clase.
	 */
	public ArrayList<TratamientoMedico> getListaTratamientoMedico() {
		return listaTratamientoMedico;
	}

	/**
	 * Establece la lista de tratamientos médicos con una nueva lista proporcionada.
	 */
	public void setListaTratamientoMedico(ArrayList<TratamientoMedico> listaTratamientoMedico) {
		this.listaTratamientoMedico = listaTratamientoMedico;
	}

	/**
	 * Devuelve todos los tratamientos médicos como una lista de objetos TratamientoMedicoDTO.
	 * Convierte la lista de objetos TratamientoMedico en una lista de objetos TratamientoMedicoDTO.
	 */
	@Override
	public ArrayList<TratamientoMedicoDTO> getAll() {
		return DataMapperTratamientoMedico.listaTratamientoMedicoToListaTratamientoMedicoDTO(listaTratamientoMedico);
	}

	/**
	 * Añade un nuevo tratamiento médico a la lista de tratamientos médicos.
	 * Verifica que el tratamiento médico no exista en la lista antes de agregarlo.
	 * Si el tratamiento médico no está en la lista, se agrega y se guarda el estado.
	 */
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

	/**
	 * Elimina un tratamiento médico de la lista.
	 * Busca el tratamiento médico por su identificación y lo elimina si está presente.
	 */
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

	/**
	 * Busca un tratamiento médico por su identificación en la lista de tratamientos médicos.
	 * Si encuentra el tratamiento médico, lo devuelve, de lo contrario retorna null.
	 */
	@Override
	public TratamientoMedico find(TratamientoMedico toFind) {
		// Implementación pendiente
		return null;
	}

	/**
	 * Actualiza los datos de un tratamiento médico existente en la lista.
	 * Busca el tratamiento médico y, si lo encuentra, lo reemplaza por los nuevos datos proporcionados.
	 */
	@Override
	public boolean update(TratamientoMedicoDTO previous, TratamientoMedicoDTO newData) {
		// Implementación pendiente
		return false;
	}

	/**
	 * Guarda la lista de tratamientos médicos en un archivo CSV.
	 * Los datos se escriben en formato de texto con campos separados por punto y coma.
	 */
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

	/**
	 * Lee los datos de los tratamientos médicos desde un archivo CSV.
	 * Los datos se separan por líneas y columnas para formar la lista de tratamientos médicos.
	 */
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

	/**
	 * Guarda la lista de tratamientos médicos en un archivo binario serializado.
	 * Los datos se escriben de forma serializada para permitir su recuperación posterior.
	 */
	public void writeSerializable() {
		FileHandler.writeSerializable(SERIAL_NAME, listaTratamientoMedico);
	}

	/**
	 * Lee los datos de los tratamientos médicos desde un archivo binario serializado.
	 * Si el archivo no contiene datos, se inicializa la lista de tratamientos médicos vacía.
	 */
	public void readSerializable() {
		Object content = FileHandler.readSerializable(SERIAL_NAME);
		if (content == null) {
			listaTratamientoMedico = new ArrayList<>();
		} else {
			listaTratamientoMedico = (ArrayList<TratamientoMedico>) content;
		}
	}
}
