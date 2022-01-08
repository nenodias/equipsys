package br.com.glabs.equipsys.fornecedor.entidade;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Table
@Entity
public class FornecedorDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long cnpj;

    @Column
    private String nome;

}
