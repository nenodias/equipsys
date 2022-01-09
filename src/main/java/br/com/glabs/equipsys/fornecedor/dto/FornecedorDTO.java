package br.com.glabs.equipsys.fornecedor.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class FornecedorDTO implements Serializable {

    private Long id;
    private String cnpj;
    private String nome;

}
