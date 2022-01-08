package br.com.glabs.equipsys.despesa.mapper;

import br.com.glabs.equipsys.conta.dto.ContaDTO;
import br.com.glabs.equipsys.conta.entidade.ContaDB;
import br.com.glabs.equipsys.conta.mapper.ContaMapper;
import br.com.glabs.equipsys.despesa.dto.DespesaDTO;
import br.com.glabs.equipsys.despesa.entidade.DespesaDB;
import br.com.glabs.equipsys.fornecedor.dto.FornecedorDTO;
import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import br.com.glabs.equipsys.fornecedor.mapper.FornecedorMapper;
import br.com.glabs.equipsys.obra.dto.ObraDTO;
import br.com.glabs.equipsys.obra.entidade.ObraDB;
import br.com.glabs.equipsys.obra.mapper.ObraMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class DespesaMapper {

    @Autowired
    private FornecedorMapper fornecedorMapper;

    @Autowired
    private ContaMapper contaMapper;

    @Autowired
    private ObraMapper obraMapper;

    @Mappings({
        @Mapping(source = "fornecedor", target = "fornecedor", qualifiedByName = "fornecedorToDTO"),
        @Mapping(source = "obra", target = "obra", qualifiedByName = "obraToDTO"),
        @Mapping(source = "conta", target = "conta", qualifiedByName = "contaToDTO")
    })
    public abstract DespesaDTO toDTO(DespesaDB despesaDB);

    @Named("fornecedorToDTO")
    protected FornecedorDTO fornecedorToDTO(FornecedorDB db){
        return Optional.ofNullable(db).map(fornecedorMapper::toDTO).orElse(null);
    }

    @Named("obraToDTO")
    protected ObraDTO obraToDTO(ObraDB db){
        return Optional.ofNullable(db).map(obraMapper::toDTO).orElse(null);
    }

    @Named("contaToDTO")
    protected ContaDTO contaToDTO(ContaDB db){
        return Optional.ofNullable(db).map(contaMapper::toDTO).orElse(null);
    }
}
