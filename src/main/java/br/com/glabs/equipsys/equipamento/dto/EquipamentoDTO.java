package br.com.glabs.equipsys.equipamento.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class EquipamentoDTO implements Serializable {

    private Long id;
    private String nome;

}
