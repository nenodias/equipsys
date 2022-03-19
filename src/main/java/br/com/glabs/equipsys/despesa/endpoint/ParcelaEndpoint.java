package br.com.glabs.equipsys.despesa.endpoint;

import br.com.glabs.equipsys.despesa.dao.ParcelaDao;
import br.com.glabs.equipsys.despesa.dto.DespesaParcelaDTO;
import br.com.glabs.equipsys.despesa.dto.ParcelaDTO;
import br.com.glabs.equipsys.despesa.mapper.DespesaParcelaMapper;
import br.com.glabs.equipsys.despesa.mapper.ParcelaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable final Long id) {
        final var entity = dao.findById(id);
        return entity.map(i -> {
            dao.delete(i);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }).orElse(ResponseEntity.notFound().build());
    }

}