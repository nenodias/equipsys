package br.com.glabs.equipsys.equipamento.dao;

import br.com.glabs.equipsys.equipamento.entidade.EquipamentoDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EquipamentoDao extends CrudRepository<EquipamentoDB, Long> {

    @Query("select count(e) from EquipamentoDB e where e.nome like %:nome% ")
    long countByNome(@Param("nome") String nome);

}
