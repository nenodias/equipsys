package br.com.glabs.equipsys.conta.dao;

import br.com.glabs.equipsys.conta.entidade.ContaDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContaDao extends CrudRepository<ContaDB, Long> {
    @Query("select count(e) from ContaDB e where e.nome like %:nome% ")
    long countByNome(@Param("nome") String nome);
}
