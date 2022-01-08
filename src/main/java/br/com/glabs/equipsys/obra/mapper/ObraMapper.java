package br.com.glabs.equipsys.obra.mapper;

import br.com.glabs.equipsys.obra.dto.ObraDTO;
import br.com.glabs.equipsys.obra.entidade.ObraDB;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObraMapper {
    ObraDTO toDTO(ObraDB obraDB);
}
