package br.com.glabs.equipsys.equipamento.dao;

import br.com.glabs.equipsys.equipamento.entidade.EquipamentoDB;
import org.springframework.data.repository.CrudRepository;

public interface EquipamentoDao extends CrudRepository<EquipamentoDB, Long> {
}
