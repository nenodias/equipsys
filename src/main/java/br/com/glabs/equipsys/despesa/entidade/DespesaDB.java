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
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@Table
@Entity
public class DespesaDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Column
    private Timestamp dataEmissao;

    @Column
    private Timestamp dataVencimento;

    @Column
    private Timestamp dataPagamento;

    @Column
    private Timestamp dataRealizacaoObraInicio;

    @Column
    private Timestamp dataRealizacaoObraTermino;

    @Column
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoDespesaEnum tipo;

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
