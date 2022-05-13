package br.com.glabs.equipsys.despesa.mapper;

import br.com.glabs.equipsys.conta.dao.ContaDao;
import br.com.glabs.equipsys.conta.dto.ContaDTO;
import br.com.glabs.equipsys.conta.entidade.ContaDB;
import br.com.glabs.equipsys.conta.mapper.ContaMapper;
import br.com.glabs.equipsys.despesa.dao.ParcelaDao;
import br.com.glabs.equipsys.despesa.dto.DespesaDTO;
import br.com.glabs.equipsys.despesa.dto.DespesaParcelaDTO;
import br.com.glabs.equipsys.despesa.entidade.DespesaDB;
import br.com.glabs.equipsys.despesa.entidade.ParcelaDB;
import br.com.glabs.equipsys.despesa.entidade.enums.SituacaoDespesaEnum;
import br.com.glabs.equipsys.despesa.entidade.enums.TipoDespesaEnum;
import br.com.glabs.equipsys.fornecedor.dao.FornecedorDao;
import br.com.glabs.equipsys.fornecedor.dto.FornecedorDTO;
import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import br.com.glabs.equipsys.fornecedor.mapper.FornecedorMapper;
import br.com.glabs.equipsys.obra.dao.ObraDao;
import br.com.glabs.equipsys.obra.dto.ObraDTO;
import br.com.glabs.equipsys.obra.entidade.ObraDB;
import br.com.glabs.equipsys.obra.mapper.ObraMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class DespesaMapper {

    @Autowired
    private FornecedorMapper fornecedorMapper;

    @Autowired
    private FornecedorDao fornecedorDao;

    @Autowired
    private ContaMapper contaMapper;

    @Autowired
    private ContaDao contaDao;

    @Autowired
    private ObraMapper obraMapper;

    @Autowired
    private ObraDao obraDao;

    @Autowired
    private DespesaParcelaMapper despesaParcelaMapper;

    @Autowired
    private ParcelaDao parcelaDao;

    @Mappings({
            @Mapping(source = "fornecedor", target = "fornecedor", qualifiedByName = "fornecedorToDTO"),
            @Mapping(source = "obra", target = "obra", qualifiedByName = "obraToDTO"),
            @Mapping(source = "conta", target = "conta", qualifiedByName = "contaToDTO"),
            @Mapping(source = "tipo", target = "tipo", qualifiedByName = "tipoDespesaEnumToString"),
            @Mapping(source = "situacao", target = "situacao", qualifiedByName = "situacaoPagamentoEnumToString"),
            @Mapping(source = "parcelas", target = "parcelas", qualifiedByName = "parcelasToDTO"),
            @Mapping(source = "valorRecebido", target = "valorRecebido"),
            @Mapping(source = "valorTotal", target = "valorTotal"),
    })
    public abstract DespesaDTO toDTO(DespesaDB despesaDB);

    @Named(value = "fornecedorToDTO")
    protected FornecedorDTO fornecedorToDTO(FornecedorDB db) {
        return Optional.ofNullable(db).map(fornecedorMapper::toDTO).orElse(null);
    }

    @Named(value = "obraToDTO")
    protected ObraDTO obraToDTO(ObraDB db) {
        return Optional.ofNullable(db).map(obraMapper::toDTO).orElse(null);
    }

    @Named(value = "contaToDTO")
    protected ContaDTO contaToDTO(ContaDB db) {
        return Optional.ofNullable(db).map(contaMapper::toDTO).orElse(null);
    }

    @Mappings({
            @Mapping(source = "fornecedor", target = "fornecedor", qualifiedByName = "fornecedorToModel"),
            @Mapping(source = "obra", target = "obra", qualifiedByName = "obraToModel"),
            @Mapping(source = "conta", target = "conta", qualifiedByName = "contaToModel"),
            @Mapping(source = "tipo", target = "tipo", qualifiedByName = "stringToTipoDespesaEnum"),
            @Mapping(source = "situacao", target = "situacao", qualifiedByName = "stringToSituacaoPagamentoEnum"),
            @Mapping(source = "parcelas", target = "parcelas", qualifiedByName = "parcelasToModel"),
            @Mapping(source = "valorRecebido", target = "valorRecebido"),
            @Mapping(source = "valorTotal", target = "valorTotal")
    })
    public abstract DespesaDB toModel(DespesaDTO despesaDTO);

    @Named(value = "parcelasToDTO")
    protected List<DespesaParcelaDTO> parcelasToDTO(List<ParcelaDB> parcelas) {
        return Optional.ofNullable(parcelas).orElseGet(ArrayList::new)
                .stream()
                .map(despesaParcelaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Named(value = "parcelasToModel")
    protected List<ParcelaDB> parcelasToModel(List<DespesaParcelaDTO> parcelas) {
        List<ParcelaDB> collect = Optional.ofNullable(parcelas)
                .orElseGet(ArrayList::new)
                .stream()
                .map(despesaParcelaMapper::toModel)
                .collect(Collectors.toList());
        return collect;
    }

    @Named(value = "fornecedorToModel")
    protected FornecedorDB fornecedorToModel(FornecedorDTO dto) {
        return Optional.ofNullable(dto)
                .map(FornecedorDTO::getId)
                .map(fornecedorDao::findById)
                .map(Optional::get)
                .orElse(null);
    }

    @Named(value = "obraToModel")
    protected ObraDB obraToModel(ObraDTO dto) {
        return Optional.ofNullable(dto)
                .map(ObraDTO::getId)
                .map(obraDao::findById)
                .map(Optional::get)
                .orElse(null);
    }

    @Named(value = "contaToModel")
    protected ContaDB contaToModel(ContaDTO dto) {
        return Optional.ofNullable(dto)
                .map(ContaDTO::getId)
                .map(contaDao::findById)
                .map(Optional::get)
                .orElse(null);
    }

    @Named(value = "situacaoPagamentoEnumToString")
    protected String situacaoPagamentoEnumToString(SituacaoDespesaEnum situacaoDespesaEnum) {
        return Optional.ofNullable(situacaoDespesaEnum)
                .map(SituacaoDespesaEnum::name)
                .orElse(null);
    }

    @Named(value = "stringToSituacaoPagamentoEnum")
    protected SituacaoDespesaEnum stringToSituacaoPagamentoEnum(String value) {
        return SituacaoDespesaEnum.findByName(value);
    }

    @Named(value = "tipoDespesaEnumToString")
    protected String tipoDespesaEnumToString(TipoDespesaEnum tipoDespesaEnumToString) {
        return Optional.ofNullable(tipoDespesaEnumToString)
                .map(TipoDespesaEnum::name)
                .orElse(null);
    }

    @Named(value = "stringToTipoDespesaEnum")
    protected TipoDespesaEnum stringToTipoDespesaEnum(String value) {
        return TipoDespesaEnum.findByName(value);
    }

    @AfterMapping
    protected void afterMapping(@MappingTarget DespesaDB despesaDB) {
        Consumer<ParcelaDB> consumer = i -> i.setDespesa(despesaDB);
        Optional.ofNullable(despesaDB)
                .map(DespesaDB::getParcelas).orElseGet(ArrayList::new)
                .forEach(consumer);
    }

    @AfterMapping
    protected void afterMapping(@MappingTarget DespesaDTO despesaDTO) {
        if(Objects.isNull(despesaDTO.getValorRecebido())){
            despesaDTO.setValorRecebido(BigDecimal.ZERO);
        }
        if(Objects.isNull(despesaDTO.getValorTotal())){
            despesaDTO.setValorTotal(BigDecimal.ZERO);
        }
    }
}
