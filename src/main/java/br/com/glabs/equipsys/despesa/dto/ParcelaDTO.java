package br.com.glabs.equipsys.despesa.dto;

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
public class ParcelaDTO implements Serializable {

    private Long id;
    private String comentario;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private BigDecimal valor;
    private Integer parcela;
    private String situacao;
    private String numeroNF;
    private String serieNF;
    private Long despesa;

}
