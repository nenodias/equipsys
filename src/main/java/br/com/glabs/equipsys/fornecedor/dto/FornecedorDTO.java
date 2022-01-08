package br.com.glabs.equipsys.fornecedor.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class FornecedorDTO {

    private Long id;
    private Long cnpj;
    private String nome;

}
