package br.com.glabs.equipsys.obra.dao;

import br.com.glabs.equipsys.obra.entidade.ObraDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ObraDao extends CrudRepository<ObraDB, Long> {

    Page<ObraDB> findAllByNomeContains(String nome, Pageable pageable);

    Page<ObraDB> findAll(Pageable pageable);

}
