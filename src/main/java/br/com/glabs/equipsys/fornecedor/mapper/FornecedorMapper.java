package br.com.glabs.equipsys.fornecedor.mapper;

import br.com.glabs.equipsys.fornecedor.dto.FornecedorDTO;
import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

    FornecedorDTO toDTO(FornecedorDB fornecedorDB);

    FornecedorDB toModel(FornecedorDTO fornecedorDTO);
}
