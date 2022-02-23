package br.com.glabs.equipsys.despesa.mapper;

import br.com.glabs.equipsys.despesa.dto.DespesaParcelaDTO;
import br.com.glabs.equipsys.despesa.entidade.ParcelaDB;
import br.com.glabs.equipsys.despesa.entidade.enums.SituacaoPagamentoEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class DespesaParcelaMapper {

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
    })
    public abstract DespesaParcelaDTO toDTO(ParcelaDB parcelaDB);

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
            @Mapping(target = "despesa", ignore = true),
    })
    public abstract ParcelaDB toModel(DespesaParcelaDTO despesaDTO);

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

}
