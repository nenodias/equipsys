package br.com.glabs.equipsys.fornecedor.entidade;

import javax.persistence.*;

@Table
public class FornecedorDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long cnpj;

    @Column
    private String nome;

}
