package br.com.glabs.equipsys.conta.mapper;

import br.com.glabs.equipsys.conta.dto.ContaDTO;
import br.com.glabs.equipsys.conta.entidade.ContaDB;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapper {
    ContaDTO toDTO(ContaDB contaDB);

    ContaDB toModel(ContaDTO contaDTO);
}
