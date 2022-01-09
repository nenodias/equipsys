package br.com.glabs.equipsys.despesa.dto;

import br.com.glabs.equipsys.conta.dto.ContaDTO;
import br.com.glabs.equipsys.fornecedor.dto.FornecedorDTO;
import br.com.glabs.equipsys.obra.dto.
        ObraDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class DespesaDTO implements Serializable {

    private Long id;
    private String descricao;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataRealizacaoObraInicio;
    private LocalDateTime dataRealizacaoObraTermino;
    private BigDecimal valor;
    private String tipo;
    private Integer parcela;
    private String situacao;
    private String informacoesAdicionais;
    private FornecedorDTO fornecedor;
    private ObraDTO obra;
    private ContaDTO conta;
    private String numeroNF;
    private String serieNF;

}
