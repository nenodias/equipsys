package br.com.glabs.equipsys.conta.entidade;

import javax.persistence.*;

@Table
public class ContaDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

}
