package br.com.glabs.equipsys.despesa.dto;

import br.com.glabs.equipsys.despesa.entidade.enums.SituacaoPagamentoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class DespesaParcelaDTO implements Serializable {

    private Long id;
    private String comentario;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private BigDecimal valor;
    private Integer parcela;
    private SituacaoPagamentoEnum situacao;
    private String numeroNF;
    private String serieNF;

}
