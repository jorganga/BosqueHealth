package model.persistence;

import java.util.ArrayList;
import model.TipoExamen;
import model.TipoExamenDTO;

public class TipoExamenDAO implements CRUDOperation<TipoExamenDTO, TipoExamen>{
	

	private ArrayList<TipoExamen> listaTipoExamen;
	private final String FILE_NAME = "TipoExamen.csv"; // csv: excel txt: texto docx:word
	private final String SERIAL_NAME = "TipoExamen.bin"; // .dat o .bin

	public TipoExamenDAO() {
		FileHandler.checkFolder();
		readFile();
		readSerializable();
	}

	public String showAll() {
		String rta = "";
		if (listaTipoExamen.isEmpty()) {
			return "No hay tipo examen en la lista";
		} else {
			for (TipoExamen TipoExamen : listaTipoExamen) {
				rta += TipoExamen;
			}
			return rta;
		}
	}

	public ArrayList<TipoExamen> getListaTipoExamen() {
		return listaTipoExamen;
	}

	public void setListaTipoExamen(ArrayList<TipoExamen> listaTipoExamen) {
		this.listaTipoExamen = listaTipoExamen;
	}

	@Override
	public ArrayList<TipoExamenDTO> getAll() {
		return DataMapperTipoExamen.listaTipoExamenToListaTipoExamenDTO(listaTipoExamen);
	}

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

	@Override
	public TipoExamen find(TipoExamen toFind) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(TipoExamenDTO previous, TipoExamenDTO newData) {
		// TODO Auto-generated method stub
		return false;
	}

	public void writeFile() {
		String content = "";
		for (TipoExamen m : listaTipoExamen) {
			content += m.getId() + ";";
			content += m.getNombre() + ";";
			content += "\n";
		}
		FileHandler.writeFile(FILE_NAME, content);
	}

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

	public void writeSerializable() {
		FileHandler.writeSerializable(SERIAL_NAME, listaTipoExamen);
	}

	public void readSerializable() {
		Object content = FileHandler.readSerializable(SERIAL_NAME);
		if (content == null) {
			listaTipoExamen = new ArrayList<>();
		} else {
			listaTipoExamen = (ArrayList<TipoExamen>) content;
		}
	}

}
