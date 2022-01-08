package br.com.glabs.equipsys.despesa.dao;

import br.com.glabs.equipsys.despesa.entidade.DespesaDB;
import org.springframework.data.repository.CrudRepository;

public interface DespesaDao extends CrudRepository<DespesaDB, Long> {
}
