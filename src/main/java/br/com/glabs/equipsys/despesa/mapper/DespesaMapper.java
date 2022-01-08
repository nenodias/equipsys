package br.com.glabs.equipsys.despesa.mapper;

import br.com.glabs.equipsys.despesa.dto.DespesaDTO;
import br.com.glabs.equipsys.despesa.entidade.DespesaDB;
import org.mapstruct.Mapper;

@Mapper
public interface DespesaMapper {
    DespesaDTO toDTO(DespesaDB despesaDB);
}
