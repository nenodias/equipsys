package br.com.glabs.equipsys.conta.endpoint;

import br.com.glabs.equipsys.NotFoundException;
import br.com.glabs.equipsys.conta.dao.ContaDao;
import br.com.glabs.equipsys.conta.dto.ContaDTO;
import br.com.glabs.equipsys.conta.entidade.ContaDB;
import br.com.glabs.equipsys.conta.mapper.ContaMapper;
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

@CrossOrigin
@RestController
@RequestMapping("/api/conta")
public class ContaEndpoint {

    @Autowired
    private ContaDao dao;

    @Autowired
    private ContaMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> get(@PathVariable Long id) {
        return dao.findById(id).map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ContaDTO>> getAll(
            @RequestParam(required = false) String nome,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "nome", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        final boolean nomeIsPresent = Optional.ofNullable(nome).isPresent();
        Page<ContaDB> response = Page.empty();
        if (nomeIsPresent) {
            response = dao.findAllByNomeContains(nome, pageable);
        } else {
            response = dao.findAll(pageable);
        }
        return ResponseEntity.ok(response.map(mapper::toDTO));
    }

    @PostMapping
    public ResponseEntity<ContaDTO> post(@RequestBody ContaDTO dto) {
        return Optional.ofNullable(dto).map(mapper::toModel)
                .map(dao::save)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> put(@PathVariable Long id, @RequestBody ContaDTO dto) {
        Optional.ofNullable(dao.findById(id)).orElseThrow(NotFoundException::new);
        return Optional.ofNullable(dto).map(i -> {
                    i.setId(id);
                    return i;
                }).map(mapper::toModel)
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
