package br.com.glabs.equipsys.despesa.dto;

import br.com.glabs.equipsys.conta.dto.ContaDTO;
import br.com.glabs.equipsys.fornecedor.dto.FornecedorDTO;
import br.com.glabs.equipsys.obra.dto.
        ObraDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class DespesaDTO implements Serializable {

    private Long id;
    private String descricao;
    private Timestamp dataEmissao;
    private Timestamp dataVencimento;
    private Timestamp dataPagamento;
    private Timestamp dataRealizacaoObraInicio;
    private Timestamp dataRealizacaoObraTermino;
    private BigDecimal valor;
    private String tipo;
    private String situacao;
    private String informacoesAdicionais;
    private FornecedorDTO fornecedor;
    private ObraDTO obra;
    private ContaDTO conta;
    private String numeroNF;
    private String serieNF;

}
