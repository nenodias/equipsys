package br.com.glabs.equipsys.despesa.dto;

import br.com.glabs.equipsys.conta.dto.ContaDTO;
import br.com.glabs.equipsys.fornecedor.dto.FornecedorDTO;
import br.com.glabs.equipsys.obra.dto.ObraDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class DespesaDTO implements Serializable {

    private Long id;
    private String descricao;
    private LocalDateTime dataRealizacaoObraInicio;
    private LocalDateTime dataRealizacaoObraTermino;
    private String tipo;
    private String situacao;
    private String informacoesAdicionais;
    private FornecedorDTO fornecedor;
    private ObraDTO obra;
    private ContaDTO conta;
    private List<DespesaParcelaDTO> parcelas;
    private BigDecimal valorTotal;
    private BigDecimal valorRecebido;

}
