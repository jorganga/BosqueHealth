package model.persistence;

import java.util.ArrayList;
import model.TipoExamen;
import model.TipoExamenDTO;

/**
 * Clase encargada de realizar las operaciones CRUD (Crear, Leer, Actualizar,
 * Eliminar) para los tipos de examen. Utiliza archivos CSV y binarios para la
 * persistencia de los datos.
 */
public class TipoExamenDAO implements CRUDOperation<TipoExamenDTO, TipoExamen> {

	private ArrayList<TipoExamen> listaTipoExamen;
	private final String FILE_NAME = "TipoExamen.csv"; // csv: excel, txt: texto, docx: word
	private final String SERIAL_NAME = "TipoExamen.bin"; // .dat o .bin

	/**
	 * Constructor que verifica la existencia de la carpeta y carga los datos desde
	 * archivos CSV y binarios.
	 */
	public TipoExamenDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}

	/**
	 * Muestra todos los tipos de examen en formato de texto. Si la lista está
	 * vacía, se indica que no hay tipos de examen. Si hay tipos de examen, los
	 * devuelve en formato de texto concatenado.
	 */
	public String showAll() {
		String rta = "";
		if (listaTipoExamen.isEmpty()) {
			return "No hay tipo examen en la lista";
		} else {
			for (TipoExamen tipoExamen : listaTipoExamen) {
				rta += tipoExamen;
			}
			return rta;
		}
	}

	/**
	 * Obtiene la lista de tipos de examen. Devuelve la lista de tipos de examen
	 * almacenada en la clase.
	 */
	public ArrayList<TipoExamen> getListaTipoExamen() {
		return listaTipoExamen;
	}

	/**
	 * Establece la lista de tipos de examen con una nueva lista proporcionada.
	 */
	public void setListaTipoExamen(ArrayList<TipoExamen> listaTipoExamen) {
		this.listaTipoExamen = listaTipoExamen;
	}

	/**
	 * Devuelve todos los tipos de examen como una lista de objetos TipoExamenDTO.
	 * Convierte la lista de objetos TipoExamen en una lista de objetos
	 * TipoExamenDTO.
	 */
	@Override
	public ArrayList<TipoExamenDTO> getAll() {
		return DataMapperTipoExamen.listaTipoExamenToListaTipoExamenDTO(listaTipoExamen);
	}

	/**
	 * Añade un nuevo tipo de examen a la lista de tipos de examen. Verifica que el
	 * tipo de examen no exista en la lista antes de agregarlo. Si el tipo de examen
	 * no está en la lista, se agrega y se guarda el estado.
	 */
	@Override
	public boolean add(TipoExamenDTO newData) {
		if (find(DataMapperTipoExamen.TipoExamenDTOToTipoExamen(newData)) == null) {
			listaTipoExamen.add(DataMapperTipoExamen.TipoExamenDTOToTipoExamen(newData));
			writeFile();
			writeSerializable();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Elimina un tipo de examen de la lista. Busca el tipo de examen por su
	 * identificación y lo elimina si está presente.
	 */
	@Override
	public boolean delete(TipoExamenDTO toDelete) {
		TipoExamen found = find(DataMapperTipoExamen.TipoExamenDTOToTipoExamen(toDelete));
		if (found != null) {
			writeFile();
			writeSerializable();
			return listaTipoExamen.remove(found);
		} else {
			return false;
		}
	}

	/**
	 * Busca un tipo de examen por su identificación en la lista de tipos de examen.
	 * Si encuentra el tipo de examen, lo devuelve, de lo contrario retorna null.
	 */
	@Override
	public TipoExamen find(TipoExamen toFind) {
		// Implementación pendiente
		return null;
	}

	/**
	 * Actualiza los datos de un tipo de examen existente en la lista. Busca el tipo
	 * de examen y, si lo encuentra, lo reemplaza por los nuevos datos
	 * proporcionados.
	 */
	@Override
	public boolean update(TipoExamenDTO previous, TipoExamenDTO newData) {
		// Implementación pendiente
		return false;
	}

	/**
	 * Guarda la lista de tipos de examen en un archivo CSV. Los datos se escriben
	 * en formato de texto con campos separados por punto y coma.
	 */
	public void writeFile() {
		String content = "";
		for (TipoExamen m : listaTipoExamen) {
			content += m.getId() + ";";
			content += m.getNombre() + ";";
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

	/**
	 * Lee los datos de los tipos de examen desde un archivo CSV. Los datos se
	 * separan por líneas y columnas para formar la lista de tipos de examen.
	 */
	public void readFile() {
		String content = FileHandler.readFile(FILE_NAME);
		if (content == "" || content == null) {
			this.listaTipoExamen = new ArrayList<>();
		} else {
			this.listaTipoExamen = new ArrayList<>();
			String[] rows = content.split("\n");
			for (String row : rows) {
				String[] columns = row.split(";");
			}
		}
	}

	/**
	 * Guarda la lista de tipos de examen en un archivo binario serializado. Los
	 * datos se escriben de forma serializada para permitir su recuperación
	 * posterior.
	 */
	public void writeSerializable() {
		FileHandler.writeSerializable(SERIAL_NAME, listaTipoExamen);
	}

	/**
	 * Lee los datos de los tipos de examen desde un archivo binario serializado. Si
	 * el archivo no contiene datos, se inicializa la lista de tipos de examen
	 * vacía.
	 */
	public void readSerializable() {
		Object content = FileHandler.readSerializable(SERIAL_NAME);
		if (content == null) {
			listaTipoExamen = new ArrayList<>();
		} else {
			listaTipoExamen = (ArrayList<TipoExamen>) content;
		}
	}
}
