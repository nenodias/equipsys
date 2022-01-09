package br.com.glabs.equipsys.fornecedor.dao;

import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FornecedorDao extends CrudRepository<FornecedorDB, Long> {

    @Query("select count(e) from FornecedorDB e where e.nome like ?1%")
    long countByNome(String nome);

    @Query("select count(e) from FornecedorDB e where e.cnpj = ?1")
    long countByCnpj(Long cnpj);

    @Query("select count(e) from FornecedorDB e where e.nome like ?1% and e.cnpj = ?2")
    long countByNomeAndCnpj(String nome, Long cnpj);

}
