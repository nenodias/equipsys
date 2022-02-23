package br.com.glabs.equipsys.despesa.dao;

import br.com.glabs.equipsys.despesa.entidade.ParcelaDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ParcelaDao extends CrudRepository<ParcelaDB, Long> {

    Page<ParcelaDB> findAll(Pageable pageable);
}
