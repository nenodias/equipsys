package br.com.glabs.equipsys.equipamento.dao;

import br.com.glabs.equipsys.equipamento.entidade.EquipamentoDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EquipamentoDao extends CrudRepository<EquipamentoDB, Long> {

    @Query("select count(e) from EquipamentoDB e where e.nome like ?1%")
    long countByNome(String nome);

}
