package model.persistence;

import java.util.ArrayList;

import model.TipoExamen;
import model.TipoExamenDTO;

public class DataMapperTipoExamen {

    /**
     * Convierte un TipoExamenDTO en un TipoExamen.
     */
    public static TipoExamen TipoExamenDTOToTipoExamen(TipoExamenDTO dto) {
        TipoExamen entity;
        entity = new TipoExamen(dto.getId(), dto.getNombre());
        return entity;
    }

    /**
     * Convierte un TipoExamen en un TipoExamenDTO.
     */
    public static TipoExamenDTO TipoExamenToTipoExamenDTO(TipoExamen entity) {
        TipoExamenDTO dto;
        dto = new TipoExamenDTO(entity.getId(), entity.getNombre());
        return dto;
    }

    /**
     * Convierte una lista de TipoExamen a una lista de TipoExamenDTOs.
     */
    public static ArrayList<TipoExamenDTO> listaTipoExamenToListaTipoExamenDTO(ArrayList<TipoExamen> entityList) {
        ArrayList<TipoExamenDTO> dtoList = new ArrayList<>();
        for (TipoExamen m : entityList) {
            dtoList.add(new TipoExamenDTO(m.getId(), m.getNombre()));
        }
        return dtoList;
    }

    /**
     * Convierte una lista de TipoExamenDTOs a una lista de TipoExamen.
     */
    public static ArrayList<TipoExamen> listaTipoExamenDTOToListaTipoExamen(ArrayList<TipoExamenDTO> dtoList) {
        ArrayList<TipoExamen> entityList = new ArrayList<>();
        for (TipoExamenDTO d : dtoList) {
            entityList.add(new TipoExamen(d.getId(), d.getNombre()));
        }
        return entityList;
    }
}

