package br.com.glabs.equipsys.despesa.endpoint;

import br.com.glabs.equipsys.despesa.dao.DespesaDao;
import br.com.glabs.equipsys.despesa.dto.DespesaDTO;
import br.com.glabs.equipsys.despesa.entidade.DespesaDB;
import br.com.glabs.equipsys.despesa.mapper.DespesaMapper;
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
@RequestMapping("/api/despesa")
public class DespesaEndpoint {

    @Autowired
    private DespesaDao dao;

    @Autowired
    private DespesaMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<DespesaDTO> get(@PathVariable Long id) {
        return dao.findById(id).map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<DespesaDTO>> getAll(
            @RequestParam(required = false) String descricao,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                @SortDefault(sort = "id", direction = Sort.Direction.ASC),
                @SortDefault(sort = "descricao", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        final boolean descricaoIsPresent = Optional.ofNullable(descricao).isPresent();
        Page<DespesaDB> response = Page.empty();
        if (descricaoIsPresent) {
            response = dao.findAllByDescricaoContains(descricao, pageable);
        } else {
            response = dao.findAll(pageable);
        }
        return ResponseEntity.ok(response.map(mapper::toDTO));
    }

    @PostMapping
    public ResponseEntity<DespesaDTO> post(@RequestBody DespesaDTO dto) {
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
