package br.com.glabs.equipsys.despesa.endpoint;

import br.com.glabs.equipsys.NotFoundException;
import br.com.glabs.equipsys.despesa.dao.ParcelaDao;
import br.com.glabs.equipsys.despesa.dto.ParcelaDTO;
import br.com.glabs.equipsys.despesa.mapper.ParcelaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/parcela")
public class ParcelaEndpoint {

    @Autowired
    private ParcelaDao dao;

    @Autowired
    private ParcelaMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ParcelaDTO> get(@PathVariable Long idDespesa, @PathVariable Long id) {
        return dao.findById(id).map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ParcelaDTO> post(@RequestBody ParcelaDTO dto) {
        return Optional.ofNullable(dto).map(mapper::toModel)
                .map(dao::save)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParcelaDTO> put(@PathVariable Long id, @RequestBody ParcelaDTO dto) {
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
