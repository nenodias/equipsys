package br.com.glabs.equipsys.equipamento.dao;

import br.com.glabs.equipsys.equipamento.entidade.EquipamentoDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface EquipamentoDao extends CrudRepository<EquipamentoDB, Long> {

    Page<EquipamentoDB> findAllByNomeContains(String nome, Pageable pageable);

    Page<EquipamentoDB> findAll(Pageable pageable);

}
