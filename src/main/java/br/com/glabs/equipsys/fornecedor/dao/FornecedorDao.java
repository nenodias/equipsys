package br.com.glabs.equipsys.fornecedor.dao;

import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface FornecedorDao extends CrudRepository<FornecedorDB, Long> {

    Page<FornecedorDB> findAllByNomeContainsAndCnpjContains(String nome, String cnpj, Pageable pageable);

    Page<FornecedorDB> findAllByNomeContains(String nome, Pageable pageable);

    Page<FornecedorDB> findAllByCnpjContains(String cnpj, Pageable pageable);

    Page<FornecedorDB> findAll(Pageable pageable);

}
