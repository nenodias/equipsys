package br.com.glabs.equipsys.fornecedor.endpoint;

import br.com.glabs.equipsys.fornecedor.dao.FornecedorDao;
import br.com.glabs.equipsys.fornecedor.dto.FornecedorDTO;
import br.com.glabs.equipsys.fornecedor.entidade.FornecedorDB;
import br.com.glabs.equipsys.fornecedor.mapper.FornecedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<Page<FornecedorDTO>> getAll(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cnpj,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "name", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "cnpj", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        final boolean nomeIsPresent = Optional.ofNullable(nome).isPresent();
        final boolean cnpjIsPresent = Optional.ofNullable(cnpj).isPresent();
        Page<FornecedorDB> response = Page.empty();
        if (nomeIsPresent && cnpjIsPresent) {
            response = dao.findAllByNomeContainsAndCnpjContains(nome, cnpj, pageable);
        } else if (nomeIsPresent) {
            response = dao.findAllByNomeContains(nome, pageable);
        } else if (cnpjIsPresent) {
            response = dao.findAllByCnpjContains(cnpj, pageable);
        } else {
            response = dao.findAll(pageable);
        }
        return ResponseEntity.ok(response.map(mapper::toDTO));
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> post(@RequestBody FornecedorDTO dto) {
        return Optional.ofNullable(dto).map(mapper::toModel)
                .map(dao::save)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable final Long id) {
        final var entity = dao.findById(id);
        return entity.map(i -> {
            dao.delete(i);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }).orElse(ResponseEntity.notFound().build());
    }

}
