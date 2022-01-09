package br.com.glabs.equipsys.conta.dao;

import br.com.glabs.equipsys.conta.entidade.ContaDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ContaDao extends CrudRepository<ContaDB, Long> {
    @Query("select count(e) from ContaDB e where e.nome like ?1%")
    long countByNome(String nome);
}
