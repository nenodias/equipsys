package br.com.glabs.equipsys.equipamento.mapper;

import br.com.glabs.equipsys.equipamento.dto.EquipamentoDTO;
import br.com.glabs.equipsys.equipamento.entidade.EquipamentoDB;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper {
    EquipamentoDTO toDTO(EquipamentoDB equipamentoDB);
}
