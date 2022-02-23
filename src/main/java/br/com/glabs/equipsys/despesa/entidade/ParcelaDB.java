package br.com.glabs.equipsys.despesa.entidade;

import br.com.glabs.equipsys.despesa.entidade.enums.SituacaoPagamentoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Table
@Entity
public class ParcelaDB implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comentario;

    @Column
    private LocalDateTime dataEmissao;

    @Column
    private LocalDateTime dataVencimento;

    @Column
    private LocalDateTime dataPagamento;

    @Column
    private BigDecimal valor;

    @Column
    private Integer parcela;

    @Enumerated(EnumType.STRING)
    @Column
    private SituacaoPagamentoEnum situacao;

    @Column
    private String numeroNF;

    @Column
    private String serieNF;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DespesaDB despesa;

}
