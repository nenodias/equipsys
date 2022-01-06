package br.com.glabs.equipsys.equipamento.entidade;

import javax.persistence.*;

@Table
public class EquipamentoDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

}
