package br.com.glabs.equipsys.obra.endpoint;

import br.com.glabs.equipsys.obra.dao.ObraDao;
import br.com.glabs.equipsys.obra.dto.ObraDTO;
import br.com.glabs.equipsys.obra.entidade.ObraDB;
import br.com.glabs.equipsys.obra.mapper.ObraMapper;
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
@RequestMapping("/api/obra")
public class ObraEndpoint {

    @Autowired
    private ObraDao dao;

    @Autowired
    private ObraMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ObraDTO> get(@PathVariable Long id) {
        return dao.findById(id).map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ObraDTO>> getAll(
            @RequestParam(required = false) String nome,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "name", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        final boolean nomeIsPresent = Optional.ofNullable(nome).isPresent();
        Page<ObraDB> response = Page.empty();
        if (nomeIsPresent) {
            response = dao.findAllByNomeContains(nome, pageable);
        } else {
            response = dao.findAll(pageable);
        }
        return ResponseEntity.ok(response.map(mapper::toDTO));
    }

    @PostMapping
    public ResponseEntity<ObraDTO> post(@RequestBody ObraDTO dto) {
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
