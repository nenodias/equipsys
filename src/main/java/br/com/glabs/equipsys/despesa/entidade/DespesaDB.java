package br.com.glabs.equipsys.despesa.entidade;

import br.com.glabs.equipsys.conta.entidade.ContaDB;
import br.com.glabs.equipsys.despesa.entidade.enums.SituacaoPagamentoEnum;
import br.com.glabs.equipsys.despesa.entidade.enums.TipoDespesaEnum;
import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import br.com.glabs.equipsys.obra.entidade.ObraDB;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Table
@Entity
public class DespesaDB implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Column
    private LocalDateTime dataEmissao;

    @Column
    private LocalDateTime dataVencimento;

    @Column
    private LocalDateTime dataPagamento;

    @Column
    private LocalDateTime dataRealizacaoObraInicio;

    @Column
    private LocalDateTime dataRealizacaoObraTermino;

    @Column
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoDespesaEnum tipo;

    @Column
    private Integer parcela;

    @Enumerated(EnumType.STRING)
    @Column
    private SituacaoPagamentoEnum situacao;

    @Column
    private String informacoesAdicionais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private FornecedorDB fornecedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ObraDB obra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ContaDB conta;

    @Column
    private String numeroNF;

    @Column
    private String serieNF;

}
