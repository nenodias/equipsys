package br.com.glabs.equipsys.despesa.entidade;

import br.com.glabs.equipsys.despesa.entidade.enums.SituacaoPagamentoEnum;
import br.com.glabs.equipsys.despesa.entidade.enums.TipoDespesaEnum;
import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import br.com.glabs.equipsys.obra.entidade.ObraDB;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Table
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

    @Column
    private TipoDespesaEnum tipo;

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

    @Column
    private String numeroNF;

    @Column
    private String serieNF;

}
