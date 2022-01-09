package br.com.glabs.equipsys.conta.dao;

import br.com.glabs.equipsys.conta.entidade.ContaDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ContaDao extends CrudRepository<ContaDB, Long> {

    Page<ContaDB> findAllByNomeContains(String nome, Pageable pageable);

    Page<ContaDB> findAll(Pageable pageable);
}
