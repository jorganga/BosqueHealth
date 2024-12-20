package model.persistence;

import java.util.ArrayList;
import java.util.stream.Collectors;

import model.Cita;
import model.CitaDTO;
import model.Cita;
import model.CitaDTO;
import model.Turno;
/**
 * Esta clase maneja operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las citas.
 * Utiliza archivos CSV y binarios para la persistencia de datos.
 */
public class CitaDAO implements CRUDOperation<CitaDTO, Cita>{

	private ArrayList<Cita> listaCitas;
	private ArrayList<Cita> listaCitasActivas;
	private final String FILE_NAME = "cita.csv"; // csv: excel txt: texto docx:word
	private final String SERIAL_NAME = "cita.bin"; // .dat o .bin
	
	
	/**
     * Constructor de la clase CitaDAO.
     * Verifica la existencia de la carpeta y carga los datos desde archivos CSV y binarios.
     */
	public CitaDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}
	/**
     * Muestra todas las citas en formato de texto.
     */
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
	/**
     * Devuelve todas las citas como una lista de CitaDTO.
     */
	@Override
	public ArrayList<CitaDTO> getAll() {
		return DataMapperCita.listaCitaToListaCitaDTO(listaCitas);

	}
	/**
     * Devuelve solo las citas activas como una lista de CitaDTO.
     */
	public ArrayList<CitaDTO> getAllActiva() {
		return DataMapperCita.listaCitaToListaCitaDTO(listaCitasActivas);
		
	}
	/**
     * Añade una nueva cita a la lista.
     * Verifica que la cita no exista antes de agregarla.
     */
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
	/**
     * Elimina una cita de la lista.
     */
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
	/**
     * Busca una cita por su ID.
     */
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
	 /**
     * Actualiza una cita existente con nuevos datos.
     */
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
	 /**
     * Guarda las citas en un archivo CSV.
     */
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
			listaCitasActivas = new ArrayList<>();
		} else {
			listaCitas = (ArrayList<Cita>) content;
			listaCitasActivas =  listaCitas.stream().filter(cit -> cit.getEstado().equals("activo")).collect(Collectors.toCollection(ArrayList::new));
		}
	}

}
