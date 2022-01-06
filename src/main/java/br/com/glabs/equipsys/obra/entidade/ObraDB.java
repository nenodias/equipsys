package br.com.glabs.equipsys.obra.entidade;

import javax.persistence.*;

@Table
public class ObraDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

}
