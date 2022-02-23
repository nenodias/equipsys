package br.com.glabs.equipsys.despesa.mapper;

import br.com.glabs.equipsys.despesa.dao.DespesaDao;
import br.com.glabs.equipsys.despesa.dto.ParcelaDTO;
import br.com.glabs.equipsys.despesa.entidade.DespesaDB;
import br.com.glabs.equipsys.despesa.entidade.ParcelaDB;
import br.com.glabs.equipsys.despesa.entidade.enums.SituacaoPagamentoEnum;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class ParcelaMapper {

    private DespesaDao despesaDao;

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "comentario", target = "comentario"),
            @Mapping(source = "dataEmissao", target = "dataEmissao"),
            @Mapping(source = "dataVencimento", target = "dataVencimento"),
            @Mapping(source = "dataPagamento", target = "dataPagamento"),
            @Mapping(source = "valor", target = "valor"),
            @Mapping(source = "parcela", target = "parcela"),
            @Mapping(source = "situacao", target = "situacao", qualifiedByName = "situacaoPagamentoEnumToString"),
            @Mapping(source = "numeroNF", target = "numeroNF"),
            @Mapping(source = "serieNF", target = "serieNF"),
            @Mapping(source = "despesa", target = "despesa", qualifiedByName = "despesaToLong"),
    })
    public abstract ParcelaDTO toDTO(ParcelaDB parcelaDB);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "comentario", target = "comentario"),
            @Mapping(source = "dataEmissao", target = "dataEmissao"),
            @Mapping(source = "dataVencimento", target = "dataVencimento"),
            @Mapping(source = "dataPagamento", target = "dataPagamento"),
            @Mapping(source = "valor", target = "valor"),
            @Mapping(source = "parcela", target = "parcela"),
            @Mapping(source = "situacao", target = "situacao", qualifiedByName = "stringToSituacaoPagamentoEnum"),
            @Mapping(source = "numeroNF", target = "numeroNF"),
            @Mapping(source = "serieNF", target = "serieNF"),
            @Mapping(source = "despesa", target = "despesa", ignore = true),
    })
    public abstract ParcelaDB toModel(ParcelaDTO despesaDTO);

    @Named("situacaoPagamentoEnumToString")
    protected String situacaoPagamentoEnumToString(SituacaoPagamentoEnum situacaoPagamentoEnum) {
        return Optional.ofNullable(situacaoPagamentoEnum)
                .map(SituacaoPagamentoEnum::name)
                .orElse(null);
    }

    @Named("stringToSituacaoPagamentoEnum")
    protected SituacaoPagamentoEnum stringToSituacaoPagamentoEnum(String value) {
        return SituacaoPagamentoEnum.findByName(value);
    }

    @Named("despesaToLong")
    protected Long despesaToLong(DespesaDB despesaDB) {
        return Optional.ofNullable(despesaDB)
                .map(DespesaDB::getId)
                .orElse(null);
    }

    @AfterMapping
    protected void afterMapping(@MappingTarget ParcelaDB parcelaDB, ParcelaDTO dto) {
        Optional.ofNullable(dto)
                .map(ParcelaDTO::getDespesa)
                .flatMap(this::longToDespesa)
                .ifPresent(parcelaDB::setDespesa);
    }


    protected Optional<DespesaDB> longToDespesa(Long despesa) {
        return Optional.ofNullable(despesa)
                .map(despesaDao::findById)
                .orElse(null);
    }

}
