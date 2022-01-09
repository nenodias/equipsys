package br.com.glabs.equipsys.fornecedor.endpoint;

import br.com.glabs.equipsys.fornecedor.dao.FornecedorDao;
import br.com.glabs.equipsys.fornecedor.dto.FornecedorDTO;
import br.com.glabs.equipsys.fornecedor.mapper.FornecedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorEndpoint {

    @Autowired
    private FornecedorDao dao;

    @Autowired
    private FornecedorMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> get(@PathVariable Long id) {
        return dao.findById(id).map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countByFilter(@RequestParam(defaultValue = "") String nome, @RequestParam(required = false) String cnpj) {
        final boolean nomeIsPresent = Optional.ofNullable(nome).isPresent();
        final boolean cnpjIsPresent = Optional.ofNullable(cnpj).isPresent();
        Long response = 0L;
        if (nomeIsPresent && cnpjIsPresent) {
            response = dao.countByNomeAndCnpj(nome, cnpj);
        } else if (nomeIsPresent) {
            response = dao.countByNome(nome);
        } else if (cnpjIsPresent) {
            response = dao.countByCnpj(cnpj);
        }
        return Optional.ofNullable(response)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
