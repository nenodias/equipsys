package br.com.glabs.equipsys.fornecedor.dao;

import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import org.springframework.data.repository.CrudRepository;

public interface FornecedorDao extends CrudRepository<FornecedorDB, Long> {
}
