package br.com.glabs.equipsys.conta.dao;

import br.com.glabs.equipsys.conta.entidade.ContaDB;
import org.springframework.data.repository.CrudRepository;

public interface ContaDao extends CrudRepository<ContaDB, Long> {

}
