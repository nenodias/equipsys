package br.com.glabs.equipsys.fornecedor.dao;

import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FornecedorDao extends CrudRepository<FornecedorDB, Long> {

    @Query("select count(e) from FornecedorDB e where e.nome like %:nome% ")
    long countByNome(@Param("nome") String nome);

    @Query("select count(e) from FornecedorDB e where e.cnpj like %:cnpj% ")
    long countByCnpj(@Param("cnpj") String cnpj);

    @Query("select count(e) from FornecedorDB e where e.nome like %:nome% and e.cnpj like %:cnpj% ")
    long countByNomeAndCnpj(@Param("nome") String nome, @Param("cnpj") String cnpj);

}
