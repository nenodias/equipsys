package br.com.glabs.equipsys.fornecedor.mapper;

import br.com.glabs.equipsys.fornecedor.dto.FornecedorDTO;
import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import org.mapstruct.Mapper;

@Mapper
public interface FornecedorMapper {
    FornecedorDTO toDTO(FornecedorDB fornecedorDB);
}
