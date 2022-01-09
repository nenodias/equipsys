package br.com.glabs.equipsys.obra.dao;

import br.com.glabs.equipsys.obra.entidade.ObraDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ObraDao extends CrudRepository<ObraDB, Long> {

    @Query("select count(e) from ObraDB e where e.nome like ?1%")
    long countByNome(String nome);

}
