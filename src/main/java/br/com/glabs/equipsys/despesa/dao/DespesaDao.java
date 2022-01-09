package br.com.glabs.equipsys.despesa.dao;

import br.com.glabs.equipsys.despesa.entidade.DespesaDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DespesaDao extends CrudRepository<DespesaDB, Long> {

    Page<DespesaDB> findAll(Pageable pageable);

    Page<DespesaDB> findAllByDescricaoContains(String descricao, Pageable pageable);
}
