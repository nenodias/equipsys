package br.com.glabs.equipsys.despesa.entidade;

import br.com.glabs.equipsys.conta.entidade.ContaDB;
import br.com.glabs.equipsys.despesa.entidade.enums.SituacaoDespesaEnum;
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
import java.util.List;

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
    private LocalDateTime dataRealizacaoObraInicio;

    @Column
    private LocalDateTime dataRealizacaoObraTermino;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoDespesaEnum tipo;

    @Enumerated(EnumType.STRING)
    @Column
    private SituacaoDespesaEnum situacao;

    @Lob
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

    @OneToMany(mappedBy = "despesa", fetch = FetchType.LAZY)
    private List<ParcelaDB> parcelas;

    @Column
    private BigDecimal valorTotal;

    @Column
    private BigDecimal valorRecebido;

}
